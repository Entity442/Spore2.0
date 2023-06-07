package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Features.BiomassTowerFeature;
import com.Harbinger.Spore.Features.BiomassTowerFeatureBig;
import com.Harbinger.Spore.Features.BiomassTowerFeatureGiant;
import com.Harbinger.Spore.Features.MyceliumGroundFoliage;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SporeFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Spore.MODID);
    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
    public static final RegistryObject<Feature<?>> BIOMASS_TOWER = FEATURES.register("biomass_tower", BiomassTowerFeature::feature);
    public static final RegistryObject<Feature<?>> BIOMASS_TOWER_SMALL = FEATURES.register("biomass_tower_big", BiomassTowerFeatureBig::feature);
    public static final RegistryObject<Feature<?>> BIOMASS_TOWER_BIG = FEATURES.register("biomass_tower_giant", BiomassTowerFeatureGiant::feature);
    public static final RegistryObject<Feature<?>> GROUND_FOLIAGE = FEATURES.register("ground_foliage", MyceliumGroundFoliage::feature);



}
