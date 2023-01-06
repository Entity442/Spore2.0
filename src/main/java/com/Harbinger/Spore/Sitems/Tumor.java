package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Sentities.Projectile.ThrownTumor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Tumor extends Item {
    public Tumor(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level p_41128_, Player p_41129_, InteractionHand p_41130_) {
        ItemStack itemstack = p_41129_.getItemInHand(p_41130_);
        p_41128_.playSound(null, p_41129_.getX(), p_41129_.getY(), p_41129_.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (p_41128_.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!p_41128_.isClientSide) {
            ThrownTumor tumor = new ThrownTumor(p_41128_, p_41129_);
            tumor.setItem(itemstack);
            tumor.shootFromRotation(p_41129_, p_41129_.getXRot(), p_41129_.getYRot(), 0.0F, 1.5F, 1.0F);
            p_41128_.addFreshEntity(tumor);
        }

        p_41129_.awardStat(Stats.ITEM_USED.get(this));
        if (!p_41129_.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, p_41128_.isClientSide());
    }
}
