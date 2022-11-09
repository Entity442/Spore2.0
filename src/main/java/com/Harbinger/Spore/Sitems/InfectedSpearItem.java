package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sitems;
import com.Harbinger.Spore.Sentities.Projectile.ThrownSpear;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InfectedSpearItem extends Item implements Vanishable {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public InfectedSpearItem(Properties properties) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", SConfig.SERVER.spear_damage.get() - 1, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", SConfig.SERVER.spear_swing.get(), AttributeModifier.Operation.ADDITION));

        this.defaultModifiers = builder.build();
    }
    @Override
    public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
        return List.of(Sitems.BIOMASS.get()).contains(repairitem.getItem());
    }


    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);

    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int T) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack) - T;
            if (i >= 10 && !level.isClientSide) {
                            stack.hurtAndBreak(1, player, (ss) -> {
                            ss.broadcastBreakEvent(entity.getUsedItemHand());});

                            ThrownSpear thrownSpear = new ThrownSpear(level, player, stack);
                            thrownSpear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F , 1.0F);
                            if (player.getAbilities().instabuild) {
                                thrownSpear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }
                            level.addFreshEntity(thrownSpear);
                            level.playSound((Player)null, thrownSpear, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                            if (!player.getAbilities().instabuild) {
                                player.getInventory().removeItem(stack);
                            }

                int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
                if (j > 0) {
                    thrownSpear.setBaseDamage(thrownSpear.getBaseDamage() + (double)j * 0.5D + 0.5D);
                }

                int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, stack);
                if (k > 0) {
                    thrownSpear.setKnockback(k);
                }
            }

                    player.awardStat(Stats.ITEM_USED.get(this));


            }
        }

    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity livingEntity) {
        stack.hurtAndBreak(1, livingEntity, (ss) -> {
            ss.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if ((double)state.getDestroySpeed(level, pos) != 0.0D) {
            stack.hurtAndBreak(2, entity, (ss) -> {
                ss.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return true;
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }
    @Override
    public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        return target.getBoundingBox().inflate(1);
    }
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }
    public int getEnchantmentValue() {
        return 2;
    }


}

