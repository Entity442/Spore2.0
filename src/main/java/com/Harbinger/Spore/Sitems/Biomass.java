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
        super(new Item.Properties().tab(ScreativeTab.SPORE_T));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if (Screen.hasShiftDown()){
            components.add(Component.translatable("item.line.shift").withStyle(ChatFormatting.DARK_RED));
        } else {
            components.add(Component.translatable("item.line.normal").withStyle(ChatFormatting.GOLD));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
