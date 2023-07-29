package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class PlatedExoskeleton extends ArmorItem {
    public PlatedExoskeleton(EquipmentSlot slot, Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(EquipmentSlot slot1) {
                return new int[]{SConfig.SERVER.boots_durability1.get(), SConfig.SERVER.pants_durability1.get(), SConfig.SERVER.chestplate_durability1.get(), SConfig.SERVER.helmet_durability1.get()}
                        [slot.getIndex()];
            }

            @Override
            public int getDefenseForSlot(EquipmentSlot slot1) {
                return new int[]{SConfig.SERVER.boots_protection1.get(), SConfig.SERVER.pants_protection1.get(), SConfig.SERVER.chestplate_protection1.get(), SConfig.SERVER.helmet_protection1.get()}
                        [slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 18;
            }

            @Override
            public SoundEvent getEquipSound() {
                return SoundEvents.ARMOR_EQUIP_CHAIN;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(Sitems.ARMOR_FRAGMENT.get());
            }

            @Override
            public String getName() {
                return "Plated";
            }

            @Override
            public float getToughness() {
                return SConfig.SERVER.armor_toughness1.get();
            }

            @Override
            public float getKnockbackResistance() {
                return SConfig.SERVER.knockback_resistance1.get() / 10F;
            }
        } , slot,properties);

    }
}
