package com.Harbinger.Spore.Client.Layers;

import com.Harbinger.Spore.Client.Models.NuckelaveArmorModel;
import com.Harbinger.Spore.Client.Models.NuckelaveModel;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Nuclealave;
import com.Harbinger.Spore.Spore;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NucleaChestplateLayer<T extends Nuclealave> extends RenderLayer<T, NuckelaveModel<T>> {
    private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
    public final NuckelaveArmorModel<T> Nucklemodel;
    public final List<ModelPart> helmetModels = new ArrayList<>();
    public final List<ModelPart> chestModels = new ArrayList<>();
    public final List<ModelPart> pantsModels = new ArrayList<>();
    public final List<ModelPart> bootsModels = new ArrayList<>();
    private static final ResourceLocation BLOOD_LAYER1 = new ResourceLocation(Spore.MODID,
            "textures/overlay/blood_overlay.png");
    private static final ResourceLocation BLOOD_LAYER2 = new ResourceLocation(Spore.MODID,
            "textures/overlay/blood_overlay_2.png");
    public NucleaChestplateLayer(RenderLayerParent<T, NuckelaveModel<T>> p_117346_, EntityModelSet set) {
        super(p_117346_);
        this.Nucklemodel = new NuckelaveArmorModel<>(set.bakeLayer(NuckelaveArmorModel.LAYER_LOCATION));
        this.helmetModels.add(this.getParentModel().HeadWear);
        this.chestModels.add(this.getParentModel().LeftArmWear);
        this.chestModels.add(this.getParentModel().RightArmWear);
        this.pantsModels.add(this.getParentModel().BackRightLegWear);
        this.pantsModels.add(this.getParentModel().FrontRightLegWear);
        this.bootsModels.add(this.getParentModel().BackLeftFootWear);
        this.bootsModels.add(this.getParentModel().FrontRightFootWear);
    }


    @Override
    public void render(PoseStack stack, MultiBufferSource bufferSource, int value, T type, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        renderArmorBuffer(type,stack,bufferSource,value);
        renderToBufferPerArmorPiece(type,stack,bufferSource,value,OverlayTexture.NO_OVERLAY,1,1,1,1);
    }
    public void renderToBufferPerArmorPiece(T entity , PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        renderArmorPart(entity,EquipmentSlot.HEAD,helmetModels,poseStack,bufferSource, packedLight, packedOverlay, red, green, blue, alpha);
        renderArmorPart(entity,EquipmentSlot.CHEST,chestModels,poseStack,bufferSource, packedLight, packedOverlay, red, green, blue, alpha);
        renderArmorPart(entity,EquipmentSlot.LEGS,pantsModels,poseStack,bufferSource, packedLight, packedOverlay, red, green, blue, alpha);
        renderArmorPart(entity,EquipmentSlot.FEET,bootsModels,poseStack,bufferSource, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void renderArmorPart(T entity, EquipmentSlot slot , List<ModelPart> parts, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        ItemStack itemStack = entity.getItemBySlot(slot);
        boolean flag = itemStack.hasFoil();
        if (itemStack.getItem() instanceof ArmorItem armorItem){
            if (armorItem instanceof DyeableLeatherItem) {
                int i = ((DyeableLeatherItem)armorItem).getColor(itemStack);
                float f = (float)(i >> 16 & 255) / 255.0F;
                float f1 = (float)(i >> 8 & 255) / 255.0F;
                float f2 = (float)(i & 255) / 255.0F;
                renderArmor(parts,stack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,f,f1,f2,1,this.getArmorResource(entity, itemStack, slot, (String)null),flag,slot);
            } else {
                renderArmor(parts,stack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,1,1,1,1,this.getArmorResource(entity, itemStack, slot, (String)null),flag,slot);
            }
        }
    }
    private void renderArmor(List<ModelPart> parts, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, float red, float green, float blue, float alpha,ResourceLocation location,boolean glint,EquipmentSlot slot){
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(location));
        this.getParentModel().Nuckelavee.getAllParts().forEach(modelPart -> {setInvisible(modelPart,parts);});
        this.getParentModel().Nuckelavee.render(stack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
        if (glint){
            this.getParentModel().Nuckelavee.render(stack, bufferSource.getBuffer(RenderType.entityGlint()), packedLight, packedOverlay, red, green, blue, alpha);
        }
        renderBloodLayer(this.getParentModel().Nuckelavee,slot,stack,bufferSource,packedLight);
    }

    private void setInvisible(ModelPart part,List<ModelPart> parts){
        part.skipDraw = !parts.contains(part);
    }

    private void renderBloodLayer(ModelPart part,EquipmentSlot slot, PoseStack stack, MultiBufferSource bufferSource, int packedLight){
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(slot == EquipmentSlot.LEGS ? BLOOD_LAYER2 : BLOOD_LAYER1));
        part.render(stack,consumer,packedLight,OverlayTexture.NO_OVERLAY);
    }

    public void renderArmorBuffer(T entity, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);
        boolean flag = itemStack.hasFoil();
        if (itemStack.getItem() instanceof ArmorItem armorItem){
            if (armorItem instanceof DyeableLeatherItem) {
                int i = ((DyeableLeatherItem)armorItem).getColor(itemStack);
                float f = (float)(i >> 16 & 255) / 255.0F;
                float f1 = (float)(i >> 8 & 255) / 255.0F;
                float f2 = (float)(i & 255) / 255.0F;
                RenderChestplate(poseStack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,f,f1,f2,1,this.getArmorResource(entity, itemStack, EquipmentSlot.CHEST, (String)null),flag);
            } else {
                RenderChestplate(poseStack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,1,1,1,1,this.getArmorResource(entity, itemStack, EquipmentSlot.CHEST, (String)null),flag);
            }
        }
    }
    private void RenderChestplate(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, float red, float green, float blue, float alpha ,ResourceLocation location,boolean glint){
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(location));
        Nucklemodel.ChestPlate.render(poseStack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
        if (glint){
            Nucklemodel.ChestPlate.render(poseStack, bufferSource.getBuffer(RenderType.entityGlint()), packedLight, packedOverlay, red, green, blue, alpha);
        }
        renderBloodLayer(Nucklemodel.ChestPlate,EquipmentSlot.CHEST,poseStack,bufferSource,packedLight);
    }

    public ResourceLocation getArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "minecraft";
        int idx = texture.indexOf(58);
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }

        String s1 = String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_%d%s.png", domain, texture,slot == EquipmentSlot.LEGS ? 2 :  1, type == null ? "" : String.format(Locale.ROOT, "_%s", type));
        s1 = ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
        ResourceLocation resourcelocation = ARMOR_LOCATION_CACHE.get(s1);
        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            ARMOR_LOCATION_CACHE.put(s1, resourcelocation);
        }
        return resourcelocation;
    }
}
