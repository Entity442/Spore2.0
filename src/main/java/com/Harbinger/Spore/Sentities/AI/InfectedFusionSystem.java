package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class InfectedFusionSystem extends Goal{
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(4.0D).ignoreLineOfSight();
    protected final Infected infected;
    private final Class<? extends Infected> partnerClass;
    protected final Level level;
    @Nullable
    protected Infected partner;
    private int loveTime;
    private final double speedModifier;
    private final  Class<? extends Infected> child;


    public InfectedFusionSystem(Infected infected, double speed,Class<? extends Infected> infected2 ,Class<? extends Infected> child) {
        this.infected = infected;
        this.level = infected.level;
        this.partnerClass = infected2;
        this.speedModifier = speed;
        this.child = child;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        if (!this.infected.CanFuse()) {
            return false;
        } else {
            this.partner = this.getFreePartner();
            return this.partner != null;
        }
    }

    public boolean canContinueToUse() {
        return this.partner.isAlive() && this.partner.CanFuse();
    }

    public void stop() {
        this.partner = null;
    }

    public void tick() {
        this.infected.getLookControl().setLookAt(this.partner, 10.0F, (float)this.infected.getMaxHeadXRot());
        this.infected.getNavigation().moveTo(this.partner, this.speedModifier);
        ++this.loveTime;
        if (this.loveTime >= this.adjustedTickDelay(60) && this.infected.distanceToSqr(this.partner) < 9.0D) {
            infected.discard();

        }

    }




    @Nullable
    private Infected getFreePartner() {
        List<? extends Infected> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.infected, this.infected.getBoundingBox().inflate(4.0D));
        double d0 = Double.MAX_VALUE;
        Infected animal = null;

        for(Infected animal1 : list) {
            if (this.infected.CanFuse() && this.infected.distanceToSqr(animal1) < d0) {
                animal = animal1;
                d0 = this.infected.distanceToSqr(animal1);
            }
        }

        return animal;
    }
}
