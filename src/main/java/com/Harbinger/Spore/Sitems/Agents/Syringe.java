package com.Harbinger.Spore.Sitems.Agents;

import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Recipes.EntityContainer;
import com.Harbinger.Spore.Recipes.InjectionRecipe;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Optional;


public class Syringe extends Item {
    public Syringe() {
        super(new Item.Properties().stacksTo(16).tab(ScreativeTab.SPORE_T));
    }

    public Optional<InjectionRecipe> getRecipe(Level level, Entity entity){
        EntityContainer container = new EntityContainer(entity);
        Optional<InjectionRecipe> recipe =
                level.getRecipeManager().getRecipeFor(InjectionRecipe.InjectionRecipeType.INSTANCE, container, level);
        if (recipe.isPresent()) {
            System.out.println("Found matching recipe: " + recipe.get().getId());
        } else {
            System.out.println("No matching recipe found.");
        }
        return recipe;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity living, InteractionHand hand) {
        Level level = player.level;
        if (!level.isClientSide){
            Optional<InjectionRecipe> match = this.getRecipe(level,living);
            if (match.isPresent()){
                ItemStack stack = match.get().getResultItem();
                if (stack != ItemStack.EMPTY){
                    player.playNotifySound(Ssounds.SYRINGE_SUCK.get(), SoundSource.AMBIENT,1F,1F);
                    living.hurt(DamageSource.playerAttack(player),1f);
                    player.addItem(stack.copy());
                    itemStack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.interactLivingEntity(itemStack, player, living, hand);
    }
}
