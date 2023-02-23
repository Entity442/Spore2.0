package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.Client.ClientModEvents;
import com.Harbinger.Spore.Sitems.*;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sitems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Spore.MODID);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

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



    public  static final RegistryObject<Item> INFECTED_HUMAN_SPAWNEGG = ITEMS.register("infected_human_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_HUMAN,-9357608, -16724788, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_VILLAGER_SPAWNEGG = ITEMS.register("inf_villager_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_VILLAGER,-6639718,-97141773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_WITCH_SPAWNEGG = ITEMS.register("inf_witch_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_WITCH,-8512718,-95711773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_PILLAGER_SPAWNEGG = ITEMS.register("inf_pillager_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_PILLAGER,-2312718,-15211773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_VIND_SPAWNEGG = ITEMS.register("inf_vind_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_VINDICATOR,-984718,-95171773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_EVO_SPAWNEGG = ITEMS.register("inf_evo_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_EVOKER,-254718,-45671773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_WANDERER_SPAWNEGG = ITEMS.register("inf_wanderer_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_WANDERER,-6639718,-97141773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_DROWNED_SPAWNEGG = ITEMS.register("inf_drowned_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_DROWNED,-16751002, -16764109, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_PLAYER_SPAWNEGG = ITEMS.register("inf_player_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_PLAYER,-86751002, -43764109, new Item.Properties().tab(ScreativeTab.SPORE)));






    public  static final RegistryObject<Item> KNIGHT_SPAWNEGG = ITEMS.register("knight_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.KNIGHT,-7681208, -16777012, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> GRIEFER_SPAWNEGG = ITEMS.register("griefer_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.GRIEFER,-5750208,-10092442, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BRAIO_SPAWNEGG = ITEMS.register("braio_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.BRAIOMIL,-6124508,-13421773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BUSSER_SPAWNEGG = ITEMS.register("busser_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.BUSSER,-3724508,-23121773, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> LEAPER_SPAWNEGG = ITEMS.register("leaper_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.LEAPER,-9762718,-12345773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> SLASHER_SPAWNEGG = ITEMS.register("slasher_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.SLASHER,-8564118,-62891773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> SPITTER_SPAWNEGG = ITEMS.register("spitter_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.SPITTER,-8164818,-68590173, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> HOWLER_SPAWNEGG = ITEMS.register("howler_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.HOWLER,-32464818,-85290173, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> STALKER_SPAWNEGG = ITEMS.register("stalker_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.STALKER,-42364818,-21390173, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BRUTE_SPAWNEGG = ITEMS.register("brute_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.BRUTE,-1235818,-9423173, new Item.Properties().tab(ScreativeTab.SPORE)));


    public  static final RegistryObject<Item> SCENT_SPAWNEGG = ITEMS.register("scent_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.SCENT,-16777216, -3355648, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> CLAW_SPAWNEGG = ITEMS.register("claw_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.CLAW,-16777216, -3394816, new Item.Properties().tab(ScreativeTab.SPORE)));

    public  static final RegistryObject<Item> SCAMPER_SPAWNEGG = ITEMS.register("scamper_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.SCAMPER,-33777216, -3894816, new Item.Properties().tab(ScreativeTab.SPORE)));



    public  static final RegistryObject<Item> ACID = ITEMS.register("acid",
            () -> new Item( new Item.Properties()));
    public  static final RegistryObject<Item> ACID_BALL = ITEMS.register("acid_ball",
            () -> new Item( new Item.Properties()));


    public  static final RegistryObject<Item> SABER = ITEMS.register("saber",
            () -> new InfectedSaber());
    public  static final RegistryObject<Item> GREATSWORD = ITEMS.register("greatsword",
            () -> new InfectedGreatSword());
    public  static final RegistryObject<Item> ARMADS = ITEMS.register("armads",
            () -> new InfectedArmads());
    public  static final RegistryObject<Item> INFECTED_BOW = ITEMS.register("infected_bow",
            () -> new InfectedGreatBow( new Item.Properties().tab(ScreativeTab.SPORE).durability(SConfig.SERVER.bow_durability.get())));
    public  static final RegistryObject<Item> MAUL = ITEMS.register("maul",
            () -> new InfectedMaul());
    public  static final RegistryObject<Item> SCYTHE = ITEMS.register("scythe",
            () -> new InfectedScythe());
    public  static final RegistryObject<Item> COMBAT_SHOVEL = ITEMS.register("combat_shovel",
            () -> new InfectedCombatShovel());
    public  static final RegistryObject<Item> INFECTED_SPEAR = ITEMS.register("infected_spear",
            () -> new InfectedSpearItem(new Item.Properties().tab(ScreativeTab.SPORE).durability(SConfig.SERVER.spear_durability.get())));
    public  static final RegistryObject<Item> INFECTED_CROSSBOW = ITEMS.register("infected_crossbow",
            () -> new InfectedCrossbow());
    public  static final RegistryObject<Item> MACE = ITEMS.register("mace",
            () -> new InfectedMace());
    public  static final RegistryObject<Item> SICKLE = ITEMS.register("sickle",
            () -> new InfectedSickle());
    public  static final RegistryObject<Item> KNIFE = ITEMS.register("knife",
            () -> new InfectedKnife());
    public  static final RegistryObject<Item> RAPIER = ITEMS.register("rapier",
            () -> new InfectedRapier());

    public  static final RegistryObject<Item> INF_HELMET = ITEMS.register("inf_helmet",
            () -> new InfectedHelmet());
    public  static final RegistryObject<Item> INF_CHEST = ITEMS.register("inf_chest",
            () -> new InfectedChestplate());
    public  static final RegistryObject<Item> INF_PANTS = ITEMS.register("inf_pants",
            () -> new InfectedLeggings());
    public  static final RegistryObject<Item> INF_BOOTS = ITEMS.register("inf_boots",
            () -> new InfectedBoots());

    public  static final RegistryObject<Item> PLATED_HELMET = ITEMS.register("plated_helmet",
            () -> new PlatedHelmet());
    public  static final RegistryObject<Item> PLATED_CHEST = ITEMS.register("plated_chest",
            () -> new PlatedChestplate());
    public  static final RegistryObject<Item> PLATED_PANTS = ITEMS.register("plated_pants",
            () -> new PlatedLeggings());
    public  static final RegistryObject<Item> PLATED_BOOTS = ITEMS.register("plated_boots",
            () -> new PlatedBoots());

    public  static final RegistryObject<Item> LIVING_HELMET = ITEMS.register("living_helmet",
            () -> new LivingHelmet());
    public  static final RegistryObject<Item> LIVING_CHEST = ITEMS.register("living_chest",
            () -> new LivingChestplate());
    public  static final RegistryObject<Item> LIVING_PANTS = ITEMS.register("living_pants",
            () -> new LivingLeggings());
    public  static final RegistryObject<Item> LIVING_BOOTS = ITEMS.register("living_boots",
            () -> new LivingBoots());
    public  static final RegistryObject<Item> R_ELYTRON = ITEMS.register("r_elytron", ClientModEvents.getElytraItem());

    public  static final RegistryObject<Item> INF_UP_CHESTPLATE = ITEMS.register("inf_up_chest",
            () -> new UpgradedInfectedExoskeleton.InfectedUpChestplate());

    public  static final RegistryObject<Item> BIOMASS = ITEMS.register("biomass",
            () -> new Biomass());
    public  static final RegistryObject<Item> AMETHYST_DUST = ITEMS.register("amethyst_dust",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE_T)));
    public  static final RegistryObject<Item> COMPOUND = ITEMS.register("compound",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE_T)));
    public  static final RegistryObject<Item> COMPOUND_PLATE = ITEMS.register("compound_plate",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE_T)));
}
