package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
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

public class Slasher extends EvolvedInfected{
    public Slasher(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.leap_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.leap_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.leap_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.ATTACK_KNOCKBACK, 3);

    }

    @Override
    protected void registerGoals() {



        this.goalSelector.addGoal(1, new SlasherAttackGoal());
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        super.registerGoals();
    }


    class SlasherAttackGoal extends MeleeAttackGoal{

        public SlasherAttackGoal() {
            super(Slasher.this, 1, true);
        }

        protected double getAttackReachSqr(LivingEntity entity) {
            float f = Slasher.this.getBbWidth() + 0.4F;
            return (double)(f * 2.0F * f * 2.0F + (entity.getBbWidth() * 2));
        }


    }
}
