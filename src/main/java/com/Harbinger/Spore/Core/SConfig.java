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



        public final ForgeConfigSpec.ConfigValue<Boolean> scent_spawn;
        public final ForgeConfigSpec.ConfigValue<Boolean> scent_particles;
        public final ForgeConfigSpec.ConfigValue<Integer> scent_life;

        public final ForgeConfigSpec.ConfigValue<Double> inf_human_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_human_damage;

        public final ForgeConfigSpec.ConfigValue<Double> inf_vil_hp;
        public final ForgeConfigSpec.ConfigValue<Double> inf_vil_damage;

        public final ForgeConfigSpec.ConfigValue<Double> inf_witch_hp;

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

        public final ForgeConfigSpec.ConfigValue<Double> leap_hp;
        public final ForgeConfigSpec.ConfigValue<Double> leap_damage;
        public final ForgeConfigSpec.ConfigValue<Double> leap_armor;

        public final ForgeConfigSpec.ConfigValue<Double> sla_hp;
        public final ForgeConfigSpec.ConfigValue<Double> sla_damage;
        public final ForgeConfigSpec.ConfigValue<Double> sla_armor;

        public final ForgeConfigSpec.ConfigValue<Double> spit_hp;
        public final ForgeConfigSpec.ConfigValue<Double> spit_armor;
        public final ForgeConfigSpec.ConfigValue<Double> spit_damage_l;
        public final ForgeConfigSpec.ConfigValue<Double> spit_damage_c;

        public final ForgeConfigSpec.ConfigValue<Integer> evolution_age_human;


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

        public final ForgeConfigSpec.ConfigValue<Integer> bow_durability;
        public final ForgeConfigSpec.ConfigValue<Double> bow_arrow_damage_multiplier;
        public final ForgeConfigSpec.ConfigValue<Integer> bow_melee_damage;
        public final ForgeConfigSpec.ConfigValue<Double> bow_swing_speed;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> stations;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("Global Variables for Mobs");
            this.global_damage = builder.define("Global Damage Modifier",1.0);
            this.global_health = builder.define("Global Health Modifier",1.0);
            this.global_armor = builder.define("Global Health Modifier",1.0);
            builder.pop();


            builder.push("Mobs");

            builder.push("Scent");
            this.scent_spawn = builder.comment("Default true").define("Should scent spawn?",true);
            this.scent_particles = builder.comment("Default true").define("Should scent have particles?",true);
            this.scent_life = builder.comment("Default 6000").define("Scent life",6000);
            builder.pop();

            builder.push("Infected Villager");
            this.inf_vil_hp = builder.comment("Default 20").defineInRange("Sets Infected Human Max health", 20, 1, Double.MAX_VALUE);
            this.inf_vil_damage = builder.comment("Default 6").defineInRange("Sets Infected Human Damage", 6, 1, Double.MAX_VALUE);
            this.stations = builder.defineList("Villager Work Stations", Lists.newArrayList("work in progress" ) , o -> o instanceof String);
            builder.pop();

            builder.push("Infected Witch");
            this.inf_witch_hp = builder.comment("Default 25").defineInRange("Sets Infected Witch Max health", 25, 1, Double.MAX_VALUE);
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
            this.evolution_age_human = builder.comment("Default 300").define("Evolution Timer in seconds",300);
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


            builder.push("Weapons and Tools");
            builder.push("Saber").comment("Keep the values whole , values like 6.5 might crash");
            this.saber_durability = builder.comment("Default 1000").define("Durability",1000);
            this.saber_damage = builder.comment("Default 8").defineInRange("Damage", 8, 1, Integer.MAX_VALUE);
            this.saber_swing = builder.comment("Default 2").define("Swing",2);
            builder.pop();
            builder.push("GreatSword");
            this.greatsword_durability = builder.comment("Default 2000").define("Durability",2000);
            this.greatsword_damage = builder.comment("Default 12").defineInRange("Damage", 12, 1, Integer.MAX_VALUE);
            this.greatsword_swing = builder.comment("Default 3").define("Swing",3);
            builder.pop();
            builder.push("Bow");
            this.bow_durability = builder.comment("Default 700").define("Bow Durability",700);
            this.bow_arrow_damage_multiplier = builder.comment("Default 1.0").define("Bow Range Damage",1.0);
            this.bow_melee_damage = builder.comment("Default 5").defineInRange("Damage", 5, 1, Integer.MAX_VALUE);
            this.bow_swing_speed = builder.comment("Default -2.4").define("Bow Swing",-2.4);
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
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
                .writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
