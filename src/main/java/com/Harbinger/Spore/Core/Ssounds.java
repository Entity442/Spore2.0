package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Spore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Ssounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Spore.MODID);
    public static void register(IEventBus eventBus) {SOUNDS.register(eventBus);}

    public static final RegistryObject<SoundEvent> INF_DAMAGE = SOUNDS.register(
            "inf_damage",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_damage")));

    public static final RegistryObject<SoundEvent> INF_GROWL = SOUNDS.register(
            "inf_growl",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_growl")));

    public static final RegistryObject<SoundEvent> HOWLER_GROWL = SOUNDS.register(
            "howler_growl",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "howler_growl")));


    public static final RegistryObject<SoundEvent> INF_VILLAGER_DAMAGE = SOUNDS.register(
            "inf_villager_damage",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_villager_damage")));

    public static final RegistryObject<SoundEvent> INF_VILLAGER_GROWL = SOUNDS.register(
            "inf_villager_growl",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_villager_growl")));

    public static final RegistryObject<SoundEvent> INF_VILLAGER_DEATH = SOUNDS.register(
            "inf_villager_death",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_villager_death")));

    public static final RegistryObject<SoundEvent> INF_EVOKER_DAMAGE = SOUNDS.register(
            "inf_evoker_damage",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_evoker_damage")));

    public static final RegistryObject<SoundEvent> INF_EVOKER_GROWL = SOUNDS.register(
            "inf_evoker_growl",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_evoker_growl")));

    public static final RegistryObject<SoundEvent> INF_EVOKER_DEATH = SOUNDS.register(
            "inf_evoker_death",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "inf_evoker_death")));


    public static final RegistryObject<SoundEvent> BRAIOMIL_ATTACK = SOUNDS.register(
            "braiomil_attack",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "braiomil_attack")));

    public static final RegistryObject<SoundEvent> FUNGAL_BURST = SOUNDS.register(
            "fungal_burst",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "fungal_burst")));

    public static final RegistryObject<SoundEvent> PUFF = SOUNDS.register(
            "puff",
            () -> new SoundEvent(new ResourceLocation(Spore.MODID, "puff")));
    private Ssounds() {
    }
}
