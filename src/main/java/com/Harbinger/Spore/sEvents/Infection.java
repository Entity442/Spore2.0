package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.*;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class Infection {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null) {
            inf(event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
        }
    }

    public static void inf(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity instanceof Infected && SConfig.SERVER.scent_spawn.get()) {
            if (world instanceof ServerLevel _level) {
                if (Math.random() < 0.1) {
                    {
                        Mob entityToSpawn = new ScentEntity(Sentities.SCENT.get(), _level);
                        entityToSpawn.moveTo(x, y + 4, z, world.getRandom().nextFloat() * 360F, 0);
                        entityToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
                                null);
                        world.addFreshEntity(entityToSpawn);
                    }
                }
            }
        }


        if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(Seffects.MYCELIUM.get())) {

            if (SConfig.SERVER.inf_human_conv.get().contains(entity.getEncodeId()) && !((LivingEntity) entity).isBaby()) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedHuman(Sentities.INF_HUMAN.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                    entity.discard();
                }
            }


            if (SConfig.SERVER.inf_villager_conv.get().contains(entity.getEncodeId()) && !((LivingEntity) entity).isBaby()) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedVillager(Sentities.INF_VILLAGER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                    entity.discard();
                }
            }


            if (SConfig.SERVER.inf_pillager_conv.get().contains(entity.getEncodeId())) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedPillager(Sentities.INF_PILLAGER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                    entity.discard();
                }
            }
            if (SConfig.SERVER.inf_witch_conv.get().contains(entity.getEncodeId())) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedWitch(Sentities.INF_WITCH.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                    entity.discard();
                }
            }
            if (SConfig.SERVER.inf_vindi_conv.get().contains(entity.getEncodeId())) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedVendicator(Sentities.INF_VINDICATOR.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                    entity.discard();
                }
            }
            if (SConfig.SERVER.inf_evoker_conv.get().contains(entity.getEncodeId())) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedEvoker(Sentities.INF_EVOKER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                    entity.discard();
                }
            }
        }
    }




    @SubscribeEvent
    public static void onEntityDeath(LivingDropsEvent event) {
        if (event != null && event.getEntity() != null) {
            drop(event, event.getEntity());
        }
    }
    public static void drop(@Nullable Event event, Entity entity) {
        if ((SConfig.SERVER.inf_human_conv.get().contains(entity.getEncodeId()) || SConfig.SERVER.inf_villager_conv.get().contains(entity.getEncodeId())
                || SConfig.SERVER.inf_pillager_conv.get().contains(entity.getEncodeId()) || SConfig.SERVER.inf_witch_conv.get().contains(entity.getEncodeId())
                || SConfig.SERVER.inf_evoker_conv.get().contains(entity.getEncodeId())) || SConfig.SERVER.inf_vindi_conv.get().contains(entity.getEncodeId())) {
            LivingEntity _livEnt = (LivingEntity) entity;
            if (_livEnt.hasEffect(Seffects.MYCELIUM.get())) {
                if (event != null && event.isCancelable()) {
                    event.setCanceled(true);
                }
            }
        }
    }



}
