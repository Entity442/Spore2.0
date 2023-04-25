package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.Braionmil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BraionmilSwellGoal extends Goal {
    public final Braionmil griefer;
    @Nullable
    private LivingEntity target;
    private final double speedModifier;
    public BraionmilSwellGoal(Braionmil griefer1, double speedModifier) {
        this.target = griefer1.getTarget();
        this.griefer = griefer1;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.griefer.getTarget();

        return  livingentity != null && this.griefer.distanceToSqr(livingentity) < 80.0D;
    }

    public void start() {
        this.griefer.getNavigation().stop();
        this.target = this.griefer.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        assert this.griefer.getTarget() != null;
        this.griefer.getLookControl().setLookAt(this.griefer.getTarget(), 10.0F, (float) this.griefer.getMaxHeadXRot());
        this.griefer.getNavigation().moveTo(this.griefer.getTarget(), this.speedModifier);
        if (this.target == null) {
            this.griefer.setSwellDir(-1);
        } else if (this.griefer.distanceToSqr(this.target) > 49.0D) {
            this.griefer.setSwellDir(-1);
        } else if (!this.griefer.getSensing().hasLineOfSight(this.target)) {
            this.griefer.setSwellDir(-1);
        } else {
            this.griefer.setSwellDir(1);
        }
    }
}
