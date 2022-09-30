package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

public class FollowTargetGoal extends Goal {
    protected Mob mob;
    protected Level level;
    private final double speedModifier;

    public FollowTargetGoal(Mob mob, double speedModifier) {
        this.mob = mob;
        this.level = mob.level;
        this.speedModifier = speedModifier;
    }


    @Override
    public boolean canUse() {
        return this.mob.getTarget() != null;
    }

    public void tick() {
        assert this.mob.getTarget() != null;
        this.mob.getLookControl().setLookAt(this.mob.getTarget(), 10.0F, (float) this.mob.getMaxHeadXRot());
        this.mob.getNavigation().moveTo(this.mob.getTarget(), this.speedModifier);
    }
}
