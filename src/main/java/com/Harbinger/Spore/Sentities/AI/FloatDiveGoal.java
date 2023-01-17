package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FloatDiveGoal extends Goal {
    protected Mob mob;
    public FloatDiveGoal(Mob m){
        mob = m;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return this.mob.isInWater();
    }
    public void tick() {
        if (this.mob.getRandom().nextFloat() < 0.4F && (this.mob.getAirSupply() < this.mob.getMaxAirSupply()/2) || mob.getXRot() < -5 || this.mob.getTarget() == null) {
            this.mob.getJumpControl().jump();
            mob.getNavigation().setCanFloat(true);
        } else {
            mob.getNavigation().setCanFloat(false);
        }
    }
}
