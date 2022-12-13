package com.Harbinger.Spore.Sblocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class Container extends Block {
    public Container() {
        super(Properties.of(Material.STONE).strength(2f,2f).noOcclusion());
    }


}
