package com.Harbinger.Spore;

import com.Harbinger.Spore.Core.*;
import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;


@Mod(Spore.MODID)
public class Spore
{
    public static final String MODID = "spore";

    public Spore()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SConfig.SERVER_SPEC, "spore-newconfig.toml");
        SConfig.loadConfig(SConfig.SERVER_SPEC,
                FMLPaths.CONFIGDIR.get().resolve("spore-newconfig.toml").toString());


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
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

        });
    }



}


