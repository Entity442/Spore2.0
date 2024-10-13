package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.ExtremelySusThings.SporeSavedData;
import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.Projectile.ThrownBlockProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.Harbinger.Spore.ExtremelySusThings.Utilities.biomass;

public class InfestedConstruct extends UtilityEntity implements RangedAttackMob {
    public static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(InfestedConstruct.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Float> MACHINE_HEALTH = SynchedEntityData.defineId(InfestedConstruct.class, EntityDataSerializers.FLOAT);
    private int attackAnimationTick;
    public InfestedConstruct(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.specter_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.specter_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.specter_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 2);
    }
    @Override
    public boolean removeWhenFarAway(double value) {
        if (this.level instanceof ServerLevel serverLevel){
            SporeSavedData data = SporeSavedData.getDataLocation(serverLevel);
            return data != null && data.getAmountOfHiveminds() >= SConfig.SERVER.proto_spawn_world_mod.get() && value > 256;
        }
        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        entity.hurtMarked = true;
        entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.8, 0.0));
        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        if (entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(),600,0));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,600,0));
        }
        return super.doHurtTarget(entity);
    }

    public void handleEntityEvent(byte value) {
        if (value == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(value);
        }
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    private boolean canRangeAttack(){
        LivingEntity livingEntity = this.getTarget();
        return livingEntity != null && livingEntity.getY() - 2 > this.getY() && !this.navigation.isInProgress();
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(ACTIVE,true);
        entityData.define(MACHINE_HEALTH,30f);
    }
    public void setActive(boolean value){entityData.set(ACTIVE,value);}
    public boolean isActive(){return entityData.get(ACTIVE);}
    public void setMachineHealth(float value){entityData.set(MACHINE_HEALTH,value);}
    public float getMachineHealth(){return entityData.get(MACHINE_HEALTH);}

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setActive(tag.getBoolean("active"));
        setMachineHealth(tag.getFloat("machine_hp"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("active",isActive());
        tag.putFloat("machine_hp",getMachineHealth());
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        addTargettingGoals();
        this.goalSelector.addGoal(3, new RangedAttackGoal(this,1,60,16){
            @Override
            public boolean canUse() {
                return super.canUse() && canRangeAttack();
            }
        });
        this.goalSelector.addGoal(4, new AOEMeleeAttackGoal(this, 1.2, false,1.5,2,livingEntity -> {return TARGET_SELECTOR.test(livingEntity);}) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 6.0 + entity.getBbWidth() * entity.getBbWidth();}});
        this.goalSelector.addGoal(5,new RandomStrollGoal(this,1));
    }


    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }
    private List<DamageSource> resistentSources(){
        List<DamageSource> resistentSources = new ArrayList<>();
        resistentSources.add(DamageSource.IN_FIRE);
        resistentSources.add(DamageSource.LAVA);
        resistentSources.add(DamageSource.HOT_FLOOR);
        return resistentSources;
    }

    @Override
    public boolean hurt(DamageSource source, float value) {
        value = resistentSources().contains(source) ? value/2:value;
        if (getMachineHealth() > 0f){
            float damage = getDamageAfterArmorAbsorb(source,value);
            setMachineHealth(damage > getMachineHealth() ? 0 : getMachineHealth()-damage);
            hurtTime = 10;
            playHurtSound(source);
        }else{
            return super.hurt(source, value);
        }
        return true;
    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return 0;
    }

    @Override
    public void awardKillScore(Entity p_19953_, int p_19954_, DamageSource p_19955_) {
        super.awardKillScore(p_19953_, p_19954_, p_19955_);
        this.addEffect(new MobEffectInstance(MobEffects.REGENERATION,400,0));
    }
    @Override
    public boolean addEffect(MobEffectInstance effectInstance, @Nullable Entity entity) {
        if (effectInstance.getEffect().isBeneficial()){
            return super.addEffect(effectInstance, entity);
        }
        return false;
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        this.attackAnimationTick = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        BlockState state = getBlock();
        if(!level.isClientSide && state != null) {
            ThrownBlockProjectile thrownBlockProjectile = new ThrownBlockProjectile(level,this,10f,state,TARGET_SELECTOR);
            double dx = livingEntity.getX() - this.getX();
            double dy = livingEntity.getY() + livingEntity.getEyeHeight() - 1;
            double dz = livingEntity.getZ() - this.getZ();
            thrownBlockProjectile.moveTo(this.getX(),this.getY()+1.5,this.getZ());
            thrownBlockProjectile.shoot(dx, dy - thrownBlockProjectile.getY() + Math.hypot(dx, dz) * 0.05F, dz, 1f, 6.0F);
            level.addFreshEntity(thrownBlockProjectile);
        }
    }

    public BlockState getBlock(){
        AABB aabb = this.getBoundingBox().inflate(0.2);
        for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.getDestroySpeed(level, blockpos) < 5 && blockstate.getDestroySpeed(level, blockpos) >= 0 && !blockstate.isAir()) {
                level.destroyBlock(blockpos,false);
                return blockstate;
            }
        }
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (tickCount % 40 == 0 && horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)){
            griefBlocks();
        }
    }

    private void griefBlocks(){
        AABB aabb = this.getBoundingBox().inflate(0.5D).move(0,0.5,0);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockBreakingParameter(blockstate,blockpos)) {
                interactBlock(blockpos,this.level);
            }
        }
    }

    public boolean blockBreakingParameter(BlockState blockstate, BlockPos blockpos) {
        float value = blockstate.getDestroySpeed(this.level,blockpos);
        return this.tickCount % 20 == 0 && value > 0 && value <=getBreaking();
    }
    public int getBreaking(){
        return SConfig.SERVER.hyper_bd.get();
    }


    public boolean interactBlock(BlockPos blockPos, Level level) {
        BlockState state = level.getBlockState(blockPos);
        if (biomass().contains(state)){
            return level.setBlock(blockPos, Sblocks.MEMBRANE_BLOCK.get().defaultBlockState(), 3);
        }
        return level.destroyBlock(blockPos, false, this);
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.INF_GROWL.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return this.getMachineHealth() > 0 ? SoundEvents.IRON_GOLEM_HURT : Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }
}
