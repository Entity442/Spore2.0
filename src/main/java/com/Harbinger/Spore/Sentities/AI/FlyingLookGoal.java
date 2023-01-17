package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FlyingLookGoal extends Goal {
    private final Mob mob;

    public FlyingLookGoal(Mob p_32762_) {
        this.mob = p_32762_;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean canUse() {
        return true;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.mob.getTarget() == null) {
            Vec3 vec3 = this.mob.getDeltaMovement();
            this.mob.setYRot(-((float) Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
            this.mob.yBodyRot = this.mob.getYRot();
        } else {
            LivingEntity livingentity = this.mob.getTarget();
            double d0 = 64.0D;
            if (livingentity.distanceToSqr(this.mob) < 4096.0D) {
                double d1 = livingentity.getX() - this.mob.getX();
                double d2 = livingentity.getZ() - this.mob.getZ();
                this.mob.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                this.mob.yBodyRot = this.mob.getYRot();
            }
        }

    }
}