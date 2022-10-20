package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.Utility.InfEvoClaw;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class ClawAI extends Goal {
    private final InfEvoClaw claw;
    @Nullable
    private LivingEntity target;

    public ClawAI(InfEvoClaw claw1) {
        this.claw = claw1;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }
    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.claw.getTarget();
        return this.claw.getRise() > 0 || livingentity != null && this.claw.distanceToSqr(livingentity) < 9.0D;
    }
    public void start() {
        this.target = this.claw.getTarget();
    }
    public boolean requiresUpdateEveryTick() {
        return true;
    }


    public void tick() {
        if (target != null){
         if (this.claw.distanceToSqr(this.target) > 49.0D) {
            this.claw.setRise(1);
        }}  else {
            this.claw.setRise(-1);
        }
    }
}
