package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfectedRapier extends SwordItem {
    public InfectedRapier() {
        super(new Tier() {
            @Override
            public int getUses() {
                return SConfig.SERVER.rapier_durability.get();
            }

            @Override
            public float getSpeed() {
                return SConfig.SERVER.rapier_swing.get();
            }

            @Override
            public float getAttackDamageBonus() {
                return SConfig.SERVER.rapier_damage.get() - 4f;
            }

            @Override
            public int getLevel() {
                return 1;
            }

            @Override
            public int getEnchantmentValue() {
                return 5;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(Sitems.BIOMASS.get());
            }},3, -3f, new Item.Properties().tab(ScreativeTab.SPORE));
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity livingEntity) {
        entity.addEffect(new MobEffectInstance(Seffects.CORROSION.get(),60,1 ,true,true));
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("item.rapier.corrosive").withStyle(ChatFormatting.GREEN));
        super.appendHoverText(itemStack, level, components, flag);
    }
}
