package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.ScreativeTab;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Biomass extends Item {
    public Biomass() {
        super(new Item.Properties().tab(ScreativeTab.SPORE));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if (Screen.hasShiftDown()){
            components.add(Component.literal("A nutrient soup made out of animal matter").withStyle(ChatFormatting.DARK_RED));
        } else {
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
