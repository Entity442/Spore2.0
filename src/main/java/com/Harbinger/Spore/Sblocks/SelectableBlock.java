package com.Harbinger.Spore.Sblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SelectableBlock extends Block {
    public ItemStack stack;
    public SelectableBlock(ItemStack stack1,Properties properties) {
        super(properties);
        this.stack = stack1;
    }

    public ItemStack getCloneItemStack(BlockGetter p_57256_, BlockPos p_57257_, BlockState p_57258_) {
        return stack;
    }


}
