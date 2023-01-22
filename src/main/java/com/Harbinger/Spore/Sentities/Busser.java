package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.AI.FlyAroundGoal;
import com.Harbinger.Spore.Sentities.AI.FlyingLookGoal;
import com.Harbinger.Spore.Sentities.AI.TransportInfected;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedArialMovementControl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Busser extends EvolvedInfected implements Carrier{

    public Busser(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        if (this.getTarget() != null || !this.isOnGround()){
        this.moveControl = new InfectedArialMovementControl(this , 20 , true);
        }
    }
    public boolean causeFallDamage(float p_147105_, float p_147106_, DamageSource p_147107_) {
        return false;
    }


    protected PathNavigation createNavigation(Level p_27815_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_27815_) {
            public boolean isStableDestination(BlockPos pos) {
                for (int i = 0; i < 3; ++i){
                if (this.mob.isVehicle()){
                    return this.level.getBlockState(pos.below((int) this.mob.getY() - i)).isAir();
                    }
                }
                return !this.level.getBlockState(pos.below()).isAir();
            }
        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    protected void customServerAiStep() {
        if (this.isVehicle() && this.getFirstPassenger() != null){
            if (this.getFirstPassenger().verticalCollisionBelow){
                this.setDeltaMovement(this.getDeltaMovement().add(0.0,0.01,0.0));
            }
        }else {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0,-0.01,0.0));
        }
        super.customServerAiStep();
    }

    public void positionRider(Entity entity) {
        super.positionRider(entity);
        entity.setPos(this.getX(), this.getY() - 1.2,this.getZ());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new TransportInfected<>(this, Mob.class,32,0.8 ,
                e -> { return SConfig.SERVER.can_be_carried.get().contains(e.getEncodeId()) || SConfig.SERVER.ranged.get().contains(e.getEncodeId());}));

        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 3.0 + entity.getBbWidth() * entity.getBbWidth();}});

        this.goalSelector.addGoal(4, new FlyAroundGoal(this));

        this.goalSelector.addGoal(5 ,new  FlyingLookGoal(this));

        super.registerGoals();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (Math.random() < 0.3 && this.isVehicle()){
            this.ejectPassengers();
        }
        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.brute_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.brute_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.brute_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.FLYING_SPEED, 4);


    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive() && this.isVehicle() && this.getTarget() != null){
            if (this.distanceToSqr(this.getTarget()) < 30D){
                LivingEntity entity = (LivingEntity) this.getFirstPassenger();
                if (entity != null && SConfig.SERVER.can_be_carried.get().contains(entity.getEncodeId())){
                entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING ,200,1));
                entity.stopRiding();}
            }
        }
    }
}