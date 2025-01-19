package com.Harbinger.Spore.Sitems;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class LivingHelmet extends LivingExoskeleton {
    public LivingHelmet() {
        super(EquipmentSlot.HEAD);
    }
    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return GET_LOCATION_1.get(getVariant(stack));
    }
}
