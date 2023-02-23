package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Scamper;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ScamperModel<T extends Scamper> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "scampermodel"), "main");
	private final ModelPart body;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart head;
	private final ModelPart tumor;
	private final ModelPart tumor2;
	private final ModelPart tumor3;
	private final ModelPart tumor4;
	private final ModelPart tendril;
	private final ModelPart tendril2;

	public ScamperModel(ModelPart root) {
		this.body = root.getChild("body");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.head = root.getChild("head");
		this.tumor = root.getChild("tumor");
		this.tumor2 = root.getChild("tumor2");
		this.tumor3 = root.getChild("tumor3");
		this.tumor4 = root.getChild("tumor4");
		this.tendril = root.getChild("tendril");
		this.tendril2 = root.getChild("tendril2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 42).addBox(-4.0F, 8.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 2.0F, 2.0F));

		PartDefinition spine = body.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(42, 20).addBox(-2.0F, -4.5F, -4.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 8.0F, 2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition back = spine.addOrReplaceChild("back", CubeListBuilder.create().texOffs(62, 8).addBox(0.0F, -4.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back2 = spine.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(62, 0).addBox(-1.0F, -4.0F, -2.25F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition spine2 = body.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(0, 23).addBox(-4.0F, -5.5F, -4.5F, 8.0F, 6.0F, 5.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 4.0F, 1.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition back3 = spine2.addOrReplaceChild("back3", CubeListBuilder.create().texOffs(58, 16).addBox(0.0F, -4.0F, -1.75F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back4 = spine2.addOrReplaceChild("back4", CubeListBuilder.create().texOffs(48, 12).addBox(-2.0F, -4.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(12, 53).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 2.0F));

		PartDefinition leftForLeg = LeftLeg.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(48, 49).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(26, 53).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 2.0F));

		PartDefinition rightForLeg = RightLeg.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(52, 39).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(52, 59).addBox(-1.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 4.0F, 0.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition cube_r1 = LeftForArm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 57).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(60, 24).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 4.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(40, 59).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(42, 6).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.04F)), PartPose.offset(0.0F, 2.0F, -1.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -5.0F, -4.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition tendrilH = head.addOrReplaceChild("tendrilH", CubeListBuilder.create().texOffs(32, 31).addBox(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -4.0F));

		PartDefinition tendrilH2 = head.addOrReplaceChild("tendrilH2", CubeListBuilder.create().texOffs(32, 23).addBox(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -8.0F, -2.0F));

		PartDefinition tendrilH3 = head.addOrReplaceChild("tendrilH3", CubeListBuilder.create().texOffs(16, 26).addBox(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -8.0F, -3.0F));

		PartDefinition tendrilH4 = head.addOrReplaceChild("tendrilH4", CubeListBuilder.create().texOffs(26, 15).addBox(0.0F, -4.0F, -1.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -7.0F, 4.0F));

		PartDefinition tendrilH5 = head.addOrReplaceChild("tendrilH5", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, -4.0F, -1.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, 4.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(44, 0).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw2 = jaw.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(62, 35).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(0.0F, -1.7F, -5.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -0.5F, -3.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition jaw3 = jaw.addOrReplaceChild("jaw3", CubeListBuilder.create().texOffs(61, 55).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -1.7F, -5.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -0.5F, -3.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition tumor = partdefinition.addOrReplaceChild("tumor", CubeListBuilder.create().texOffs(48, 30).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 10.0F, 3.0F));

		PartDefinition tumor2 = partdefinition.addOrReplaceChild("tumor2", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 10.0F, 3.0F, -0.1309F, -0.4363F, 0.0F));

		PartDefinition tumor3 = partdefinition.addOrReplaceChild("tumor3", CubeListBuilder.create().texOffs(26, 8).addBox(-3.0F, -4.0F, -1.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 5.0F, 2.0F, 0.2618F, -0.2618F, 0.0F));

		PartDefinition tumor4 = partdefinition.addOrReplaceChild("tumor4", CubeListBuilder.create().texOffs(62, 49).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 8.0F, 0.0F, 0.2618F, 0.0F, 0.5672F));

		PartDefinition tendril = partdefinition.addOrReplaceChild("tendril", CubeListBuilder.create().texOffs(38, 40).addBox(0.0F, -4.0F, -1.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.0F, 3.0F));

		PartDefinition tendril2 = partdefinition.addOrReplaceChild("tendril2", CubeListBuilder.create().texOffs(24, 40).addBox(-2.0F, -6.0F, 0.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		if (entity.isAggressive()) {
			this.RightArm.xRot = -90F - (Mth.sin(ageInTicks / 4) / 7);
			this.LeftArm.xRot = -90F + (Mth.sin(ageInTicks / 4) / 7);
		}else if (!(limbSwingAmount > -0.05F && limbSwingAmount < 0.15F)){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
			this.RightArm.zRot = 0;
			this.LeftArm.zRot = 0;
		}else {
			this.RightArm.xRot = Mth.sin(ageInTicks/8)/10;
			this.LeftArm.xRot = -Mth.sin(ageInTicks/8)/10;
		}





		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch /  ( 90F / (float) Math.PI);
		this.LeftLeg.xRot = Mth.cos(limbSwing ) * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing ) * -limbSwingAmount;
		if (LeftLeg.xRot < 0){
			this.LeftLeg.getChild("leftForLeg").xRot = -LeftLeg.xRot;}
		if (RightLeg.xRot < 0){
			this.RightLeg.getChild("rightForLeg").xRot = -RightLeg.xRot;}


		this.tendril.xRot = Mth.sin(ageInTicks/6)/6;
		this.tendril2.xRot = Mth.cos(ageInTicks/7)/4;

		this.tumor.xScale = 1 + Mth.sin(ageInTicks/6)/6;
		this.tumor.yScale = 1 - Mth.sin(ageInTicks/6)/6;
		this.tumor.zScale = 1 + Mth.sin(ageInTicks/6)/6;

		this.tumor2.xScale = 1 + Mth.sin(ageInTicks/6)/7;
		this.tumor2.yScale = 1 - Mth.sin(ageInTicks/5)/6;
		this.tumor2.zScale = 1 + Mth.sin(ageInTicks/6)/4;

		this.tumor3.xScale = 1 + Mth.sin(ageInTicks/6)/8;
		this.tumor3.yScale = 1 - Mth.sin(ageInTicks/6)/6;
		this.tumor3.zScale = 1 + Mth.sin(ageInTicks/6)/8;

		this.tumor4.xScale = 1 + Mth.sin(ageInTicks/8)/6;
		this.tumor4.yScale = 1 - Mth.sin(ageInTicks/8)/6;
		this.tumor4.zScale = 1 + Mth.sin(ageInTicks/8)/6;

		this.head.getChild("tendrilH").xRot = Mth.sin(ageInTicks/6)/5;
		this.head.getChild("tendrilH2").xRot = -Mth.sin(ageInTicks/6)/8;
		this.head.getChild("tendrilH3").xRot = Mth.sin(ageInTicks/6)/7;
		this.head.getChild("tendrilH4").xRot = -Mth.sin(ageInTicks/7)/5;
		this.head.getChild("tendrilH5").xRot = Mth.sin(ageInTicks/8)/5;

		this.head.getChild("jaw").xRot = 0.2f + Mth.sin(ageInTicks/6)/5;
		this.head.getChild("jaw").getChild("jaw2").xRot = 0.2f + Mth.sin(ageInTicks/6)/5;
		this.head.getChild("jaw").getChild("jaw2").xRot = 0.2f + Mth.sin(ageInTicks/6)/5;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tumor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tumor2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tumor3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tumor4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendril.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendril2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}