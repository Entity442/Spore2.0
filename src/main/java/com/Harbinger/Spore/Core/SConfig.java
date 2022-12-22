package com.Harbinger.Spore.Core;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.List;

public class SConfig {
    public static class Server {

        public final ForgeConfigSpec.ConfigValue<Double> global_damage;
        public final ForgeConfigSpec.ConfigValue<Double> global_health;
        public final ForgeConfigSpec.ConfigValue<Double> global_armor;
        public final ForgeConfigSpec.ConfigValue<Boolean> at_mob;
        public final ForgeConfigSpec.ConfigValue<Boolean> at_an;


        public final ForgeConfigSpec.ConfigValue<Boolean> scent_spawn;
        public final ForgeConfigSpec.ConfigValue<Boolean> scent_summon;
        public final ForgeConfigSpec.ConfigValue<Integer> scent_summon_cooldown;
        public final ForgeConfigSpec.ConfigValue<Boolean> scent_particles;
        public final ForgeConfigSpec.ConfigValue<Integer> scent_life;

        public final ForgeConfigSpec.ConfigValue<Double> inf_human_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_human_damage;
        public final ForgeConfigSpec.ConfigValue<Double> inf_human_armor;

        public final ForgeConfigSpec.ConfigValue<Double> inf_vil_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_vil_damage;
        public final ForgeConfigSpec.ConfigValue<Double> inf_vil_armor;

        public final ForgeConfigSpec.ConfigValue<Double> inf_van_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_van_damage;
        public final ForgeConfigSpec.ConfigValue<Double> inf_van_armor;

        public final ForgeConfigSpec.ConfigValue<Double> inf_vin_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_vin_damage;
        public final ForgeConfigSpec.ConfigValue<Double> inf_vin_armor;

        public final ForgeConfigSpec.ConfigValue<Double> inf_pil_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_pil_damage;
        public final ForgeConfigSpec.ConfigValue<Double> inf_pil_armor;
        public final ForgeConfigSpec.ConfigValue<Float> inf_pil_range_damage;

        public final ForgeConfigSpec.ConfigValue<Double> inf_evo_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_evo_damage;
        public final ForgeConfigSpec.ConfigValue<Double> inf_evo_armor;
        public final ForgeConfigSpec.ConfigValue<Double> inf_claw_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_claw_damage;
        public final ForgeConfigSpec.ConfigValue<Double> inf_claw_armor;

        public final ForgeConfigSpec.ConfigValue<Double> inf_witch_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_witch_armor;

        public final ForgeConfigSpec.ConfigValue<Double> braio_armor;
        public final ForgeConfigSpec.ConfigValue<Double> braio_hp;
        public final ForgeConfigSpec.ConfigValue<Integer> poison_level;
        public final ForgeConfigSpec.ConfigValue<Integer> poison_duration;
        public final ForgeConfigSpec.ConfigValue<Integer> mycelium_level;
        public final ForgeConfigSpec.ConfigValue<Integer> mycelium_duration;
        public final ForgeConfigSpec.ConfigValue<Integer> marker_level;
        public final ForgeConfigSpec.ConfigValue<Integer> marker_duration;



        public final ForgeConfigSpec.ConfigValue<Double> griefer_armor;
        public final ForgeConfigSpec.ConfigValue<Double> griefer_hp;
        public final ForgeConfigSpec.ConfigValue<Double> griefer_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> explosion;
        public final ForgeConfigSpec.ConfigValue<Boolean> explosion_on;

        public final ForgeConfigSpec.ConfigValue<Double> knight_hp;
        public final ForgeConfigSpec.ConfigValue<Double> knight_damage;
        public final ForgeConfigSpec.ConfigValue<Double> knight_armor;

        public final ForgeConfigSpec.ConfigValue<Double> stalker_hp;
        public final ForgeConfigSpec.ConfigValue<Double> stalker_damage;
        public final ForgeConfigSpec.ConfigValue<Double> stalker_armor;

