package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class SwimToTarget extends Goal {
    protected Mob mob;
    protected Level level;
    private final double speedModifier;

    public SwimToTarget(Mob mob, double speedModifier) {
        this.mob = mob;
        this.level = mob.level;
        this.speedModifier = speedModifier * 4;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP));
        mob.getNavigation().setCanFloat(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.mob.isOnGround();
    }

    @Override
    public boolean canUse() {
        return this.mob.getTarget() != null && this.mob.isInWater();
    }

    public void tick() {
        assert this.mob.getTarget() != null;
        this.mob.getLookControl().setLookAt(this.mob.getTarget(), 10.0F, (float) this.mob.getMaxHeadXRot());
        this.mob.getNavigation().moveTo(this.mob.getTarget(), this.speedModifier);
        if (this.mob.getRandom().nextFloat() < 0.4F) {
            this.mob.getJumpControl().jump();
        }
    }
}
