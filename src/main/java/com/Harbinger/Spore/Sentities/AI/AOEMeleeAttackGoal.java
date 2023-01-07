package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.Sentities.Utility.UtilityEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Objects;

public class AOEMeleeAttackGoal extends MeleeAttackGoal {
    public double box;
    public float ranged;
    public AOEMeleeAttackGoal(PathfinderMob mob, double speed, boolean p_25554_ , double hitbox ,float range) {
        super(mob, speed, p_25554_);
        box = hitbox;
        ranged = range;
    }
    protected double getAttackReachSqr(LivingEntity entity) {
        return mob.getBbWidth() + ranged;
    }

    protected void checkAndPerformAttack(LivingEntity entity, double d) {
        super.checkAndPerformAttack(entity, d);
        AABB hitbox = entity.getBoundingBox().inflate(box);
        List<Entity> targets = entity.level.getEntities(entity , hitbox);
        for (Entity en : targets) {
            if (en instanceof LivingEntity && !(en.is(mob) || SConfig.SERVER.blacklist.get().contains(en.getEncodeId()) || en instanceof Infected || en instanceof UtilityEntity)){
                 en.hurt(DamageSource.mobAttack(mob) , (float) Objects.requireNonNull(mob.getAttribute(Attributes.ATTACK_DAMAGE)).getBaseValue());
                 ((LivingEntity) en).knockback(Objects.requireNonNull(mob.getAttribute(Attributes.ATTACK_KNOCKBACK)).getBaseValue() ,Mth.sin(mob.getYRot() * ((float) Math.PI / 180F)), (-Mth.cos(mob.getYRot() * ((float) Math.PI / 180F))));
            }
        }
    }
}
