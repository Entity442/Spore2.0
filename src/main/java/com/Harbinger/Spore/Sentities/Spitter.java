package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.Projectile.AcidBall;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;

public class Spitter extends EvolvedInfected implements RangedAttackMob {
    public Spitter(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {



        this.goalSelector.addGoal(1, new RangedAttackGoal(this,1.1, 20 , 16));

        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        super.registerGoals();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.spit_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ARMOR, SConfig.SERVER.spit_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 1);

    }




    @Override
    public void performRangedAttack(LivingEntity entity, float f) {

        if (this.getTarget() != null){
        AcidBall.shoot(this , this.getTarget());
        this.playSound(SoundEvents.SLIME_JUMP ,1,0.5f);
        }

    }
}
