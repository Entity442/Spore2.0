package com.Harbinger.Spore.sEvents;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.Sentities.Utility.UtilityEntity;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spore.MODID)
public class HandlerEvents {
    @SubscribeEvent
    public static void onLivingSpawned(EntityJoinLevelEvent event) {

        if (SConfig.SERVER.attack.get().contains(event.getEntity().getEncodeId())){
            Mob mob = (Mob) event.getEntity();
            mob.targetSelector.addGoal(1 , new NearestAttackableTargetGoal<>(mob , Infected.class, false));
        }
        if (SConfig.SERVER.flee.get().contains(event.getEntity().getEncodeId())){
            PathfinderMob mob = (PathfinderMob) event.getEntity();
            mob.goalSelector.addGoal(2, new AvoidEntityGoal<>(mob , Infected.class,6.0F, 1.0D, 0.9D));
            mob.goalSelector.addGoal(2, new AvoidEntityGoal<>(mob , UtilityEntity.class,8.0F, 1.0D, 0.9D));
        }
    }
}
