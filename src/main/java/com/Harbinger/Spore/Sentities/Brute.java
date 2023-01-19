package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.TransportInfected;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Brute extends EvolvedInfected {
    public Brute(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new TransportInfected<>(this,Infected.class,16,1.1,entity -> {
            return SConfig.SERVER.ranged.get().contains(entity.getEncodeId());
        }));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 3.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });

        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        super.registerGoals();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.brute_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.brute_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.brute_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 1);

    }

    @Override
    public void tick() {
        if (this.isAlive() && this.getTarget() != null && checkForInfected(this) && !switchy()){performRangedAttack(this);}
        super.tick();
    }
    private boolean switchy() {
        if (this.getTarget() != null){
            double ze = this.distanceToSqr(this.getTarget());
            return (ze < 60.0D);
        }
        return false;
    }

    boolean checkForInfected(Entity entity){
        AABB boundingBox = entity.getBoundingBox().inflate(1.2);
        List<Entity> entities = entity.level.getEntities(entity, boundingBox);

        for (Entity en : entities) {
            if (SConfig.SERVER.can_be_carried.get().contains(en.getEncodeId())){
                return true;
            }
        }
        return false;
    }

    public void performRangedAttack(LivingEntity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        double d0 = entity.getX() + vec3.x - this.getTarget().getX();
        double d1 = entity.getEyeY() - (double)1.1F - this.getY();
        double d2 = entity.getZ() + vec3.z - this.getTarget().getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        AABB boundingBox = entity.getBoundingBox().inflate(1.2);
        List<Entity> entities = entity.level.getEntities(entity, boundingBox);

        for (Entity en : entities) {
            if (en instanceof Infected){
                en.setDeltaMovement(d0 * -0.2D, (d1 + d3) * 0.02D, d2 * -0.2D);
                ((Infected) en).addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING , 200,0));
            }
        }

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
