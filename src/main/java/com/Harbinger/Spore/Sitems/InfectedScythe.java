package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Sitems;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

public class InfectedScythe extends HoeItem {

    public InfectedScythe() {
        super(new Tier() {
            public int getUses() {
                return SConfig.SERVER.scythe_durability.get();
            }

            public float getSpeed() {
                return 3;
            }

            public float getAttackDamageBonus() {return SConfig.SERVER.scythe_damage.get() - 4;}
            public int getLevel() {
                return 1;}
            public int getEnchantmentValue() {
                return 3;}

            public Ingredient getRepairIngredient() {
                return Ingredient.of(Sitems.BIOMASS.get());
            }}, 3, -3f, new Item.Properties().tab(ScreativeTab.SPORE));

    }
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction) ||
                ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction);
    }

    @Override
    public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        return target.getBoundingBox().inflate(4);
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment) || ImmutableSet.of(Enchantments.SHARPNESS, Enchantments.FIRE_ASPECT, Enchantments.MOB_LOOTING).contains(enchantment);
    }

}

