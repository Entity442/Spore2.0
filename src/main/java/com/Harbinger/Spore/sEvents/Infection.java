package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.Sentities.InfectedHuman;
import com.Harbinger.Spore.Sentities.InfectedWitch;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Infection {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
        }
    }

    private static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
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

            if (entity instanceof Zombie && !((Zombie) entity).isBaby()) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedHuman(Sentities.INFECTED_HUMAN.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                }
            }


            if (entity instanceof Villager && !((Villager) entity).isBaby()) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedHuman(Sentities.INF_VILLAGER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                }
            }


            if (entity instanceof Pillager) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedHuman(Sentities.INF_VILLAGER.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                }
            }
            if (entity instanceof Witch) {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new InfectedWitch(Sentities.INF_WITCH.get(), _level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    Mob _mobToSpawn = (Mob) entityToSpawn;
                    _mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.CONVERSION,
                            null, null);
                    world.addFreshEntity(entityToSpawn);
                }
            }
        }
    }

}
