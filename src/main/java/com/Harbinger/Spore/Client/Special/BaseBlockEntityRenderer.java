package com.Harbinger.Spore.Client.Special;

import com.Harbinger.Spore.SBlockEntities.AnimatedEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public abstract class BaseBlockEntityRenderer<T extends BlockEntity & AnimatedEntity> implements BlockEntityRenderer<T>, BlockEntityRendererProvider<T> {
    private final BlockEntityModel<T> model;
    protected BaseBlockEntityRenderer(BlockEntityModel<T> model) {
        this.model = model;
    }
    public abstract ResourceLocation getTexture();
    public BlockEntityModel<T> getModel(){return model;}

    @Override
    public void render(@NotNull T blockEntity, float partialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if (!unRenderBlock(blockEntity)){
            return;
        }
        pPoseStack.pushPose();
        float f = ((float)blockEntity.getTicks() + partialTicks);
        VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityCutout(getTexture()));
        setModelScale(pPoseStack,blockEntity);
        this.model.setupAnim(blockEntity,f);
        this.model.renderToBuffer(pPoseStack,vertexConsumer,pPackedLight, pPackedOverlay,1,1,1,1);
        pPoseStack.popPose();
    }
    protected boolean unRenderBlock(T blockEntity){
        if (Minecraft.getInstance().cameraEntity instanceof Player player){
            int x = blockEntity.getBlockPos().getX();
            int y = blockEntity.getBlockPos().getY();
            int z = blockEntity.getBlockPos().getZ();
            double d0 = player.distanceToSqr(x,y,z);
            return d0 < 256;
        }
        return false;
    }

    public void setModelScale(PoseStack pPoseStack,T block){
        setModelScale(pPoseStack,block,2);
    }
    public void setModelScale(PoseStack pPoseStack,T block,int value){
        pPoseStack.translate(0.5,1.5,0.5);
        pPoseStack.scale(0.99f,0.99f,0.99f);
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(-180F));
        if (value == 2){
            pPoseStack.mulPose(Vector3f.YP.rotationDegrees(0));
        }if (value == 3){
            pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
        }if (value == 4){
            pPoseStack.mulPose(Vector3f.YP.rotationDegrees(270));
        }if (value == 5){
            pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        }
    }


    @Override
    public BlockEntityRenderer<T> create(Context context) {
        return this;
    }
}
