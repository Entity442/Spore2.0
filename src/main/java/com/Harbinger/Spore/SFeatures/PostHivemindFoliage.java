package com.Harbinger.Spore.SFeatures;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.ExtremelySusThings.SporeSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PostHivemindFoliage extends RandomPatchFeature {
    public static PostHivemindFoliage FEATURE;
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE;
    public static Holder<PlacedFeature> PLACED_FEATURE;

    public static Feature<?> feature() {
        FEATURE = new PostHivemindFoliage();
        CONFIGURED_FEATURE = FeatureUtils.register("spore:hive_foliage", Feature.RANDOM_PATCH,
                grassPatch(new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>()
                        .add(Sblocks.BLOOM_G.get().defaultBlockState(),1).add(Sblocks.BLOOM_GG.get().defaultBlockState(),2)
                        .add(Sblocks.GROWTHS_BIG.get().defaultBlockState(),3).add(Sblocks.GROWTHS_SMALL.get().defaultBlockState(),4)
                        .add(Sblocks.GROWTH_MYCELIUM.get().defaultBlockState(),5).add(Sblocks.FUNGAL_STEM_SAPLING.get().defaultBlockState(),6)), 128));
        PLACED_FEATURE = PlacementUtils.register("spore:hive_foliage", CONFIGURED_FEATURE, List.of(CountPlacement.of(7), RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        return FEATURE;
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider provider, int value) {
        return FeatureUtils.simpleRandomPatchConfiguration(value, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider)));
    }

    public PostHivemindFoliage() {
        super(RandomPatchConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        BlockPos pos = context.origin();
        ServerLevel serverLevel = context.level().getLevel();
        Holder<Biome> biome = serverLevel.getBiome(pos);
        SporeSavedData data = SporeSavedData.getDataLocation(serverLevel);
        if (data == null){
            return false;
        }
        if (data.getAmountOfHiveminds() >= SConfig.SERVER.proto_spawn_world_mod.get()){
            for (String biomeString : SConfig.SERVER.dimension_parameters.get()){
                if (biome.is(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(biomeString))) || biome.is(new ResourceLocation(biomeString))) {
                    return super.place(context);
                }
            }
        }
        return false;
    }

}
