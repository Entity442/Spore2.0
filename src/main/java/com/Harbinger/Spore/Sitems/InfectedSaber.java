package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeSwordBase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class InfectedSaber extends SporeSwordBase {
    public InfectedSaber() {
        super(SConfig.SERVER.saber_damage.get(), 1f, 2f, SConfig.SERVER.saber_durability.get());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            leap(serverPlayer);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    public void leap(Player player) {
        player.hurtMarked = true;
        player.knockback(2.5F, -player.getLookAngle().x, -player.getLookAngle().z);
        player.getCooldowns().addCooldown(this,40);
    }
}
