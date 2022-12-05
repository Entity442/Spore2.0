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

    private Ssounds() {
    }
}
