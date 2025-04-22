package com.Harbinger.Spore.Screens;

import com.Harbinger.Spore.Recipes.WombRecipe;
import com.Harbinger.Spore.Sentities.VariantKeeper;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class AssimilationScreen extends AbstractContainerScreen<AssimilationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Spore.MODID, "textures/gui/assimilation_gui.png");
    private final List<WombRecipe> recipes;
    private List<WombRecipe.Pair> mobPairs;
    private int tickCounter = 0;
    private int currentItemIndex = 0;
    private Button leftButton;
    private Button rightButton;
    private int currentEntityIndex = 0;
    ClientLevel level = Minecraft.getInstance().level;
    public AssimilationScreen(AssimilationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
        if (level == null){
            this.recipes = new ArrayList<>();
        }else {
            this.recipes = level.getRecipeManager().getAllRecipesFor(WombRecipe.WombRecipeType.INSTANCE);
        }
    }
    private void changeRecipe(int direction) {
        if (!recipes.isEmpty()) {
            currentItemIndex = (currentItemIndex + direction) % recipes.size();
            currentEntityIndex = 0;
            if (currentItemIndex < 0) {
                currentItemIndex += recipes.size();
            }
        }
    }
    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        int buttonY = topPos-20;
        int buttonX = leftPos+88;
        this.leftButton = this.addRenderableWidget(new Button(
                buttonX - 10, buttonY, 20, 20, Component.literal("<"),
                button -> changeRecipe(-1)
        ));

        this.rightButton = this.addRenderableWidget(new Button(
                buttonX + 10, buttonY, 20, 20, Component.literal(">"),
                button -> changeRecipe(1)
        ));
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
    public void render(PoseStack guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        int x = this.leftPos+34;
        int y = this.topPos+70;
        renderTooltip(guiGraphics, mouseX, mouseY);
        WombRecipe recipe = recipes.get(currentItemIndex);
        if (recipe == null){return;}
        this.mobPairs = recipe.getEntityPairs();
        if (level != null){
            WombRecipe.Pair pairs = mobPairs.get(currentEntityIndex);
            int variant = pairs.type();
            ResourceLocation location = new ResourceLocation(pairs.entityId());
            Entity entity = ForgeRegistries.ENTITY_TYPES.getValue(location).create(level);
            if (entity instanceof LivingEntity living){
                if (living instanceof VariantKeeper keeper){
                    keeper.setVariant(variant);
                    renderEntityInInventoryFollowsAngle(guiGraphics,x,y,20,0f,0f,living);
                }else{
                    renderEntityInInventoryFollowsAngle(guiGraphics,x,y,20,0f,0f,living);
                }
            }
        }
        renderIcon(guiGraphics,recipe.getIcon());
        renderName(guiGraphics,recipe.getAttribute());
    }
    private void renderIcon(PoseStack guiGraphics,ResourceLocation iconLocation){
        RenderSystem.setShaderTexture(0, iconLocation);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        int iconX = this.leftPos + 100;
        int iconY = this.topPos + 30;
        int iconWidth = 32;
        int iconHeight = 32;
        blit(guiGraphics, iconX, iconY, 0, 0, iconWidth, iconHeight, iconWidth, iconHeight);
        RenderSystem.disableBlend();
    }
    private void renderName(PoseStack guiGraphics,String attributeName){
        String[] strings = attributeName.split(":");
        Component name = Component.translatable("attribute.name.spore."+strings[1]);
        int iconX = this.leftPos + 100;
        int iconY = this.topPos + 25;
        int iconWidth = 32;
        int textX = iconX + (iconWidth / 2) - (font.width(name) / 2);
        int textY = iconY - 10;
        drawString(guiGraphics,this.font, name, textX, textY, 0xFFFFFF);
    }

    public void renderEntityInInventoryFollowsAngle(PoseStack poseStack, int x, int y, int scale, float angleXComponent, float angleYComponent, LivingEntity entity) {
        poseStack.pushPose();

        // Apply rotations using Quaternion (correct for 1.19.2)
        Quaternion pose = new Quaternion(0.0F, 180.0F, 180.0F, true);
        Quaternion cameraOrientation = new Quaternion(angleYComponent * 20.0F, 0.0F, 0.0F, true);
        pose.mul(cameraOrientation);

        // Save entity's original rotation states
        float originalBodyRot = entity.yBodyRot;
        float originalYRot = entity.getYRot();
        float originalXRot = entity.getXRot();
        float originalHeadRotO = entity.yHeadRotO;
        float originalHeadRot = entity.yHeadRot;

        // Apply new rotation based on angles
        entity.yBodyRot = 180.0F + angleXComponent * 20.0F;
        entity.setYRot(180.0F + angleXComponent * 40.0F);
        entity.setXRot(-angleYComponent * 20.0F);
        entity.yHeadRot = entity.getYRot();
        entity.yHeadRotO = entity.getYRot();

        // Render entity using the correct method for 1.19.2
        renderEntityInInventory(poseStack, x, y, scale,pose, entity);

        // Restore entity's original rotation states
        entity.yBodyRot = originalBodyRot;
        entity.setYRot(originalYRot);
        entity.setXRot(originalXRot);
        entity.yHeadRotO = originalHeadRotO;
        entity.yHeadRot = originalHeadRot;

        poseStack.popPose();
    }

    private void renderEntityInInventory(PoseStack poseStack, int x, int y, int scale,Quaternion quaternion, LivingEntity entity) {
        poseStack.pushPose();
        poseStack.translate(x, y, 50.0);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(quaternion);
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        Lighting.setupForEntityInInventory();
        RenderSystem.applyModelViewMatrix();
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        dispatcher.setRenderShadow(false);
        dispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> {
            dispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, poseStack, bufferSource, 15728880);
        });
        bufferSource.endBatch();
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        if (!recipes.isEmpty()) {
            tickCounter++;
            if (tickCounter % 40 == 0) {
                currentEntityIndex = (currentEntityIndex + 1) % mobPairs.size();
            }
        }
    }
}
