package com.Harbinger.Spore.Sblocks;

import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Core.Sfluids;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BileLiquidBlock extends LiquidBlock {
    public BileLiquidBlock() {
        super(Sfluids.Bile_FLUID_SOURCE,BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).lightLevel(state -> 1).emissiveRendering((state, world, pos) -> false).noLootTable().color(MaterialColor.COLOR_ORANGE).randomTicks());
    }
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (level.getBlockState(pos.above()).isAir() && Math.random() < 0.3f) {
            level.setBlockAndUpdate(pos, Sblocks.CRUSTED_BILE.get().defaultBlockState());
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }
}
