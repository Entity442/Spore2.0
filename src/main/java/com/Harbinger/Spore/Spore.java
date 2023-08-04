package com.Harbinger.Spore;

import com.Harbinger.Spore.Core.*;
import com.Harbinger.Spore.ExtremelySusThings.BiomeModification;
import com.Harbinger.Spore.ExtremelySusThings.StructureModification;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BasicInfected.InfectedDrowned;
import com.mojang.serialization.Codec;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.StructureModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Spore.MODID)
public class Spore
{
    public  static Spore instance;
    public static final String MODID = "spore";
    public static final Logger LOGGER = LogManager.getLogger(Spore.MODID);
    public Spore()
    {
        instance = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SConfig.DATAGEN_SPEC ,"sporedata.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SConfig.SERVER_SPEC ,"sporeconfig.toml");

        SConfig.loadConfig(SConfig.SERVER_SPEC, FMLPaths.CONFIGDIR.get().resolve("sporeconfig.toml").toString());

        modEventBus.addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        Sblocks.register(modEventBus);
        Sitems.register(modEventBus);
        Sentities.register(modEventBus);
        Seffects.register(modEventBus);
        Spotion.register(modEventBus);
        Sparticles.register(modEventBus);
        Ssounds.register(modEventBus);
        SMenu.register(modEventBus);
        SblockEntities.register(modEventBus);
        final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifiers =
                DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Spore.MODID);
        biomeModifiers.register(modEventBus);
        biomeModifiers.register("inf_spawns", BiomeModification::makeCodec);
        final DeferredRegister<Codec<? extends StructureModifier>> structureModifiers = DeferredRegister.create(ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, Spore.MODID);
        structureModifiers.register(modEventBus);
        structureModifiers.register("spore_structure_spawns", StructureModification::makeCodec);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(Sentities.INF_VINDICATOR.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_EVOKER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_PILLAGER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_HUMAN.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_VILLAGER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_WITCH.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_WANDERER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_PLAYER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.INF_DROWNED.get(),
                    SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    InfectedDrowned::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.KNIGHT.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.GRIEFER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.BRAIOMIL.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.BUSSER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.SCAMPER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.SPITTER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.LEAPER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.SLASHER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.HOWLER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.BRUTE.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
            SpawnPlacements.register(Sentities.STALKER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Infected::checkMonsterInfectedRules);
        });
    }



}


