package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Sentities.*;
import com.Harbinger.Spore.Sentities.Projectile.AcidBall;
import com.Harbinger.Spore.Sentities.Projectile.ThrownSpear;
import com.Harbinger.Spore.Sentities.Projectile.ThrownTumor;
import com.Harbinger.Spore.Sentities.Projectile.Vomit;
import com.Harbinger.Spore.Sentities.InfEvoClaw;
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


    public static final RegistryObject<EntityType<InfectedHuman>> INF_HUMAN = SPORE_ENTITIES.register("inf_human",
            () -> EntityType.Builder.of(InfectedHuman::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "inf_human").toString()));



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

    public static final RegistryObject<EntityType<InfectedWanderingTrader>> INF_WANDERER = SPORE_ENTITIES.register("inf_wanderer",
            () -> EntityType.Builder.of(InfectedWanderingTrader::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "inf_wanderer").toString()));

    public static final RegistryObject<EntityType<InfectedWitch>> INF_WITCH = SPORE_ENTITIES.register("inf_witch",
            () -> EntityType.Builder.of(InfectedWitch::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "inf_witch").toString()));

    public static final RegistryObject<EntityType<Leaper>> LEAPER = SPORE_ENTITIES.register("leaper",
            () -> EntityType.Builder.of(Leaper::new, MobCategory.MONSTER).sized(0.6f, 2.3f)
                    .build(new ResourceLocation(Spore.MODID, "leaper").toString()));

    public static final RegistryObject<EntityType<Slasher>> SLASHER = SPORE_ENTITIES.register("slasher",
            () -> EntityType.Builder.of(Slasher::new, MobCategory.MONSTER).sized(0.6f, 2.2f)
                    .build(new ResourceLocation(Spore.MODID, "slasher").toString()));

    public static final RegistryObject<EntityType<Spitter>> SPITTER = SPORE_ENTITIES.register("spitter",
            () -> EntityType.Builder.of(Spitter::new, MobCategory.MONSTER).sized(0.6f, 1.9f)
                    .build(new ResourceLocation(Spore.MODID, "spitter").toString()));

    public static final RegistryObject<EntityType<InfectedPillager>> INF_PILLAGER = SPORE_ENTITIES.register("inf_pillager",
            () -> EntityType.Builder.of(InfectedPillager::new, MobCategory.MONSTER).sized(0.6f, 1.9f)
                    .build(new ResourceLocation(Spore.MODID, "inf_pillager").toString()));

    public static final RegistryObject<EntityType<InfectedVendicator>> INF_VINDICATOR = SPORE_ENTITIES.register("inf_vindicator",
            () -> EntityType.Builder.of(InfectedVendicator::new, MobCategory.MONSTER).sized(0.6f, 1.9f)
                    .build(new ResourceLocation(Spore.MODID, "inf_vindicator").toString()));

    public static final RegistryObject<EntityType<InfectedEvoker>> INF_EVOKER = SPORE_ENTITIES.register("inf_evoker",
            () -> EntityType.Builder.of(InfectedEvoker::new, MobCategory.MONSTER).sized(0.6f, 1.9f)
                    .build(new ResourceLocation(Spore.MODID, "inf_evoker").toString()));

    public static final RegistryObject<EntityType<Howler>> HOWLER = SPORE_ENTITIES.register("howler",
            () -> EntityType.Builder.of(Howler::new, MobCategory.MONSTER).sized(0.6f, 2.1f)
                    .build(new ResourceLocation(Spore.MODID, "howler").toString()));

    public static final RegistryObject<EntityType<Stalker>> STALKER = SPORE_ENTITIES.register("stalker",
            () -> EntityType.Builder.of(Stalker::new, MobCategory.MONSTER).sized(0.6f, 2.3f)
                    .build(new ResourceLocation(Spore.MODID, "stalker").toString()));

    public static final RegistryObject<EntityType<Brute>> BRUTE = SPORE_ENTITIES.register("brute",
            () -> EntityType.Builder.of(Brute::new, MobCategory.MONSTER).sized(1.8f,1.6f)
                    .build(new ResourceLocation(Spore.MODID, "brute").toString()));

    public static final RegistryObject<EntityType<Busser>> BUSSER = SPORE_ENTITIES.register("busser",
            () -> EntityType.Builder.of(Busser::new, MobCategory.MONSTER).sized(0.6f, 1.9f)
                    .build(new ResourceLocation(Spore.MODID, "busser").toString()));

    public static final RegistryObject<EntityType<InfectedDrowned>> INF_DROWNED = SPORE_ENTITIES.register("inf_drowned",
            () -> EntityType.Builder.of(InfectedDrowned::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "inf_drowned").toString()));

    public static final RegistryObject<EntityType<Host>> HOST = SPORE_ENTITIES.register("host",
            () -> EntityType.Builder.of(Host::new, MobCategory.MONSTER).sized(0.6f, 2f)
                    .build(new ResourceLocation(Spore.MODID, "host").toString()));


    public static final RegistryObject<EntityType<AcidBall>> ACID_BALL = register("acid_ball",
            EntityType.Builder.<AcidBall>of(AcidBall::new, MobCategory.MISC).setCustomClientFactory(AcidBall::new)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    public static final RegistryObject<EntityType<Vomit>> ACID = register("acid",
            EntityType.Builder.<Vomit>of(Vomit::new, MobCategory.MISC).setCustomClientFactory(Vomit::new)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    public static final RegistryObject<EntityType<ThrownSpear>> THROWN_SPEAR = register("thrown_spear",
            EntityType.Builder.<ThrownSpear>of(ThrownSpear::new, MobCategory.MISC).setCustomClientFactory(ThrownSpear::new)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    public static final RegistryObject<EntityType<ThrownTumor>> THROWN_TUMOR = register("thrown_tumor",
            EntityType.Builder.<ThrownTumor>of(ThrownTumor::new, MobCategory.MISC).setCustomClientFactory(ThrownTumor::new)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));


    public static final RegistryObject<EntityType<ScentEntity>> SCENT = SPORE_ENTITIES.register("scent",
            () -> EntityType.Builder.of(ScentEntity::new, MobCategory.MISC).sized(0.2f,0.2f)
                    .build(new ResourceLocation(Spore.MODID, "scent").toString()));

    public static final RegistryObject<EntityType<InfEvoClaw>> CLAW = SPORE_ENTITIES.register("claw",
            () -> EntityType.Builder.of(InfEvoClaw::new, MobCategory.MONSTER).sized(0.5f, 1f)
                    .build(new ResourceLocation(Spore.MODID, "claw").toString()));



}