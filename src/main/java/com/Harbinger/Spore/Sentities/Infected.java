package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Module.SmobType;
import com.Harbinger.Spore.Sentities.AI.FollowOthersGoal;
import com.Harbinger.Spore.Sentities.AI.InfectedWaterMovementControl;
import com.Harbinger.Spore.Sentities.AI.SwimToBlockGoal;
import com.Harbinger.Spore.Sentities.AI.SwimToTarget;
import com.Harbinger.Spore.Sentities.Projectile.AcidBall;
import com.Harbinger.Spore.Sentities.Projectile.Vomit;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class Infected extends Monster {
    public int kills;
    protected Infected(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
        this.moveControl =  new InfectedWaterMovementControl(this);
        this.maxUpStep = 1.0F;
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

    public MobType getMobType() {
        return SmobType.INFECTED;
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3,(new HurtByTargetGoal(this , Infected.class)).setAlertOthers(Infected.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, Player.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, Villager.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, IronGolem.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, true, (en) -> {
            return (en instanceof Enemy && !(en instanceof Creeper || en instanceof Infected) && SConfig.SERVER.at_mob.get());
        }));
        this.goalSelector.addGoal(8, new SwimToTarget(this , 1.0));
        this.goalSelector.addGoal(7, new SwimToBlockGoal(this , 1.5, 8));
        this.goalSelector.addGoal(9,new FollowOthersGoal(this, 1.2, ScentEntity.class , 128 , false));
        this.goalSelector.addGoal(10,new FollowOthersGoal(this, 0.7 , 32, true));
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
        this.addEffect(new MobEffectInstance(MobEffects.REGENERATION ,1,100));
        super.awardKillScore(entity, i, damageSource);
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof AcidBall || source.getDirectEntity() instanceof Vomit){
            return  false;
        }
        return super.hurt(source, amount);
    }



}
