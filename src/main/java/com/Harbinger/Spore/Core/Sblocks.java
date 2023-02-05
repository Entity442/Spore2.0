package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Sblocks.Acid;
import com.Harbinger.Spore.Sblocks.Container;
import com.Harbinger.Spore.Sblocks.Mycelium;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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


    private static <T extends Block> RegistryObject<T> registerBlockT(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItemT(name, toReturn);
        return toReturn;}
    private static <T extends Block> void registerBlockItemT(String name, RegistryObject<T> block) {
        Sitems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ScreativeTab.SPORE_T)));}

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);}


    public static final RegistryObject<Block> MYCELIUM = registerBlock("mycelium",
            Mycelium::new);


    public static final RegistryObject<Block> ACID = registerBlockWithoutBlockItem("acid",
            Acid::new);

    public static final RegistryObject<Block> CONTAINER = registerBlockT("container",
            Container::new);
    
    public  static final RegistryObject<Block> LAB_BLOCK = registerBlockT("lab_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f, 20f)));
    public  static final RegistryObject<SlabBlock> LAB_SLAB = registerBlockT("lab_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Sblocks.LAB_BLOCK.get())));
    public  static final RegistryObject<StairBlock> LAB_STAIR = registerBlockT("lab_stair",
            () -> new StairBlock(()-> LAB_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Sblocks.LAB_BLOCK.get())));

}
