package com.Harbinger.Spore.Sentities.AI.LocHiv;

import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SearchAreaGoal extends Goal {
    public final Infected infected;
    public final double speed;

    public SearchAreaGoal(Infected infected1 , double speed){
        this.infected = infected1;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.setFlags(EnumSet.of(Flag.LOOK));
        this.setFlags(EnumSet.of(Flag.JUMP));
    }


    @Override
    public boolean canUse() {
        if (this.infected.getSearchPos() != null && infected.getTarget() == null){
            return this.infected.getSearchPos().distToCenterSqr(this.infected.position()) > 4.0;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return infected.getTarget() == null;
    }


    @Override
    public void tick() {
        if (this.infected.getSearchPos() != null){
            infected.getNavigation().moveTo(this.infected.getSearchPos().getX(),this.infected.getSearchPos().getY(), this.infected.getSearchPos().getZ(),this.speed);
        }
        if (this.infected.getSearchPos() != null && this.infected.getSearchPos().distToCenterSqr(this.infected.position()) < 20.0){
            infected.setSearchPos(null);
        }
    }

}
