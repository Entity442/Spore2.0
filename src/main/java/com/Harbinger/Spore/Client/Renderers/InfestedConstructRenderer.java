package com.Harbinger.Spore.Client.Renderers;

import com.Harbinger.Spore.Client.Models.InfestedContructModel;
import com.Harbinger.Spore.Client.Special.BaseInfectedRenderer;
import com.Harbinger.Spore.Sentities.Utility.InfestedConstruct;
import com.Harbinger.Spore.Spore;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfestedConstructRenderer<Type extends InfestedConstruct> extends BaseInfectedRenderer<Type , InfestedContructModel<Type>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Spore.MODID,
            "textures/entity/broken_construct.png");
    private static final ResourceLocation EYES_TEXTURE = new ResourceLocation(Spore.MODID,
            "textures/entity/eyes/broken_construct.png");


    public InfestedConstructRenderer(EntityRendererProvider.Context context) {
        super(context, new InfestedContructModel<>(context.bakeLayer(InfestedContructModel.LAYER_LOCATION)), 1f);
    }


    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation eyeLayerTexture() {
        return EYES_TEXTURE;
    }


}
