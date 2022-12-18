package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.SBlockEntities.ContainerBlockEntity;
import com.Harbinger.Spore.Sblocks.Container;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class ContainerItemRenderer implements BlockEntityRenderer<ContainerBlockEntity> {

    public ContainerItemRenderer(BlockEntityRendererProvider.Context context){}

    @Override
    public void render(ContainerBlockEntity entity, float p_112308_, PoseStack poseStack, MultiBufferSource source, int d1, int d2) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = entity.getItem(0);
        poseStack.pushPose();
        poseStack.translate(0.5f , 0.65f,0.5f);
        poseStack.scale(0.5f,0.5f,0.5f);
        poseStack.mulPose(Vector3f.YP.rotation(0));

        switch (entity.getBlockState().getValue(Container.FACING)) {
            case NORTH -> poseStack.mulPose(Vector3f.YP.rotationDegrees(0));
            case EAST -> poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
            case SOUTH -> poseStack.mulPose(Vector3f.YP.rotationDegrees(180));
            case WEST -> poseStack.mulPose(Vector3f.YP.rotationDegrees(270));
        }


        itemRenderer.renderStatic(itemStack , ItemTransforms.TransformType.GUI, getLightLevel(entity.getLevel(),entity.getBlockPos())
        , OverlayTexture.NO_OVERLAY, poseStack,source,1);
        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos blockPos) {
        int blight = level.getBrightness(LightLayer.BLOCK,blockPos);
        int light = level.getBrightness(LightLayer.SKY,blockPos);
        return LightTexture.pack(blight,light);
    }
}
