package com.Harbinger.Spore.Features;

import com.Harbinger.Spore.Spore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Set;

public class BiomassTowerFeatureBig extends Feature<NoneFeatureConfiguration> {
    public static BiomassTowerFeatureBig FEATURE = null;
    public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;

    public static Feature<?> feature() {
        FEATURE = new BiomassTowerFeatureBig();
        CONFIGURED_FEATURE = FeatureUtils.register("spore:biomass_tower_big", FEATURE, FeatureConfiguration.NONE);
        PLACED_FEATURE = PlacementUtils.register("spore:biomass_tower_big", CONFIGURED_FEATURE, List.of());
        return FEATURE;
    }

    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);
    private final List<Block> base_blocks;
    private StructureTemplate template = null;

    public BiomassTowerFeatureBig() {
        super(NoneFeatureConfiguration.CODEC);
        base_blocks = List.of(Blocks.MYCELIUM);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!generate_dimensions.contains(context.level().getLevel().dimension()))
            return false;
        if (template == null)
                template = context.level().getLevel().getStructureManager().getOrCreate(new ResourceLocation(Spore.MODID, "biomass_tower_tall"));

        boolean anyPlaced = false;
        if ((context.random().nextInt(1000000) + 1) <= 10000) {
            int count = context.random().nextInt(1) + 1;
            for (int a = 0; a < count; a++) {
                int i = context.origin().getX() + context.random().nextInt(16);
                int k = context.origin().getZ() + context.random().nextInt(16);
                int j = context.level().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, i, k) - 1;
                if (!base_blocks.contains(context.level().getBlockState(new BlockPos(i, j, k)).getBlock()))
                    continue;
                BlockPos spawnTo = new BlockPos(i, j + -1, k);
                if (template.placeInWorld(context.level(), spawnTo, spawnTo,
                        new StructurePlaceSettings().setMirror(Mirror.values()[context.random().nextInt(2)])
                                .setRotation(Rotation.values()[context.random().nextInt(3)]).setRandom(context.random())
                                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK),
                        context.random(), 2)) {
                    anyPlaced = true;
                }
            }
        }
        return anyPlaced;
    }
}
