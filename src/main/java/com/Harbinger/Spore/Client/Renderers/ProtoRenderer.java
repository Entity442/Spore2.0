package com.Harbinger.Spore.Client.Renderers;


import com.Harbinger.Spore.Client.Models.protomodel;
import com.Harbinger.Spore.Sentities.Utility.Proto;
import com.Harbinger.Spore.Spore;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ProtoRenderer<Type extends Proto> extends MobRenderer<Type , protomodel<Type>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Spore.MODID,
            "textures/entity/proto.png");


    public ProtoRenderer(EntityRendererProvider.Context context) {
        super(context, new protomodel<>(context.bakeLayer(protomodel.LAYER_LOCATION)), 1.5f);

    }



    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }

}
