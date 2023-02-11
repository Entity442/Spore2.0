package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class LivingExoskeleton extends ArmorItem {


    public LivingExoskeleton(EquipmentSlot slot, Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(EquipmentSlot slot1) {
                return new int[]{SConfig.SERVER.boots_durability2.get(), SConfig.SERVER.pants_durability2.get(), SConfig.SERVER.chestplate_durability2.get(), SConfig.SERVER.helmet_durability2.get()}
                        [slot.getIndex()];
            }

            @Override
            public int getDefenseForSlot(EquipmentSlot slot1) {
                return new int[]{SConfig.SERVER.boots_protection2.get(), SConfig.SERVER.pants_protection2.get(), SConfig.SERVER.chestplate_protection2.get(), SConfig.SERVER.helmet_protection2.get()}
                        [slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 18;
            }

            @Override
            public SoundEvent getEquipSound() {
                return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(Sitems.BIOMASS.get());
            }

            @Override
            public String getName() {
                return "Flesh";
            }

            @Override
            public float getToughness() {
                return SConfig.SERVER.armor_toughness2.get();
            }

            @Override
            public float getKnockbackResistance() {
                return SConfig.SERVER.knockback_resistance2.get() /10F;
            }
        } , slot,properties);

    }
}
