package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Sblocks.Acid;
import com.Harbinger.Spore.Sblocks.Container;
import com.Harbinger.Spore.Sblocks.GenericFoliageBlock;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sblocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Spore.MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    public static final RegistryObject<Block> ACID = BLOCKS.register("acid", Acid::new);
    public static final RegistryObject<Block> CONTAINER = BLOCKS.register("container", Container::new);
    public static final RegistryObject<Block> LAB_BLOCK = BLOCKS.register("lab_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f, 20f)));
    public static final RegistryObject<Block> LAB_SLAB = BLOCKS.register("lab_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Sblocks.LAB_BLOCK.get())));
    public static final RegistryObject<Block> LAB_STAIR = BLOCKS.register("lab_stair", () -> new StairBlock(()-> LAB_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Sblocks.LAB_BLOCK.get())));
    public static final RegistryObject<Block> GROWTHS_BIG = BLOCKS.register("growths_big", GenericFoliageBlock::new);
    public static final RegistryObject<Block> GROWTHS_SMALL = BLOCKS.register("growths_small", GenericFoliageBlock::new);
    public static final RegistryObject<Block> INFESTED_DIRT = BLOCKS.register("infested_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));


}
