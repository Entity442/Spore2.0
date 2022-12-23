package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Knight;
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

public class KnightModel<T extends Knight> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "knightmodel"), "main");
	private final ModelPart body;

	public KnightModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-3.5F, 4.5F, -2.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(-0.3F))
		.texOffs(22, 39).addBox(-3.5F, 10.0F, -2.0F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 63).addBox(3.0F, -3.0F, -3.0F, 2.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 63).addBox(5.0F, -7.0F, -3.0F, 2.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(17, 57).addBox(3.0F, 9.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(27, 55).addBox(-2.0F, -0.25F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(19, 66).addBox(-2.0F, 7.75F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 22).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(40, 41).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition mushroom2 = head.addOrReplaceChild("mushroom2", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -4.0F, -6.0F, 0.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-2.0F, -2.0F, -6.0F, 0.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition RightArm = body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 54).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(52, 27).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition fingers = RightForArm.addOrReplaceChild("fingers", CubeListBuilder.create().texOffs(12, 55).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(12, 55).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, 0.0F));

		PartDefinition tumb = RightForArm.addOrReplaceChild("tumb", CubeListBuilder.create().texOffs(58, -3).addBox(0.0F, -1.0F, -3.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -1.0F));

		PartDefinition RightLeg = body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(42, 47).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightForLeg = RightLeg.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(14, 45).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition LeftLeg = body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(28, 45).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftForLeg = LeftLeg.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(0, 44).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition mushroom = body.addOrReplaceChild("mushroom", CubeListBuilder.create().texOffs(32, 27).addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 6).addBox(-0.5F, 0.25F, -0.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(-1.0F, 1.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));

		PartDefinition tendril = body.addOrReplaceChild("tendril", CubeListBuilder.create(), PartPose.offset(5.0F, 6.0F, 2.0F));

		PartDefinition cube_r1 = tendril.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 59).addBox(-2.0F, -11.0F, 2.0F, 6.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition tendril2 = body.addOrReplaceChild("tendril2", CubeListBuilder.create(), PartPose.offset(5.0F, 6.0F, -2.0F));

		PartDefinition cube_r2 = tendril2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 59).addBox(-1.0F, -10.0F, -2.0F, 6.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition tendril3 = body.addOrReplaceChild("tendril3", CubeListBuilder.create(), PartPose.offset(7.0F, 0.0F, -1.0F));

		PartDefinition cube_r3 = tendril3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 59).addBox(-5.0F, -15.0F, -1.0F, 6.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.0F, 1.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition tendril4 = body.addOrReplaceChild("tendril4", CubeListBuilder.create(), PartPose.offset(7.0F, -3.0F, 1.0F));

		PartDefinition cube_r4 = tendril4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(52, 59).addBox(-7.0F, -17.0F, 1.0F, 6.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 9.0F, -1.0F, 0.0F, 0.0F, 0.6981F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		 if (entity.isAggressive()){
			this.body.getChild("head").getChild("jaw").xRot = (Mth.sin(ageInTicks/6)/10);
			this.body.getChild("RightArm").xRot = 25F + Mth.cos(limbSwing * 0.2F)  * limbSwingAmount;
			this.body.getChild("RightArm").getChild("RightForArm").xRot = - 88.5F;
			this.body.zRot = Mth.cos(limbSwing/4)/10;

			 this.body.getChild("tendril").yRot = -1;
			 this.body.getChild("tendril2").yRot = -1;
			 this.body.getChild("tendril3").yRot = -1;
			 this.body.getChild("tendril4").yRot = -1;
			 if (entity.swinging){

				 float f = 0;
				 f = f + 1F;
				 this.body.getChild("RightArm").xRot = -90F + f;
				 this.body.getChild("RightArm").getChild("RightForArm").xRot = 0;
			 }


		}else if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)){
			this.body.getChild("RightArm").xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.body.getChild("RightArm").zRot = 0;

		}
		else {

			this.body.zRot = 0;
			this.body.getChild("RightArm").zRot = Mth.sin(ageInTicks/8)/10;
			this.body.getChild("RightArm").getChild("RightForArm").getChild("fingers").zRot = Mth.sin(ageInTicks/4)/8;
			this.body.getChild("RightArm").getChild("RightForArm").getChild("tumb").xRot = -Mth.sin(ageInTicks/4)/8;
			this.body.getChild("RightArm").getChild("RightForArm").xRot = 0;
			 this.body.getChild("tendril").yRot = 0;
			 this.body.getChild("tendril2").yRot = 0;
			 this.body.getChild("tendril3").yRot = 0;
			 this.body.getChild("tendril4").yRot = 0;
		}

		this.body.getChild("LeftLeg").xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
		this.body.getChild("RightLeg").xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
		if (body.getChild("LeftLeg").xRot < 0){
			this.body.getChild("LeftLeg").getChild("leftForLeg").xRot = -2 * body.getChild("LeftLeg").xRot;}
		if (body.getChild("RightLeg").xRot < 0){
			this.body.getChild("RightLeg").getChild("rightForLeg").xRot = -2 * body.getChild("RightLeg").xRot;}

		this.body.getChild("head").getChild("jaw").xRot = Mth.sin(ageInTicks/8)/10;
		this.body.getChild("tendril").xRot = (Mth.sin(ageInTicks/6)/6);
		this.body.getChild("tendril2").xRot = -(Mth.sin(ageInTicks/6)/7);
		this.body.getChild("tendril3").xRot = (Mth.sin(ageInTicks/7)/6);
		this.body.getChild("tendril4").xRot = -(Mth.sin(ageInTicks/7)/7);
		this.body.getChild("tendril").zRot = (Mth.sin(ageInTicks/6)/6);
		this.body.getChild("tendril2").zRot = -(Mth.sin(ageInTicks/6)/7);
		this.body.getChild("tendril3").zRot = (Mth.sin(ageInTicks/7)/6);
		this.body.getChild("tendril4").zRot = -(Mth.sin(ageInTicks/7)/7);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}