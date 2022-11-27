package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class InfectionDropEvent {
    @SubscribeEvent
    public static void onEntityDeath(LivingDropsEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity());
        }
    }
    private static void execute(@Nullable Event event, Entity entity) {
        if ((SConfig.SERVER.inf_human_conv.get().contains(entity.getEncodeId()) || SConfig.SERVER.inf_villager_conv.get().contains(entity.getEncodeId())
        || SConfig.SERVER.inf_pillager_conv.get().contains(entity.getEncodeId()) || SConfig.SERVER.inf_witch_conv.get().contains(entity.getEncodeId())
        || SConfig.SERVER.inf_evoker_conv.get().contains(entity.getEncodeId())) || SConfig.SERVER.inf_vindi_conv.get().contains(entity.getEncodeId())) {
            LivingEntity _livEnt = (LivingEntity) entity;
            if (_livEnt.hasEffect(Seffects.MYCELIUM.get())) {
                if (event != null && event.isCancelable()) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
