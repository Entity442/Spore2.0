package com.Harbinger.Spore.Core;

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
    public  static final RegistryObject<Item> FLESHY_BONE = ITEMS.register("fleshy_bone",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> HARDENED_BIND = ITEMS.register("hardened_bind",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> FLESHY_CLAW = ITEMS.register("fleshy_claw",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> LIVING_CORE = ITEMS.register("living_core",
            () -> new Item( new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BIOMASS = ITEMS.register("biomass",
            () -> new Biomass());


    public  static final RegistryObject<Item> INFECTED_HUMAN_SPAWNEGG = ITEMS.register("infected_human_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INFECTED_HUMAN,-6750208, -16724788, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> KNIGHT_SPAWNEGG = ITEMS.register("knight_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.KNIGHT,-6750208, -16777012, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> GRIEFER_SPAWNEGG = ITEMS.register("griefer_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.GRIEFER,-6750208,-10092442, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> BRAIO_SPAWNEGG = ITEMS.register("braio_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.BRAIOMIL,-6750208,-13421773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_VILLAGER_SPAWNEGG = ITEMS.register("inf_villager_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_VILLAGER,-6639718,-97141773, new Item.Properties().tab(ScreativeTab.SPORE)));
    public  static final RegistryObject<Item> INF_WITCH_SPAWNEGG = ITEMS.register("inf_witch_spawnegg",
            () -> new ForgeSpawnEggItem(Sentities.INF_WITCH,-8512718,-95711773, new Item.Properties().tab(ScreativeTab.SPORE)));


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

}
