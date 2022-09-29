package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class FollowOthersGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(32.0D);
    protected final Level level;
    protected final PathfinderMob mob;
    private final Class<? extends Infected> partnerClass;
    @Nullable
    protected Infected partner;
    private final double speedModifier;

    public  FollowOthersGoal(Infected mob , double speedModifier){
        this(mob , speedModifier, mob.getClass());
    }

    public  FollowOthersGoal( PathfinderMob mob, double speedModifier, Class<? extends Infected> partnerClass){
    this.level = mob.level;
    this.mob = mob;
    this.speedModifier = speedModifier;
    this.partnerClass = partnerClass;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
}

    @Override
    public boolean canUse() {
    if (mob.getTarget() != null || this.getFreePartner() == null){
        return false;
    } else{
        this.partner = this.getFreePartner();
        return this.partnerClass != null;
     }
    }
    public boolean canContinueToUse() {
        return this.partner.isAlive() || this.getFreePartner() != null;}

    public void stop() {
        this.partner = null;
}

    public void tick() {
        assert this.partner != null;
        this.mob.getLookControl().setLookAt(this.partner, 10.0F, (float)this.mob.getMaxHeadXRot());
        this.mob.getNavigation().moveTo(this.partner, this.speedModifier);
}


    @Nullable
    private Infected getFreePartner() {
        List<? extends Infected> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.mob, this.mob.getBoundingBox().inflate(32.0D));
        double d0 = Double.MAX_VALUE;
        Infected inf = null;

        for(Infected inf1 : list) {
            if ( this.mob.distanceToSqr(inf1) < d0) {
                inf = inf1;
                d0 = this.mob.distanceToSqr(inf1);
            }
        }

        return inf;
    }

}

