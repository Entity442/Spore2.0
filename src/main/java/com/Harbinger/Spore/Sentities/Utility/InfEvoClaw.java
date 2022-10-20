package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.ClawAI;
import com.Harbinger.Spore.Sentities.AI.PullGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.Level;

public class InfEvoClaw extends UtilityEntity{
    private int rise = -40;
    private boolean is_underground;

    private static final EntityDataAccessor<Integer> DATA_RISE = SynchedEntityData.defineId(InfEvoClaw.class, EntityDataSerializers.INT);

    public InfEvoClaw(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new ClawAI(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this ,0 ,false){
            @Override
            public boolean canUse() {
                return !is_underground;}
        });
        this.goalSelector.addGoal(2, new PullGoal(this){
            @Override
            public boolean canUse() {
                return !is_underground;}
        });

        super.registerGoals();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.sla_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.sla_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.FOLLOW_RANGE, 4);

    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_RISE, -40);
    }


    public int setRise(int n) {
        this.entityData.set(DATA_RISE , n);
        return n;
    }

    public boolean isIs_underground(){
        return is_underground;
    }

    public int getRise() {
        return this.entityData.get(DATA_RISE);
    }

    public boolean riseShake(){
        return (-40 < rise && rise < 0);
    }

    public void tick() {
        if (this.isAlive()) {
            if (rise >= -40){is_underground=true;}
            int i = this.getRise();
            if (i > 0 && is_underground && rise <= 0){
                rise = rise + 1;
            }
            if (rise >= 0){
                is_underground = true;
            }
        }}

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
}
