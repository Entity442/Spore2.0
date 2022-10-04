package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Sentities.*;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import com.Harbinger.Spore.Spore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sentities {
    public static DeferredRegister<EntityType<?>> SPORE_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            Spore.MODID);
    public static void register(IEventBus eventBus) {
        SPORE_ENTITIES.register(eventBus);
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return SPORE_ENTITIES.register(registryname, () -> entityTypeBuilder.build(registryname));
    }


    public static final RegistryObject<EntityType<InfectedHuman>> INFECTED_HUMAN = SPORE_ENTITIES.register("infected_human",
            () -> EntityType.Builder.of(InfectedHuman::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "infected_human").toString()));
    public static final RegistryObject<EntityType<Knight>> KNIGHT = SPORE_ENTITIES.register("knight",
            () -> EntityType.Builder.of(Knight::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "knight").toString()));
    public static final RegistryObject<EntityType<Griefer>> GRIEFER = SPORE_ENTITIES.register("griefer",
            () -> EntityType.Builder.of(Griefer::new, MobCategory.MONSTER).sized(0.8f, 2.1f)
                    .build(new ResourceLocation(Spore.MODID, "griefer").toString()));
    public static final RegistryObject<EntityType<Braionmil>> BRAIOMIL = SPORE_ENTITIES.register("braiomil",
            () -> EntityType.Builder.of(Braionmil::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "braiomil").toString()));
    public static final RegistryObject<EntityType<InfectedVillager>> INF_VILLAGER = SPORE_ENTITIES.register("inf_villager",
            () -> EntityType.Builder.of(InfectedVillager::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "inf_villager").toString()));
    public static final RegistryObject<EntityType<InfectedWitch>> INF_WITCH = SPORE_ENTITIES.register("inf_witch",
            () -> EntityType.Builder.of(InfectedWitch::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "inf_witch").toString()));
    public static final RegistryObject<EntityType<Leaper>> LEAPER = SPORE_ENTITIES.register("leaper",
            () -> EntityType.Builder.of(Leaper::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "leaper").toString()));
    public static final RegistryObject<EntityType<ScentEntity>> SCENT = SPORE_ENTITIES.register("scent",
            () -> EntityType.Builder.of(ScentEntity::new, MobCategory.MISC).sized(0.2f,0.2f)
                    .build(new ResourceLocation(Spore.MODID, "scent").toString()));
}
