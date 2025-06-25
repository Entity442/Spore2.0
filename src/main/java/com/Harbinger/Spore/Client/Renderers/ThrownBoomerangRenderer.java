package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.TintedBufferSource;
import com.Harbinger.Spore.Sentities.Projectile.ThrownBoomerang;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ThrownBoomerangRenderer extends EntityRenderer<ThrownBoomerang> {
    private final ItemRenderer itemRenderer;

    public ThrownBoomerangRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ThrownBoomerang entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        float ageInTicks = entity.tickCount + partialTicks;
        ItemStack stack = entity.getBoomerang().copy();
        int color = entity.getColorValue(stack);
        float r = ((color >> 16) & 0xFF) / 255.0f;
        float g = ((color >> 8) & 0xFF) / 255.0f;
        float b = (color & 0xFF) / 255.0f;

        poseStack.pushPose();
        poseStack.translate(0.0D, 0.15D, 0.0D);
        poseStack.scale(2.5f, 2.5f, 2.5f);
        poseStack.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(ageInTicks * 20));
        poseStack.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(90));

        MultiBufferSource wrappedBuffer = new TintedBufferSource(buffer, r, g, b, 1.0f);
        this.itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, packedLight, 0, poseStack, wrappedBuffer, entity.getId());

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownBoomerang entity) {
        return null;
    }
}