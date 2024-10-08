package com.Harbinger.Spore.Effect;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.ExtremelySusThings.CoolDamageSources;
import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Mycelium extends MobEffect {
    public Mycelium() {
        super(MobEffectCategory.HARMFUL, 9643043);
    }

    public void applyEffectTick(LivingEntity entity, int intense) {
        if (!(SConfig.SERVER.mycelium.get().contains(entity.getEncodeId()) || entity instanceof Infected || entity instanceof UtilityEntity)){
        if (this == Seffects.MYCELIUM.get()) {
            if (!entity.level.isClientSide && entity instanceof Player player && player.getFoodData().getFoodLevel() > 0 && intense < 1){
                player.causeFoodExhaustion(1.0F);
            }else {
                entity.hurt(CoolDamageSources.MYCELIUM_OVERTAKE, 1.0F);
            }
        }
        }

    }


    public boolean isDurationEffectTick(int duration, int intensity) {
        if (this == Seffects.MYCELIUM.get()) {
            int i = 80 >> intensity;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}