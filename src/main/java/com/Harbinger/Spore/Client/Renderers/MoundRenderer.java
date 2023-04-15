package com.Harbinger.Spore.Client.Renderers;


import com.Harbinger.Spore.Client.Models.MoundModel;
import com.Harbinger.Spore.Sentities.Utility.Mound;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MoundRenderer<Type extends Mound> extends MobRenderer<Type , MoundModel<Type>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Spore.MODID,
            "textures/entity/mound.png");
    public MoundRenderer(EntityRendererProvider.Context context) {
        super(context, new MoundModel<>(context.bakeLayer(MoundModel.LAYER_LOCATION)), 0.5f);
    }


    @Override
    protected void scale(Mound mound, PoseStack poseStack, float p_115316_) {
        int scale = Math.max(mound.getAge(), 1);
        poseStack.scale((float) scale,(float)scale,(float)scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }

}
