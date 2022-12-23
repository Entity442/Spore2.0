package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.4.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Slasher;
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

public class SlasherModel<T extends Slasher> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "slashermodel"), "main");
	private final ModelPart body;
	private final ModelPart bodywear;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart HeadJoint;
	private final ModelPart Marm;
	private final ModelPart flesh;
	private final ModelPart flesh2;
	private final ModelPart flesh3;
	private final ModelPart flesh4;
	private final ModelPart flesh5;

	public SlasherModel(ModelPart root) {
		this.body = root.getChild("body");
		this.bodywear = root.getChild("bodywear");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.HeadJoint = root.getChild("HeadJoint");
		this.Marm = root.getChild("Marm");
		this.flesh = root.getChild("flesh");
		this.flesh2 = root.getChild("flesh2");
		this.flesh3 = root.getChild("flesh3");
		this.flesh4 = root.getChild("flesh4");
		this.flesh5 = root.getChild("flesh5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(36, 21).addBox(-4.0F, 9.0F, -3.0F, 8.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(59, 41).addBox(1.5F, -5.0F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 47).addBox(-3.5F, 7.0F, -3.25F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.5F, -1.0F, -4.0F, 8.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(36, 12).addBox(-4.0F, -2.5F, -3.0F, 8.0F, 3.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition body_r2 = body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(0, 34).addBox(-9.0F, -11.0F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.1F))
		.texOffs(28, 34).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, -0.25F, 0.0436F, 0.0F, 0.0F));

		PartDefinition bodywear = partdefinition.addOrReplaceChild("bodywear", CubeListBuilder.create().texOffs(75, 0).addBox(-4.0F, 0.0F, -3.25F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.25F, 0.0436F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 66).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 4.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(62, 59).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(62, 50).addBox(-1.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition cube_r2 = LeftForArm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(61, 18).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(32, 56).addBox(-1.75F, 0.0F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightForLeg = RightLeg.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(56, 30).addBox(-1.75F, 0.0F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(16, 55).addBox(-1.25F, 0.0F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftForLeg = LeftLeg.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(0, 55).addBox(-1.25F, 0.0F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition HeadJoint = partdefinition.addOrReplaceChild("HeadJoint", CubeListBuilder.create(), PartPose.offset(3.0F, -3.0F, 0.0F));

		PartDefinition head = HeadJoint.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-3.5F, -2.0F, -4.5F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -10.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(55, 0).addBox(-4.0F, -2.0F, 1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 1).addBox(-3.5F, -2.0F, -4.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(3.5F, -2.0F, -4.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, -1.0F, 1.0F));

		PartDefinition Marm = partdefinition.addOrReplaceChild("Marm", CubeListBuilder.create(), PartPose.offset(-6.0F, -4.0F, -1.0F));

		PartDefinition MarmJoint = Marm.addOrReplaceChild("MarmJoint", CubeListBuilder.create().texOffs(58, 8).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 47).addBox(-1.0F, -18.0F, -1.5F, 2.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition MArm2 = MarmJoint.addOrReplaceChild("MArm2", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, 0.0F));

		PartDefinition MarmJoint2 = MArm2.addOrReplaceChild("MarmJoint2", CubeListBuilder.create().texOffs(13, 13).addBox(-1.0F, -2.0F, -17.0F, 2.0F, 2.0F, 19.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition claw = MarmJoint2.addOrReplaceChild("claw", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -9.0F, -16.0F, 0.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -17.0F));

		PartDefinition flesh = partdefinition.addOrReplaceChild("flesh", CubeListBuilder.create().texOffs(22, 34).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -1.0F, 3.0F));

		PartDefinition flesh2 = partdefinition.addOrReplaceChild("flesh2", CubeListBuilder.create().texOffs(32, 1).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 1.0F, 3.0F));

		PartDefinition flesh3 = partdefinition.addOrReplaceChild("flesh3", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.0F, 2.75F));

		PartDefinition flesh4 = partdefinition.addOrReplaceChild("flesh4", CubeListBuilder.create().texOffs(28, 2).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -3.0F, 3.25F));

		PartDefinition flesh5 = partdefinition.addOrReplaceChild("flesh5", CubeListBuilder.create().texOffs(24, 1).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -2.0F, 2.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		if(entity.swinging){
			float f = 0;
			{if (f <= 20)
				f = f + 1;}
			this.Marm.xRot = f * 0.5F;
			this.Marm.getChild("MarmJoint").getChild("MArm2").xRot = -f;
		}

		else if (entity.isAggressive()){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = -89F + (Mth.sin(ageInTicks/4)/7);
			this.Marm.xRot = Mth.sin(ageInTicks/8)/10;
			this.Marm.getChild("MarmJoint").getChild("MArm2").xRot = -Mth.sin(ageInTicks/8)/10;
			this.Marm.getChild("MarmJoint").getChild("MArm2").getChild("MarmJoint2").getChild("claw").xRot = -Mth.sin(ageInTicks/8)/10;
		}
		else if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
			this.RightArm.zRot = 0;
			this.LeftArm.zRot = 0;
			this.Marm.xRot = Mth.sin(ageInTicks/8)/10;
			this.Marm.getChild("MarmJoint").getChild("MArm2").xRot = -Mth.sin(ageInTicks/8)/10;
			this.Marm.getChild("MarmJoint").getChild("MArm2").getChild("MarmJoint2").getChild("claw").xRot = -Mth.sin(ageInTicks/8)/10;

		}
		else {
			this.Marm.xRot = Mth.sin(ageInTicks/8)/10;
			this.Marm.getChild("MarmJoint").getChild("MArm2").xRot = -Mth.sin(ageInTicks/8)/10;
			this.Marm.getChild("MarmJoint").getChild("MArm2").getChild("MarmJoint2").getChild("claw").xRot = -Mth.sin(ageInTicks/8)/10;
			this.RightArm.zRot = Mth.sin(ageInTicks/8)/10;
			this.LeftArm.zRot = -Mth.sin(ageInTicks/8)/10;
		}


		this.LeftLeg.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
		if (LeftLeg.xRot < 0){
			this.LeftLeg.getChild("leftForLeg").xRot = -LeftLeg.xRot;}
		if (RightLeg.xRot < 0){
			this.RightLeg.getChild("rightForLeg").xRot = -RightLeg.xRot;}

		this.HeadJoint.getChild("head").getChild("jaw").xRot = Mth.sin(ageInTicks/8)/10;
		this.HeadJoint.zRot = Mth.sin(ageInTicks/10)/10;

		this.flesh.z =-1 +Mth.cos(ageInTicks/5)/5;
		this.flesh2.z =-1 +Mth.cos(ageInTicks/5)/6;
		this.flesh3.z =-1 +Mth.cos(ageInTicks/7)/5;
		this.flesh4.z =-1 +Mth.cos(ageInTicks/5)/8;
		this.flesh5.z =-1 +Mth.cos(ageInTicks/9)/5;
		}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bodywear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		HeadJoint.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Marm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		flesh.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		flesh2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		flesh3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		flesh4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		flesh5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}