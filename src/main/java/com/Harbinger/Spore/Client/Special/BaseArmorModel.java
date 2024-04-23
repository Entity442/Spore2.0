package com.Harbinger.Spore.Client.Special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class BaseArmorModel <T extends LivingEntity> extends EntityModel<T>{
    public void animateCrouch(T entity,ModelPart body){
        if (entity.isCrouching()){
            body.xRot = 0.5F;
            body.y = 3.2F;
        }else{
            body.xRot = 0.0F;
            body.y = 0.0F;
        }
    }

    @Override
    public void setupAnim(T entity, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }

    @Override
    public void renderToBuffer(PoseStack entity, VertexConsumer consumer, int p_103113_, int p_103114_, float p_103115_, float p_103116_, float p_103117_, float p_103118_) {

    }
}
