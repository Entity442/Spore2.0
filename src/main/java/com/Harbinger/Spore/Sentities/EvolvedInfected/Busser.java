package com.Harbinger.Spore.Sentities.EvolvedInfected;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.*;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.ScatterShotRangedGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.EvolvedInfected;
import com.Harbinger.Spore.Sentities.Carrier;
import com.Harbinger.Spore.Sentities.FlyingInfected;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedArialMovementControl;
import com.Harbinger.Spore.Sentities.Projectile.StingerProjectile;
import com.Harbinger.Spore.Sentities.Variants.BusserVariants;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class Busser extends EvolvedInfected implements Carrier, FlyingInfected, RangedAttackMob {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Busser.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(Busser.class, EntityDataSerializers.INT);
    public Busser(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new InfectedArialMovementControl(this , 20,true);
    }
    private int flytimeV;
    private int swell;
    public boolean causeFallDamage(float p_147105_, float p_147106_, DamageSource p_147107_) {
        return false;
    }


    public void positionRider(Entity entity) {
        super.positionRider(entity);
        Vec3 vec3 = (new Vec3(0.4D, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
        entity.setPos(this.getX() + vec3.x, this.getY() - 1.2,this.getZ()+ vec3.z);
    }

    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.inf_bus_loot.get();
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new BusserSwellGoal(this));
        this.goalSelector.addGoal(3, new PhayerGrabAndDropTargets(this));
        this.goalSelector.addGoal(3,new ScatterShotRangedGoal(this,1.2,40,20,1,3){
            @Override
            public boolean canUse() {
                return super.canUse() && Busser.this.getTypeVariant() == 3;
            }
        });
        this.goalSelector.addGoal(3, new PullGoal(this, 32, 16) {
            @Override
            public boolean canUse() {
                return super.canUse() && Busser.this.getTypeVariant() == 0  && !(Busser.this.isOnGround() || Busser.this.isVehicle());
            }
        });
        this.goalSelector.addGoal(4, new CustomMeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 5.0 + entity.getBbWidth() * entity.getBbWidth();
            }

            @Override
            public boolean canUse() {
                return super.canUse() && !(Busser.this.getTypeVariant() == 3);
            }
        });
        this.goalSelector.addGoal(5, new BusserFlyAndDrop(this, 6){
            @Override
            public boolean canUse() {
                return Busser.this.getTypeVariant() == 0 && super.canUse();
            }
        });
        this.goalSelector.addGoal(6, new TransportInfected<>(this, Mob.class, 0.8 ,
                e -> { return SConfig.SERVER.can_be_carried.get().contains(e.getEncodeId()) || SConfig.SERVER.ranged.get().contains(e.getEncodeId());}){
            @Override
            public boolean canUse() {
                return Busser.this.getTypeVariant() == 0 && super.canUse();
            }
        });
        this.goalSelector.addGoal(7,new RandomStrollGoal(this ,1.0));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.bus_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.bus_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.bus_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 128)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.FLYING_SPEED, 0.4);

    }
    @Override
    public void travel(Vec3 vec) {
        if (this.isEffectiveAi() && !this.isOnGround()) {
            this.moveRelative(0.1F, vec);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.85D));
        } else {
            super.travel(vec);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive()) {

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= 20) {
                this.swell = 20;
                this.explodeBusser();
            }
        }
    }

    private void explodeBusser() {
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2f, explosion$blockinteraction);
            discard();
            for(int i = 0;i<3;i++){
                int x = this.random.nextInt(-2,2);
                int z = this.random.nextInt(-2,2);
                AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX() + x, this.getY(), this.getZ() + z);
                areaeffectcloud.setRadius(2.5F);
                areaeffectcloud.setRadiusOnUse(-0.5F);
                areaeffectcloud.setWaitTime(10);
                areaeffectcloud.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(), 200, 2));
                areaeffectcloud.addEffect(new MobEffectInstance(Seffects.MARKER.get(), 600, 0));
                areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
                areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float) areaeffectcloud.getDuration());
                this.level.addFreshEntity(areaeffectcloud);
            }

        }
    }


    @Override
    protected PathNavigation createNavigation(Level level) {
        if (this.isOnGround() || this.getTarget() != null){
            GroundPathNavigation navigation = new GroundPathNavigation(this,level);
            navigation.canPassDoors();
            return navigation;
        }else {
            FlyingPathNavigation navigation = new FlyingPathNavigation(this,level);
            navigation.canPassDoors();
            return navigation;
        }
    }
    @Override
    protected void customServerAiStep() {
        if (this.getTypeVariant() == 1){
            AttributeInstance armor = this.getAttribute(Attributes.ARMOR);
            AttributeInstance health = this.getAttribute(Attributes.MAX_HEALTH);
            if (armor != null && health != null){
                armor.setBaseValue(SConfig.SERVER.bus_armor.get() * SConfig.SERVER.global_armor.get() * 2);
                health.setBaseValue(SConfig.SERVER.bus_hp.get() * SConfig.SERVER.global_health.get() * 2);
            }
            if (this.isVehicle()){
                this.setDeltaMovement(this.getDeltaMovement().add(0,0.025,0));
                if (this.flytimeV < 200){
                    this.flytimeV++;
                }else{
                    this.flytimeV = 0;
                    this.ejectPassengers();
                }
            }
        }
        if (!this.getMoveControl().hasWanted() && this.getTarget() == null){this.setDeltaMovement(this.getDeltaMovement().add(0,-0.005,0));}
        super.customServerAiStep();
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(DATA_SWELL_DIR, -1);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    public void setSwellDir(int p_32284_) {
        this.entityData.set(DATA_SWELL_DIR, p_32284_);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
        BusserVariants variant = Util.getRandom(BusserVariants.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    public BusserVariants getVariant() {
        return BusserVariants.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(BusserVariants variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.INF_VILLAGER_GROWL.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_VILLAGER_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_VILLAGER_DEATH.get();
    }


    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float p_33318_) {
        StingerProjectile stinger = new StingerProjectile(this.level, this, (float) (SConfig.SERVER.bus_damage.get() *1f));
        stinger.moveTo(this.getX(),this.getY(),this.getZ());
        double dx = entity.getX() - this.getX();
        double dy = entity.getY() + entity.getEyeHeight() - 2;
        double dz = entity.getZ() - this.getZ();
        stinger.shoot(dx, dy - stinger.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
        this.level.addFreshEntity(stinger);
        this.level.addFreshEntity(stinger);
        this.setDeltaMovement(this.getDeltaMovement().add(0,0.3,0));
    }
}
