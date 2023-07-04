package com.Harbinger.Spore.Sentities.AI.CalamitiesAI;

import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class SummonScentInCombat extends Goal {
    private final Calamity calamity;
    private final int kills;

    public SummonScentInCombat(Calamity calamity,int kills){
        this.calamity = calamity;
        this.kills = kills;
    }
    @Override
    public boolean canUse() {
        return this.calamity.isAlive() && this.calamity.getKills() >= kills && calamity.getRandom().nextInt(400) == 0 && calamity.isAggressive();
    }

    @Override
    public void start() {
        SummonScent();
        this.calamity.setKills(this.calamity.getKills() - kills);
        this.calamity.setStun(80);
        super.start();
    }

    private void SummonScent(){
        ScentEntity scent = new ScentEntity(Sentities.SCENT.get(),calamity.level);
        scent.setOvercharged(true);
        scent.moveTo(calamity.getX(),calamity.getY(),calamity.getZ());
        calamity.level.addFreshEntity(scent);
    }
}
