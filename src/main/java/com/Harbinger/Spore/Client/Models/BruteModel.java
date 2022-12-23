package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Brute;
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

public class BruteModel<T extends Brute> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "brutemodel"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart RightArm;
	private final ModelPart tail;
	private final ModelPart LeftArm;
	private final ModelPart tendril;
	private final ModelPart tendril4;
	private final ModelPart tendril7;

	public BruteModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.RightArm = root.getChild("RightArm");
		this.tail = root.getChild("tail");
		this.LeftArm = root.getChild("LeftArm");
		this.tendril = root.getChild("tendril");
		this.tendril4 = root.getChild("tendril4");
		this.tendril7 = root.getChild("tendril7");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 0).addBox(-3.5F, 2.0F, -7.5F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-4.0F, -6.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(36, 10).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(18, 30).addBox(-3.5F, 2.0F, -7.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(28, 0).addBox(3.5F, 2.0F, -7.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -5.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -4.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(24, 15).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, 3.0F, -2.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 35).addBox(-3.5F, 9.0F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 42).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(26, 25).addBox(-4.0F, -3.5F, -3.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition body_r2 = body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, -4.0F, 10.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, -0.25F, 0.3927F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(50, 31).addBox(-2.0F, -3.0F, -1.5F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 8.0F, 0.0F));

		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(62, 19).addBox(0.0F, 0.0F, -4.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.0F, -3.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(56, 62).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition cube_r2 = RightForArm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 58).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r3 = RightForArm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(62, 0).addBox(-2.0F, 0.25F, -1.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 31).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, 7.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(36, 0).addBox(-2.25F, -2.0F, 0.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.25F, 5.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(36, 44).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.5F, 6.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(16, 44).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, -3.0F, -1.5F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 8.0F, 0.0F));

		PartDefinition RightArm_r2 = LeftArm.addOrReplaceChild("RightArm_r2", CubeListBuilder.create().texOffs(28, 53).addBox(0.0F, 0.0F, -4.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create().texOffs(14, 53).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(1.0F, 7.0F, 0.0F));

		PartDefinition cube_r4 = LeftForArm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(52, 10).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r5 = LeftForArm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(52, 49).addBox(-1.0F, 0.25F, -1.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition tendril = partdefinition.addOrReplaceChild("tendril", CubeListBuilder.create().texOffs(70, 40).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 4.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition tendril2 = tendril.addOrReplaceChild("tendril2", CubeListBuilder.create().texOffs(66, 53).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition tendril3 = tendril2.addOrReplaceChild("tendril3", CubeListBuilder.create().texOffs(32, 66).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition tendril4 = partdefinition.addOrReplaceChild("tendril4", CubeListBuilder.create().texOffs(24, 66).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 7.0F, -1.0F, -0.4363F, 0.0F, -0.3491F));

		PartDefinition tendril5 = tendril4.addOrReplaceChild("tendril5", CubeListBuilder.create().texOffs(16, 66).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition tendril6 = tendril5.addOrReplaceChild("tendril6", CubeListBuilder.create().texOffs(64, 32).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition tendril7 = partdefinition.addOrReplaceChild("tendril7", CubeListBuilder.create().texOffs(8, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 7.0F, -1.0F, -0.4363F, 0.0F, 0.3491F));

		PartDefinition tendril8 = tendril7.addOrReplaceChild("tendril8", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition tendril9 = tendril8.addOrReplaceChild("tendril9", CubeListBuilder.create().texOffs(62, 43).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)) {
			this.tail.yRot = Mth.cos(limbSwing * 0.1f);
			this.tail.getChild("tail2").yRot = Mth.cos(limbSwing * 0.1f);
			this.tail.getChild("tail2").getChild("tail3").yRot = Mth.cos(limbSwing * 0.1f);
			this.tail.getChild("tail2").getChild("tail3").getChild("tail4").yRot = Mth.cos(limbSwing * 0.1f);
		}
		this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
		this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
		if (RightArm.xRot > 0) {
			this.RightArm.getChild("RightForArm").xRot = -RightArm.xRot;
		}
		if (LeftArm.xRot > 0) {
			this.LeftArm.getChild("LeftForArm").xRot = -LeftArm.xRot;
		}

		this.tail.yRot = Mth.cos(ageInTicks/6)/6;
		this.tail.getChild("tail2").yRot = Mth.cos(ageInTicks/6)/6;
		this.tail.getChild("tail2").getChild("tail3").yRot = Mth.cos(ageInTicks/6)/6;
		this.tail.getChild("tail2").getChild("tail3").getChild("tail4").yRot = Mth.cos(ageInTicks/6)/6;

		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch /  ( 90F / (float) Math.PI);
		this.head.getChild("jaw").xRot = Mth.sin(ageInTicks/8)/10;

		this.tendril.xRot = -1f + Mth.sin(ageInTicks/8)/10;
		this.tendril.getChild("tendril2").xRot = 1f + Mth.sin(ageInTicks/6)/6;
		this.tendril.getChild("tendril2").getChild("tendril3").xRot = 1f + Mth.sin(ageInTicks/4)/5;

		this.tendril4.getChild("tendril5").zRot = -1f + Mth.sin(ageInTicks/6)/7;
		this.tendril4.getChild("tendril5").getChild("tendril6").zRot = -1f + Mth.sin(ageInTicks/5)/4;

		this.tendril7.getChild("tendril8").zRot = 1f + Mth.sin(ageInTicks/7)/6;
		this.tendril7.getChild("tendril8").getChild("tendril9").zRot = 1f + Mth.sin(ageInTicks/5)/6;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendril.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendril4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendril7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}