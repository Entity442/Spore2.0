package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.Seffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FallEvent {
    @SubscribeEvent
    public  static  void Ifall(LivingDamageEvent event){
        if (event != null && event.getEntity() != null) {
            damage(event.getAmount(), event.getSource(), event.getEntity());
        }
    }

    private static void damage(float amount, DamageSource source, Entity entity) {
        if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(Seffects.SYMBIOSIS.get())) {

            if (source == DamageSource.FALL) {
                entity.causeFallDamage(amount, 0.2f, DamageSource.FALL);
            }
        }
    }
}
