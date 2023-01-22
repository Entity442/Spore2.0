package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
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
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart head;
	private final ModelPart ribs;
	private final ModelPart ribs2;
	private final ModelPart ribs3;
	private final ModelPart ribs4;

	public BusserModel(ModelPart root) {
		this.body = root.getChild("body");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.head = root.getChild("head");
		this.ribs = root.getChild("ribs");
		this.ribs2 = root.getChild("ribs2");
		this.ribs3 = root.getChild("ribs3");
		this.ribs4 = root.getChild("ribs4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(48, 0).addBox(-3.0F, 10.0F, -1.75F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 48).addBox(0.0F, -1.0F, 0.0F, 0.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition spine = body.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(36, 35).addBox(-2.0F, -2.5F, -4.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(-0.5F))
		.texOffs(22, 26).addBox(-3.0F, -4.5F, -5.2F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 8.0F, 2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition back = spine.addOrReplaceChild("back", CubeListBuilder.create().texOffs(54, 32).addBox(0.0F, -4.0F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back2 = spine.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(32, 52).addBox(-1.0F, -4.0F, -2.25F, 2.0F, 6.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition spine2 = body.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(22, 14).addBox(-4.0F, -5.5F, -5.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 4.0F, 1.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition back3 = spine2.addOrReplaceChild("back3", CubeListBuilder.create().texOffs(22, 43).addBox(0.0F, -4.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back4 = spine2.addOrReplaceChild("back4", CubeListBuilder.create().texOffs(9, 39).addBox(-3.0F, -4.0F, -2.25F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(48, 49).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 11.0F, 2.0F));

		PartDefinition leftForLeg = LeftLeg.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(0, 56).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition cube_r2 = leftForLeg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.7F, -0.1F, 1.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.45F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition toesL = leftForLeg.addOrReplaceChild("toesL", CubeListBuilder.create().texOffs(60, 56).addBox(-1.75F, 0.0F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.45F, 5.0F, -2.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(52, 15).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 11.0F, 2.0F));

		PartDefinition rightForLeg = RightLeg.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(58, 42).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition cube_r3 = rightForLeg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(31, 62).addBox(-1.75F, -0.1F, 1.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition toesR = rightForLeg.addOrReplaceChild("toesR", CubeListBuilder.create().texOffs(58, 25).addBox(-1.25F, 0.0F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.45F, 5.0F, -2.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(12, 60).addBox(-1.0F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(53, 59).addBox(-0.5F, 2.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(1.0F, -1.0F, 1.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 1.0F, 0.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create().texOffs(22, 64).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.5F, 6.75F, 0.0F));

		PartDefinition cube_r4 = LeftForArm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 46).addBox(1.01F, -5.0F, -0.25F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.25F, 1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r5 = LeftForArm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(22, 26).addBox(0.0F, 0.0F, 0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftHand = LeftForArm.addOrReplaceChild("LeftHand", CubeListBuilder.create().texOffs(47, 65).addBox(0.0F, -0.25F, -0.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 5.75F, 0.0F));

		PartDefinition cube_r6 = LeftHand.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(43, 65).addBox(0.0F, 0.0F, -1.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r7 = LeftHand.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 66).addBox(0.0F, 0.0F, -0.25F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r8 = LeftHand.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 14).addBox(0.5F, -4.0F, -5.0F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(41, 59).addBox(-2.0F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(56, 6).addBox(-1.5F, 2.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(-1.0F, -1.0F, 1.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 1.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(63, 61).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-0.5F, 6.75F, 0.0F));

		PartDefinition cube_r9 = RightForArm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(44, 5).addBox(0.01F, -5.0F, -0.25F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.25F, 1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r10 = RightForArm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(22, 14).addBox(0.0F, 0.0F, 0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightArm2 = RightForArm.addOrReplaceChild("RightArm2", CubeListBuilder.create().texOffs(39, 65).addBox(0.0F, -0.25F, -0.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(-1.0F, 5.75F, 0.0F));

		PartDefinition cube_r11 = RightArm2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(49, 32).addBox(0.0F, 0.0F, -1.75F, 1.0F, 7.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r12 = RightArm2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(54, 42).addBox(0.0F, 0.0F, -0.25F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r13 = RightArm2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 3).addBox(0.5F, -4.0F, -5.0F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.01F))
		.texOffs(24, 0).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 6).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(40, 26).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition ear = head.addOrReplaceChild("ear", CubeListBuilder.create().texOffs(0, 42).addBox(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -5.0F, 2.0F));

		PartDefinition ear2 = head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(40, 39).addBox(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 2.0F));

		PartDefinition ribs = partdefinition.addOrReplaceChild("ribs", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 6.0F, 0.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition r = ribs.addOrReplaceChild("r", CubeListBuilder.create().texOffs(31, 9).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r1 = r.addOrReplaceChild("r1", CubeListBuilder.create().texOffs(31, 8).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r2 = r1.addOrReplaceChild("r2", CubeListBuilder.create().texOffs(31, 7).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition ribs2 = partdefinition.addOrReplaceChild("ribs2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 8.0F, 1.5F, 0.1745F, 0.0F, 0.0F));

		PartDefinition r3 = ribs2.addOrReplaceChild("r3", CubeListBuilder.create().texOffs(31, 6).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r4 = r3.addOrReplaceChild("r4", CubeListBuilder.create().texOffs(27, 7).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r5 = r4.addOrReplaceChild("r5", CubeListBuilder.create().texOffs(27, 6).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition ribs3 = partdefinition.addOrReplaceChild("ribs3", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 8.0F, 1.5F, -0.1745F, 0.0F, -3.1416F));

		PartDefinition r6 = ribs3.addOrReplaceChild("r6", CubeListBuilder.create().texOffs(23, 7).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r7 = r6.addOrReplaceChild("r7", CubeListBuilder.create().texOffs(23, 6).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r8 = r7.addOrReplaceChild("r8", CubeListBuilder.create().texOffs(23, 3).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition ribs4 = partdefinition.addOrReplaceChild("ribs4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 8.0F, 0.5F, -0.1309F, 0.0F, -3.1416F));

		PartDefinition r9 = ribs4.addOrReplaceChild("r9", CubeListBuilder.create().texOffs(23, 2).addBox(-2.0F, 2.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition r10 = r9.addOrReplaceChild("r10", CubeListBuilder.create().texOffs(23, 1).addBox(-2.0F, 2.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition r11 = r10.addOrReplaceChild("r11", CubeListBuilder.create().texOffs(23, 0).addBox(-2.0F, 2.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		if (entity.isOnGround() || entity.isInFluidType()){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
			this.RightArm.zRot = 0;
			this.LeftArm.zRot = 0;
		}else {
			this.RightArm.xRot = 0;
			this.LeftArm.xRot = 0;

			this.RightArm.zRot = 1.1f + Mth.sin(ageInTicks/3);
			this.LeftArm.zRot = -1.1f - Mth.sin(ageInTicks/3);
		}


		this.LeftLeg.xRot = Mth.cos(limbSwing ) * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing ) * -1F * limbSwingAmount;
		if (LeftLeg.xRot > 0){
			this.LeftLeg.getChild("leftForLeg").xRot = -LeftLeg.xRot;}
		if (RightLeg.xRot > 0){
			this.RightLeg.getChild("rightForLeg").xRot = -RightLeg.xRot;}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ribs4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}