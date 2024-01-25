package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.BusserModel;
import com.Harbinger.Spore.Client.Models.ExplodingBusserModel;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Busser;
import com.Harbinger.Spore.Sentities.Variants.BusserVariants;
import com.Harbinger.Spore.Spore;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class BusserRenderer extends BaseInfectedRenderer<Busser , EntityModel<Busser>> {
    private final EntityModel<Busser> normalBusser = this.getModel();
    private final EntityModel<Busser> explodingBusser;
    private static final ResourceLocation EYES_TEXTURE = new ResourceLocation(Spore.MODID,
            "textures/entity/eyes/busser.png");
    public static final Map<BusserVariants, ResourceLocation> TEXTURE =
            Util.make(Maps.newEnumMap(BusserVariants.class), (p_114874_) -> {
                p_114874_.put(BusserVariants.DEFAULT,
                        new ResourceLocation(Spore.MODID, "textures/entity/busser.png"));
                p_114874_.put(BusserVariants.ENHANCED,
                        new ResourceLocation(Spore.MODID, "textures/entity/busser_aggressive.png"));
                p_114874_.put(BusserVariants.BOMBER,
                        new ResourceLocation(Spore.MODID, "textures/entity/busserbomber.png"));
            });

    public BusserRenderer(EntityRendererProvider.Context context) {
        super(context, new BusserModel<>(context.bakeLayer(BusserModel.LAYER_LOCATION)), 0.5f);
        explodingBusser = new ExplodingBusserModel<>(context.bakeLayer(ExplodingBusserModel.LAYER_LOCATION));
    }
    @Override
    public ResourceLocation getTextureLocation(Busser entity) {
        return TEXTURE.get(entity.getVariant());
    }
    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }
    @Override
    protected void scale(Busser type, PoseStack stack, float value) {
        if (type.getVariant() == BusserVariants.ENHANCED){
            float size = 1.4f;
            stack.scale(size,size,size);
        }
        super.scale(type, stack, value);
    }

    @Override
    public void render(Busser busser, float p_115456_, float p_115457_, PoseStack stack, MultiBufferSource bufferSource, int p_115460_) {
        EntityModel<Busser> entityModel;
        if (busser.getVariant() == BusserVariants.BOMBER){
            entityModel = explodingBusser;
        }else{
            entityModel = this.normalBusser;
        }
        this.model = entityModel;
        super.render(busser, p_115456_, p_115457_, stack, bufferSource, p_115460_);
    }


}
