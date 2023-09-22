package com.Harbinger.Spore.Senchantments;

import com.Harbinger.Spore.Core.Senchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class SymbioticReconstitution extends Enchantment {
    public SymbioticReconstitution(EquipmentSlot... slots) {
        super(Rarity.COMMON, Senchantments.FUNGAL_ITEMS, slots);
        Senchantments.SPORE_ENCHANTS.add(this);
    }
}