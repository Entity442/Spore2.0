package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.*;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedMovementControl;
import com.Harbinger.Spore.Sentities.Utility.UtilityEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;

public class InfectedDrowned extends UtilityEntity implements Enemy {
    public int kills;
    protected final WaterBoundPathNavigation waterNavigation;
    protected final GroundPathNavigation groundNavigation;
    public InfectedDrowned(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new InfectedMovementControl(this);
        this.waterNavigation = new WaterBoundPathNavigation(this, level);
        this.groundNavigation = new GroundPathNavigation(this, level);
    }

    public void travel(Vec3 p_32858_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, p_32858_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));

        } else {
            super.travel(p_32858_);
        }
    }

    @Override
    public float getStepHeight() {
        return 1.0F;
    }

    @Override
    protected void customServerAiStep() {
        if (!this.isInWater()){
            AttributeInstance speed = this.getAttribute(Attributes.MOVEMENT_SPEED);
            assert speed != null;
            speed.setBaseValue(0.15);
        }
    }

    public void updateSwimming() {
        if (!this.level.isClientSide) {
            if (this.getTarget() != null){
                if (this.distanceToSqr(this.getTarget()) < 8.0D && !this.getTarget().isEyeInFluidType(ForgeMod.WATER_TYPE.get())){
                    this.navigation = this.groundNavigation;
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.015D, 0.0D));
                }
            }
            if (this.isEffectiveAi() && this.isInWater() && isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.groundNavigation;
                this.setSwimming(false);
            }
        }

    }

    public int getMaxAirSupply() {
        return 600;
    }
    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }


    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 3.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });

        this.goalSelector.addGoal(3, new HurtTargetGoal(this , entity -> {return !SConfig.SERVER.blacklist.get().contains(entity.getEncodeId());}, Infected.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, Player.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, AbstractGolem.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, AbstractVillager.class,  true));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, true, (en) -> {
            return !(en instanceof Animal || en instanceof AbstractFish || en instanceof Infected || en instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(en.getEncodeId())) && SConfig.SERVER.at_mob.get();
        }));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Animal.class, 5, false, true, (en) -> {
            return !SConfig.SERVER.blacklist.get().contains(en.getEncodeId()) && SConfig.SERVER.at_an.get();
        }));

        this.goalSelector.addGoal(4 , new ReturnToWater(this, 1.2));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this ,0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5,new FloatDiveGoalDR(this));
        this.goalSelector.addGoal(6,new MoveTowardsRestrictionGoal(this , 1.0));
        this.goalSelector.addGoal(10,new FollowOthersGoal(this, 0.7 , 32));

        this.goalSelector.addGoal(1, new CustomMeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 3.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });
        super.registerGoals();
    }


    protected SoundEvent getAmbientSound() {
        return Ssounds.INF_GROWL.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.DROWNED_STEP;
    }

    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_dr_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inf_dr_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.inf_dr_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3);

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
    public void awardKillScore(Entity entity, int i, DamageSource damageSource) {
        if (this.getHealth() < this.getMaxHealth()){ this.heal(this.getMaxHealth()/5);}
        kills = kills + 1;
        super.awardKillScore(entity, i, damageSource);
    }

    public static boolean checkUnderwaterInfectedRules(EntityType<InfectedDrowned> p_218956_, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource source) {
        if (!levelAccessor.getFluidState(pos.below()).is(FluidTags.WATER)) {
            return false;
        } else {
            Holder<Biome> holder = levelAccessor.getBiome(pos);
            boolean flag = levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, source) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.getFluidState(pos).is(FluidTags.WATER));
            if (holder.is(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS)) {
                return source.nextInt(15) == 0 && flag;
            } else {
                return source.nextInt(40) == 0 && isDeepEnoughToSpawn(levelAccessor, pos) && flag;
            }
        }
    }
    private static boolean isDeepEnoughToSpawn(LevelAccessor levelAccessor, BlockPos pos) {
        return pos.getY() < levelAccessor.getSeaLevel() - 5;
    }
}
