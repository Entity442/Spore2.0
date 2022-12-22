package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Sitems;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class InfectedCombatShovel extends ShovelItem {
    public InfectedCombatShovel() {
        super(new Tier() {
            public int getUses() {
                return SConfig.SERVER.shovel_durability.get();
            }

            public float getSpeed() {
                return SConfig.SERVER.shovel_swing.get();
            }

            public float getAttackDamageBonus() {return SConfig.SERVER.shovel_damage.get() - 4;}
            public int getLevel() {
                return 5;}
            public int getEnchantmentValue() {
                return 3;}

            public Ingredient getRepairIngredient() {
                return Ingredient.of(Sitems.BIOMASS.get());
            }}, 3, -3f, new Item.Properties().tab(ScreativeTab.SPORE));
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment) || ImmutableSet.of(Enchantments.SHARPNESS, Enchantments.FIRE_ASPECT, Enchantments.MOB_LOOTING).contains(enchantment);
    }
}
