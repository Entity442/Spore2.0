package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class InfectedArmads extends AxeItem {
    public InfectedArmads() {
    super(new Tier() {
        public int getUses() {
            return SConfig.SERVER.armads_durability.get();
        }

        public float getSpeed() {
            return SConfig.SERVER.armads_swing.get();
        }

        public float getAttackDamageBonus() {return SConfig.SERVER.armads_damage.get() - 4;}
        public int getLevel() {
            return 1;}
        public int getEnchantmentValue() {
            return 3;}

        public Ingredient getRepairIngredient() {
            return Ingredient.of(Sitems.BIOMASS.get());
        }}, 3, -3f, new Item.Properties().tab(ScreativeTab.SPORE));
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        return entity.addEffect(new MobEffectInstance(Seffects.STUNT.get(), 40, 1));
    }
}
