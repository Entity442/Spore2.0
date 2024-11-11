package com.Harbinger.Spore.Sitems;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OrganItem extends Item {
    private final String info;
    public OrganItem(Properties properties,String value) {
        super(properties);
        info = value;
    }
    public void setScanned(ItemStack stack){
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("scanned",true);
    }
    public boolean isScanned(ItemStack stack){
        return stack.getOrCreateTag().getBoolean("scanned");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, p_41422_, list, tooltipFlag);
        if (isScanned(stack)){
            list.add(Component.translatable(info).withStyle(ChatFormatting.GOLD));
        }else{
            list.add(Component.translatable("spore.scanner.organ.default").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
