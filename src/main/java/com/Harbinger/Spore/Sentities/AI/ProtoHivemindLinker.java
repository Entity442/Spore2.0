package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.Sentities.Utility.Proto;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ProtoHivemindLinker extends Goal {
    public Proto proto;
    public ProtoHivemindLinker(Proto proto){
        this.proto = proto;
    }
    @Override
    public boolean canUse() {
        return proto.isAlive() && checkForInfected();
    }



    boolean checkForInfected(){
        AABB searchbox = AABB.ofSize(new Vec3(proto.getX(), proto.getY(), proto.getZ()), 300, 200, 300);
        List<Entity> entities = this.proto.level.getEntities(this.proto, searchbox , EntitySelector.NO_CREATIVE_OR_SPECTATOR);
        for (Entity en : entities) {
            if (en instanceof Infected){
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        AABB searchbox = AABB.ofSize(new Vec3(proto.getX(), proto.getY(), proto.getZ()), 300, 200, 300);
        List<Entity> entities = this.proto.level.getEntities(this.proto, searchbox , EntitySelector.NO_CREATIVE_OR_SPECTATOR);
        for (Entity en : entities) {
            if (en instanceof Infected infected){
                infected.setLinked(true);
            }
        }
    }
}
