package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Utility.Proto;
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

public class protomodel<T extends Proto> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "protomodel"), "main");
	private final ModelPart base;
	private final ModelPart body;
	private final ModelPart tentacleBase;
	private final ModelPart tentacleBase2;
	private final ModelPart tentacleBase3;
	private final ModelPart tentacleBase4;

	public protomodel(ModelPart root) {
		this.base = root.getChild("base");
		this.body = root.getChild("body");
		this.tentacleBase = root.getChild("tentacleBase");
		this.tentacleBase2 = root.getChild("tentacleBase2");
		this.tentacleBase3 = root.getChild("tentacleBase3");
		this.tentacleBase4 = root.getChild("tentacleBase4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r1 = base.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 34).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition roots = base.addOrReplaceChild("roots", CubeListBuilder.create().texOffs(152, 47).addBox(-13.984F, -10.6729F, -2.0F, 8.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition roots2 = roots.addOrReplaceChild("roots2", CubeListBuilder.create().texOffs(70, 0).addBox(-5.016F, -4.0271F, -1.0F, 5.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.484F, 6.3271F, -0.5F, 0.0F, 0.0F, 0.9163F));

		PartDefinition cube_r2 = roots2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 80).addBox(-0.016F, -0.0271F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-5.0F, 7.0F, 1.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition roots3 = base.addOrReplaceChild("roots3", CubeListBuilder.create().texOffs(126, 149).addBox(-2.0F, -10.6729F, 5.984F, 4.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition roots4 = roots3.addOrReplaceChild("roots4", CubeListBuilder.create().texOffs(0, 50).addBox(-1.016F, -4.0271F, 0.0F, 3.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.484F, 6.3271F, 9.5F, 0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r3 = roots4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(30, 80).addBox(-2.0F, -0.0271F, -2.984F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.984F, 7.0F, 4.984F, -0.9599F, 0.0F, 0.0F));

		PartDefinition roots5 = base.addOrReplaceChild("roots5", CubeListBuilder.create().texOffs(146, 118).addBox(-2.0F, -10.6729F, -13.984F, 4.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition roots6 = roots5.addOrReplaceChild("roots6", CubeListBuilder.create().texOffs(0, 0).addBox(-2.016F, -4.0271F, -5.0F, 3.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.516F, 6.3271F, -9.5F, -0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r4 = roots6.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 80).addBox(-1.0F, -0.0271F, -0.016F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.016F, 7.0F, -4.984F, 0.9163F, 0.0F, 0.0F));

		PartDefinition roots7 = base.addOrReplaceChild("roots7", CubeListBuilder.create().texOffs(150, 149).addBox(5.984F, -10.6729F, -2.0F, 8.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition roots8 = roots7.addOrReplaceChild("roots8", CubeListBuilder.create().texOffs(54, 0).addBox(-0.016F, -4.0271F, -1.0F, 5.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.516F, 6.3271F, -0.5F, 0.0F, 0.0F, -0.9163F));

		PartDefinition cube_r5 = roots8.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(72, 26).addBox(-2.984F, -0.0271F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(4.9681F, 7.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(104, 0).addBox(-6.0F, -36.0F, -6.0F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 50).addBox(-9.0F, -16.0F, -9.0F, 18.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-9.0F, -32.0F, -9.0F, 18.0F, 32.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(46, 77).addBox(0.0F, -38.0F, -24.0F, 0.0F, 19.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.3562F, 0.0F));

		PartDefinition lowerB = body.addOrReplaceChild("lowerB", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r13 = lowerB.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(113, 59).addBox(-8.0F, -11.0F, -6.0F, 16.0F, 12.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.5F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r14 = lowerB.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(80, 104).addBox(-8.0F, -2.0F, -6.5F, 16.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -10.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition lowerB2 = body.addOrReplaceChild("lowerB2", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r15 = lowerB2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 80).addBox(-6.0F, -11.0F, -8.0F, 7.0F, 12.0F, 16.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-10.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r16 = lowerB2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(86, 78).addBox(-6.5F, -2.0F, -8.0F, 8.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, 1.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition lowerB3 = body.addOrReplaceChild("lowerB3", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r17 = lowerB3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(56, 66).addBox(-2.0F, -11.0F, -8.0F, 7.0F, 12.0F, 16.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(11.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r18 = lowerB3.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(72, 0).addBox(-2.5F, -2.0F, -8.0F, 8.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5F, 1.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition lowerB4 = body.addOrReplaceChild("lowerB4", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r19 = lowerB4.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 108).addBox(-8.0F, -11.0F, -1.0F, 16.0F, 12.0F, 7.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 10.5F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r20 = lowerB4.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(104, 26).addBox(-8.0F, -2.0F, -1.5F, 16.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 10.5F, -0.2618F, 0.0F, 0.0F));

		PartDefinition tentacleBase = partdefinition.addOrReplaceChild("tentacleBase", CubeListBuilder.create(), PartPose.offsetAndRotation(9.0F, 4.0F, -9.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition tentacle = tentacleBase.addOrReplaceChild("tentacle", CubeListBuilder.create().texOffs(137, 1).addBox(-2.0F, -2.0F, -13.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition tentacle2 = tentacle.addOrReplaceChild("tentacle2", CubeListBuilder.create().texOffs(136, 97).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, -12.75F, 0.4363F, 0.0F, 0.0F));

		PartDefinition tentacle3 = tentacle2.addOrReplaceChild("tentacle3", CubeListBuilder.create().texOffs(46, 136).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.6981F, 0.0F, 0.0F));

		PartDefinition tentacle4 = tentacle3.addOrReplaceChild("tentacle4", CubeListBuilder.create().texOffs(0, 146).addBox(-1.5F, -1.5F, -15.0F, 3.0F, 3.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.7854F, 0.0F, 0.0F));

		PartDefinition tentacleBase2 = partdefinition.addOrReplaceChild("tentacleBase2", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.0F, 4.0F, -9.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition tentacle5 = tentacleBase2.addOrReplaceChild("tentacle5", CubeListBuilder.create().texOffs(134, 78).addBox(-2.0F, -2.0F, -13.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition tentacle6 = tentacle5.addOrReplaceChild("tentacle6", CubeListBuilder.create().texOffs(23, 132).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, -12.75F, 0.4363F, 0.0F, 0.0F));

		PartDefinition tentacle7 = tentacle6.addOrReplaceChild("tentacle7", CubeListBuilder.create().texOffs(123, 130).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.6981F, 0.0F, 0.0F));

		PartDefinition tentacle8 = tentacle7.addOrReplaceChild("tentacle8", CubeListBuilder.create().texOffs(90, 145).addBox(-1.5F, -1.5F, -15.0F, 3.0F, 3.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.7854F, 0.0F, 0.0F));

		PartDefinition tentacleBase3 = partdefinition.addOrReplaceChild("tentacleBase3", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, 4.0F, 8.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition tentacle9 = tentacleBase3.addOrReplaceChild("tentacle9", CubeListBuilder.create().texOffs(0, 127).addBox(-2.0F, -2.0F, -13.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition tentacle10 = tentacle9.addOrReplaceChild("tentacle10", CubeListBuilder.create().texOffs(100, 126).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, -12.75F, 0.4363F, 0.0F, 0.0F));

		PartDefinition tentacle11 = tentacle10.addOrReplaceChild("tentacle11", CubeListBuilder.create().texOffs(77, 122).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.6981F, 0.0F, 0.0F));

		PartDefinition tentacle12 = tentacle11.addOrReplaceChild("tentacle12", CubeListBuilder.create().texOffs(69, 141).addBox(-1.5F, -1.5F, -15.0F, 3.0F, 3.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.7854F, 0.0F, 0.0F));

		PartDefinition tentacleBase4 = partdefinition.addOrReplaceChild("tentacleBase4", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, 4.0F, 8.0F, 0.0F, 2.5307F, 0.0F));

		PartDefinition tentacle13 = tentacleBase4.addOrReplaceChild("tentacle13", CubeListBuilder.create().texOffs(54, 117).addBox(-2.0F, -2.0F, -13.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition tentacle14 = tentacle13.addOrReplaceChild("tentacle14", CubeListBuilder.create().texOffs(113, 107).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, -12.75F, 0.4363F, 0.0F, 0.0F));

		PartDefinition tentacle15 = tentacle14.addOrReplaceChild("tentacle15", CubeListBuilder.create().texOffs(31, 113).addBox(-2.0F, -2.0F, -15.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.6981F, 0.0F, 0.0F));

		PartDefinition tentacle16 = tentacle15.addOrReplaceChild("tentacle16", CubeListBuilder.create().texOffs(137, 29).addBox(-1.5F, -1.5F, -15.0F, 3.0F, 3.0F, 15.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, -14.75F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.tentacleBase.xRot =   (Mth.sin(ageInTicks/8)/9);
		this.tentacleBase2.xRot = -(Mth.sin(ageInTicks/8)/9);
		this.tentacleBase3.xRot = -(Mth.sin(ageInTicks/8)/9);
		this.tentacleBase4.xRot =  (Mth.sin(ageInTicks/8)/9);

		this.tentacleBase.getChild("tentacle").getChild("tentacle2").getChild("tentacle3").xRot =20 + (Mth.sin(ageInTicks/8)/4);
		this.tentacleBase2.getChild("tentacle5").getChild("tentacle6").getChild("tentacle7").xRot =20 - (Mth.sin(ageInTicks/8)/4);
		this.tentacleBase3.getChild("tentacle9").getChild("tentacle10").getChild("tentacle11").xRot =20 - (Mth.sin(ageInTicks/8)/4);
		this.tentacleBase4.getChild("tentacle13").getChild("tentacle14").getChild("tentacle15").xRot =20 + (Mth.sin(ageInTicks/8)/4);

		this.body.getChild("lowerB").y = -10 + Mth.sin(ageInTicks/6);
		this.body.getChild("lowerB2").y =-10 - Mth.sin(ageInTicks/6);
		this.body.getChild("lowerB3").y =-10 - Mth.sin(ageInTicks/6);
		this.body.getChild("lowerB4").y =-10 + Mth.sin(ageInTicks/6);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacleBase.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacleBase2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacleBase3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacleBase4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}