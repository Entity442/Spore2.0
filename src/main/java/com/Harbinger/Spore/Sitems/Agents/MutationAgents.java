package com.Harbinger.Spore.Sitems.Agents;

import com.Harbinger.Spore.Core.ScreativeTab;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class MutationAgents extends Item {
    protected final int mutationChance;
    protected final RandomSource source = RandomSource.create();
    public MutationAgents(int mutationChance) {
        super(new Item.Properties().stacksTo(8).tab(ScreativeTab.SPORE_T));
        this.mutationChance = mutationChance;
    }

    public int getMutationChance(){
        return mutationChance;
    }

    public abstract void mutateWeapon(ItemStack stack);
}
