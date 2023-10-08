package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Client.ClientModEvents;
import com.Harbinger.Spore.Sitems.*;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class Sitems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Spore.MODID);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public  static  final List<Item> BIOLOGICAL_ITEMS = new ArrayList<>();

    public  static final RegistryObject<Item> CLAW_FRAGMENT = ITEMS.register("claw_fragment",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> CLAW = ITEMS.register("claw",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> ARMOR_FRAGMENT = ITEMS.register("armor_fragment",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> MUTATED_HEART = ITEMS.register("mutated_heart",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> MUTATED_FIBER = ITEMS.register("mutated_fiber",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> WING_MEMBRANE = ITEMS.register("wing_membrane",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> FLESHY_BONE = ITEMS.register("fleshy_bone",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> HARDENED_BIND = ITEMS.register("hardened_bind",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> FLESHY_CLAW = ITEMS.register("fleshy_claw",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> LIVING_CORE = ITEMS.register("living_core",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> SPINE_FRAGMENT = ITEMS.register("spine_fragment",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> NERVES = ITEMS.register("nerves",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> CEREBRUM = ITEMS.register("cerebrum",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> SPINE = ITEMS.register("spine",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> ARMOR_PLATE = ITEMS.register("armor_plate",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> PLATED_MUSCLE = ITEMS.register("plated_muscle",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> ALVEOLIC_SACK = ITEMS.register("alveolic_sack",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> ALTERED_SPLEEN = ITEMS.register("altered_spleen",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> CORROSIVE_SACK = ITEMS.register("corrosive_sack",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> TENDONS = ITEMS.register("tendons",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INNARDS = ITEMS.register("innards",
            () -> new Innards( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> SICKLE_FRAGMENT = ITEMS.register("sickle_fragment",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> R_WING = ITEMS.register("r_wing",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> TUMOR = ITEMS.register("tumor",
            () -> new Tumor( new Item.Properties().tab(ScreativeTab.SPORE).stacksTo(16)));
    public  static final RegistryObject<Item> SAUSAGE = ITEMS.register("sausage",
            () -> new Item(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).effect(()-> new MobEffectInstance(Seffects.MYCELIUM.get(),200,0),0.4f)
                    .effect(()-> new MobEffectInstance(MobEffects.ABSORPTION,300,1),1f).meat().build())));

    public  static final RegistryObject<Item> INFECTED_HUMAN_SPAWNEGG = ITEMS.register("infected_human_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_HUMAN,-9357608, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INFECTED_HUSK_SPAWNEGG = ITEMS.register("inf_husk_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_HUSK,-875608, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_VILLAGER_SPAWNEGG = ITEMS.register("inf_villager_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_VILLAGER,-6639718, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_WITCH_SPAWNEGG = ITEMS.register("inf_witch_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_WITCH,-8512718, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_PILLAGER_SPAWNEGG = ITEMS.register("inf_pillager_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_PILLAGER,-2312718, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_VIND_SPAWNEGG = ITEMS.register("inf_vind_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_VINDICATOR,-984718, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_EVO_SPAWNEGG = ITEMS.register("inf_evo_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_EVOKER,-254718, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_WANDERER_SPAWNEGG = ITEMS.register("inf_wanderer_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_WANDERER,-6639718, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_DROWNED_SPAWNEGG = ITEMS.register("inf_drowned_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_DROWNED,-16751002, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_PLAYER_SPAWNEGG = ITEMS.register("inf_player_spawnegg",
            () -> new SporeSpawnEgg(Sentities.INF_PLAYER,-86751002, new Item.Properties().tab(ScreativeTab.SPORE)));






    public  static final RegistryObject<Item> KNIGHT_SPAWNEGG = ITEMS.register("knight_spawnegg",
            () -> new SporeSpawnEgg(Sentities.KNIGHT,-7681208, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> GRIEFER_SPAWNEGG = ITEMS.register("griefer_spawnegg",
            () -> new SporeSpawnEgg(Sentities.GRIEFER,-5750208, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BRAIO_SPAWNEGG = ITEMS.register("braio_spawnegg",
            () -> new SporeSpawnEgg(Sentities.BRAIOMIL,-6124508, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BUSSER_SPAWNEGG = ITEMS.register("busser_spawnegg",
            () -> new SporeSpawnEgg(Sentities.BUSSER,-3724508, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> LEAPER_SPAWNEGG = ITEMS.register("leaper_spawnegg",
            () -> new SporeSpawnEgg(Sentities.LEAPER,-9762718, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> SLASHER_SPAWNEGG = ITEMS.register("slasher_spawnegg",
            () -> new SporeSpawnEgg(Sentities.SLASHER,-8564118, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> SPITTER_SPAWNEGG = ITEMS.register("spitter_spawnegg",
            () -> new SporeSpawnEgg(Sentities.SPITTER,-8164818, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> HOWLER_SPAWNEGG = ITEMS.register("howler_spawnegg",
            () -> new SporeSpawnEgg(Sentities.HOWLER,-32464818, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> STALKER_SPAWNEGG = ITEMS.register("stalker_spawnegg",
            () -> new SporeSpawnEgg(Sentities.STALKER,-42364818, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BRUTE_SPAWNEGG = ITEMS.register("brute_spawnegg",
            () -> new SporeSpawnEgg(Sentities.BRUTE,-1235818, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> VOLATILE_SPAWNEGG = ITEMS.register("volatile_spawnegg",
            () -> new SporeSpawnEgg(Sentities.VOLATILE,-976435818, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> SCENT_SPAWNEGG = ITEMS.register("scent_spawnegg",
            () -> new SporeSpawnEgg(Sentities.SCENT,-1, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> CLAW_SPAWNEGG = ITEMS.register("claw_spawnegg",
            () -> new SporeSpawnEgg(Sentities.CLAW,-5437216, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> SCAMPER_SPAWNEGG = ITEMS.register("scamper_spawnegg",
            () -> new SporeSpawnEgg(Sentities.SCAMPER,-33777216, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> MOUND_SPAWNEGG = ITEMS.register("mound_spawnegg",
            () -> new SporeSpawnEgg(Sentities.MOUND,-5750208, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> RECONSTRUCTOR_SPAWNEGG = ITEMS.register("reconstructor_spawnegg",
            () -> new SporeSpawnEgg(Sentities.RECONSTRUCTOR,-2353208, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> VIGIL_SPAWNEGG = ITEMS.register("vigil_spawnegg",
            () -> new SporeSpawnEgg(Sentities.VIGIL,-64160208, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> PROTO_SPAWNEGG = ITEMS.register("proto_spawnegg",
            () -> new SporeSpawnEgg(Sentities.PROTO,244208, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> SIEGER_SPAWNEGG = ITEMS.register("sieger_spawnegg",
            () -> new SporeSpawnEgg(Sentities.SIEGER,244208, new Item.Properties().tab(ScreativeTab.SPORE)));


    public  static final RegistryObject<Item> ACID = ITEMS.register("acid",
            () -> new Item( new Item.Properties()));
    public  static final RegistryObject<Item> ACID_BALL = ITEMS.register("acid_ball",
            () -> new Item( new Item.Properties()));


    public  static final RegistryObject<Item> SABER = ITEMS.register("saber",
            InfectedSaber::new);
    public  static final RegistryObject<Item> GREATSWORD = ITEMS.register("greatsword",
            InfectedGreatSword::new);
    public  static final RegistryObject<Item> ARMADS = ITEMS.register("armads",
            InfectedArmads::new);
    public  static final RegistryObject<Item> INFECTED_BOW = ITEMS.register("infected_bow",
            () -> new InfectedGreatBow( new Item.Properties().tab(ScreativeTab.SPORE).durability(SConfig.SERVER.bow_durability.get())));
    public  static final RegistryObject<Item> MAUL = ITEMS.register("maul",
            InfectedMaul::new);
    public  static final RegistryObject<Item> SCYTHE = ITEMS.register("scythe",
            InfectedScythe::new);
    public  static final RegistryObject<Item> COMBAT_SHOVEL = ITEMS.register("combat_shovel",
            InfectedCombatShovel::new);
    public  static final RegistryObject<Item> INFECTED_SPEAR = ITEMS.register("infected_spear",
            () -> new InfectedSpearItem(new Item.Properties().tab(ScreativeTab.SPORE).durability(SConfig.SERVER.spear_durability.get())));
    public  static final RegistryObject<Item> INFECTED_CROSSBOW = ITEMS.register("infected_crossbow",
            InfectedCrossbow::new);
    public  static final RegistryObject<Item> MACE = ITEMS.register("mace",
            InfectedMace::new);
    public  static final RegistryObject<Item> SICKLE = ITEMS.register("sickle",
            InfectedSickle::new);
    public  static final RegistryObject<Item> KNIFE = ITEMS.register("knife",
            InfectedKnife::new);
    public  static final RegistryObject<Item> RAPIER = ITEMS.register("rapier",
            InfectedRapier::new);

    public  static final RegistryObject<Item> INF_HELMET = ITEMS.register("inf_helmet",
            InfectedHelmet::new);
    public  static final RegistryObject<Item> INF_CHEST = ITEMS.register("inf_chest",
            InfectedChestplate::new);
    public  static final RegistryObject<Item> INF_PANTS = ITEMS.register("inf_pants",
            InfectedLeggings::new);
    public  static final RegistryObject<Item> INF_BOOTS = ITEMS.register("inf_boots",
            InfectedBoots::new);

    public  static final RegistryObject<Item> PLATED_HELMET = ITEMS.register("plated_helmet",
            PlatedHelmet::new);
    public  static final RegistryObject<Item> PLATED_CHEST = ITEMS.register("plated_chest",
            PlatedChestplate::new);
    public  static final RegistryObject<Item> PLATED_PANTS = ITEMS.register("plated_pants",
            PlatedLeggings::new);
    public  static final RegistryObject<Item> PLATED_BOOTS = ITEMS.register("plated_boots",
            PlatedBoots::new);

    public  static final RegistryObject<Item> LIVING_HELMET = ITEMS.register("living_helmet",
            LivingHelmet::new);
    public  static final RegistryObject<Item> LIVING_CHEST = ITEMS.register("living_chest",
            LivingChestplate::new);
    public  static final RegistryObject<Item> LIVING_PANTS = ITEMS.register("living_pants",
            LivingLeggings::new);
    public  static final RegistryObject<Item> LIVING_BOOTS = ITEMS.register("living_boots",
            LivingBoots::new);
    public  static final RegistryObject<Item> R_ELYTRON = ITEMS.register("r_elytron", ClientModEvents.getElytraItem());

    public  static final RegistryObject<Item> INF_UP_CHESTPLATE = ITEMS.register("inf_up_chest",
            UpgradedInfectedExoskeleton.InfectedUpChestplate::new);

    public  static final RegistryObject<Item> CORRUPTED_RECORD = ITEMS.register("corrupted_record",
            CorruptedRecord::new);
    public  static final RegistryObject<Item> GAS_MASK = ITEMS.register("gas_mask",
            GasMaskItem::new);
    public  static final RegistryObject<Item> BIOMASS = ITEMS.register("biomass",
            Biomass::new);
    public  static final RegistryObject<Item> AMETHYST_DUST = ITEMS.register("amethyst_dust",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE_T)));
    public  static final RegistryObject<Item> COMPOUND = ITEMS.register("compound",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE_T)));
    public  static final RegistryObject<Item> COMPOUND_PLATE = ITEMS.register("compound_plate",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE_T)));















    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static final RegistryObject<Item> CONTAINER = block(Sblocks.CONTAINER, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> LAB_BLOCK = block(Sblocks.LAB_BLOCK, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> LAB_SLAB = block(Sblocks.LAB_SLAB, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> LAB_STAIR = block(Sblocks.LAB_STAIR, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> IRON_LADDER = block(Sblocks.IRON_LADDER, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> VENT_PLATE = block(Sblocks.VENT_PLATE, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> RUSTED_VENT_PLATE = block(Sblocks.RUSTED_VENT_PLATE, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> VENT_DOOR = block(Sblocks.VENT_DOOR, ScreativeTab.SPORE_T);

    public static final RegistryObject<Item> REINFORCED_DOOR = block(Sblocks.REINFORCED_DOOR, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> FROZEN_REINFORCED_DOOR = block(Sblocks.FROZEN_REINFORCED_DOOR, ScreativeTab.SPORE_T);
    public static final RegistryObject<Item> RUSTED_REINFORCED_DOOR = block(Sblocks.RUSTED_REINFORCED_DOOR, ScreativeTab.SPORE_T);

    public static final RegistryObject<Item> GROWTHS_BIG = block(Sblocks.GROWTHS_BIG, ScreativeTab.SPORE);
    public static final RegistryObject<Item> GROWTHS_SMALL = block(Sblocks.GROWTHS_SMALL, ScreativeTab.SPORE);
    public static final RegistryObject<Item> BLOOM_G = block(Sblocks.BLOOM_G, ScreativeTab.SPORE);
    public static final RegistryObject<Item> BLOOM_GG = block(Sblocks.BLOOM_GG, ScreativeTab.SPORE);
    public static final RegistryObject<Item> FUNGAL_ROOTS = block(Sblocks.FUNGAL_ROOTS, ScreativeTab.SPORE);
    public static final RegistryObject<Item> GROWTH_MYCELIUM = block(Sblocks.GROWTH_MYCELIUM, ScreativeTab.SPORE);
    public static final RegistryObject<Item> FUNGAL_STEP_SAPLING = block(Sblocks.FUNGAL_STEM_SAPLING, ScreativeTab.SPORE);
    public static final RegistryObject<Item> MYCELIUM_VEINS = block(Sblocks.MYCELIUM_VEINS, ScreativeTab.SPORE);

    public static final RegistryObject<Item> ROTTEN_LOG = block(Sblocks.ROTTEN_LOG, ScreativeTab.SPORE);
    public static final RegistryObject<Item> ROOTED_BIOMASS = block(Sblocks.ROOTED_BIOMASS, ScreativeTab.SPORE);
    public static final RegistryObject<Item> BIOMASS_BLOCK = block(Sblocks.BIOMASS_BLOCK, ScreativeTab.SPORE);
    public static final RegistryObject<Item> ROOTED_MYCELIUM = block(Sblocks.ROOTED_MYCELIUM, ScreativeTab.SPORE);
    public static final RegistryObject<Item> MYCELIUM_BLOCK = block(Sblocks.MYCELIUM_BLOCK, ScreativeTab.SPORE);
    public static final RegistryObject<Item> MYCELIUM_SLAB = block(Sblocks.MYCELIUM_SLAB, ScreativeTab.SPORE);
    public static final RegistryObject<Item> FUNGAL_SHELL = block(Sblocks.FUNGAL_SHELL, ScreativeTab.SPORE);
    public static final RegistryObject<Item> ORGANITE = block(Sblocks.ORGANITE, ScreativeTab.SPORE);

    public static final RegistryObject<Item> INFESTED_DEEPSLATE = block(Sblocks.INFESTED_DEEPSLATE, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_DIRT = block(Sblocks.INFESTED_DIRT, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_END_STONE = block(Sblocks.INFESTED_END_STONE, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_GRAVEL = block(Sblocks.INFESTED_GRAVEL, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_NETHERRACK = block(Sblocks.INFESTED_NETHERRACK, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_SAND = block(Sblocks.INFESTED_SAND, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_SOUL_SAND = block(Sblocks.INFESTED_SOUL_SAND, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_STONE = block(Sblocks.INFESTED_STONE, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_RED_SAND = block(Sblocks.INFESTED_RED_SAND, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_CLAY = block(Sblocks.INFESTED_CLAY, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_COBBLESTONE = block(Sblocks.INFESTED_COBBLESTONE, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_COBBLED_DEEPSLATE = block(Sblocks.INFESTED_COBBLED_DEEPSLATE, ScreativeTab.SPORE);
    public static final RegistryObject<Item> INFESTED_LABORATORY_BLOCK = block(Sblocks.INFESTED_LABORATORY_BLOCK, ScreativeTab.SPORE);


    public static final RegistryObject<Item> REMAINS = block(Sblocks.REMAINS, ScreativeTab.SPORE);
    public static final RegistryObject<Item> BIOMASS_LUMP = block(Sblocks.BIOMASS_LUMP, ScreativeTab.SPORE);
    public static final RegistryObject<Item> HIVE_SPAWN = block(Sblocks.HIVE_SPAWN, ScreativeTab.SPORE);

}
