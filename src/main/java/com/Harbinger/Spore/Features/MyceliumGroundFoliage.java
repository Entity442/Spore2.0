package com.Harbinger.Spore.Features;

import com.Harbinger.Spore.Core.Sblocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
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
import java.util.Set;

public class MyceliumGroundFoliage extends RandomPatchFeature {
    public static MyceliumGroundFoliage FEATURE = null;
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;

    public static Feature<?> feature() {

        FEATURE = new MyceliumGroundFoliage();
        CONFIGURED_FEATURE = FeatureUtils.register("spore:ground_foliage",
                Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(Sblocks.GROWTHS_SMALL.get().defaultBlockState(), 2).add(Sblocks.GROWTHS_BIG.get().defaultBlockState(), 1)
                        .add(Sblocks.GROWTH_MYCELIUM.get().defaultBlockState(), 1).add(Sblocks.BLOOM_G.get().defaultBlockState(), 1)
                        .add(Sblocks.BLOOM_GG.get().defaultBlockState(), 1).add(Sblocks.FUNGAL_STEM_SAPLING.get().defaultBlockState(), 1)), 128));

        PLACED_FEATURE = PlacementUtils.register("spore:ground_foliage", CONFIGURED_FEATURE, List.of(CountPlacement.of(5), RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        return FEATURE;
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
        return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
    }
    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD, Level.NETHER);

    public MyceliumGroundFoliage() {
        super(RandomPatchConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel world = context.level();
        if (!generate_dimensions.contains(world.getLevel().dimension()))
            return false;
        return super.place(context);
    }
}