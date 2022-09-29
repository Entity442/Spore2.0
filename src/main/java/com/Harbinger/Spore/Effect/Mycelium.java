package com.Harbinger.Spore.Effect;

import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class Mycelium extends MobEffect {
    public Mycelium() {
        super(MobEffectCategory.HARMFUL, 9643043);
    }

    public void applyEffectTick(LivingEntity entity, int p_19468_) {
        if (!((entity.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY,
                new ResourceLocation("spore:immune")))) || entity instanceof Infected)){
        if (this == Seffects.MYCELIUM.get()) {
            entity.hurt(DamageSource.GENERIC.bypassArmor(), 1.0F);
        }
        }

    }


    public boolean isDurationEffectTick(int duration, int intensity) {
        if (this == Seffects.MYCELIUM.get()) {
            int i = 60 >> intensity;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}