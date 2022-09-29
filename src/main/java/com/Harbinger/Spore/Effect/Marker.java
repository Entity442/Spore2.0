package com.Harbinger.Spore.Effect;

import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Marker extends MobEffect {
    public Marker() {
        super(MobEffectCategory.HARMFUL, 8412043);
    }
    private int j;

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        j = pLivingEntity.getEffect(Seffects.MARKER.get()).getDuration();
        if (!(pLivingEntity instanceof Infected)){
        AABB boundingBox = pLivingEntity.getBoundingBox().inflate(32);
        List<Entity> entities = pLivingEntity.level.getEntities(pLivingEntity, boundingBox);

        for (Entity entity : entities) {
            if(entity instanceof Infected livingEntity) {
                livingEntity.addEffect( new MobEffectInstance(Seffects.MARKER.get() , j * 2,pAmplifier + 1, false,false));
            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
