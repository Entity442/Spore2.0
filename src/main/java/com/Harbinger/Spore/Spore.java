package com.Harbinger.Spore;

import com.Harbinger.Spore.Core.*;
import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.util.SporeEntitySpawn;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import static net.minecraft.world.entity.Mob.checkMobSpawnRules;
import static net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn;


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
        SporeEntitySpawn.SERIALIZER.register(modEventBus);



    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(Sentities.INFECTED_HUMAN.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Spore::checkMonsterInfectedRules);

            SpawnPlacements.register(Sentities.INF_VILLAGER.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Spore::checkMonsterInfectedRules);

            SpawnPlacements.register(Sentities.INF_PILLAGER.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Spore::checkMonsterInfectedRules);

            SpawnPlacements.register(Sentities.INF_WITCH.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Spore::checkMonsterInfectedRules);

            SpawnPlacements.register(Sentities.INF_PILLAGER.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Spore::checkMonsterInfectedRules);

            SpawnPlacements.register(Sentities.INF_EVOKER.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Spore::checkMonsterInfectedRules);

            SpawnPlacements.register(Sentities.INF_VINDICATOR.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Spore::checkMonsterInfectedRules);

        });}


    public static boolean checkMonsterInfectedRules(EntityType<? extends Infected> p_219014_, ServerLevelAccessor levelAccessor, MobSpawnType p_219016_, BlockPos pos, RandomSource p_219018_) {
        return (levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, p_219018_) && checkMobSpawnRules(p_219014_, levelAccessor, p_219016_, pos, p_219018_ ) && levelAccessor.getBlockState(pos.below()).canOcclude())
                || (levelAccessor.getDifficulty() != Difficulty.PEACEFUL && levelAccessor.getBlockState(pos.below()).is(BlockTags.MOOSHROOMS_SPAWNABLE_ON));
    }


}


