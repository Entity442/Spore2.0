package com.Harbinger.Spore.Sentities.AI.CalamitiesAI;

import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import net.minecraft.world.entity.ai.goal.Goal;

public class SporeBurstSupport extends Goal {
    private final Calamity calamity;

    public SporeBurstSupport(Calamity calamity){
        this.calamity = calamity;
    }

    @Override
    public boolean canUse() {
        return this.calamity.isAlive() && this.calamity.getRandom().nextInt(300) == 0 && this.calamity.getKills() > 6 && calamity.isAggressive();
    }

    @Override
    public void start() {
        this.calamity.setStun(60);
        this.calamity.setKills(this.calamity.getKills()-2);
        super.start();
    }

}
