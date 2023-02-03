package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class FloatDiveGoalDR extends Goal {
    protected Mob mob;
    public FloatDiveGoalDR(Mob m){
        mob = m;
    }

    @Override
    public boolean canUse() {
        return this.mob.isInWater();
    }
    public void tick() {
        if (this.mob.getRandom().nextFloat() < 0.8F && mob.getXRot() < -5) {
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, 0.01D, 0.0D));
            mob.getNavigation().setCanFloat(true);
        } else {
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            mob.getNavigation().setCanFloat(false);
        }
    }
}
