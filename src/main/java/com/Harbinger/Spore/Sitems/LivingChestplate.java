package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LivingChestplate extends LivingExoskeleton {
    public LivingChestplate() {
        super(EquipmentSlot.CHEST, new Properties().tab(ScreativeTab.SPORE));
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "spore:textures/armor/flesh_layer_1.png";
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        this.geteffect(player);
        super.onArmorTick(stack, level, player);
    }

    private void geteffect(Entity entity) {
        if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
                .getItem() == Sitems.LIVING_BOOTS.get()
                && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
                .getItem() == Sitems.LIVING_PANTS.get()
                && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
                .getItem() == Sitems.LIVING_HELMET.get()) {
            if (entity instanceof LivingEntity _entity)
                _entity.addEffect(new MobEffectInstance(Seffects.SYMBIOSIS.get(), 60, 0, (false), (false)));
        }
    }
}