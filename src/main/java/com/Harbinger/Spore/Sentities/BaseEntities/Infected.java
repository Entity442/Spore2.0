package com.Harbinger.Spore.Sentities.BaseEntities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sparticles;
import com.Harbinger.Spore.Sentities.AI.*;
import com.Harbinger.Spore.Sentities.AI.LocHiv.BufferAI;
import com.Harbinger.Spore.Sentities.AI.LocHiv.FollowOthersGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.LocalTargettingGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.SearchAreaGoal;
import com.Harbinger.Spore.Sentities.Projectile.AcidBall;
import com.Harbinger.Spore.Sentities.Projectile.Vomit;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class Infected extends Monster{
    public static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> KILLS = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> EVOLUTION = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> LINKED = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> PERSISTENT = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.BOOLEAN);
    @Nullable
    BlockPos searchPos;

    public Infected(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
        this.xpReward = 5;
    }

    @Nullable
    public BlockPos getSearchPos() {
        return searchPos;
    }

    public void setSearchPos(@Nullable BlockPos searchPos) {
        this.searchPos = searchPos;
    }

    public void setEvolution(int e){
        entityData.set(EVOLUTION,e);
    }

    public void travel(Vec3 p_32858_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, p_32858_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.6D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_32858_);
        }

    }

    @Override
    public void setTarget(@org.jetbrains.annotations.Nullable LivingEntity entity) {
        super.setTarget(entity);
    }

    public int getMaxAirSupply() {
        return 1200;
    }
    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }

    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(),  600, 0), this);
            }
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new SearchAreaGoal(this, 1.2));
        this.goalSelector.addGoal(3,new LocalTargettingGoal(this));
        this.goalSelector.addGoal(2, new HurtTargetGoal(this ,entity -> {return !SConfig.SERVER.blacklist.get().contains(entity.getEncodeId());}, Infected.class).setAlertOthers(Infected.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, Player.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 5, false, true, (en) -> {
            return SConfig.SERVER.whitelist.get().contains(en.getEncodeId());
        }));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 5, false, true, (en) -> {
            return !(en instanceof Animal || en instanceof AbstractFish || en instanceof Infected || en instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(en.getEncodeId())) && SConfig.SERVER.at_mob.get();
        }));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Animal.class, 5, false, true, (en) -> {
            return !SConfig.SERVER.blacklist.get().contains(en.getEncodeId()) && SConfig.SERVER.at_an.get();
        }));
        this.goalSelector.addGoal(5 , new InfectedPanicGoal(this , 1.5));
        this.goalSelector.addGoal(4 , new BufferAI(this ));
        this.goalSelector.addGoal(6,new FloatDiveGoal(this));
        this.goalSelector.addGoal(7, new SwimToBlockGoal(this , 1.5, 8));
        this.goalSelector.addGoal(7, new InfectedConsumeFromRemains(this));
        this.goalSelector.addGoal(8,new FollowOthersGoal(this, 1.2, Calamity.class ));
        this.goalSelector.addGoal(9,new FollowOthersGoal(this, 1.2, ScentEntity.class ));
        this.goalSelector.addGoal(10,new FollowOthersGoal(this, 0.7 ));
    }
    public void aiStep() {
        super.aiStep();

        if (SConfig.SERVER.weaktocold.get()){
        if (!this.level.isClientSide && this.getRandom().nextInt(0, 10) == 3 && (this.isInPowderSnow || this.isFreazing())) {
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, false), Infected.this);
            this.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, false), Infected.this);
        }}

        if (SConfig.SERVER.should_starve.get()){
            if (!(this instanceof EvolvedInfected) && entityData.get(HUNGER) < SConfig.SERVER.hunger.get() && entityData.get(KILLS) <= 0 && SConfig.SERVER.starve.get()) {
                int i;
                if (this.isInPowderSnow || this.isFreazing()) {
                    i = 2;
                } else {
                    i = 1;
                }
                entityData.set(HUNGER, entityData.get(HUNGER) + i);
            } else if (!(this instanceof EvolvedInfected) && entityData.get(HUNGER) >= SConfig.SERVER.hunger.get() &&
                    !this.hasEffect(Seffects.STARVATION.get()) && this.random.nextInt(0, 7) == 3 && SConfig.SERVER.starve.get()) {
                this.addEffect(new MobEffectInstance(Seffects.STARVATION.get(), 100, 0));
            }
        }

        if (this.horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) && this.isAggressive()) {
            boolean flag = false;
            AABB aabb = this.getBoundingBox().inflate(0.2D);
            for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                BlockState blockstate = this.level.getBlockState(blockpos);
                if ((blockstate.getMaterial() == Material.GLASS || blockstate.getMaterial() == Material.LEAVES) && blockstate.getDestroySpeed(level ,blockpos) >= 0 && blockstate.getDestroySpeed(level ,blockpos) < 2) {
                    flag = this.level.destroyBlock(blockpos, true, this) || flag;
                }
                if (!flag && this.onGround) {
                    this.jumpFromGround();
                }
            }

        }
        if (this.getLastDamageSource() == DamageSource.IN_WALL && this.isOnGround()){
            this.jumpControl.jump();
        }
        if (this.horizontalCollision && this.isInWater()){
            this.jumpInFluid(ForgeMod.WATER_TYPE.get());
        }
    }


    public boolean isStarving(){
        return entityData.get(HUNGER) >= SConfig.SERVER.hunger.get() || this.hasEffect(Seffects.STARVATION.get());
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return this.entityData.get(KILLS) <= 0 && !this.entityData.get(PERSISTENT);
    }

    public boolean isFreazing(){
        int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY());
        int k = Mth.floor(this.getZ());
        Entity entity = this;
        BlockPos blockpos = new BlockPos(i, j, k);
        Biome biome = this.level.getBiome(blockpos).value();
        return (SConfig.SERVER.weaktocold.get() && biome.getBaseTemperature() <= 0.2) && (!entity.isOnFire());
    }

    @Override
    public void awardKillScore(Entity entity, int i, DamageSource damageSource) {
        this.entityData.set(KILLS,entityData.get(KILLS) + 1);
        setHunger(0);
        super.awardKillScore(entity, i, damageSource);
    }
    public void setHunger(Integer count){entityData.set(HUNGER,count);}
    public int getHunger(){return entityData.get(HUNGER);}
    public void setKills(Integer count){
        entityData.set(KILLS,count);
    }
    public  int getKills(){return entityData.get(KILLS);}
    public void setLinked(Boolean count){
        entityData.set(LINKED,count);
    }
    public boolean getLinked(){return entityData.get(LINKED);}
    public int getEvolutionCoolDown(){
        return this.entityData.get(EVOLUTION);
    }
    public void setPersistent(Boolean count){
        entityData.set(PERSISTENT,count);
    }
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("hunger",entityData.get(HUNGER));
        tag.putInt("kills",entityData.get(KILLS));
        tag.putInt("evolution",entityData.get(EVOLUTION));
        tag.putBoolean("linked",entityData.get(LINKED));
        tag.putBoolean("persistent",entityData.get(PERSISTENT));
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(HUNGER, tag.getInt("hunger"));
        entityData.set(KILLS, tag.getInt("kills"));
        entityData.set(EVOLUTION,tag.getInt("evolution"));
        entityData.set(LINKED, tag.getBoolean("linked"));
        entityData.set(PERSISTENT, tag.getBoolean("persistent"));
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HUNGER, 0);
        this.entityData.define(KILLS, 0);
        this.entityData.define(LINKED,false);
        this.entityData.define(PERSISTENT,false);
        this.entityData.define(EVOLUTION,0);
    }



    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.hasEffect(Seffects.STARVATION.get()) && source == DamageSource.GENERIC && this.level instanceof ServerLevel serverLevel){
            double x0 = this.getX() - (random.nextFloat() - 0.1) * 0.1D;
            double y0 = this.getY() + (random.nextFloat() - 0.25) * 0.25D * 5;
            double z0 = this.getZ() + (random.nextFloat() - 0.1) * 0.1D;
            serverLevel.sendParticles(Sparticles.SPORE_PARTICLE.get(), x0, y0, z0, 4,0, 0, 0,1);
        }
        if (source.getDirectEntity() instanceof AcidBall || source.getDirectEntity() instanceof Vomit){
            return  false;
        }
        if (ModList.get().isLoaded("ad_astra") && source == DamageSource.DROWN){
            return false;
        }
        return super.hurt(source, amount);
    }

    public static boolean checkMonsterInfectedRules(EntityType<? extends Infected> p_219014_, ServerLevelAccessor levelAccessor, MobSpawnType p_219016_, BlockPos pos, RandomSource source) {
        if (SConfig.SERVER.spawn.get()){if (levelAccessor.dayTime() < (24000L * SConfig.SERVER.days.get())){return false;}}

        return (levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, source) && checkMobSpawnRules(p_219014_, levelAccessor, p_219016_, pos, source));
    }


    @Override
    public boolean addEffect(MobEffectInstance effectInstance, @org.jetbrains.annotations.Nullable Entity entity) {
        if (entityData.get(HUNGER) >= SConfig.SERVER.hunger.get() && (effectInstance.getEffect() == MobEffects.HEAL || effectInstance.getEffect() == MobEffects.REGENERATION)){
           setHunger(0);
        }
        return super.addEffect(effectInstance, entity);
    }


    @Override
    public void die(DamageSource source) {
        if (this.hasEffect(Seffects.STARVATION.get()) && source == DamageSource.GENERIC){
            AABB aabb = this.getBoundingBox().inflate(1);
            for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                BlockState blockState = level.getBlockState(blockpos);
                BlockState above = level.getBlockState(blockpos.above());
                if (!level.isClientSide() && blockState.isSolidRender(level,blockpos) && !above.isSolidRender(level,blockpos)){
                    if (Math.random() < 0.9){
                        if (Math.random() < 0.5) {
                            level.setBlock(blockpos.above(), Sblocks.GROWTHS_BIG.get().defaultBlockState(), 3);
                        } else {
                            level.setBlock(blockpos.above(), Sblocks.GROWTHS_SMALL.get().defaultBlockState(), 3);
                        }
                    }if (Math.random() < 0.3){
                        level.setBlock(blockpos.above(), Sblocks.REMAINS.get().defaultBlockState(), 3);
                        break;
                    }
                }
            }
        }
        super.die(source);
    }
}
