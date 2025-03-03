package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Core.ScreativeTab;
import com.Harbinger.Spore.Core.Sitems;
import com.Harbinger.Spore.ExtremelySusThings.ClientAdvancementTracker;
import com.Harbinger.Spore.ExtremelySusThings.Package.RequestAdvancementPacket;
import com.Harbinger.Spore.ExtremelySusThings.SporePacketHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OrganItem extends Item {
    private final String info;
    private final String advancementIds;

    public OrganItem(String value, String advancementId) {
        super(new Item.Properties().tab(ScreativeTab.SPORE));
        this.info = value;
        this.advancementIds = advancementId;
        Sitems.BIOLOGICAL_ITEMS.add(this);
    }

    public String getAdvancementIds() {
        return advancementIds;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, list, tooltipFlag);
        if (level != null && level.isClientSide) {
            Entity entity = Minecraft.getInstance().getCameraEntity();
            if (entity instanceof Player player) {
                if (ClientAdvancementTracker.hasAdvancement(advancementIds)) {
                    list.add(Component.translatable(info).withStyle(ChatFormatting.GOLD));
                } else {
                    list.add(Component.translatable("spore.scanner.organ.default").withStyle(ChatFormatting.RED));
                    SporePacketHandler.sendToServer(new RequestAdvancementPacket(advancementIds,player.getId()));
                }
            }
        }else{
            list.add(Component.translatable("spore.scanner.organ.default").withStyle(ChatFormatting.RED));
        }
    }
}