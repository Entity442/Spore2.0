package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.ScreativeTab;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class LivingHelmet extends LivingExoskeleton{
    public LivingHelmet() {
        super(EquipmentSlot.HEAD, new Properties().tab(ScreativeTab.SPORE));
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "spore:textures/armor/flesh_layer_1.png";
    }

}
