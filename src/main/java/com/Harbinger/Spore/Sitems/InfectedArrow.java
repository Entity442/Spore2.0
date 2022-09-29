package com.Harbinger.Spore.Sitems;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class InfectedArrow extends AbstractArrow {
    protected InfectedArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }
}
