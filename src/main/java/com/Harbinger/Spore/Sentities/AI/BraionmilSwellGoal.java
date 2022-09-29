package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.Braionmil;
import com.Harbinger.Spore.Sentities.Griefer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BraionmilSwellGoal extends Goal {
    private final Braionmil griefer;
    @Nullable
    private LivingEntity target;

    public BraionmilSwellGoal(Braionmil griefer1) {
        this.griefer = griefer1;
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
        if (this.target == null) {
            this.griefer.setSwellDir(-1);
        } else if (this.griefer.distanceToSqr(this.target) > 100.0D) {
            this.griefer.setSwellDir(-1);
        } else if (!this.griefer.getSensing().hasLineOfSight(this.target)) {
            this.griefer.setSwellDir(-1);
        } else {
            this.griefer.setSwellDir(1);
        }
    }
}
