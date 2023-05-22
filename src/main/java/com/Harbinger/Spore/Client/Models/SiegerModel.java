package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Sieger;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SiegerModel<T extends Sieger> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "siegermodel"), "main");
	private final ModelPart mainbody;
	private final ModelPart jaw;
	private final ModelPart lowerbody;
	private final ModelPart tail;
	private final ModelPart RightLegJointY;
	private final ModelPart LeftLegJointY;
	private final ModelPart BackLeftLeg;
	private final ModelPart BackRightLeg;
	private final ModelPart body;

	public SiegerModel(ModelPart root) {
		this.mainbody = root.getChild("mainbody");
		this.jaw = root.getChild("jaw");
		this.lowerbody = root.getChild("lowerbody");
		this.tail = root.getChild("tail");
		this.RightLegJointY = root.getChild("RightLegJointY");
		this.LeftLegJointY = root.getChild("LeftLegJointY");
		this.BackLeftLeg = root.getChild("BackLeftLeg");
		this.BackRightLeg = root.getChild("BackRightLeg");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition mainbody = partdefinition.addOrReplaceChild("mainbody", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -13.0F, -14.0F, 30.0F, 13.0F, 38.0F, new CubeDeformation(0.0F))
		.texOffs(90, 205).addBox(-15.0F, 0.0F, 6.0F, 30.0F, 17.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(191, 50).addBox(-15.0F, -4.0F, 24.0F, 30.0F, 17.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(72, 133).addBox(-5.0F, -15.0F, -36.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(274, 115).addBox(-7.0F, -16.0F, -28.0F, 15.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -5.0F));

		PartDefinition cube_r1 = mainbody.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(146, 68).addBox(-5.0F, -10.0F, -6.0F, 9.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, 0.0F, 23.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition cube_r2 = mainbody.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(74, 240).addBox(-12.0F, 7.0F, 14.0F, 23.0F, 9.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(155, 163).addBox(-12.0F, 4.0F, -21.0F, 23.0F, 9.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 13.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r3 = mainbody.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(280, 226).addBox(-5.0F, -7.0F, -2.0F, 12.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -16.0F, -6.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r4 = mainbody.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(281, 140).addBox(-5.0F, -7.0F, -2.0F, 12.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -16.0F, -6.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r5 = mainbody.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(243, 263).addBox(-9.0F, -7.0F, 4.0F, 19.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -24.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r6 = mainbody.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(271, 69).addBox(-8.0F, -9.0F, -14.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -23.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition Head_r1 = mainbody.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(52, 220).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -7.0F, -24.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition Head_r2 = mainbody.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(98, 21).addBox(-4.0F, -7.0F, -1.0F, 9.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -7.0F, -23.0F, 0.0F, 0.6109F, 0.0F));

		PartDefinition cube_r7 = mainbody.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 179).addBox(-12.0F, -7.0F, -9.0F, 24.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition teeth3 = mainbody.addOrReplaceChild("teeth3", CubeListBuilder.create().texOffs(72, 133).addBox(12.0F, 0.0F, -2.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(129, 21).addBox(11.0F, -1.0F, -7.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(125, 21).addBox(8.0F, -1.0F, -12.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(114, 68).addBox(7.0F, -1.0F, -16.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(29, 114).addBox(5.5F, 0.0F, -20.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 114).addBox(5.0F, 1.0F, -23.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(104, 68).addBox(4.75F, 1.0F, -26.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(98, 21).addBox(4.0F, 1.0F, -28.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(98, 0).addBox(2.0F, 1.0F, -30.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(102, 21).addBox(13.0F, 0.0F, -5.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.0F));

		PartDefinition teeth4 = mainbody.addOrReplaceChild("teeth4", CubeListBuilder.create().texOffs(86, 63).addBox(-14.0F, 0.0F, -2.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(82, 63).addBox(-13.0F, -1.0F, -7.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(78, 63).addBox(-10.0F, -1.0F, -12.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(74, 63).addBox(-9.0F, -1.0F, -16.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(29, 51).addBox(-7.5F, 0.0F, -20.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 51).addBox(-7.0F, 1.0F, -23.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(32, 29).addBox(-6.75F, 1.0F, -26.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(32, 6).addBox(-6.0F, 1.0F, -28.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(4, 3).addBox(-4.0F, 1.0F, -30.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-15.0F, 0.0F, -5.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.0F));

		PartDefinition body3 = mainbody.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(174, 150).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(208, 38).addBox(-3.5F, 4.5F, -2.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(-0.3F))
		.texOffs(0, 226).addBox(-3.5F, 10.0F, -2.0F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, 3.0F, 13.0F, 0.4363F, -1.3963F, 0.0F));

		PartDefinition head3 = body3.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(142, 150).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(122, 196).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(234, 162).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3712F, -0.1313F, -0.3244F));

		PartDefinition jaw4 = head3.addOrReplaceChild("jaw4", CubeListBuilder.create().texOffs(0, 220).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition RightArm3 = body3.addOrReplaceChild("RightArm3", CubeListBuilder.create().texOffs(57, 179).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm3 = body3.addOrReplaceChild("LeftArm3", CubeListBuilder.create().texOffs(174, 170).addBox(-1.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.3054F, 0.0F, -0.6981F));

		PartDefinition LeftForArm3 = LeftArm3.addOrReplaceChild("LeftForArm3", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition cube_r8 = LeftForArm3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 150).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition jaw = partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(74, 53).addBox(-14.75F, -21.0F, -11.0F, 0.0F, 19.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(14.75F, -21.0F, -11.0F, 0.0F, 19.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 1.0F));

		PartDefinition cube_r9 = jaw.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(269, 48).addBox(-7.0F, -1.0F, -9.0F, 15.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -29.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r10 = jaw.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(267, 187).addBox(-10.0F, -1.0F, -11.0F, 21.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -18.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r11 = jaw.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(168, 226).addBox(-15.0F, -4.0F, -18.0F, 30.0F, 5.0F, 18.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition teeth = jaw.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(142, 153).addBox(-14.0F, -5.0F, -2.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(146, 150).addBox(-13.0F, -4.0F, -7.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(150, 88).addBox(-10.0F, -4.0F, -12.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(150, 68).addBox(-9.0F, -4.0F, -16.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(112, 149).addBox(-7.0F, -5.0F, -20.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(142, 148).addBox(-6.0F, -6.0F, -23.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(146, 88).addBox(-4.0F, -6.0F, -25.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(146, 68).addBox(-15.0F, -5.0F, -5.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -11.0F));

		PartDefinition teeth2 = jaw.addOrReplaceChild("teeth2", CubeListBuilder.create().texOffs(112, 144).addBox(12.0F, -5.0F, -2.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(142, 143).addBox(11.0F, -4.0F, -7.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(142, 138).addBox(8.0F, -4.0F, -12.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(142, 133).addBox(7.0F, -4.0F, -16.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(112, 139).addBox(5.0F, -5.0F, -20.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(76, 138).addBox(4.0F, -6.0F, -23.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 138).addBox(2.0F, -6.0F, -25.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(76, 133).addBox(13.0F, -5.0F, -5.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -11.0F));

		PartDefinition lowerbody = partdefinition.addOrReplaceChild("lowerbody", CubeListBuilder.create().texOffs(164, 0).addBox(-14.0F, -5.5F, -1.0F, 28.0F, 16.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 37.0F));

		PartDefinition cube_r12 = lowerbody.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(216, 85).addBox(-16.0F, -10.0F, 0.0F, 15.0F, 24.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.0F, 0.4363F, -0.3491F, 0.0F));

		PartDefinition cube_r13 = lowerbody.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 220).addBox(-1.0F, -10.0F, 0.0F, 15.0F, 24.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.0F, 0.4363F, 0.3491F, 0.0F));

		PartDefinition body4 = lowerbody.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(72, 151).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(168, 205).addBox(-3.5F, 4.5F, -2.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(-1.0F, -15.0F, 24.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition head4 = body4.addOrReplaceChild("head4", CubeListBuilder.create().texOffs(146, 88).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(98, 196).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 234).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition jaw5 = head4.addOrReplaceChild("jaw5", CubeListBuilder.create().texOffs(216, 95).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition RightArm4 = body4.addOrReplaceChild("RightArm4", CubeListBuilder.create().texOffs(34, 141).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition RightForArm3 = RightArm4.addOrReplaceChild("RightForArm3", CubeListBuilder.create().texOffs(34, 132).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LeftArm4 = body4.addOrReplaceChild("LeftArm4", CubeListBuilder.create().texOffs(34, 123).addBox(-1.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

		PartDefinition LeftForArm4 = LeftArm4.addOrReplaceChild("LeftForArm4", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition cube_r14 = LeftForArm4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(34, 114).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -3.0F, 57.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition tailjoint = tail.addOrReplaceChild("tailjoint", CubeListBuilder.create().texOffs(234, 150).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 10.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail2 = tailjoint.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 25.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition tailjoint2 = tail2.addOrReplaceChild("tailjoint2", CubeListBuilder.create().texOffs(237, 11).addBox(-4.5F, -6.0F, -2.0F, 9.0F, 10.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition tail3 = tailjoint2.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 25.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition tailjoint3 = tail3.addOrReplaceChild("tailjoint3", CubeListBuilder.create().texOffs(237, 226).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 10.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition tail4 = tailjoint3.addOrReplaceChild("tail4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 25.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition tailjoint4 = tail4.addOrReplaceChild("tailjoint4", CubeListBuilder.create().texOffs(127, 240).addBox(-3.5F, -6.0F, -2.0F, 7.0F, 10.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition tumor = tailjoint4.addOrReplaceChild("tumor", CubeListBuilder.create().texOffs(0, 266).addBox(-8.0F, -8.0F, 7.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(98, 0).addBox(-6.0F, -7.0F, 0.0F, 12.0F, 14.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 22.0F));

		PartDefinition RightLegJointY = partdefinition.addOrReplaceChild("RightLegJointY", CubeListBuilder.create(), PartPose.offset(13.0F, -23.0F, -7.0F));

		PartDefinition RightLegJointZ = RightLegJointY.addOrReplaceChild("RightLegJointZ", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg = RightLegJointZ.addOrReplaceChild("leg", CubeListBuilder.create().texOffs(186, 205).addBox(-1.0F, -5.0F, -5.0F, 44.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition cube_r15 = leg.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(195, 251).addBox(10.0F, 37.0F, -6.5F, 12.0F, 38.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(39.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition cube_r16 = leg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(64, 266).addBox(1.0F, -7.0F, -4.5F, 9.0F, 48.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(39.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition body2 = leg.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(184, 38).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(216, 85).addBox(-3.5F, 4.5F, -2.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(36.0F, 30.0F, -1.0F, 0.5672F, -1.5272F, 0.0F));

		PartDefinition head2 = body2.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(142, 164).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(216, 156).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition jaw3 = head2.addOrReplaceChild("jaw3", CubeListBuilder.create().texOffs(216, 101).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition RightArm2 = body2.addOrReplaceChild("RightArm2", CubeListBuilder.create().texOffs(234, 187).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.6545F, 0.0873F, 0.0F));

		PartDefinition RightForArm2 = RightArm2.addOrReplaceChild("RightForArm2", CubeListBuilder.create().texOffs(234, 167).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.0873F, 0.0F, -0.3491F));

		PartDefinition LeftArm2 = body2.addOrReplaceChild("LeftArm2", CubeListBuilder.create().texOffs(76, 234).addBox(-1.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition LeftForArm2 = LeftArm2.addOrReplaceChild("LeftForArm2", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition cube_r17 = LeftForArm2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 232).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LeftLegJointY = partdefinition.addOrReplaceChild("LeftLegJointY", CubeListBuilder.create(), PartPose.offset(-12.0F, -24.0F, -7.0F));

		PartDefinition LeftLegJointZ = LeftLegJointY.addOrReplaceChild("LeftLegJointZ", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg2 = LeftLegJointZ.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 196).addBox(-43.0F, -7.0F, 9.0F, 44.0F, 14.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -14.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition twitstedleg2 = leg2.addOrReplaceChild("twitstedleg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-40.0F, 0.0F, 12.0F, -0.818F, -0.6085F, 0.4711F));

		PartDefinition cube_r18 = twitstedleg2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 51).addBox(-22.0F, 37.0F, -4.0F, 12.0F, 38.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition cube_r19 = twitstedleg2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(100, 269).addBox(-10.0F, -9.0F, -5.5F, 9.0F, 50.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition twitstedleg = leg2.addOrReplaceChild("twitstedleg", CubeListBuilder.create(), PartPose.offsetAndRotation(-40.0F, 0.0F, 12.0F, 0.5859F, 0.4559F, 0.3028F));

		PartDefinition cube_r20 = twitstedleg.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 114).addBox(-22.0F, 37.0F, -4.0F, 12.0F, 38.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition cube_r21 = twitstedleg.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(134, 277).addBox(-10.0F, -9.0F, -5.5F, 9.0F, 50.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition BackLeftLeg = partdefinition.addOrReplaceChild("BackLeftLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(12.0F, -7.0F, 77.0F, 1.0908F, 0.3491F, 0.0F));

		PartDefinition legback = BackLeftLeg.addOrReplaceChild("legback", CubeListBuilder.create().texOffs(0, 114).addBox(-5.0F, -13.0F, -7.0F, 13.0F, 19.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition BackLeftLeg2 = legback.addOrReplaceChild("BackLeftLeg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 0.0F, 40.0F, -2.2253F, 0.0F, 0.0F));

		PartDefinition legback2 = BackLeftLeg2.addOrReplaceChild("legback2", CubeListBuilder.create().texOffs(146, 87).addBox(-4.0F, -12.0F, -7.0F, 12.0F, 17.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r22 = legback2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(91, 6).addBox(-5.0F, -28.0F, 25.0F, 14.0F, 17.0F, 45.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition BackRightLeg = partdefinition.addOrReplaceChild("BackRightLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-18.0F, -7.0F, 77.0F, 1.0908F, -0.3491F, 0.0F));

		PartDefinition legback3 = BackRightLeg.addOrReplaceChild("legback3", CubeListBuilder.create().texOffs(74, 68).addBox(-5.0F, -13.0F, -7.0F, 13.0F, 19.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition BackRightLeg2 = legback3.addOrReplaceChild("BackRightLeg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 0.0F, 40.0F, -2.2253F, 0.0F, 0.0F));

		PartDefinition legback4 = BackRightLeg2.addOrReplaceChild("legback4", CubeListBuilder.create().texOffs(72, 133).addBox(-4.0F, -12.0F, -7.0F, 12.0F, 17.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r23 = legback4.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 51).addBox(-5.0F, -28.0F, 25.0F, 14.0F, 17.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(146, 102).addBox(-4.0F, -10.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(198, 150).addBox(-3.5F, -5.5F, -2.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(-0.3F))
		.texOffs(104, 68).addBox(3.0F, -13.0F, -3.0F, 2.0F, 12.0F, 6.0F, new CubeDeformation(0.1F))
		.texOffs(164, 0).addBox(5.0F, -17.0F, -3.0F, 2.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(25, 29).addBox(3.0F, -1.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(164, 38).addBox(-2.0F, -10.25F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(173, 68).addBox(-2.0F, -2.25F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-19.0F, -9.0F, 17.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(68, 57).addBox(1.0F, -15.0F, -5.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(123, 0).addBox(4.0F, -20.0F, -5.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(96, 133).addBox(-3.0F, -25.0F, 0.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, 0.2182F, 0.0F, 0.2182F));

		PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(137, 0).addBox(-2.0F, -21.0F, -6.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(90, 151).addBox(-6.0F, -18.0F, -4.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(164, 88).addBox(-12.0F, -28.0F, -6.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(164, 102).addBox(-12.0F, -30.0F, -9.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -0.2182F, 0.0F, 0.2182F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(166, 108).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(220, 150).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition jaw2 = head.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(166, 164).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition mushroom2 = head.addOrReplaceChild("mushroom2", CubeListBuilder.create().texOffs(96, 76).addBox(0.0F, -4.0F, -6.0F, 0.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(74, 76).addBox(-2.0F, -2.0F, -6.0F, 0.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(27, 87).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition RightArm = body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(34, 72).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -8.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(34, 63).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition fingers = RightForArm.addOrReplaceChild("fingers", CubeListBuilder.create().texOffs(36, 81).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(30, 14).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, 0.0F));

		PartDefinition tumb = RightForArm.addOrReplaceChild("tumb", CubeListBuilder.create().texOffs(30, 20).addBox(0.0F, -1.0F, -3.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -1.0F));

		PartDefinition flower = body.addOrReplaceChild("flower", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, -15.0F, -1.0F, 0.3054F, 0.0F, 0.4363F));

		PartDefinition cube_r27 = flower.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(28, 51).addBox(-6.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r28 = flower.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(28, 57).addBox(0.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r29 = flower.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(18, 0).addBox(-4.0F, 0.0F, 0.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r30 = flower.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(68, 51).addBox(-4.0F, 0.0F, -6.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		mainbody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		jaw.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lowerbody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLegJointY.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLegJointY.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		BackLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		BackRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}