package com.Harbinger.Spore.Sentities.BaseEntities;

import com.Harbinger.Spore.Sentities.EvolvedInfected.Scamper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EvolvedInfected extends Infected {
    public EvolvedInfected(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
    @Override
    protected boolean canRide(Entity entity) {
        if (entity instanceof Infected || entity instanceof UtilityEntity){
            return super.canRide(entity);
        }
        return false;
    }

    @Override
    public boolean canStarve() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return this.getLinked() && !(this instanceof Scamper);
    }
}
