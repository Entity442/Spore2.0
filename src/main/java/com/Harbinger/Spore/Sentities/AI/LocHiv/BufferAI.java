package com.Harbinger.Spore.Sentities.AI.LocHiv;

import com.Harbinger.Spore.Sentities.EvolvedInfected;
import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.Sentities.InfectedEvoker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;

public class BufferAI extends Goal {
    public Infected infected;
    public BufferAI(Infected infected1){
        this.infected = infected1;
    }
    @Override
    public boolean canUse() {
        return infected.isAlive() && infected.getKills() > 1;
    }

    @Override
    public void tick() {
        super.tick();
        if (infected.getHealth() < infected.getMaxHealth() && infected.getKills() > 1){
            if (!infected.hasEffect(MobEffects.REGENERATION)){
                infected.addEffect(new MobEffectInstance(MobEffects.REGENERATION,200,0));
                infected.setKills(infected.getKills() - 1);
            }
        }
        if (infected.getLastDamageSource() == DamageSource.DROWN && infected.getKills() > 1){
            if (!infected.hasEffect(MobEffects.WATER_BREATHING)){
                infected.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,100,0));
                infected.setKills(infected.getKills() - 1);
            }
        }
        if (infected instanceof EvolvedInfected evolved && evolved.getTarget() != null && Math.random() < 0.01){

            if (!evolved.hasEffect(MobEffects.MOVEMENT_SPEED) && evolved.getKills() >= 2 && evolved.isAggressive() && evolved.distanceToSqr(evolved.getTarget()) > 300){
                evolved.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,200,0));
                evolved.setKills(evolved.getKills() - 2);
            }
            if (!evolved.hasEffect(MobEffects.DAMAGE_BOOST) && evolved.getKills() >= 2 && evolved.isAggressive() && evolved.distanceToSqr(evolved.getTarget()) < 60){
                evolved.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,100,0));
                evolved.setKills(evolved.getKills() - 2);
            }
        }
        if (infected instanceof InfectedEvoker evoker && !evoker.hasArm() && evoker.getKills() >= 5 && Math.random() < 0.1){
            evoker.setArm(true);
            evoker.setKills(evoker.getKills() -5);
        }
    }
}
