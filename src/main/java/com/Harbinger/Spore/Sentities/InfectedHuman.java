package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.AI.FollowOthersGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class InfectedHuman extends Infected {
    private int ev;

    public InfectedHuman(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.xpReward = 5;

    }

    @Override
    protected void registerGoals() {


        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 4.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });

        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new FollowOthersGoal(this , 1 , EvolvedInfected.class, 32, true));


        super.registerGoals();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_human_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inf_human_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3);

    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!isFreazing() && kills >= 1) {
            this.ev = ev + 1;
        }
        if (ev >= (20 * SConfig.SERVER.evolution_age_human.get()) && kills >= 1) {
            sosu(this.level, this.getX(), this.getY(), this.getZ(), this);
        }
    }


    public void sosu(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (world instanceof ServerLevel _level) {

            if ((Math.random() < 0.5) && (kills >= 2)) {
                {
                    Mob entityToSpawn = new Knight(Sentities.BRAIOMIL.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    entityToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
                            null);
                    world.addFreshEntity(entityToSpawn);
                }
            } else if ((Math.random() < 0.5) && (kills >= 3)) {
                {
                    Mob entityToSpawn = new Knight(Sentities.GRIEFER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    entityToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION, null,
                            null);
                    world.addFreshEntity(entityToSpawn);
                }
            } else {
                Mob entityToSpawn = new Knight(Sentities.KNIGHT.get(), _level);
                entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                entityToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION, null,
                        null);
                world.addFreshEntity(entityToSpawn);
            }

        }
        entity.discard();
    }


    public boolean evolution() {
        int i = SConfig.SERVER.evolution_age_human.get() * 20;
        return ev >= (i / 4) * 3;
    }

    public int stomac() {
        return kills;
    }

    @Override
    public void awardKillScore(Entity entity, int i, DamageSource damageSource) {
        kills = kills + 1;
        super.awardKillScore(entity, i, damageSource);
    }



}
