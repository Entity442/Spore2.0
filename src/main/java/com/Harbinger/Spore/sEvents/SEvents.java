package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.*;
import com.Harbinger.Spore.Spore;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(Sentities.INFECTED_HUMAN.get(), InfectedHuman.createAttributes().build());
        event.put(Sentities.KNIGHT.get(), Knight.createAttributes().build());
        event.put(Sentities.GRIEFER.get(), Griefer.createAttributes().build());
        event.put(Sentities.BRAIOMIL.get(), Braionmil.createAttributes().build());
        event.put(Sentities.INF_VILLAGER.get(), InfectedVillager.createAttributes().build());
        event.put(Sentities.INF_WITCH.get(), InfectedWitch.createAttributes().build());

    }



}
