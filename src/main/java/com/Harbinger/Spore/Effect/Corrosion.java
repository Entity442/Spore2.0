package com.Harbinger.Spore.Effect;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;

public class Corrosion extends MobEffect {
    public Corrosion() {
        super(MobEffectCategory.HARMFUL, -13369549);
    }
    public void applyEffectTick(LivingEntity entity, int p_19468_) {
        if (SConfig.SERVER.corrosion.get().contains(entity.getEncodeId())){
            if (this == Seffects.CORROSION.get()) {
                entity.hurt(DamageSource.GENERIC.bypassArmor(), 1.0F);
            }
        }


    }

    public boolean isDurationEffectTick(int duration, int intensity) {
        if (this == Seffects.CORROSION.get()) {
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
