package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import net.minecraft.world.damagesource.DamageSource;

public interface TrueCalamity {
    boolean hurt(CalamityMultipart calamityMultipart, DamageSource source, float value);
    void chemAttack();
}
