package com.Harbinger.Spore.Sentities.BaseEntities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EvolvedInfected extends Infected {
    public EvolvedInfected(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        setPersistenceRequired();
    }
    @Override
    protected boolean canRide(Entity entity) {
        if (entity instanceof Infected || entity instanceof UtilityEntity){
            return super.canRide(entity);
        }
        return false;
    }
}
