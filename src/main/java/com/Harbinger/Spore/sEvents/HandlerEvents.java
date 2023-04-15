package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.AI.LocHiv.FollowOthersGoal;
import com.Harbinger.Spore.Sentities.*;
import com.Harbinger.Spore.Sentities.Utility.Mound;
import com.Harbinger.Spore.Sentities.Utility.Proto;
import com.Harbinger.Spore.Sentities.Utility.UtilityEntity;
import com.Harbinger.Spore.Spore;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(modid = Spore.MODID)
public class HandlerEvents {
    @SubscribeEvent
    public static void onLivingSpawned(EntityJoinLevelEvent event) {
        if (event != null && event.getEntity() != null) {
            if (event.getEntity() instanceof PathfinderMob mob){
            if (SConfig.SERVER.attack.get().contains(event.getEntity().getEncodeId())) {
                mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Infected.class, false));
            }

            if (SConfig.SERVER.flee.get().contains(event.getEntity().getEncodeId())) {
                mob.goalSelector.addGoal(4, new AvoidEntityGoal<>(mob, Infected.class, 6.0F, 1.0D, 0.9D));
                mob.goalSelector.addGoal(4, new AvoidEntityGoal<>(mob, UtilityEntity.class, 8.0F, 1.0D, 0.9D));
            }

            if (SConfig.SERVER.basic.get().contains(event.getEntity().getEncodeId())) {
                mob.goalSelector.addGoal(8, new FollowOthersGoal(mob, 0.8, PathfinderMob.class, entity -> {
                    return SConfig.SERVER.evolved.get().contains(event.getEntity().getEncodeId());
                }));
            }
            if (SConfig.SERVER.ranged.get().contains(event.getEntity().getEncodeId())) {
                mob.goalSelector.addGoal(6, new FollowOthersGoal(mob, 0.8, PathfinderMob.class, entity -> {
                    return entity instanceof Carrier;
                }));
            }
            if (SConfig.SERVER.can_be_carried.get().contains(event.getEntity().getEncodeId())) {
                mob.goalSelector.addGoal(7, new FollowOthersGoal(mob, 0.8, PathfinderMob.class, entity -> {
                    return entity instanceof Carrier;
                }));
            }
            if (SConfig.SERVER.support.get().contains(event.getEntity().getEncodeId())) {
                mob.goalSelector.addGoal(6, new FollowOthersGoal(mob, 0.8, PathfinderMob.class, entity -> {
                    return
                            SConfig.SERVER.evolved.get().contains(event.getEntity().getEncodeId());
                }));
            }}
        }
    }


    @SubscribeEvent
    public static  void Command(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal("spore:set_area")
        .executes(arguments -> {
            ServerLevel world = arguments.getSource().getLevel();
            double x = arguments.getSource().getPosition().x();
            double y = arguments.getSource().getPosition().y();
            double z = arguments.getSource().getPosition().z();
            Entity entity = arguments.getSource().getEntity();
            if (entity == null)
                entity = FakePlayerFactory.getMinecraft(world);
             if (entity != null){
                 AABB hitbox = entity.getBoundingBox().inflate(20);
                 List<Entity> entities = entity.level.getEntities(entity, hitbox);
                 for (Entity entity1 : entities) {
                     if(entity1 instanceof Infected infected) {
                         BlockPos pos = new BlockPos(x ,y,z);
                         infected.setSearchPos(pos);
                     }
                 }
             }
            return 0;
        }));
        event.getDispatcher().register(Commands.literal("spore:check_entity")
                .executes(arguments -> {
                    ServerLevel world = arguments.getSource().getLevel();
                    Entity entity = arguments.getSource().getEntity();
                    if (entity == null)
                        entity = FakePlayerFactory.getMinecraft(world);
                    if (entity != null){
                        AABB hitbox = entity.getBoundingBox().inflate(5);
                        List<Entity> entities = entity.level.getEntities(entity, hitbox);
                        for (Entity entity1 : entities) {
                            if(entity1 instanceof Infected infected) {
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ infected.getEncodeId() + " " + infected.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + infected.getHealth()),false);
                                    player.displayClientMessage(Component.literal("Kills " + infected.getKills()),false);
                                    player.displayClientMessage(Component.literal("Position to be Searched " + infected.getSearchPos()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + infected.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("Ticks until evolution: " + infected.getEvolutionCoolDown() + "/" + (SConfig.SERVER.evolution_age_human.get() * 20)),false);
                                    player.displayClientMessage(Component.literal("Ticks until starvation: " + infected.getHunger() + "/0 , hunger value " + (SConfig.SERVER.hunger.get())),false);
                                    player.displayClientMessage(Component.literal("Is Linked ? " + infected.getLinked()),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);

                                }
                            }
                        }
                    }
                    return 0;
                }));

        event.getDispatcher().register(Commands.literal("spore:check_hive")
                .executes(arguments -> {
                    ServerLevel world = arguments.getSource().getLevel();
                    Entity entity = arguments.getSource().getEntity();
                    if (entity == null)
                        entity = FakePlayerFactory.getMinecraft(world);
                    if (entity != null){
                        AABB hitbox = entity.getBoundingBox().inflate(5);
                        List<Entity> entities = entity.level.getEntities(entity, hitbox);
                        for (Entity entity1 : entities) {
                            if(entity1 instanceof Proto proto) {
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ proto.getEncodeId() + " " + proto.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + proto.getHealth()),false);
                                    player.displayClientMessage(Component.literal("Biomass " + proto.getBiomass()),false);
                                    player.displayClientMessage(Component.literal("Alert " + proto.getAlert()),false);
                                    player.displayClientMessage(Component.literal("Last Signaled Position " + proto.getSignal()),false);
                                    player.displayClientMessage(Component.literal("Targeted Location " + proto.getLocation()),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);

                                }
                            }
                        }
                    }
                    return 0;
                }));
    }
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null) {
            if (!(event.getEntity().getLastDamageSource() == DamageSource.IN_WALL)){
            RandomSource random = RandomSource.create();
            if (event.getEntity() instanceof InfectedHuman){
                for (String str : SConfig.DATAGEN.inf_human_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                            m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                            itemStack.setCount(m);
                            ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                            item.setPickUpDelay(10);
                            event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof InfectedVendicator){
                for (String str : SConfig.DATAGEN.inf_vin_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof InfectedVillager){
                for (String str : SConfig.DATAGEN.inf_villager_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof InfectedPillager){
                for (String str : SConfig.DATAGEN.inf_pillager_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof InfectedEvoker){
                for (String str : SConfig.DATAGEN.inf_evoker_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof InfectedWanderingTrader){
                for (String str : SConfig.DATAGEN.inf_wan_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof InfectedWitch){
                for (String str : SConfig.DATAGEN.inf_witch_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Knight){
                for (String str : SConfig.DATAGEN.inf_knight_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Braionmil){
                for (String str : SConfig.DATAGEN.inf_braio_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Griefer){
                for (String str : SConfig.DATAGEN.inf_griefer_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Spitter){
                for (String str : SConfig.DATAGEN.inf_spitter_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Leaper){
                for (String str : SConfig.DATAGEN.inf_leap_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Slasher){
                for (String str : SConfig.DATAGEN.inf_slasher_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof InfEvoClaw){
                for (String str : SConfig.DATAGEN.inf_claw_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Howler){
                for (String str : SConfig.DATAGEN.inf_howler_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Stalker){
                for (String str : SConfig.DATAGEN.inf_stalker_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

            if (event.getEntity() instanceof Brute){
                for (String str : SConfig.DATAGEN.inf_brute_loot.get()){
                    String[] string = str.split("\\|" );
                    ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                    int m = 1;
                    if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                        m = Integer.parseUnsignedInt(string[3]);

                    } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                        m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                    if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                        itemStack.setCount(m);
                        ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                        item.setPickUpDelay(10);
                        event.getEntity().getLevel().addFreshEntity(item);}}}

                if (event.getEntity() instanceof Busser){
                    for (String str : SConfig.DATAGEN.inf_bus_loot.get()){
                        String[] string = str.split("\\|" );
                        ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                        int m = 1;
                        if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                            m = Integer.parseUnsignedInt(string[3]);

                        } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                            m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                        if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                            itemStack.setCount(m);
                            ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                            item.setPickUpDelay(10);
                            event.getEntity().getLevel().addFreshEntity(item);}}}

                if (event.getEntity() instanceof InfectedDrowned){
                    for (String str : SConfig.DATAGEN.inf_drow_loot.get()){
                        String[] string = str.split("\\|" );
                        ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                        int m = 1;
                        if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                            m = Integer.parseUnsignedInt(string[3]);

                        } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                            m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                        if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                            itemStack.setCount(m);
                            ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                            item.setPickUpDelay(10);
                            event.getEntity().getLevel().addFreshEntity(item);}}}

                if (event.getEntity() instanceof InfectedPlayer){
                    for (String str : SConfig.DATAGEN.inf_player_loot.get()){
                        String[] string = str.split("\\|" );
                        ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                        int m = 1;
                        if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                            m = Integer.parseUnsignedInt(string[3]);

                        } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                            m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                        if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                            itemStack.setCount(m);
                            ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                            item.setPickUpDelay(10);
                            event.getEntity().getLevel().addFreshEntity(item);}}}

                if (event.getEntity() instanceof Scamper){
                    for (String str : SConfig.DATAGEN.sca_loot.get()){
                        String[] string = str.split("\\|" );
                        ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                        int m = 1;
                        if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                            m = Integer.parseUnsignedInt(string[3]);

                        } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                            m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                        if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                            itemStack.setCount(m);
                            ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                            item.setPickUpDelay(10);
                            event.getEntity().getLevel().addFreshEntity(item);}}}

                if (event.getEntity() instanceof Mound){
                    for (String str : SConfig.DATAGEN.mound_loot.get()){
                        String[] string = str.split("\\|" );
                        ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(string[0])));
                        int m = 1;
                        if (Integer.parseUnsignedInt(string[2]) == Integer.parseUnsignedInt(string[3])){
                            m = Integer.parseUnsignedInt(string[3]);

                        } else {if (Integer.parseUnsignedInt(string[2]) >= 1 && Integer.parseUnsignedInt(string[2]) >= 1){
                            m = random.nextInt(Integer.parseUnsignedInt(string[2]), Integer.parseUnsignedInt(string[3]));}}

                        if (Math.random() < (Integer.parseUnsignedInt(string[1]) / 100F)) {
                            itemStack.setCount(m);
                            ItemEntity item = new ItemEntity(event.getEntity().getLevel(), event.getEntity().getX() , event.getEntity().getY(),event.getEntity().getZ(),itemStack);
                            item.setPickUpDelay(10);
                            event.getEntity().getLevel().addFreshEntity(item);}}}
        }}}
}
