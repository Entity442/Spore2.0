package com.Harbinger.Spore.Client.Renderers;


import com.Harbinger.Spore.Client.Models.BiomassReformatorModel;
import com.Harbinger.Spore.Sentities.Organoids.BiomassReformator;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BiomassReconfiguratorRenderer<Type extends BiomassReformator> extends MobRenderer<Type , BiomassReformatorModel<Type>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Spore.MODID,
            "textures/entity/blank.png");
    public BiomassReconfiguratorRenderer(EntityRendererProvider.Context context) {
        super(context, new BiomassReformatorModel<>(context.bakeLayer(BiomassReformatorModel.LAYER_LOCATION)), 0.5f);
    }


    @Override
    protected void scale(BiomassReformator reformator, PoseStack poseStack, float p_115316_) {
        float scale;
        if (reformator.getBiomass() <=25){
            scale = 0.5f;
        }else if (reformator.getBiomass() <=50){
            scale = 1f;
        }else if (reformator.getBiomass() <=75){
            scale = 2f;
        }else{
            scale = 3f;
        }

        poseStack.scale((float) scale,(float)scale,(float)scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
            return TEXTURE;
    }

}
