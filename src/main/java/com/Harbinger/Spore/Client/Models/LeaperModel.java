package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.4.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Leaper;
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

public class LeaperModel<T extends Leaper> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "leapermodel"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart armor;
	private final ModelPart tendrils;
	private final ModelPart tendrils3;
	private final ModelPart tendrils4;
	private final ModelPart tendrils5;
	private final ModelPart tendrils6;

	public LeaperModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.armor = root.getChild("armor");
		this.tendrils = root.getChild("tendrils");
		this.tendrils3 = root.getChild("tendrils3");
		this.tendrils4 = root.getChild("tendrils4");
		this.tendrils5 = root.getChild("tendrils5");
		this.tendrils6 = root.getChild("tendrils6");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 21).addBox(-3.5F, -2.0F, -5.0F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-4.0F, -10.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(61, 48).addBox(-4.0F, -2.0F, 1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 17).addBox(-4.0F, -2.0F, -5.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(32, 19).addBox(4.0F, -2.0F, -4.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.5F, -10.5F, -5.5F, 4.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(78, 0).addBox(-1.0F, -5.0F, -3.0F, 3.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -10.0F, -2.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(24, 15).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, -1.0F, 1.0F));

		PartDefinition tendrils2 = head.addOrReplaceChild("tendrils2", CubeListBuilder.create(), PartPose.offset(-2.5F, -14.0F, 1.0F));

		PartDefinition tip3 = tendrils2.addOrReplaceChild("tip3", CubeListBuilder.create().texOffs(13, 40).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition tip4 = tip3.addOrReplaceChild("tip4", CubeListBuilder.create().texOffs(9, 40).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 31).addBox(-5.0F, 9.0F, -3.0F, 10.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(26, 25).addBox(-4.5F, 4.5F, -3.0F, 9.0F, 5.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition spine2 = body.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.5F, -6.5F, 12.0F, 8.0F, 7.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 3.0F, 3.25F));

		PartDefinition back3 = spine2.addOrReplaceChild("back3", CubeListBuilder.create().texOffs(18, 59).addBox(0.0F, -4.0F, -2.75F, 3.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -1.0F, -3.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition back4 = spine2.addOrReplaceChild("back4", CubeListBuilder.create().texOffs(0, 54).addBox(-2.0F, -4.0F, -4.0F, 3.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -1.0F, -2.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(45, 36).addBox(-2.0F, -2.0F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -1.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(60, 12).addBox(-2.0F, 0.0F, -2.25F, 4.0F, 7.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 7.0F, -0.25F));

		PartDefinition claw = RightForArm.addOrReplaceChild("claw", CubeListBuilder.create().texOffs(0, 66).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, 1.75F));

		PartDefinition claw2 = RightForArm.addOrReplaceChild("claw2", CubeListBuilder.create().texOffs(56, 63).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, -0.5F));

		PartDefinition claw3 = RightForArm.addOrReplaceChild("claw3", CubeListBuilder.create(), PartPose.offset(1.0F, 7.0F, -2.0F));

		PartDefinition cube_r2 = claw3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(66, 52).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, -3.0F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create().texOffs(60, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 7.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition claw4 = LeftForArm.addOrReplaceChild("claw4", CubeListBuilder.create().texOffs(46, 63).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 1.5F));

		PartDefinition claw5 = LeftForArm.addOrReplaceChild("claw5", CubeListBuilder.create().texOffs(36, 63).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -0.75F));

		PartDefinition claw6 = LeftForArm.addOrReplaceChild("claw6", CubeListBuilder.create(), PartPose.offset(-1.0F, 7.0F, -2.25F));

		PartDefinition cube_r3 = claw6.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(63, 40).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.9F, 10.0F, 0.0F));

		PartDefinition RL = RightLeg.addOrReplaceChild("RL", CubeListBuilder.create().texOffs(48, 50).addBox(-1.75F, -1.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition rightForLeg = RL.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(38, 0).addBox(-1.75F, -1.0F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 6.5F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.9F, 10.0F, 0.0F));

		PartDefinition LJ = LeftLeg.addOrReplaceChild("LJ", CubeListBuilder.create().texOffs(30, 50).addBox(-2.25F, -1.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition leftForLeg = LJ.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(27, 36).addBox(-2.25F, -1.0F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 6.5F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition armor = partdefinition.addOrReplaceChild("armor", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition cube_r4 = armor.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(60, 24).addBox(0.0F, -2.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r5 = armor.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(60, 32).addBox(-6.0F, -2.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition tendrils = partdefinition.addOrReplaceChild("tendrils", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.0F, 3.0F));

		PartDefinition tip = tendrils.addOrReplaceChild("tip", CubeListBuilder.create().texOffs(17, 50).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition tip2 = tip.addOrReplaceChild("tip2", CubeListBuilder.create().texOffs(13, 49).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition tendrils3 = partdefinition.addOrReplaceChild("tendrils3", CubeListBuilder.create(), PartPose.offset(4.0F, -2.0F, 3.0F));

		PartDefinition tip5 = tendrils3.addOrReplaceChild("tip5", CubeListBuilder.create().texOffs(9, 49).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9163F, 0.0F, 0.0F));

		PartDefinition tip6 = tip5.addOrReplaceChild("tip6", CubeListBuilder.create().texOffs(47, 27).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition tendrils4 = partdefinition.addOrReplaceChild("tendrils4", CubeListBuilder.create(), PartPose.offset(-4.0F, 3.0F, 3.0F));

		PartDefinition tip7 = tendrils4.addOrReplaceChild("tip7", CubeListBuilder.create().texOffs(47, 18).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.6109F, 0.0F));

		PartDefinition tip8 = tip7.addOrReplaceChild("tip8", CubeListBuilder.create().texOffs(47, 9).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition tendrils5 = partdefinition.addOrReplaceChild("tendrils5", CubeListBuilder.create(), PartPose.offset(5.0F, 3.0F, 2.0F));

		PartDefinition tip9 = tendrils5.addOrReplaceChild("tip9", CubeListBuilder.create().texOffs(47, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1781F, 0.5236F, 0.0F));

		PartDefinition tip10 = tip9.addOrReplaceChild("tip10", CubeListBuilder.create().texOffs(43, 14).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition tendrils6 = partdefinition.addOrReplaceChild("tendrils6", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 3.0F));

		PartDefinition tip11 = tendrils6.addOrReplaceChild("tip11", CubeListBuilder.create().texOffs(13, 40).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition tip12 = tip11.addOrReplaceChild("tip12", CubeListBuilder.create().texOffs(9, 40).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isAggressive()){
			this.RightArm.xRot = -90F -Mth.sin(ageInTicks/10)/10;
			this.LeftArm.xRot = -90F +Mth.sin(ageInTicks/10)/10;
			this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6F) * 0.6F * limbSwingAmount;
			this.RightLeg.xRot = Mth.cos(limbSwing * 0.6F) * -0.6F * limbSwingAmount;

			if (entity.swinging){
				float f = 0;
				f = f + 1F;
				this.RightArm.xRot = -90F + f;
				this.LeftArm.xRot = -90F + f;

			}
		}else if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
			this.LeftLeg.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.RightLeg.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;

		}else if (!entity.isOnGround() && !entity.isInWater() && !entity.isClimbing()){
			this.RightArm.xRot = (ageInTicks/10)/6;
			this.LeftArm.xRot = (ageInTicks/10)/6;

		}else {


			this.RightArm.xRot = Mth.sin(ageInTicks/8)/10;
			this.LeftArm.xRot = -Mth.sin(ageInTicks/8)/10;
			this.LeftLeg.xRot = 0;
			this.RightLeg.xRot = 0;
		}
		this.head.getChild("jaw").xRot = Mth.sin(ageInTicks/8)/10;
		this.head.getChild("tendrils2").xRot = Mth.sin(ageInTicks/8)/10;
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch /  ( 90F / (float) Math.PI);

		this.tendrils.xRot = Mth.sin(ageInTicks/7)/10;
		this.tendrils3.xRot = Mth.sin(ageInTicks/8)/9;
		this.tendrils4.xRot = Mth.sin(ageInTicks/8)/8;
		this.tendrils5.xRot = Mth.sin(ageInTicks/10)/10;
		this.tendrils6.xRot = Mth.sin(ageInTicks/9)/9;


		this.RightArm.getChild("RightForArm").getChild("claw").zRot = Mth.sin(ageInTicks/8)/8;
		this.RightArm.getChild("RightForArm").getChild("claw2").zRot = Mth.sin(ageInTicks/8)/8;
		this.RightArm.getChild("RightForArm").getChild("claw3").xRot = Mth.sin(ageInTicks/8)/8;

		this.LeftArm.getChild("LeftForArm").getChild("claw4").zRot = -Mth.sin(ageInTicks/8)/8;
		this.LeftArm.getChild("LeftForArm").getChild("claw5").zRot = -Mth.sin(ageInTicks/8)/8;
		this.LeftArm.getChild("LeftForArm").getChild("claw6").xRot = -Mth.sin(ageInTicks/8)/8;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		armor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendrils.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendrils3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendrils4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendrils5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tendrils6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}