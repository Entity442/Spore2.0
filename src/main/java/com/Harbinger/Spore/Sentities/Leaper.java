package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.LeapGoal;
import com.Harbinger.Spore.Sentities.AI.TransportInfected;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedWallMovementControl;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Leaper extends EvolvedInfected implements Carrier{
    public Leaper(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new InfectedWallMovementControl(this);
        this.navigation = new WallClimberNavigation(this,level);
    }

    @Override
    protected void registerGoals() {


        this.goalSelector.addGoal(0, new LeapAtTargetGoal(this,0.4F));
        this.goalSelector.addGoal(1, new CustomMeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 4.0 + entity.getBbWidth() * entity.getBbWidth();}});

        this.goalSelector.addGoal(2, new LeapGoal(this,0.8F){
            @Override
            public boolean canContinueToUse() {
                return (onGround);}
        });
        this.goalSelector.addGoal(3, new TransportInfected<>(this,Mob.class,32,0.8 ,
                entity -> SConfig.SERVER.ranged.get().contains(entity.getEncodeId()) || SConfig.SERVER.support.get().contains(entity.getEncodeId())
                        && !(entity instanceof  Busser)));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        super.registerGoals();
    }


    protected int calculateFallDamage(float p_149389_, float p_149390_) {
        return super.calculateFallDamage(p_149389_, p_149390_) - 10;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.leap_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.leap_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.leap_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.ATTACK_KNOCKBACK, 3)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }

    @Override
    public boolean causeFallDamage(float f1, float f2, DamageSource source) {
        if (this.isVehicle()) {
            return false;
        }
        return super.causeFallDamage(f1, f2, source);
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