        public final ForgeConfigSpec.ConfigValue<Double> brute_hp;
        public final ForgeConfigSpec.ConfigValue<Double> brute_damage;
        public final ForgeConfigSpec.ConfigValue<Double> brute_armor;

        public final ForgeConfigSpec.ConfigValue<Double> leap_hp;
        public final ForgeConfigSpec.ConfigValue<Double> leap_damage;
        public final ForgeConfigSpec.ConfigValue<Double> leap_armor;

        public final ForgeConfigSpec.ConfigValue<Double> sla_hp;
        public final ForgeConfigSpec.ConfigValue<Double> sla_damage;
        public final ForgeConfigSpec.ConfigValue<Double> sla_armor;

        public final ForgeConfigSpec.ConfigValue<Double> how_hp;
        public final ForgeConfigSpec.ConfigValue<Double> how_damage;
        public final ForgeConfigSpec.ConfigValue<Double> how_armor;

        public final ForgeConfigSpec.ConfigValue<Double> spit_hp;
        public final ForgeConfigSpec.ConfigValue<Double> spit_armor;
        public final ForgeConfigSpec.ConfigValue<Double> spit_damage_l;
        public final ForgeConfigSpec.ConfigValue<Double> spit_damage_c;

        public final ForgeConfigSpec.ConfigValue<Integer> evolution_age_human;

        public final ForgeConfigSpec.ConfigValue<Integer> spear_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> spear_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> spear_swing;
        public final ForgeConfigSpec.ConfigValue<Integer> spear_range;

        public final ForgeConfigSpec.ConfigValue<Integer> saber_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> saber_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> saber_swing;

        public final ForgeConfigSpec.ConfigValue<Integer> greatsword_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> greatsword_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> greatsword_swing;

        public final ForgeConfigSpec.ConfigValue<Integer> armads_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> armads_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> armads_swing;

        public final ForgeConfigSpec.ConfigValue<Integer> maul_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> maul_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> maul_swing;

        public final ForgeConfigSpec.ConfigValue<Integer> scythe_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> scythe_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> scythe_swing;

        public final ForgeConfigSpec.ConfigValue<Integer> shovel_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> shovel_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> shovel_swing;

        public final ForgeConfigSpec.ConfigValue<Integer> crossbow_durability;
        public final ForgeConfigSpec.ConfigValue<Double> crossbow_arrow_damage_multiplier;

        public final ForgeConfigSpec.ConfigValue<Integer> bow_durability;
        public final ForgeConfigSpec.ConfigValue<Double> bow_arrow_damage_multiplier;
        public final ForgeConfigSpec.ConfigValue<Integer> bow_melee_damage;
        public final ForgeConfigSpec.ConfigValue<Double> bow_swing_speed;

        public final ForgeConfigSpec.ConfigValue<Integer> helmet_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> chestplate_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> pants_durability;
        public final ForgeConfigSpec.ConfigValue<Integer> boots_durability;

        public final ForgeConfigSpec.ConfigValue<Integer> helmet_protection;
        public final ForgeConfigSpec.ConfigValue<Integer> chestplate_protection;
        public final ForgeConfigSpec.ConfigValue<Integer> pants_protection;
        public final ForgeConfigSpec.ConfigValue<Integer> boots_protection;

        public final ForgeConfigSpec.ConfigValue<Integer> armor_toughness;

        public final ForgeConfigSpec.ConfigValue<Integer> knockback_resistance;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> human_ev;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> villager_ev;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> pil_ev;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_summon;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> howler_summon;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklist;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> flee;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> attack;


        public final ForgeConfigSpec.ConfigValue<List<? extends String>> howler_effects_buff;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> mycelium;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> corrosion;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_human_conv;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_villager_conv;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_witch_conv;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_pillager_conv;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_evoker_conv;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_vindi_conv;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> inf_van_conv;

