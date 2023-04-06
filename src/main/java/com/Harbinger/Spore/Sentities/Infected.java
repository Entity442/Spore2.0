package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.AI.HurtTargetGoal;
import com.Harbinger.Spore.Sentities.AI.InfectedPanicGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.BufferAI;
import com.Harbinger.Spore.Sentities.AI.LocHiv.FollowOthersGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.LocalTargettingGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.SearchAreaGoal;
import com.Harbinger.Spore.Sentities.AI.SwimToBlockGoal;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedMovementControl;
import com.Harbinger.Spore.Sentities.Projectile.AcidBall;
import com.Harbinger.Spore.Sentities.Projectile.Vomit;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import com.Harbinger.Spore.Sentities.Utility.UtilityEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class Infected extends Monster{
    public static final EntityDataAccessor<Integer> KILLS = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> LINKED = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.BOOLEAN);
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
        this.moveControl =  new InfectedMovementControl(this);
        this.xpReward = 5;
    }

    @Nullable
    public BlockPos getSearchPos() {
        return searchPos;
    }

    public void setSearchPos(@Nullable BlockPos searchPos) {
        this.searchPos = searchPos;
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

    public boolean doHurtTarget(Entity p_32257_) {
        if (super.doHurtTarget(p_32257_)) {
            if (p_32257_ instanceof LivingEntity) {

                    ((LivingEntity)p_32257_).addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(),  600, 0), this);

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
        this.goalSelector.addGoal(3,new LocalTargettingGoal(this));
        this.goalSelector.addGoal(3, new HurtTargetGoal(this ,entity -> {return !SConfig.SERVER.blacklist.get().contains(entity.getEncodeId());}, Infected.class).setAlertOthers(Infected.class));
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

        this.goalSelector.addGoal(4, new SearchAreaGoal(this, 1.2));
        this.goalSelector.addGoal(5 , new InfectedPanicGoal(this , 1.5));
        this.goalSelector.addGoal(4 , new BufferAI(this ));
        this.goalSelector.addGoal(6,new FloatDiveGoal(this));
        this.goalSelector.addGoal(7, new SwimToBlockGoal(this , 1.5, 8));
        this.goalSelector.addGoal(9,new FollowOthersGoal(this, 1.2, ScentEntity.class , 128));
        this.goalSelector.addGoal(10,new FollowOthersGoal(this, 0.7 ,32 ));
    }
    public void aiStep() {
        super.aiStep();

        if (!this.level.isClientSide) {
            int i = Mth.floor(this.getX());
            int j = Mth.floor(this.getY());
            int k = Mth.floor(this.getZ());
            Entity entity = this;
            BlockPos blockpos = new BlockPos(i, j, k);
            Biome biome = this.level.getBiome(blockpos).value();
            if ((biome.getBaseTemperature() <= 0.2) && (!entity.isOnFire())) {
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 1, false, false), Infected.this);
            }
        }

        if (this.horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) && this.isAggressive()) {
            boolean flag = false;
            AABB aabb = this.getBoundingBox().inflate(0.2D);
            for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                BlockState blockstate = this.level.getBlockState(blockpos);
                if ((blockstate.getMaterial() == Material.GLASS && blockstate.getDestroySpeed(level ,blockpos) < 2) ||
                        (blockstate.getMaterial() == Material.LEAVES && blockstate.getDestroySpeed(level ,blockpos) < 2)) {
                    flag = this.level.destroyBlock(blockpos, true, this) || flag;
                }
                if (!flag && this.onGround) {
                    this.jumpFromGround();
                }
            }

        }
        if (this.getLastDamageSource() == DamageSource.IN_WALL && this.isOnGround()){
            this.jumpFromGround();
        }
    }


    public boolean isFreazing(){
        int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY());
        int k = Mth.floor(this.getZ());
        Entity entity = this;
        BlockPos blockpos = new BlockPos(i, j, k);
        Biome biome = this.level.getBiome(blockpos).value();
        return (biome.getBaseTemperature() <= 0.2) && (!entity.isOnFire());
    }

    @Override
    public void awardKillScore(Entity entity, int i, DamageSource damageSource) {
        this.entityData.set(KILLS,entityData.get(KILLS) + 1);
        super.awardKillScore(entity, i, damageSource);
    }
    public void setKills(Integer count){
        entityData.set(KILLS,count);
    }
    public  int getKills(){return entityData.get(KILLS);}

    public void setLinked(Boolean count){
        entityData.set(LINKED,count);
    }
    public boolean getLinked(){return entityData.get(LINKED);}

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("kills",entityData.get(KILLS));
        tag.putBoolean("linked",entityData.get(LINKED));
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(KILLS, tag.getInt("kills"));
        entityData.set(LINKED, tag.getBoolean("linked"));
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(KILLS, 0);
        this.entityData.define(LINKED,false);
    }



    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof AcidBall || source.getDirectEntity() instanceof Vomit){
            return  false;
        }
        return super.hurt(source, amount);
    }

    public static boolean checkMonsterInfectedRules(EntityType<? extends Infected> p_219014_, ServerLevelAccessor levelAccessor, MobSpawnType p_219016_, BlockPos pos, RandomSource source) {
        if (SConfig.DATAGEN.spawn.get()){if (levelAccessor.dayTime() < (24000L * SConfig.DATAGEN.days.get())){return false;}}

        return (levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, source) && checkMobSpawnRules(p_219014_, levelAccessor, p_219016_, pos, source));
    }


}
