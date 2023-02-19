package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.Sentities.InfectedHuman;
import com.Harbinger.Spore.Sentities.InfectedPlayer;
import com.Harbinger.Spore.Sentities.Utility.ScentEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.Set;

@Mod.EventBusSubscriber
public class Infection {
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null) {
            Level world = event.getEntity().level;
            double x = event.getEntity().getX();
            double y = event.getEntity().getY();
            double z = event.getEntity().getZ();
            Entity entity = event.getEntity();

        if (entity instanceof Infected && SConfig.SERVER.scent_spawn.get()) {
            if (world instanceof ServerLevel _level) {
                if (Math.random() < (SConfig.SERVER.scent_spawn_chance.get() / 100f)) {
                    {
                        Mob entityToSpawn = new ScentEntity(Sentities.SCENT.get(), _level);
                        entityToSpawn.moveTo(x, y + 4, z, world.getRandom().nextFloat() * 360F, 0);
                        world.addFreshEntity(entityToSpawn);
                    }
                }
            }
        }

        if (entity instanceof Player player && player.hasEffect(Seffects.MYCELIUM.get()) && !world.isClientSide && SConfig.SERVER.inf_player.get()){
             Component name = player.getName();
            InfectedPlayer infectedHuman = Sentities.INF_PLAYER.get().create(world);
            ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
            ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
            ItemStack hand = player.getItemBySlot(EquipmentSlot.MAINHAND);
            ItemStack offhand = player.getItemBySlot(EquipmentSlot.OFFHAND);
            assert infectedHuman != null;
            infectedHuman.setItemSlot(EquipmentSlot.HEAD , head);
            infectedHuman.setItemSlot(EquipmentSlot.CHEST , chest);
            infectedHuman.setItemSlot(EquipmentSlot.LEGS , legs);
            infectedHuman.setItemSlot(EquipmentSlot.FEET , feet);
            infectedHuman.setItemSlot(EquipmentSlot.MAINHAND , hand);
            infectedHuman.setItemSlot(EquipmentSlot.OFFHAND , offhand);
            infectedHuman.moveTo(event.getEntity().getX(),event.getEntity().getY(),event.getEntity().getZ());
            infectedHuman.setCustomName(name);
            world.addFreshEntity(infectedHuman);
        }


        if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(Seffects.MYCELIUM.get()) && !world.isClientSide && !(entity instanceof Player)) {

            ServerLevelAccessor worlder = (ServerLevelAccessor) entity.level;
            for (String str : SConfig.SERVER.inf_human_conv.get()) {
                String[] string = str.split("\\|");
                EntityType<?> value2 =  ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(string[1]));
                assert value2 != null;
                Mob mobT = (Mob) value2.create(event.getEntity().level);
                if (string[0].contains(Objects.requireNonNull(entity.getEncodeId()))) {
                    assert mobT != null;
                    mobT.setPos(entity.getX(), entity.getY(), entity.getZ());
                    mobT.finalizeSpawn(worlder, world.getCurrentDifficultyAt(new BlockPos(entity.getX(), entity.getY(), entity.getZ())), MobSpawnType.NATURAL, null, null);
                    world.addFreshEntity(mobT);
                    entity.discard();
                }
            }}
        }
    }
}
