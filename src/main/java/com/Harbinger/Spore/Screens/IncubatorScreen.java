package com.Harbinger.Spore.Screens;

import com.Harbinger.Spore.Core.Sitems;
import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class IncubatorScreen extends AbstractContainerScreen<IncubatorMenu> implements TutorialMenuMethods{
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Spore.MODID, "textures/gui/incubator_gui.png");
    private final List<Item> tagItems;
    private int tickCounter = 0;
    private int currentItemIndex = 0;
    public IncubatorScreen(IncubatorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 84;
        if (Minecraft.getInstance().cameraEntity instanceof ServerPlayer player && player.level instanceof ServerLevel serverLevel){
            this.tagItems = Utilities.getItemsFromTag(serverLevel,Spore.MODID, "weapons");
        }else {
            tagItems = new ArrayList<>();
        }
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float v, int i, int i1) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);

        renderFakeItem(itemRenderer,font,new ItemStack(Sitems.BIOMASS.get()), leftPos + 106, topPos + 8);
        renderFakeItem(itemRenderer,font,new ItemStack(Sitems.INCUBATOR.get()), leftPos + 79, topPos + 35);

        if (!tagItems.isEmpty()) {
            ItemStack stack = new ItemStack(tagItems.get(currentItemIndex));
            int damage = stack.getMaxDamage();
            renderFakeDamagedItem(itemRenderer,font, stack, damage / 2, leftPos + 34, topPos + 35);
            renderFakeDamagedItem(itemRenderer,font, stack, damage / 8, leftPos + 133, topPos + 35);
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        if (!tagItems.isEmpty()) {
            tickCounter++;
            if (tickCounter % 40 == 0) {
                currentItemIndex = (currentItemIndex + 1) % tagItems.size();
            }
        }
    }
}
