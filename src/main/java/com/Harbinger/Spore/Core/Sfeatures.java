package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.SFeatures.PostHivemindFoliage;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sfeatures {
    public static final DeferredRegister<Feature<?>> SPORE_FEATURE = DeferredRegister.create(ForgeRegistries.FEATURES, Spore.MODID);
    public static void register(IEventBus eventBus) {SPORE_FEATURE.register(eventBus);}
    public static final RegistryObject<Feature<?>> HIVE_FOLIAGE = SPORE_FEATURE.register("hive_foliage", PostHivemindFoliage::feature);


}
