package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Client.Models.CoreModel;
import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sitems;
import com.Harbinger.Spore.Spore;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LivingChestplate extends LivingExoskeleton implements CustomModelArmor{
    public LivingChestplate() {
        super(EquipmentSlot.CHEST, new Properties().tab(ScreativeTab.SPORE));
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "spore:textures/armor/flesh_layer_1.png";
    }

    @Override
    public EntityModel<LivingEntity> getModel() {
        return new CoreModel<>();
    }

    @Override
    public ResourceLocation getCustomArmorTexture() {
        return new ResourceLocation(Spore.MODID,"textures/armor/infected_wing.png");
    }
}