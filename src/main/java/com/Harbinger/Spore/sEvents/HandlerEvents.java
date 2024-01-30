package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.*;
import com.Harbinger.Spore.ExtremelySusThings.ChunkLoaderHelper;
import com.Harbinger.Spore.ExtremelySusThings.CoolDamageSources;
import com.Harbinger.Spore.Sentities.AI.LocHiv.FollowOthersGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.*;
import com.Harbinger.Spore.Sentities.BasicInfected.*;
import com.Harbinger.Spore.Sentities.Calamities.Gazenbrecher;
import com.Harbinger.Spore.Sentities.Calamities.Sieger;
import com.Harbinger.Spore.Sentities.EvolvedInfected.*;
import com.Harbinger.Spore.Sentities.FallenMultipart.Licker;
import com.Harbinger.Spore.Sentities.FallenMultipart.SiegerTail;
import com.Harbinger.Spore.Sentities.Organoids.*;
import com.Harbinger.Spore.Sentities.Utility.InfEvoClaw;
import com.Harbinger.Spore.Sitems.InfectedCombatShovel;
import com.Harbinger.Spore.Sitems.InfectedMaul;
import com.Harbinger.Spore.Spore;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Spore.MODID)
public class HandlerEvents {
    @SubscribeEvent
    public static void onLivingSpawned(EntityJoinLevelEvent event) {
        if (event != null && event.getEntity() != null) {
            if (event.getEntity() instanceof PathfinderMob mob){
                for (String string : SConfig.SERVER.attack.get()){
                    if (string.endsWith(":")){
                        String[] mod = string.split(":");
                        String[] iterations = mob.getEncodeId().split(":");
                        if (Objects.equals(mod[0], iterations[0])){
                            mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Infected.class, false));
                            mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Calamity.class, false));
                        }
                    }else{
                        if (SConfig.SERVER.attack.get().contains(mob.getEncodeId())){
                            mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Infected.class, false));
                            mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(mob, Calamity.class, false));
                        }
                    }
                }
                for (String string : SConfig.SERVER.flee.get()){
                    if (string.endsWith(":")){
                        String[] mod = string.split(":");
                        String[] iterations = mob.getEncodeId().split(":");
                        if (Objects.equals(mod[0], iterations[0])){
                            mob.goalSelector.addGoal(4, new AvoidEntityGoal<>(mob, Infected.class, 6.0F, 1.0D, 0.9D));
                            mob.goalSelector.addGoal(4, new AvoidEntityGoal<>(mob, UtilityEntity.class, 8.0F, 1.0D, 0.9D));
                        }
                    }else{
                        if (SConfig.SERVER.flee.get().contains(mob.getEncodeId())){
                            mob.goalSelector.addGoal(4, new AvoidEntityGoal<>(mob, Infected.class, 6.0F, 1.0D, 0.9D));
                            mob.goalSelector.addGoal(4, new AvoidEntityGoal<>(mob, UtilityEntity.class, 8.0F, 1.0D, 0.9D));

                        }
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static  void Command(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal(Spore.MODID+":set_area")
        .executes(arguments -> {
            ServerLevel world = arguments.getSource().getLevel();
            double x = arguments.getSource().getPosition().x();
            double y = arguments.getSource().getPosition().y();
            double z = arguments.getSource().getPosition().z();
            Entity entity = arguments.getSource().getEntity();
            if (entity == null)
                entity = FakePlayerFactory.getMinecraft(world);
             if (entity != null){
                 BlockPos pos = new BlockPos(x ,y,z);
                 AABB hitbox = entity.getBoundingBox().inflate(20);
                 List<Entity> entities = entity.level.getEntities(entity, hitbox);
                 for (Entity entity1 : entities) {
                     if(entity1 instanceof Infected infected) {
                         infected.setSearchPos(pos);
                     }else if (entity1 instanceof Calamity calamity){
                         calamity.setSearchArea(pos);
                     }
                 }
             }
            return 0;
        }));
        event.getDispatcher().register(Commands.literal(Spore.MODID+":check_entity")
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
                                    player.displayClientMessage(Component.literal("Current Health " + infected.getHealth() + "/" + infected.getMaxHealth()),false);
                                    player.displayClientMessage(Component.literal("Kills " + infected.getKills()),false);
                                    player.displayClientMessage(Component.literal("Evolution Points " + infected.getEvoPoints()),false);
                                    player.displayClientMessage(Component.literal("Position to be Searched " + infected.getSearchPos()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + infected.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("Seconds until evolution: " + infected.getEvolutionCoolDown() + "/" + SConfig.SERVER.evolution_age_human.get()),false);
                                    player.displayClientMessage(Component.literal("Seconds until starvation: " + infected.getHunger() + "/" + SConfig.SERVER.hunger.get()),false);
                                    player.displayClientMessage(Component.literal("Is Linked ? " + infected.getLinked()),false);
                                    player.displayClientMessage(Component.literal("Target ? " + infected.getTarget()),false);
                                    player.displayClientMessage(Component.literal("Partner ? " + infected.getFollowPartner()),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);

                                }
                            }else if (entity1 instanceof Calamity calamity){
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ calamity.getEncodeId() + " " + calamity.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + calamity.getHealth()+ "/" + calamity.getMaxHealth()),false);
                                    player.displayClientMessage(Component.literal("Kills " + calamity.getKills()),false);
                                    if (calamity instanceof Sieger sieger){
                                        player.displayClientMessage(Component.literal("Tail health "+ sieger.getTailHp()+"/"+sieger.getMaxTailHp()),false);
                                    }
                                    if (calamity instanceof Gazenbrecher sieger){
                                        player.displayClientMessage(Component.literal("Tongue health "+ sieger.getTongueHp()+"/"+sieger.getMaxTongueHp()),false);
                                    }
                                    player.displayClientMessage(Component.literal("Position to be Searched " + calamity.getSearchArea()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + calamity.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("Target ? " + calamity.getTarget()),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);
                                }
                            }else if (entity1 instanceof Mound mound){
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ mound.getEncodeId() + " " + mound.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + mound.getHealth()+ "/" + mound.getMaxHealth()),false);
                                    player.displayClientMessage(Component.literal("Is Linked ? " + mound.getLinked()),false);
                                    player.displayClientMessage(Component.literal("Age " + mound.getAge()),false);
                                    player.displayClientMessage(Component.literal("Ticks until growth " + mound.getAgeCounter() + "/" + SConfig.SERVER.mound_age.get()),false);
                                    player.displayClientMessage(Component.literal("Ticks until puff " + mound.getCounter() + "/" + mound.getMaxCounter()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + mound.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);
                                }
                            }else if(entity1 instanceof Proto proto) {
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ proto.getEncodeId() + " " + proto.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + proto.getHealth()+ "/" + proto.getMaxHealth()),false);
                                    player.displayClientMessage(Component.literal("Current Target " + proto.getTarget()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + proto.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("Mobs under control " + proto.getHosts()),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);
                                }
                            }
                            else if(entity1 instanceof BiomassReformator reformator) {
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ reformator.getEncodeId() + " " + reformator.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + reformator.getHealth()),false);
                                    player.displayClientMessage(Component.literal("Stored Location " + reformator.getLocation()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + reformator.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("Biomass " + reformator.getBiomass()),false);
                                    player.displayClientMessage(Component.literal("State " + reformator.getState()),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);
                                }
                            }else if(entity1 instanceof Vigil vigil) {
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ vigil.getEncodeId() + " " + vigil.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + vigil.getHealth()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + vigil.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("State " + vigil.getTrigger()),false);
                                    player.displayClientMessage(Component.literal("Horde size " + vigil.getWaveSize()),false);
                                    player.displayClientMessage(Component.literal("Time until it leaves " + vigil.getTimer()+"/6000"),false);
                                    player.displayClientMessage(Component.literal("-------------------------"),false);
                                }
                            }else if(entity1 instanceof Umarmer umarmer) {
                                if (entity instanceof Player player && !player.level.isClientSide){
                                    player.displayClientMessage(Component.literal("Entity "+ umarmer.getEncodeId() + " " + umarmer.getCustomName()),false);
                                    player.displayClientMessage(Component.literal("Current Health " + umarmer.getHealth()),false);
                                    player.displayClientMessage(Component.literal("Buffs " + umarmer.getActiveEffects()),false);
                                    player.displayClientMessage(Component.literal("Shielded? " + umarmer.isShielding()),false);
                                    player.displayClientMessage(Component.literal("Pins? " + umarmer.isPinned()),false);
                                    player.displayClientMessage(Component.literal("Time until it leaves " + umarmer.getTimer()+"/2400"),false);
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
        if (event != null && event.getEntity() != null && event.getEntity().level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            RandomSource random = RandomSource.create();
            List<? extends String> lootList;
            if (event.getEntity() instanceof InfectedHuman){
                lootList = SConfig.DATAGEN.inf_human_loot.get();
            }else if (event.getEntity() instanceof InfectedVendicator){
                lootList = SConfig.DATAGEN.inf_vin_loot.get();
            }else if (event.getEntity() instanceof InfectedVillager){
                lootList = SConfig.DATAGEN.inf_villager_loot.get();
            }else if (event.getEntity() instanceof InfectedPillager){
                lootList = SConfig.DATAGEN.inf_pillager_loot.get();
            }else if (event.getEntity() instanceof InfectedEvoker){
                lootList = SConfig.DATAGEN.inf_evoker_loot.get();
            }else if (event.getEntity() instanceof InfectedWanderingTrader){
                lootList = SConfig.DATAGEN.inf_wan_loot.get();
            }else if (event.getEntity() instanceof InfectedWitch){
                lootList = SConfig.DATAGEN.inf_witch_loot.get();
            }else if (event.getEntity() instanceof Knight){
                lootList = SConfig.DATAGEN.inf_knight_loot.get();
            }else if (event.getEntity() instanceof Braionmil){
                lootList = SConfig.DATAGEN.inf_braio_loot.get();
            }else if (event.getEntity() instanceof Griefer){
                lootList = SConfig.DATAGEN.inf_griefer_loot.get();
            }else if (event.getEntity() instanceof Spitter){
                lootList = SConfig.DATAGEN.inf_spitter_loot.get();
            }else if (event.getEntity() instanceof Slasher){
                lootList = SConfig.DATAGEN.inf_slasher_loot.get();
            }else if (event.getEntity() instanceof Leaper){
                lootList = SConfig.DATAGEN.inf_leap_loot.get();
            }else if (event.getEntity() instanceof InfEvoClaw){
                lootList = SConfig.DATAGEN.inf_claw_loot.get();
            }else if (event.getEntity() instanceof Howler){
                lootList = SConfig.DATAGEN.inf_howler_loot.get();
            }else if (event.getEntity() instanceof Stalker){
                lootList = SConfig.DATAGEN.inf_stalker_loot.get();
            }else if (event.getEntity() instanceof Brute){
                lootList = SConfig.DATAGEN.inf_brute_loot.get();
            }else if (event.getEntity() instanceof InfectedDrowned){
                lootList = SConfig.DATAGEN.inf_drow_loot.get();
            }else if (event.getEntity() instanceof Busser){
                lootList = SConfig.DATAGEN.inf_bus_loot.get();
            }else if (event.getEntity() instanceof Scamper){
                lootList = SConfig.DATAGEN.sca_loot.get();
            }else if (event.getEntity() instanceof InfectedPlayer){
                lootList = SConfig.DATAGEN.inf_player_loot.get();
            }else if (event.getEntity() instanceof Mound){
                lootList = SConfig.DATAGEN.mound_loot.get();
            }else if (event.getEntity() instanceof Sieger){
                lootList = SConfig.DATAGEN.sieger_loot.get();
            }else if (event.getEntity() instanceof Proto){
                lootList = SConfig.DATAGEN.proto_loot.get();
            }else if (event.getEntity() instanceof SiegerTail){
                lootList = SConfig.DATAGEN.sieger_tail_loot.get();
            }else if (event.getEntity() instanceof Vigil){
                lootList = SConfig.DATAGEN.vigil_loot.get();
            }else if (event.getEntity() instanceof InfectedHusk){
                lootList = SConfig.DATAGEN.inf_husk_loot.get();
            }else if (event.getEntity() instanceof Volatile){
                lootList = SConfig.DATAGEN.inf_volatile_loot.get();
            }else if (event.getEntity() instanceof Umarmer){
                lootList = SConfig.DATAGEN.umarmer_loot.get();
            }else if (event.getEntity() instanceof Gazenbrecher){
                lootList = SConfig.DATAGEN.gazen_loot.get();
            }else if (event.getEntity() instanceof Licker){
                lootList = SConfig.DATAGEN.gazen_tongue_loot.get();
            }
            else{
                lootList = null;
            }
            if (lootList != null){
                for (String str : lootList){
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
                        event.getEntity().getLevel().addFreshEntity(item);}}
            }
        }
    }
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void onExtendedToolUsage(BlockEvent.BreakEvent event)
    {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof InfectedMaul hammer && player instanceof ServerPlayer serverPlayer && !serverPlayer.isCrouching())
        {
            BlockPos initalBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initalBlockPos))
            {
                return;
            }

            for (BlockPos pos : InfectedMaul.getBlocksToBeDestroyed(1, initalBlockPos, serverPlayer))
            {
                if(pos == initalBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos)))
                {
                    continue;
                }
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
        if(mainHandItem.getItem() instanceof InfectedCombatShovel shovel && player instanceof ServerPlayer serverPlayer && !serverPlayer.isCrouching())
        {
            BlockPos initalBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initalBlockPos))
            {
                return;
            }

            for (BlockPos pos : InfectedCombatShovel.getBlocksToBeDestroyed(1, initalBlockPos, serverPlayer))
            {
                if(pos == initalBlockPos || !shovel.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos)))
                {
                    continue;
                }
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }


    @SubscribeEvent
    public static void Effects(TickEvent.PlayerTickEvent event){
        if (event.player instanceof ServerPlayer player){
            if (player.hasEffect(Seffects.CORROSION.get())){
                if (player.tickCount % 60 == 0){
                    player.getInventory().hurtArmor(CoolDamageSources.CORROSION,0.5f, Inventory.ALL_ARMOR_SLOTS);
                }
            }
            if (player.hasEffect(Seffects.SYMBIOSIS.get())){
                if (player.tickCount % 400 == 0){
                    int size = player.getInventory().getContainerSize();
                    for (int i = 0;i <= size;i++){
                        ItemStack itemStack = player.getInventory().getItem(i);
                        if (EnchantmentHelper.getTagEnchantmentLevel(Senchantments.SYMBIOTIC_RECONSTITUTION.get(),itemStack) != 0 && itemStack.isDamaged()){
                            int l = itemStack.getDamageValue()-1;
                            itemStack.setDamageValue(l);
                        }
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public static void FishingAnInfectedDrowned(ItemFishedEvent event){
        if (event != null){
            if (Math.random() < 0.05 && event.getHookEntity().isOpenWaterFishing()){
                InfectedDrowned infectedDrowned = new InfectedDrowned(Sentities.INF_DROWNED.get(),event.getEntity().level);
                infectedDrowned.moveTo(event.getHookEntity().getX(),event.getHookEntity().getY(),event.getHookEntity().getZ());
                infectedDrowned.setKills(1);
                infectedDrowned.setTarget(event.getEntity());
                event.getEntity().level.addFreshEntity(infectedDrowned);
            }
        }
    }
    @SubscribeEvent
    public static void ExplosiveBite(LivingEntityUseItemEvent.Finish event){
        if (event != null && Math.random() < 0.2){
            ItemStack item = event.getItem();
            if (item.getItem() == Sitems.ROASTED_TUMOR.get()){
                LivingEntity entity = event.getEntity();
                entity.level.explode(null,entity.getX(),entity.getY(),entity.getZ(),0.5f, Explosion.BlockInteraction.NONE);
            }
        }
    }


    @SubscribeEvent
    public static void LoadCalamity(EntityEvent.EnteringSection event){
        if (event.getEntity() instanceof Calamity calamity && calamity.level instanceof ServerLevel level){
            SectionPos OldChunk = event.getOldPos();
            SectionPos NewChunk = event.getNewPos();
            if (event.didChunkChange() && OldChunk != NewChunk){
                BlockPos position = new BlockPos((int)calamity.getX(),(int)calamity.getY(),(int)calamity.getZ());
                if (NewChunk != null){
                    ChunkLoaderHelper.forceLoadChunk(level,position, NewChunk.x(), NewChunk.z(), true);
                }
                if (OldChunk != null){
                    ChunkLoaderHelper.unloadChunk(level,position,OldChunk.x(), OldChunk.z(), true);
                }
            }
        }
    }
    @SubscribeEvent
    public static void UnloadAround(EntityLeaveLevelEvent event){
        if (event.getEntity() instanceof Calamity calamity && calamity.level instanceof ServerLevel level){
            BlockPos position = new BlockPos((int)calamity.getX(),(int)calamity.getY(),(int)calamity.getZ());
            SectionPos chunk = SectionPos.of(position);
            ChunkLoaderHelper.unloadChunksInRadius(level,position, chunk.x(), chunk.z(), 1);
        }
    }
    @SubscribeEvent
    public static void ProtectFromEffect(MobEffectEvent.Applicable event)
    {
        if (event.getEntity() != null && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == Sitems.GAS_MASK.get()){
            event.getEffectInstance();
            if (event.getEffectInstance().getEffect() == Seffects.MYCELIUM.get()){
                event.setResult(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public static void NoSleep(PlayerSleepInBedEvent event){
        if(event.getEntity() instanceof ServerPlayer player && player.hasEffect(Seffects.UNEASY.get())){
            player.displayClientMessage(Component.translatable("uneasy.message"),true);
            event.setResult(Player.BedSleepingProblem.OTHER_PROBLEM);
        }

    }

}
