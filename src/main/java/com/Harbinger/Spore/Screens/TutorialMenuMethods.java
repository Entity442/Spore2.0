package com.Harbinger.Spore.Screens;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public interface TutorialMenuMethods {
    default void renderFakeItem(ItemRenderer itemRenderer, Font font, ItemStack stack, int x, int y) {
        itemRenderer.renderAndDecorateItem(stack, x, y);
        itemRenderer.renderGuiItemDecorations(font, stack, x, y);
    }

    default void renderFakeDamagedItem(ItemRenderer itemRenderer, Font font,ItemStack stack, int damage, int x, int y) {
        ItemStack damagedStack = stack.copy();
        damagedStack.setDamageValue(damage);
        itemRenderer.renderAndDecorateItem(stack, x, y);
        itemRenderer.renderGuiItemDecorations(font, stack, x, y);
    }

}
