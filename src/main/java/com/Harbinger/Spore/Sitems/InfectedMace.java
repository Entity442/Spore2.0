package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sitems.BaseWeapons.SporeToolsBaseItem;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class InfectedMace extends SporeToolsBaseItem {
    public InfectedMace() {
        super(SConfig.SERVER.mace_damage.get(), 2f, 3, SConfig.SERVER.mace_durability.get(), 1);
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity livingEntity, LivingEntity entity) {
       entity.knockback(2.2F, Mth.sin(livingEntity.getYRot() * ((float) Math.PI / 180F)), (-Mth.cos(livingEntity.getYRot() * ((float) Math.PI / 180F))));
        AABB bounding = entity.getBoundingBox().inflate(2);
        List<Entity> targets = entity.level.getEntities(entity , bounding);
        for (Entity en : targets) {
            if (en instanceof LivingEntity && !(en.is(livingEntity))){
                ((LivingEntity) en).knockback(2.2F, Mth.sin(livingEntity.getYRot() * ((float) Math.PI / 180F)), (-Mth.cos(livingEntity.getYRot() * ((float) Math.PI / 180F))));
                en.hurt(DamageSource.mobAttack(livingEntity), SConfig.SERVER.mace_damage.get());
                int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
                if (k > 0){ en.setSecondsOnFire(10 * k);}
            }
        }
        return super.hurtEnemy(stack, livingEntity, entity);
    }
}
