package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.*;
import com.Harbinger.Spore.Sentities.InfEvoClaw;
import com.Harbinger.Spore.Sentities.Utility.Mound;
import com.Harbinger.Spore.Sentities.Utility.Proto;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Spore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Attributes {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(Sentities.INF_HUMAN.get(), InfectedHuman.createAttributes().build());
        event.put(Sentities.KNIGHT.get(), Knight.createAttributes().build());
        event.put(Sentities.GRIEFER.get(), Griefer.createAttributes().build());
        event.put(Sentities.BRAIOMIL.get(), Braionmil.createAttributes().build());
        event.put(Sentities.INF_VILLAGER.get(), InfectedVillager.createAttributes().build());
        event.put(Sentities.INF_WITCH.get(), InfectedWitch.createAttributes().build());
        event.put(Sentities.LEAPER.get(), Leaper.createAttributes().build());
        event.put(Sentities.SLASHER.get(), Slasher.createAttributes().build());
        event.put(Sentities.SPITTER.get(), Spitter.createAttributes().build());
        event.put(Sentities.INF_PILLAGER.get(), InfectedPillager.createAttributes().build());
        event.put(Sentities.INF_VINDICATOR.get(), InfectedVendicator.createAttributes().build());
        event.put(Sentities.CLAW.get(), InfEvoClaw.createAttributes().build());
        event.put(Sentities.INF_EVOKER.get(), InfectedEvoker.createAttributes().build());
        event.put(Sentities.HOWLER.get(), Howler.createAttributes().build());
        event.put(Sentities.INF_WANDERER.get(), InfectedWanderingTrader.createAttributes().build());
        event.put(Sentities.STALKER.get(), Stalker.createAttributes().build());
        event.put(Sentities.SCENT.get(), ScentEntity.createAttributes().build());
        event.put(Sentities.BRUTE.get(), Brute.createAttributes().build());
        event.put(Sentities.BUSSER.get(), Busser.createAttributes().build());
        event.put(Sentities.INF_DROWNED.get(), InfectedDrowned.createAttributes().build());
        event.put(Sentities.HOST.get(), Host.createAttributes().build());
        event.put(Sentities.INF_PLAYER.get(), InfectedPlayer.createAttributes().build());
        event.put(Sentities.SCAMPER.get(), Scamper.createAttributes().build());
        event.put(Sentities.MOUND.get(), Mound.createAttributes().build());
        event.put(Sentities.PROTO.get(), Proto.createAttributes().build());
    }
}


