package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.BreakBlockGoal;
import com.Harbinger.Spore.Sentities.AI.FollowOthersGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class InfectedVillager extends Infected {
    private int ev;

    public InfectedVillager(EntityType<? extends Monster> type, Level level) {
        super(type, level);
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
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.COMPOSTER , this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.SMOKER , this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.BARREL , this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.LOOM, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.BLAST_FURNACE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.BREWING_STAND, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.CAULDRON, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.FLETCHING_TABLE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.CARTOGRAPHY_TABLE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.LECTERN, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.SMITHING_TABLE, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.STONECUTTER, this,1,4));
        this.goalSelector.addGoal(4, new BreakBlockGoal(Blocks.GRINDSTONE, this,1,4));
        this.goalSelector.addGoal(7, new FollowOthersGoal(this , 1 , EvolvedInfected.class, 32, true));



        super.registerGoals();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_vil_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inf_vil_damage.get() * SConfig.SERVER.global_damage.get())
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
                    Mob entityToSpawn = new Spitter(Sentities.SPITTER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    entityToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
                            null);
                    world.addFreshEntity(entityToSpawn);
                }
            } else if ((Math.random() < 0.5) && (kills >= 3)) {
                {
                    Mob entityToSpawn = new Leaper(Sentities.LEAPER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    entityToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION, null,
                            null);
                    world.addFreshEntity(entityToSpawn);
                }
            } else {
                Mob entityToSpawn = new Slasher(Sentities.SLASHER.get(), _level);
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

    @Override
    public void awardKillScore(Entity entity, int i, DamageSource damageSource) {
        kills = kills + 1;
        super.awardKillScore(entity, i, damageSource);
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
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
}
