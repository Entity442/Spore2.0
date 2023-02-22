package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.extensions.IForgeItem;

public class InfectedGreatSword extends SwordItem implements IForgeItem {
    public InfectedGreatSword() {
        super(new Tier() {
            public int getUses() {
                return SConfig.SERVER.greatsword_durability.get();
            }

            public float getSpeed() {
                return 3;
            }

            public float getAttackDamageBonus() {return SConfig.SERVER.greatsword_damage.get() - 4;}
            public int getLevel() {
                return 1;}
            public int getEnchantmentValue() {
                return 3;}

            public Ingredient getRepairIngredient() {
                return Ingredient.of(Sitems.BIOMASS.get());
            }}, 3, -3f, new Item.Properties().tab(ScreativeTab.SPORE));
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
}
