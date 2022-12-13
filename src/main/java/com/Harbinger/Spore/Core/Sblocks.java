package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Sblocks.Acid;
import com.Harbinger.Spore.Sblocks.Container;
import com.Harbinger.Spore.Sblocks.Mycelium;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Sblocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Spore.MODID);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;}
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        Sitems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ScreativeTab.SPORE)));}
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);}
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static final RegistryObject<Block> MYCELIUM = registerBlock("mycelium",
            Mycelium::new);
    public static final RegistryObject<Block> ACID = registerBlockWithoutBlockItem("acid",
            Acid::new);
    public static final RegistryObject<Block> CONTAINER = registerBlock("container",
            Container::new);
}
