package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Sblocks.*;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
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
    public static final RegistryObject<Block> LAB_BLOCK = BLOCKS.register("lab_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f, 20f)));
    public static final RegistryObject<Block> LAB_SLAB = BLOCKS.register("lab_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Sblocks.LAB_BLOCK.get())));
    public static final RegistryObject<Block> LAB_STAIR = BLOCKS.register("lab_stair", () -> new StairBlock(()-> LAB_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Sblocks.LAB_BLOCK.get())));
    public static final RegistryObject<Block> IRON_LADDER = BLOCKS.register("iron_ladder", () -> new IronLadderBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f, 4f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> GROWTHS_BIG = BLOCKS.register("growths_big", () -> new GenericFoliageBlock( BlockBehaviour.Properties.of(Material.PLANT).strength(0f, 0f).noCollission().noOcclusion().sound(SoundType.CROP)));
    public static final RegistryObject<Block> GROWTHS_SMALL = BLOCKS.register("growths_small", () -> new GenericFoliageBlock( BlockBehaviour.Properties.of(Material.PLANT).strength(0f, 0f).noCollission().noOcclusion().sound(SoundType.CROP)));
    public static final RegistryObject<Block> FUNGAL_STEM_SAPLING = BLOCKS.register("fungal_stem_sapling", FungalSaplings::new);
    public static final RegistryObject<Block> BLOOM_G = BLOCKS.register("blomfung", HangingPlant::new);
    public static final RegistryObject<Block> BLOOM_GG = BLOCKS.register("bloomfung2", HangingPlantBub::new);
    public static final RegistryObject<Block> FUNGAL_STEM = BLOCKS.register("fungal_stem", FungalStem::new);
    public static final RegistryObject<Block> UNDERWATER_FUNGAL_STEM = BLOCKS.register("underwater_fungal_stem", UnderWaterFungalStem::new);
    public static final RegistryObject<Block> FUNGAL_STEM_TOP = BLOCKS.register("fungal_stem_top", FungalStemTop::new);
    public static final RegistryObject<Block> UNDERWATER_FUNGAL_STEM_TOP = BLOCKS.register("underwater_fungal_stem_top", UnderWaterFungusTop::new);
    public static final RegistryObject<Block> FUNGAL_ROOTS = BLOCKS.register("fungal_roots", HangingRoots::new);
    public static final RegistryObject<Block> GROWTH_MYCELIUM = BLOCKS.register("growth_mycelium", () -> new GenericFoliageBlock( BlockBehaviour.Properties.of(Material.PLANT).strength(0f, 0f).noCollission().noOcclusion().sound(SoundType.CROP)
            .lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
    public static final RegistryObject<Block> WALL_GROWTHS = BLOCKS.register("wall_growths", WallFolliage::new);
    public static final RegistryObject<Block> WALL_GROWTHS_BIG = BLOCKS.register("wall_growths_big", WallFolliage::new);

    public static final RegistryObject<Block> INFESTED_DIRT = BLOCKS.register("infested_dirt", () -> new SelectableBlock(new ItemStack(Blocks.COARSE_DIRT.asItem()),BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> INFESTED_STONE = BLOCKS.register("infested_stone", () -> new SelectableBlock(new ItemStack(Blocks.STONE.asItem()),BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> INFESTED_SAND = BLOCKS.register("infested_sand", () -> new SelectableFallingBlock(new ItemStack(Blocks.SAND.asItem()),BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistryObject<Block> INFESTED_GRAVEL = BLOCKS.register("infested_gravel", () -> new SelectableFallingBlock(new ItemStack(Blocks.GRAVEL.asItem()),BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
    public static final RegistryObject<Block> INFESTED_DEEPSLATE = BLOCKS.register("infested_deepslate", () -> new SelectableBlock(new ItemStack(Blocks.DEEPSLATE.asItem()),BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Block> ROTTEN_LOG = BLOCKS.register("rotten_log", () -> new FlamableRotatingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIOMASS_LUMP = BLOCKS.register("biomass_lump", BiomassLump::new);

    public static final RegistryObject<Block> ROOTED_BIOMASS = BLOCKS.register("rooted_biomass", () -> new Block(BlockBehaviour.Properties.of(Material.MOSS).strength(2f,2f).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> BIOMASS_BLOCK = BLOCKS.register("biomass_block", () -> new Block(BlockBehaviour.Properties.of(Material.MOSS).strength(2f,2f).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> ROOTED_MYCELIUM = BLOCKS.register("rooted_mycelium", () -> new Block(BlockBehaviour.Properties.of(Material.MOSS).strength(2f,2f).sound(SoundType.SLIME_BLOCK)));
}
