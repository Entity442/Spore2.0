package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.BreakBlockGoal;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.FollowOthersGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;

public class InfectedVillager extends Infected {
    private int ev;
    public InfectedVillager(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {


        this.goalSelector.addGoal(1, new CustomMeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 3.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });

        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(3, new OpenDoorGoal(this, true) {
            @Override
            public void start() {
                this.mob.swing(InteractionHand.MAIN_HAND);
                super.start();
            }
        });
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
        this.goalSelector.addGoal(7, new FollowOthersGoal(this , 1 , EvolvedInfected.class, 32));



        super.registerGoals();
    }


    protected void customServerAiStep() {
        if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
            ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        }

        super.customServerAiStep();
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
        if (ev >= (20 * SConfig.SERVER.evolution_age_human.get()) && kills >= SConfig.SERVER.min_kills.get()) {
            Evolve(this);
        }
    }


    public void Evolve(LivingEntity entity) {
        if (Math.random() < 0.9) {
            Random rand = new Random();
            List<? extends String> ev = SConfig.SERVER.villager_ev.get();
            for (int i = 0; i < 1; ++i) {
                int randomIndex = rand.nextInt(ev.size());
                ResourceLocation randomElement1 = new ResourceLocation(ev.get(randomIndex));
                EntityType<?> randomElement = ForgeRegistries.ENTITY_TYPES.getValue(randomElement1);
                Entity waveentity = randomElement.create(level);
                waveentity.setPos(entity.getX(), entity.getY() + 0.5D, entity.getZ());
                waveentity.setCustomName(entity.getCustomName());
                level.addFreshEntity(waveentity);
                entity.discard();
            }
        }else {
            Scamper scamper = new Scamper(Sentities.SCAMPER.get(), level);
            scamper.setPos(entity.getX(), entity.getY() + 0.5D, entity.getZ());
            scamper.setCustomName(entity.getCustomName());
            level.addFreshEntity(scamper);
            entity.discard();
        }
    }


    public boolean evolution() {
        int i = SConfig.SERVER.evolution_age_human.get() * 20;
        return ev >= (i / 4) * 3;
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