        public Server(ForgeConfigSpec.Builder builder) {

            builder.push("Global Variables");
            this.global_damage = builder.define("Global Damage Modifier",1.0);
            this.global_health = builder.define("Global Health Modifier",1.0);
            this.global_armor = builder.define("Global Armor Modifier",1.0);
            this.at_mob = builder.comment("Default true").define("Should attack other mobs?",true);
            this.at_an = builder.comment("Default true").define("Should attack Animals?",false);

            this.blacklist = builder.defineList("Mobs Not Targeted",
                    Lists.newArrayList(
                            "minecraft:creeper") , o -> o instanceof String);

            this.attack = builder.defineList("Mobs that will target infected",
                    Lists.newArrayList(
                            "minecraft:iron_golem",
                                     "guardvillagers:guard") , o -> o instanceof String);

            this.flee = builder.defineList("Mobs that will run from infected",
                    Lists.newArrayList(
                            "minecraft:villager",
                                     "minecraft:wandering_trader") , o -> o instanceof String);
            builder.pop();





            builder.push("Mob Evolutions and Infection System");
            builder.push("Evolutions");
            this.human_ev = builder.defineList("Infected Human Evolutions",
                    Lists.newArrayList(
                            "spore:knight"
                            ,"spore:griefer"
                            ,"spore:braiomil" ) , o -> o instanceof String);

            this.villager_ev = builder.defineList("Infected Villager Evolutions",
                    Lists.newArrayList(
                            "spore:slasher"
                            ,"spore:leaper"
                            ,"spore:spitter" ) , o -> o instanceof String);
            this.pil_ev = builder.defineList("Infected Pillager Evolutions",
                    Lists.newArrayList(
                            "spore:howler",
                                     "spore:stalker",
                                     "spore:brute") , o -> o instanceof String);

            this.evolution_age_human = builder.comment("Default 150").define("Evolution Timer in seconds",150);

            builder.pop();
            builder.push("Infections");
            this.inf_human_conv = builder.defineList("Mobs that can become infected humans",
                    Lists.newArrayList(
                            "minecraft:zombie"
                            ,"minecraft:husk"
                            ,"minecraft:drowned" ) , o -> o instanceof String);
            this.inf_villager_conv = builder.defineList("Mobs that can become infected villagers",
                    Lists.newArrayList(
                            "minecraft:villager"
                            ,"minecraft:zombie_villager",
                            "guardvillagers:guard") , o -> o instanceof String);
            this.inf_witch_conv = builder.defineList("Mobs that can become infected witches",
                    Lists.newArrayList(
                            "minecraft:witch" ) , o -> o instanceof String);
            this.inf_evoker_conv = builder.defineList("Mobs that can become infected evokers",
                    Lists.newArrayList(
                            "minecraft:evoker") , o -> o instanceof String);
            this.inf_pillager_conv = builder.defineList("Mobs that can become infected pillagers",
                    Lists.newArrayList(
                            "minecraft:pillager" ) , o -> o instanceof String);
            this.inf_vindi_conv = builder.defineList("Mobs that can become infected vindicators",
                    Lists.newArrayList(
                            "minecraft:vindicator","hunterillager:hunterillager" ) , o -> o instanceof String);
            this.inf_van_conv = builder.defineList("Mobs that can become infected wandering traders",
                    Lists.newArrayList(
                            "minecraft:wandering_trader" ) , o -> o instanceof String);

            builder.pop();
            builder.pop();


            builder.push("Effects");
            this.mycelium = builder.defineList("Mobs that are immune to the mycelium infection",
                    Lists.newArrayList(
                            "minecraft:ghast"
                            , "minecraft:iron_golem"
                            , "minecraft:magma_cube"
                            , "minecraft:mooshroom"
                            , "minecraft:phantom"
                            , "minecraft:skeleton_horse"
                            , "minecraft:snow_golem"
                            , "minecraft:stray"
                            , "minecraft:wither"
                            , "minecraft:skeleton" ) , o -> o instanceof String);

            this.corrosion = builder.defineList("Mobs that are damaged by corrosion",
                    Lists.newArrayList("minecraft:iron_golem" ) , o -> o instanceof String);
            builder.pop();

            builder.push("Mobs");
            builder.push("Infected Evoker");
            this.inf_evo_hp = builder.comment("Default 35").defineInRange("Sets Infected Evoker Max health", 35, 1, Double.MAX_VALUE);
            this.inf_evo_damage = builder.comment("Default 7").defineInRange("Sets Infected Evoker Damage", 7, 1, Double.MAX_VALUE);
            this.inf_evo_armor = builder.comment("Default 4").defineInRange("Sets Infected Evoker Armor", 4, 1, Double.MAX_VALUE);
            this.inf_claw_hp = builder.comment("Default 15").defineInRange("Sets Infected Claw Max health", 15, 1, Double.MAX_VALUE);
            this.inf_claw_damage = builder.comment("Default 7").defineInRange("Sets Infected Claw Damage", 7, 1, Double.MAX_VALUE);
            this.inf_claw_armor = builder.comment("Default 3").defineInRange("Sets Infected Claw Armor", 3, 1, Double.MAX_VALUE);
            builder.pop();

            builder.push("Infected Vindicator");
            this.inf_vin_hp = builder.comment("Default 28").defineInRange("Sets Infected Vindicator Max health", 28, 1, Double.MAX_VALUE);
            this.inf_vin_damage = builder.comment("Default 7").defineInRange("Sets Infected Vindicator Damage", 7, 1, Double.MAX_VALUE);
            this.inf_vin_armor = builder.comment("Default 4").defineInRange("Sets Infected Vindicator Armor", 4, 1, Double.MAX_VALUE);
            builder.pop();

            builder.push("Brute");
            this.brute_hp = builder.comment("Default 70").defineInRange("Sets Brute Max health", 70, 1, Double.MAX_VALUE);
            this.brute_damage = builder.comment("Default 7").defineInRange("Sets Brute Damage", 7, 1, Double.MAX_VALUE);
            this.brute_armor = builder.comment("Default 15").defineInRange("Sets Brute Armor", 15, 1, Double.MAX_VALUE);
            builder.pop();

            builder.push("Howler");
            this.how_hp = builder.comment("Default 32").defineInRange("Sets Howler Max health", 32, 1, Double.MAX_VALUE);
            this.how_damage = builder.comment("Default 5").defineInRange("Sets Howler Damage", 5, 1, Double.MAX_VALUE);
            this.how_armor = builder.comment("Default 4").defineInRange("Sets Howler Armor", 4, 1, Double.MAX_VALUE);
            this.howler_effects_buff = builder.defineList("Howler buff effect list",
                    Lists.newArrayList("minecraft:regeneration","minecraft:speed","minecraft:health_boost","minecraft:strength","minecraft:resistance" ) , o -> o instanceof String);
            this.howler_summon = builder.defineList("Mobs that can be summoned by the Howler",
                    Lists.newArrayList("spore:inf_human","spore:inf_villager","spore:inf_pillager","spore:knight" ) , o -> o instanceof String);
            builder.pop();

            builder.push("Infected Pillager");
            this.inf_pil_hp = builder.comment("Default 20").defineInRange("Sets Infected Pillager Max health", 20, 1, Double.MAX_VALUE);
            this.inf_pil_damage = builder.comment("Default 6").defineInRange("Sets Infected Pillager Damage", 6, 1, Double.MAX_VALUE);
            this.inf_pil_armor = builder.comment("Default 2").defineInRange("Sets Infected Pillager Armor", 2, 1, Double.MAX_VALUE);
            this.inf_pil_range_damage = builder.comment("Default 1.6f").define("Sets Infected Pillager Range Damage",1.6f);

            builder.pop();

            builder.push("Scent");
            this.scent_spawn = builder.comment("Default true").define("Should scent spawn?",true);
            this.scent_particles = builder.comment("Default true").define("Should scent have particles?",true);
            this.scent_life = builder.comment("Default 6000").define("Scent life",6000);
            this.inf_summon = builder.defineList("Mobs that can be summoned by the Scent",
                    Lists.newArrayList(
                            "spore:inf_human"
                            ,"spore:inf_villager"
                            ,"spore:inf_pillager" ) , o -> o instanceof String);
            this.scent_summon = builder.comment("Default true").define("Should scent summon entities?",true);
            this.scent_summon_cooldown = builder.comment("Default 300").define("The average amount of time it will take for a mob to be summoned",300);
            builder.pop();

            builder.push("Infected Villager");
            this.inf_vil_hp = builder.comment("Default 20").defineInRange("Sets Infected Villager Max health", 20, 1, Double.MAX_VALUE);
            this.inf_vil_damage = builder.comment("Default 6").defineInRange("Sets Infected Villager Damage", 6, 1, Double.MAX_VALUE);
            this.inf_vil_armor = builder.comment("Default 1").defineInRange("Sets Infected Villager Armor", 1, 0, Double.MAX_VALUE);
            builder.pop();

            builder.push("Stalker");
            this.stalker_hp = builder.comment("Default 35").defineInRange("Sets Stalker Max health", 35, 1, Double.MAX_VALUE);
            this.stalker_damage = builder.comment("Default 10").defineInRange("Sets Stalker Damage", 10, 1, Double.MAX_VALUE);
            this.stalker_armor = builder.comment("Default 3").defineInRange("Sets Stalker Armor", 3, 0, Double.MAX_VALUE);
            builder.pop();

            builder.push("Infected Wandering Trader");
            this.inf_van_hp = builder.comment("Default 20").defineInRange("Sets Infected Wandering Trader Max health", 20, 1, Double.MAX_VALUE);
            this.inf_van_damage = builder.comment("Default 6").defineInRange("Sets Infected Wandering Trader Damage", 6, 1, Double.MAX_VALUE);
            this.inf_van_armor = builder.comment("Default 1").defineInRange("Sets Infected Wandering Trader Armor", 1, 0, Double.MAX_VALUE);
            builder.pop();

            builder.push("Infected Witch");
            this.inf_witch_hp = builder.comment("Default 25").defineInRange("Sets Infected Witch Max health", 25, 1, Double.MAX_VALUE);
            this.inf_witch_armor = builder.comment("Default 1").defineInRange("Sets Infected Witch Armor", 1, 0, Double.MAX_VALUE);
            builder.pop();

            builder.push("Braiomil");
            this.braio_hp = builder.comment("Default 25").defineInRange("Sets Braiomil Max health", 25, 1, Double.MAX_VALUE);
            this.braio_armor = builder.comment("Default 4").defineInRange("Sets Braiomil armor", 4, 1, Double.MAX_VALUE);
            this.poison_duration = builder.comment("Default 120").defineInRange("Poison duration", 120, 20, Integer.MAX_VALUE);
            this.poison_level = builder.comment("Default 0").defineInRange("Poison level", 0, 0, Integer.MAX_VALUE);
            this.mycelium_duration = builder.comment("Default 600").defineInRange("Mycelium Duration", 600, 20, Integer.MAX_VALUE);
            this.mycelium_level = builder.comment("Default 0").defineInRange("Mycelium level", 0, 0, Integer.MAX_VALUE);
            this.marker_duration = builder.comment("Default 2400").defineInRange("Marker Duration", 2400, 20, Integer.MAX_VALUE);
            this.marker_level = builder.comment("Default 1").defineInRange("Marker Level", 1, 0, Integer.MAX_VALUE);
            builder.pop();


            builder.push("Infected Human");
            this.inf_human_hp = builder.comment("Default 15").defineInRange("Sets Infected Human Max health", 15, 1, Double.MAX_VALUE);
            this.inf_human_damage = builder.comment("Default 6").defineInRange("Sets Infected Human Damage", 6, 1, Double.MAX_VALUE);
            this.inf_human_armor = builder.comment("Default 1").defineInRange("Sets Infected Human Armor", 1, 0, Double.MAX_VALUE);
            builder.pop();
            builder.push("Knight");
            this.knight_hp = builder.comment("Default 25").defineInRange("Sets Knight Max health", 25, 1, Double.MAX_VALUE);
            this.knight_damage = builder.comment("Default 7").defineInRange("Sets Knight Damage", 7, 1, Double.MAX_VALUE);
            this.knight_armor = builder.comment("Default 7").defineInRange("Sets Knight Armor", 7, 1, Double.MAX_VALUE);
            builder.pop();
            builder.push("Leaper");
            this.leap_hp = builder.comment("Default 55").defineInRange("Sets Leaper Max health", 55, 1, Double.MAX_VALUE);
            this.leap_damage = builder.comment("Default 8").defineInRange("Sets Leaper Damage", 8, 1, Double.MAX_VALUE);
            this.leap_armor = builder.comment("Default 3").defineInRange("Sets Leaper Armor", 3, 1, Double.MAX_VALUE);
            builder.pop();

            builder.push("Slasher");
            this.sla_hp = builder.comment("Default 35").defineInRange("Sets Slasher Max health", 35, 1, Double.MAX_VALUE);
            this.sla_damage = builder.comment("Default 10").defineInRange("Sets Slasher Damage", 10, 1, Double.MAX_VALUE);
            this.sla_armor = builder.comment("Default 2").defineInRange("Sets Slasher Armor", 2, 1, Double.MAX_VALUE);
            builder.pop();

            builder.push("Spitter");
            this.spit_hp = builder.comment("Default 15").defineInRange("Sets Spiter Max health", 15, 1, Double.MAX_VALUE);
            this.spit_armor = builder.comment("Default 1").defineInRange("Sets Spitter Armor", 1, 1, Double.MAX_VALUE);
            this.spit_damage_l = builder.comment("Default 6").defineInRange("Sets Spitter Damage at long range", 6, 1, Double.MAX_VALUE);
            this.spit_damage_c = builder.comment("Default 1").defineInRange("Sets Spitter Damage at close range", 1, 1, Double.MAX_VALUE);
            builder.pop();

            builder.push("Griefer");
            this.griefer_armor = builder.comment("Default 3").defineInRange("Sets Griefer Armor", 3, 1, Double.MAX_VALUE);
            this.griefer_hp = builder.comment("Default 45").defineInRange("Sets Griefer Max health", 45, 1, Double.MAX_VALUE);
            this.griefer_damage = builder.comment("Default 5").defineInRange("Sets Griefer Damage", 5, 1, Double.MAX_VALUE);
            this.explosion = builder.comment("Default 2").define("Explosion Radius",2);
            this.explosion_on = builder.comment("Default true").define("Should explosion break blocks ?",true);
            builder.pop();
            builder.pop();


            builder.push("Weapons and Tools OwO");
            builder.push("Spear");
            this.spear_durability = builder.comment("Default 800").define("Durability",800);
            this.spear_damage = builder.comment("Default 11").defineInRange("Melee Damage", 11, 1, Integer.MAX_VALUE);
            this.spear_swing = builder.comment("Default 3").define("Swing",3);
            this.spear_range = builder.comment("Default 12").define("Ranged Damage",12);
            builder.pop();
            builder.push("Saber");
            this.saber_durability = builder.comment("Default 1000").define("Durability",1000);
            this.saber_damage = builder.comment("Default 8").defineInRange("Damage", 8, 1, Integer.MAX_VALUE);
            this.saber_swing = builder.comment("Default 2").define("Swing",2);
            builder.pop();
            builder.push("GreatSword");
            this.greatsword_durability = builder.comment("Default 2000").define("Durability",2000);
            this.greatsword_damage = builder.comment("Default 12").defineInRange("Damage", 12, 1, Integer.MAX_VALUE);
            this.greatsword_swing = builder.comment("Default 3").define("Swing",3);
            builder.pop();
            builder.push("Crossbow");
            this.crossbow_durability = builder.comment("Default 700").define("Crossbow Durability",700);
            this.crossbow_arrow_damage_multiplier = builder.comment("Default 1.0").define("Crossbow Range Damage Modifier",1.0);
             builder.pop();
            builder.push("Bow");
            this.bow_durability = builder.comment("Default 700").define("Bow Durability",700);
            this.bow_arrow_damage_multiplier = builder.comment("Default 1.0").define("Bow Range Damage Modifier",1.0);
            this.bow_melee_damage = builder.comment("Default 5").defineInRange("Damage", 5, 1, Integer.MAX_VALUE);
            this.bow_swing_speed = builder.comment("Default 2.4").define("Bow Swing",2.4);
            builder.pop();
            builder.push("Armads");
            this.armads_durability = builder.comment("Default 2500").define("Durability",2500);
            this.armads_damage = builder.comment("Default 15").defineInRange("Damage", 15, 1, Integer.MAX_VALUE);
            this.armads_swing = builder.comment("Default 3").define("Swing",3);
            builder.pop();
            builder.push("Maul");
            this.maul_durability = builder.comment("Default 1500").define("Durability",1500);
            this.maul_damage = builder.comment("Default 7").defineInRange("Damage", 7, 1, Integer.MAX_VALUE);
            this.maul_swing = builder.comment("Default 3").define("Swing",3);
            builder.pop();
            builder.push("Scythe");
            this.scythe_durability = builder.comment("Default 1200").define("Durability",1200);
            this.scythe_damage = builder.comment("Default 8").defineInRange("Damage", 8, 1, Integer.MAX_VALUE);
            this.scythe_swing = builder.comment("Default 3").define("Swing",3);
            builder.pop();
            builder.push("Combat Shovel");
            this.shovel_durability = builder.comment("Default 1400").define("Durability",1400);
            this.shovel_damage = builder.comment("Default 6").defineInRange("Damage", 6, 1, Integer.MAX_VALUE);
            this.shovel_swing = builder.comment("Default 3").define("Swing",3);
            builder.pop();
            builder.pop();
            builder.push("Armor");
            builder.push("Helmet");
            this.helmet_durability = builder.comment("Default 350").defineInRange("Helmet Durability", 350, 1, Integer.MAX_VALUE);
            this.helmet_protection = builder.comment("Default 3").defineInRange("Helmet Protection", 3, 1, Integer.MAX_VALUE);
            builder.pop();
            builder.push("Chestplate");
            this.chestplate_durability = builder.comment("Default 500").defineInRange("Chestplate Durability", 500, 1, Integer.MAX_VALUE);
            this.chestplate_protection = builder.comment("Default 8").defineInRange("Chestplate Protection", 8, 1, Integer.MAX_VALUE);
            builder.pop();
            builder.push("Leggings");
            this.pants_durability = builder.comment("Default 400").defineInRange("Leggings Durability", 400, 1, Integer.MAX_VALUE);
            this.pants_protection = builder.comment("Default 7").defineInRange("Leggings Protection", 7, 1, Integer.MAX_VALUE);
            builder.pop();
            builder.push("Boots");
            this.boots_durability = builder.comment("Default 350").defineInRange("Boots Durability", 350, 1, Integer.MAX_VALUE);
            this.boots_protection = builder.comment("Default 3").defineInRange("Boots Protection", 3, 1, Integer.MAX_VALUE);
            builder.pop();
            this.armor_toughness = builder.comment("Default 1").defineInRange("Armor Toughness", 1, 0, Integer.MAX_VALUE);
            this.knockback_resistance = builder.comment("Default 0").defineInRange("Armor Knockback Resistance", 0, 0, Integer.MAX_VALUE);
            builder.pop();
        }
    }
    public static final Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    static {
        Pair<Server, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER = commonSpecPair.getLeft();
        SERVER_SPEC = commonSpecPair.getRight();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().build();
        file.load();
        config.setConfig(file);
    }
}
