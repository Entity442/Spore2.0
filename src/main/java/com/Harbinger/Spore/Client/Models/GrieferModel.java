package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Griefer;
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

public class GrieferModel<T extends Griefer> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "griefermodel"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart belly;

	public GrieferModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.belly = root.getChild("belly");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-6.5F, 10.0F, -2.0F, 13.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(26, 50).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 3.0F, 6.0F, new CubeDeformation(0.1F))
		.texOffs(18, 48).addBox(-2.5F, 1.0F, -7.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, 4.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 18).addBox(-6.5F, -5.5F, -2.5F, 13.0F, 7.0F, 7.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -1.0F, 14.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -3.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition tumors = body.addOrReplaceChild("tumors", CubeListBuilder.create().texOffs(34, 44).addBox(0.25F, -6.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 44).addBox(-2.75F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 44).addBox(-0.75F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 44).addBox(-4.75F, -5.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 1.5F));

		PartDefinition cube_r3 = tumors.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 30).addBox(-5.0F, -4.0F, -5.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9163F, 0.0F, 0.0F));

		PartDefinition tumors2 = body.addOrReplaceChild("tumors2", CubeListBuilder.create().texOffs(18, 44).addBox(-2.75F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 2).addBox(-4.75F, -5.0F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(-6.75F, -1.0F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.5F));

		PartDefinition cube_r4 = tumors2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 44).addBox(-6.0F, -5.0F, -5.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9163F, 0.0F, 0.0F));

		PartDefinition tumors3 = body.addOrReplaceChild("tumors3", CubeListBuilder.create().texOffs(0, 23).addBox(-2.75F, -5.75F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 6).addBox(0.25F, -3.75F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-3.75F, -0.75F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 4).addBox(-0.75F, 2.25F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-6.75F, -0.75F, 0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 4.0F, 3.5F));

		PartDefinition cube_r5 = tumors3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 38).addBox(-6.0F, -2.0F, -5.0F, 7.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.4835F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 24).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 50).addBox(-4.0F, 2.0F, -5.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(60, 45).addBox(-4.0F, 2.0F, -9.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -6.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(52, 9).addBox(-3.5F, 0.0F, -3.75F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -5.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(63, 64).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -2.0F, -2.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(0, 69).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition fingers = RightForArm.addOrReplaceChild("fingers", CubeListBuilder.create().texOffs(13, 57).addBox(-2.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 44).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, 0.0F));

		PartDefinition meat = RightForArm.addOrReplaceChild("meat", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-1.0F))
		.texOffs(34, 8).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 6.0F, 10.0F, new CubeDeformation(-2.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(36, 64).addBox(-1.0F, -2.0F, -2.5F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -2.0F, -2.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create().texOffs(68, 52).addBox(-1.0F, 0.0F, -2.5F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition fingers2 = LeftForArm.addOrReplaceChild("fingers2", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 7.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 57).addBox(-2.75F, -1.0F, -2.0F, 4.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.9F, 12.0F, 0.75F));

		PartDefinition rightForLeg = RightLeg.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(18, 59).addBox(-2.75F, 0.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(49, 56).addBox(-1.25F, -1.0F, -2.0F, 4.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.9F, 12.0F, 0.75F));

		PartDefinition leftForLeg = LeftLeg.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(57, 19).addBox(-1.25F, 0.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition belly = partdefinition.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(52, 0).addBox(-6.0F, -3.0F, -1.0F, 12.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 38).addBox(-5.0F, -2.0F, -2.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		 if (entity.isAggressive()){
			this.head.getChild("jaw").xRot = (Mth.sin(ageInTicks/6)/10) + 0.4F;
			this.RightArm.xRot = -89.5F - (Mth.sin(ageInTicks/4)/7);
			this.LeftArm.xRot = -89.5F + (Mth.sin(ageInTicks/4)/7);
			if (entity.swinging){
				this.RightArm.xRot = -88.5F;
				this.LeftArm.xRot = -88.5F;}

		}else if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;

		} else{

			this.RightArm.getChild("RightForArm").getChild("fingers").zRot = Mth.sin(ageInTicks/8)/10;
			this.LeftArm.getChild("LeftForArm").getChild("fingers2").zRot = -Mth.sin(ageInTicks/8)/10;

			this.RightArm.getChild("RightForArm").getChild("meat").visible = false;

			 this.RightArm.xRot = Mth.sin(ageInTicks/8)/10;
			 this.LeftArm.xRot = -Mth.sin(ageInTicks/8)/10;
			 this.LeftLeg.xRot = 0;
			 this.RightLeg.xRot = 0;
		}
		if (entity.grieferExplosion()){
			this.head.yRot = Mth.cos(ageInTicks/4)/4;
			this.body.getChild("tumors").xScale =  body.getChild("tumors").xScale + (0.001F * entity.getSwell());
			this.body.getChild("tumors").yScale = body.getChild("tumors").yScale + (0.001F * entity.getSwell());
			this.body.getChild("tumors").zScale =  body.getChild("tumors").zScale + (0.001F * entity.getSwell());

			this.body.getChild("tumors2").xScale = body.getChild("tumors2").xScale + (0.001F * entity.getSwell());
			this.body.getChild("tumors2").yScale = body.getChild("tumors2").yScale + (0.001F * entity.getSwell());
			this.body.getChild("tumors2").zScale = body.getChild("tumors2").zScale + (0.001F * entity.getSwell());

			this.body.getChild("tumors3").xScale = body.getChild("tumors3").xScale + (0.001F * entity.getSwell());
			this.body.getChild("tumors3").yScale = body.getChild("tumors3").yScale + (0.001F * entity.getSwell());
			this.body.getChild("tumors3").zScale = body.getChild("tumors3").zScale + (0.001F * entity.getSwell());

		} else {
			this.body.getChild("tumors").xScale = 1 + Mth.sin(ageInTicks / 8) / 10;
			this.body.getChild("tumors").yScale = 1 - Mth.sin(ageInTicks / 8) / 10;
			this.body.getChild("tumors").zScale = 1 + Mth.sin(ageInTicks / 8) / 10;

			this.body.getChild("tumors2").xScale = 1 + Mth.sin(ageInTicks / 6) / 7;
			this.body.getChild("tumors2").yScale = 1 - Mth.sin(ageInTicks / 6) / 7;
			this.body.getChild("tumors2").zScale = 1 + Mth.sin(ageInTicks / 6) / 7;

			this.body.getChild("tumors3").xScale = 1 + Mth.sin(ageInTicks / 7) / 9;
			this.body.getChild("tumors3").yScale = 1 - Mth.sin(ageInTicks / 7) / 9;
			this.body.getChild("tumors3").zScale = 1 + Mth.sin(ageInTicks / 7) / 9;

			this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
			this.head.xRot = headPitch / (90F / (float) Math.PI);
			this.head.getChild("jaw").xRot = Mth.sin(ageInTicks / 8) / 10;
		}

		this.LeftLeg.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
		if (LeftLeg.xRot < 0){
			this.LeftLeg.getChild("leftForLeg").xRot = -LeftLeg.xRot;}
		if (RightLeg.xRot < 0){
			this.RightLeg.getChild("rightForLeg").xRot = -RightLeg.xRot;}

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		belly.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}