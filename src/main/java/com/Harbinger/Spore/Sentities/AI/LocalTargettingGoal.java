package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class LocalTargettingGoal extends Goal {
    private final Mob mob;
    public LocalTargettingGoal(Mob mob){
        this.mob = mob;
    }
    @Override
    public boolean canUse() {
        return mob.getTarget() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return mob.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();
        this.Targeting(mob);
    }

    public void Targeting(Entity entity){
        AABB boundingBox = entity.getBoundingBox().inflate(16);
        List<Entity> entities = entity.level.getEntities(entity, boundingBox , EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        for (Entity entity1 : entities) {
            if(entity1 instanceof Infected livingEntity) {
                if (livingEntity.getTarget() == null && this.mob.getTarget() != null && this.mob.getTarget().isAlive()){
                    livingEntity.setTarget(mob.getTarget());
                }
            }
        }
    }
}
