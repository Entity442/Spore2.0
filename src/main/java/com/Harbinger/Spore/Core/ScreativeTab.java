package com.Harbinger.Spore.Core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ScreativeTab {
    public static final CreativeModeTab SPORE = new CreativeModeTab("spore") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Sitems.MUTATED_HEART.get());
        }
    };
}
