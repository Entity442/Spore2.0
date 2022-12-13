package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Stalker;
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

public class StalkerModel<T extends Stalker> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "stalkermodel"), "main");
	private final ModelPart leg;
	private final ModelPart leg2;
	private final ModelPart body;
	private final ModelPart arm;
	private final ModelPart arm2;
	private final ModelPart head;

	public StalkerModel(ModelPart root) {
		this.leg = root.getChild("leg");
		this.leg2 = root.getChild("leg2");
		this.body = root.getChild("body");
		this.arm = root.getChild("arm");
		this.arm2 = root.getChild("arm2");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leg = partdefinition.addOrReplaceChild("leg", CubeListBuilder.create().texOffs(12, 39).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(-2.5F, 9.0F, 0.0F));

		PartDefinition cube_r1 = leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition forleg = leg.addOrReplaceChild("forleg", CubeListBuilder.create().texOffs(46, 31).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition cube_r2 = forleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 54).addBox(-1.5F, -0.15F, -2.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 35).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(2.5F, 9.0F, 0.0F));

		PartDefinition cube_r3 = leg2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(34, 50).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition forleg2 = leg2.addOrReplaceChild("forleg2", CubeListBuilder.create().texOffs(46, 18).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition cube_r4 = forleg2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 52).addBox(-1.5F, -0.15F, -2.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 24).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
				.texOffs(0, 28).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 1.0F));

		PartDefinition spine2 = body.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(0, 16).addBox(-4.5F, -5.5F, -4.25F, 9.0F, 6.0F, 6.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.25F, 0.1745F, 0.0F, 0.0F));

		PartDefinition back3 = spine2.addOrReplaceChild("back3", CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -7.0F, -1.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 2.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back4 = spine2.addOrReplaceChild("back4", CubeListBuilder.create().texOffs(36, 42).addBox(-2.0F, -7.0F, -1.25F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 2.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(34, 31).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(7.0F, -1.0F, -1.0F));

		PartDefinition cube_r5 = arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 50).addBox(-1.5F, 0.25F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition forarm = arm.addOrReplaceChild("forarm", CubeListBuilder.create().texOffs(24, 42).addBox(-1.5F, 0.0F, -1.25F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.25F));

		PartDefinition claws = forarm.addOrReplaceChild("claws", CubeListBuilder.create().texOffs(52, 41).addBox(-1.0F, -1.0F, -0.5F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(46, 41).addBox(-1.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 7.0F, 0.0F));

		PartDefinition tumb = forarm.addOrReplaceChild("tumb", CubeListBuilder.create(), PartPose.offset(-1.25F, 7.0F, -1.0F));

		PartDefinition cube_r6 = tumb.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 5).addBox(0.0F, -1.0F, -2.0F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

		PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(22, 31).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(-7.0F, -1.0F, -1.0F));

		PartDefinition cube_r7 = arm2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(48, 48).addBox(-1.5F, 0.25F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition forarm2 = arm2.addOrReplaceChild("forarm2", CubeListBuilder.create().texOffs(42, 8).addBox(-1.5F, 0.0F, -1.25F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.25F));

		PartDefinition claws2 = forarm2.addOrReplaceChild("claws2", CubeListBuilder.create().texOffs(46, 0).addBox(-2.0F, -1.0F, -0.5F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(24, 0).addBox(-2.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, 0.0F));

		PartDefinition tumb2 = forarm2.addOrReplaceChild("tumb2", CubeListBuilder.create(), PartPose.offset(1.25F, 7.0F, -1.0F));

		PartDefinition cube_r8 = tumb2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -1.0F, -2.0F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 22).addBox(-3.5F, -2.0F, -5.5F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -10.0F, -6.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(24, 16).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 16).addBox(-3.5F, -2.0F, -5.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(24, 15).addBox(3.5F, -2.0F, -5.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -2.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -2.0F, -6.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.swinging){
			this.arm.xRot = -89.5f + (headPitch /  ( 90F / (float) Math.PI));
			this.arm2.xRot = this.arm.xRot;
		}
		else if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)) {
			this.arm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.arm2.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
			this.leg.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.leg2.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * -limbSwingAmount;
			if (leg.xRot < 0) {
				this.leg.getChild("forleg").xRot = -leg.xRot;
			}
			if (leg2.xRot < 0) {
				this.leg2.getChild("forleg2").xRot = -leg2.xRot;
			}
			if (arm.xRot < 0) {
				this.arm.getChild("forarm").xRot = arm.xRot;
			}
			if (arm2.xRot < 0) {
				this.arm2.getChild("forarm2").xRot = arm2.xRot;
			}

		} else {
			this.arm.xRot = Mth.sin(ageInTicks / 8) / 10;
			this.arm2.xRot = -Mth.sin(ageInTicks / 8) / 10;
			this.leg.xRot = 0;
			this.leg2.xRot = 0;
			this.leg.getChild("forleg").xRot = 0;
			this.leg2.getChild("forleg2").xRot = 0;
		}

		this.head.getChild("jaw").xRot = Mth.sin(ageInTicks / 8) / 10;
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (90F / (float) Math.PI);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}