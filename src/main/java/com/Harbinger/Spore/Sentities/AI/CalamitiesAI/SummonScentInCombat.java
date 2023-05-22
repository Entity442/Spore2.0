package com.Harbinger.Spore.Sentities.AI.CalamitiesAI;

import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class SummonScentInCombat extends Goal {
    private final Calamity calamity;

    public SummonScentInCombat(Calamity calamity){
        this.calamity = calamity;
    }
    @Override
    public boolean canUse() {
        return this.calamity.isAlive() && this.calamity.getKills() >= 10 && calamity.getRandom().nextInt(800) == 0 && calamity.isAggressive();
    }

    @Override
    public void start() {
        SummonScent();
        this.calamity.setKills(this.calamity.getKills() - 5);
        this.calamity.setStun(80);
        super.start();
    }

    private void SummonScent(){
        ScentEntity scent = new ScentEntity(Sentities.SCENT.get(),calamity.level);
        scent.moveTo(calamity.getX(),calamity.getY(),calamity.getZ());
        calamity.level.addFreshEntity(scent);
    }
}
