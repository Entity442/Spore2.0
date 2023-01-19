package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class FollowOthersGoal extends Goal {
    protected boolean shouldLook;
    private static TargetingConditions PARTNER_TARGETING;
    protected final Level level;
    protected final PathfinderMob mob;
    private final Class<? extends PathfinderMob> partnerClass;
    protected final int range;
    @Nullable
    protected PathfinderMob partner;
    private final double speedModifier;

    public  FollowOthersGoal(PathfinderMob mob , double speedModifier , int range , boolean look){
        this(mob , speedModifier, mob.getClass(),range, look);
    }
    public FollowOthersGoal(PathfinderMob mob, double speedModifier, Class<? extends PathfinderMob> partnerClass ,int range , boolean look){
        this(mob,speedModifier,partnerClass , range,look,(Predicate<LivingEntity>)null);
    }

    public  FollowOthersGoal(PathfinderMob mob, double speedModifier, Class<? extends PathfinderMob> partnerClass ,int range , boolean look ,@Nullable Predicate<LivingEntity> en){
    this.shouldLook = look;
    this.level = mob.level;
    this.mob = mob;
    this.range = range;
    this.speedModifier = speedModifier;
    this.partnerClass = partnerClass;
    PARTNER_TARGETING = TargetingConditions.forNonCombat().range(range).selector(en);
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
        if (shouldLook){
        this.mob.getLookControl().setLookAt(this.partner, 10.0F, (float)this.mob.getMaxHeadXRot());
        }
        this.mob.getNavigation().moveTo(this.partner, this.speedModifier);
}


    @Nullable
    private PathfinderMob getFreePartner() {
        List<? extends PathfinderMob> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.mob, this.mob.getBoundingBox().inflate(range));
        double d0 = Double.MAX_VALUE;
        PathfinderMob inf = null;

        for(PathfinderMob inf1 : list) {
            if ( this.mob.distanceToSqr(inf1) < d0) {
                inf = inf1;
                d0 = this.mob.distanceToSqr(inf1);
            }
        }

        return inf;
    }

}

