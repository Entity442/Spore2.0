package com.Harbinger.Spore.Sblocks;

import com.Harbinger.Spore.Spore;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Material;

public class BiomassLump extends GenericFoliageBlock {
    public BiomassLump() {
        super(BlockBehaviour.Properties.of(Material.MOSS).strength(2f,2f).sound(SoundType.SLIME_BLOCK).randomTicks().noOcclusion());
    }


    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos blockPos, RandomSource source) {
        if (level.canSeeSky(blockPos) && Math.random() < 0.2) {
            level.destroyBlock(blockPos, false);
            RandomSource random = RandomSource.create();
            if (Math.random() < 0.4) {
                StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(Spore.MODID, "biomass_tower"));
                BlockPos pos = new BlockPos(blockPos.getX() - 3, blockPos.getY() - 2, blockPos.getZ() - 3);
                template.placeInWorld(level, pos, pos, new StructurePlaceSettings().setIgnoreEntities(true), random, 3);
            } else if (Math.random() < 0.4) {
                StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(Spore.MODID, "biomass_tower_tall"));
                BlockPos pos = new BlockPos(blockPos.getX() - 3, blockPos.getY() - 1, blockPos.getZ() - 3);
                template.placeInWorld(level, pos, pos, new StructurePlaceSettings().setIgnoreEntities(true), random, 3);
            }
            else {
            StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(Spore.MODID, "biomass_tower_small"));
            BlockPos pos = new BlockPos(blockPos.getX() - 2, blockPos.getY() - 1, blockPos.getZ() - 2);
            template.placeInWorld(level, pos, pos, new StructurePlaceSettings().setIgnoreEntities(true), random, 3);
            }
    }
  }
}
