package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.6.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Busser;
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

public class BusserModel<T extends Busser> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "bussermodel"), "main");
	private final ModelPart body;
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart head;
	private final ModelPart ribs;
	private final ModelPart ribs2;
	private final ModelPart ribs3;
	private final ModelPart ribs4;
	private final ModelPart Tail;

	public BusserModel(ModelPart root) {
		this.body = root.getChild("body");
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.head = root.getChild("head");
		this.ribs = root.getChild("ribs");
		this.ribs2 = root.getChild("ribs2");
		this.ribs3 = root.getChild("ribs3");
		this.ribs4 = root.getChild("ribs4");
		this.Tail = root.getChild("Tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(50, 19).addBox(-3.0F, 10.0F, -1.75F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, -1.0F, 0.0F, 0.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition spine = body.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(28, 42).addBox(-2.0F, -2.5F, -4.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(-0.5F))
		.texOffs(22, 26).addBox(-3.0F, -4.5F, -5.2F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 8.0F, 2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition back = spine.addOrReplaceChild("back", CubeListBuilder.create().texOffs(8, 56).addBox(0.0F, -4.0F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back2 = spine.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(54, 46).addBox(-1.0F, -4.0F, -2.25F, 2.0F, 6.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition spine2 = body.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(22, 14).addBox(-4.0F, -5.5F, -5.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 4.0F, 1.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition back3 = spine2.addOrReplaceChild("back3", CubeListBuilder.create().texOffs(44, 10).addBox(0.0F, -4.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back4 = spine2.addOrReplaceChild("back4", CubeListBuilder.create().texOffs(0, 44).addBox(-3.0F, -4.0F, -2.25F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(59, 40).addBox(-1.0F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 60).addBox(-0.5F, 2.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(14, 34).addBox(1.0F, -1.0F, 1.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 1.0F, 0.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.5F, 6.75F, 0.0F));

		PartDefinition cube_r2 = LeftForArm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 46).addBox(1.01F, -5.0F, -0.25F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.25F, 1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r3 = LeftForArm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 26).addBox(0.0F, 0.0F, 0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftHand = LeftForArm.addOrReplaceChild("LeftHand", CubeListBuilder.create().texOffs(42, 60).addBox(0.0F, -0.25F, -0.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 5.75F, 0.0F));

		PartDefinition cube_r4 = LeftHand.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(38, 60).addBox(0.0F, 0.0F, -1.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r5 = LeftHand.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(46, 60).addBox(0.0F, 0.0F, -0.25F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r6 = LeftHand.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 14).addBox(0.5F, -4.0F, -5.0F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(57, 9).addBox(-2.0F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(58, 32).addBox(-1.5F, 2.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-1.0F, -1.0F, 1.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 1.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-0.5F, 6.75F, 0.0F));

		PartDefinition cube_r7 = RightForArm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(46, 36).addBox(0.01F, -5.0F, -0.25F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.25F, 1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r8 = RightForArm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(22, 14).addBox(0.0F, 0.0F, 0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightHand = RightForArm.addOrReplaceChild("RightHand", CubeListBuilder.create().texOffs(34, 60).addBox(0.0F, -0.25F, -0.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(-1.0F, 5.75F, 0.0F));

		PartDefinition cube_r9 = RightHand.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(30, 60).addBox(0.0F, 0.0F, -1.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r10 = RightHand.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(8, 53).addBox(0.0F, 0.0F, -0.25F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r11 = RightHand.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 3).addBox(0.5F, -4.0F, -5.0F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.01F))
		.texOffs(18, 35).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 37).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(40, 26).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition ear = head.addOrReplaceChild("ear", CubeListBuilder.create().texOffs(30, 46).addBox(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -5.0F, 2.0F));

		PartDefinition ear2 = head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(18, 46).addBox(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 2.0F));

		PartDefinition ribs = partdefinition.addOrReplaceChild("ribs", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 6.0F, 0.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition r = ribs.addOrReplaceChild("r", CubeListBuilder.create().texOffs(35, 13).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r1 = r.addOrReplaceChild("r1", CubeListBuilder.create().texOffs(35, 12).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r2 = r1.addOrReplaceChild("r2", CubeListBuilder.create().texOffs(35, 11).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition ribs2 = partdefinition.addOrReplaceChild("ribs2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 8.0F, 1.5F, 0.1745F, 0.0F, 0.0F));

		PartDefinition r3 = ribs2.addOrReplaceChild("r3", CubeListBuilder.create().texOffs(35, 10).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r4 = r3.addOrReplaceChild("r4", CubeListBuilder.create().texOffs(31, 13).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r5 = r4.addOrReplaceChild("r5", CubeListBuilder.create().texOffs(31, 12).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition ribs3 = partdefinition.addOrReplaceChild("ribs3", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 8.0F, 1.5F, -0.1745F, 0.0F, -3.1416F));

		PartDefinition r6 = ribs3.addOrReplaceChild("r6", CubeListBuilder.create().texOffs(31, 11).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r7 = r6.addOrReplaceChild("r7", CubeListBuilder.create().texOffs(31, 10).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r8 = r7.addOrReplaceChild("r8", CubeListBuilder.create().texOffs(31, 3).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition ribs4 = partdefinition.addOrReplaceChild("ribs4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 8.0F, 0.5F, -0.1309F, 0.0F, -3.1416F));

		PartDefinition r9 = ribs4.addOrReplaceChild("r9", CubeListBuilder.create().texOffs(31, 2).addBox(-2.0F, 2.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r10 = r9.addOrReplaceChild("r10", CubeListBuilder.create().texOffs(29, 1).addBox(-2.0F, 2.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r11 = r10.addOrReplaceChild("r11", CubeListBuilder.create().texOffs(29, 0).addBox(-2.0F, 2.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition Tail = partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, 0.0F, -1.75F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 2.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(52, 0).addBox(-2.5F, 0.0F, -1.25F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition claw = Tail2.addOrReplaceChild("claw", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r12 = claw.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(54, 56).addBox(-1.0F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.2618F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		if (entity.isOnGround() || entity.isInFluidType()){
			if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
			} else {
				this.RightArm.xRot = Mth.sin(ageInTicks / 8) / 10;
				this.LeftArm.xRot = Mth.sin(ageInTicks / 8) / 10;
			}
			this.RightArm.zRot = 0;
			this.LeftArm.zRot = 0;
			this.LeftArm.getChild("LeftForArm").zRot = 0;
			this.RightArm.getChild("RightForArm").zRot = 0;
		}else {
			this.RightArm.xRot = 0;
			this.LeftArm.xRot = 0;

			this.RightArm.zRot = 1.1f + Mth.sin(ageInTicks/3);
			this.LeftArm.zRot = -1.1f - Mth.sin(ageInTicks/3);

			if (Mth.sin(ageInTicks/3) < 0){
				this.RightArm.getChild("RightForArm").zRot = Mth.sin(ageInTicks/3)/2;}
			if (Mth.sin(ageInTicks/3) < 0){
				this.LeftArm.getChild("LeftForArm").zRot = -Mth.sin(ageInTicks/3)/2;}
		}
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (90F / (float) Math.PI);
		this.head.getChild("jaw").xRot = Mth.sin(ageInTicks / 8) / 10;
		this.head.getChild("ear").xRot = Mth.sin(ageInTicks / 4) / 6;
		this.head.getChild("ear2").xRot = Mth.sin(ageInTicks / 4) / 6;


		if (entity.isVehicle() || entity.isOnGround()){
			this.Tail.xRot = 0;
			this.Tail.getChild("Tail2").xRot = 0;
			this.Tail.getChild("Tail2").getChild("claw").xRot = 0;
		}else {
			this.Tail.xRot = -0.6F + Mth.sin(ageInTicks / 3) / 6;
			this.Tail.getChild("Tail2").xRot = -0.6F + Mth.sin(ageInTicks / 3) / 6;
			this.Tail.getChild("Tail2").getChild("claw").xRot = -0.6F + Mth.sin(ageInTicks / 3) / 6;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}