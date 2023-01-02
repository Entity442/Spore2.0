package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Braionmil;
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

public class BraionmilModel<T extends Braionmil> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "braionmil"), "main");
	private final ModelPart body;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart head;
	private final ModelPart tongueM;
	private final ModelPart lungs;
	private final ModelPart lungs2;

	public BraionmilModel(ModelPart root) {
		this.body = root.getChild("body");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.head = root.getChild("head");
		this.tongueM = root.getChild("tongueM");
		this.lungs = root.getChild("lungs");
		this.lungs2 = root.getChild("lungs2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(22, 21).addBox(-4.5F, 8.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition spine = body.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(32, 29).addBox(-2.0F, -2.5F, -4.5F, 4.0F, 3.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 8.0F, 2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition back = spine.addOrReplaceChild("back", CubeListBuilder.create().texOffs(58, 22).addBox(0.0F, -2.0F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back2 = spine.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(58, 16).addBox(-2.0F, -2.0F, -2.25F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition spine2 = body.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -5.5F, -4.5F, 8.0F, 6.0F, 5.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 4.0F, 1.25F, 0.5672F, 0.0F, 0.0F));

		PartDefinition back3 = spine2.addOrReplaceChild("back3", CubeListBuilder.create().texOffs(36, 50).addBox(0.0F, -4.0F, -1.75F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back4 = spine2.addOrReplaceChild("back4", CubeListBuilder.create().texOffs(10, 49).addBox(-2.0F, -4.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition spine3 = body.addOrReplaceChild("spine3", CubeListBuilder.create().texOffs(27, 9).addBox(-2.0F, -2.5F, -4.5F, 4.0F, 3.0F, 5.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 6.0F, 1.75F, 0.3491F, 0.0F, 0.0F));

		PartDefinition back5 = spine3.addOrReplaceChild("back5", CubeListBuilder.create().texOffs(54, 37).addBox(0.0F, -2.0F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back6 = spine3.addOrReplaceChild("back6", CubeListBuilder.create().texOffs(52, 0).addBox(-2.0F, -2.0F, -2.25F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(28, 37).addBox(-2.25F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.9F, 12.0F, 2.0F));

		PartDefinition leftForLeg = LeftLeg.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(16, 29).addBox(-2.25F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(12, 39).addBox(-1.75F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.9F, 12.0F, 2.0F));

		PartDefinition rightForLeg = RightLeg.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(0, 32).addBox(-1.75F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 55).addBox(-1.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 3.0F, -1.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition cube_r1 = LeftForArm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(51, 7).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(12, 57).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 3.0F, -1.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(50, 52).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(21, 17).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition FangJoint = head.addOrReplaceChild("FangJoint", CubeListBuilder.create(), PartPose.offset(-3.0F, -2.5F, -2.0F));

		PartDefinition rightfang = FangJoint.addOrReplaceChild("rightfang", CubeListBuilder.create().texOffs(50, 30).addBox(-1.0821F, -0.3905F, -2.733F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-1.0821F, -1.3905F, -2.733F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 0).addBox(-0.0821F, -1.3905F, -8.733F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(40, 13).addBox(-0.0821F, 1.6095F, -2.733F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -2.0F, 0.3491F, 0.4363F, 0.0F));

		PartDefinition FangJoint2 = head.addOrReplaceChild("FangJoint2", CubeListBuilder.create(), PartPose.offset(3.15F, -2.5F, -4.0F));

		PartDefinition rightfang2 = FangJoint2.addOrReplaceChild("rightfang2", CubeListBuilder.create().texOffs(49, 45).addBox(-1.0821F, -0.3905F, -2.733F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0821F, -1.3905F, -2.733F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 11).addBox(-0.0821F, -1.3905F, -8.733F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(24, 1).addBox(-0.0821F, 1.6095F, -2.733F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.3491F, -0.4363F, 0.0F));

		PartDefinition tongueM = partdefinition.addOrReplaceChild("tongueM", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -2.0F));

		PartDefinition tongue = tongueM.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -0.5F, -6.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.9599F, 0.0F, 0.0F));

		PartDefinition tongueM2 = tongue.addOrReplaceChild("tongueM2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition tongue2 = tongueM2.addOrReplaceChild("tongue2", CubeListBuilder.create().texOffs(39, 11).addBox(-1.5F, -0.5F, -6.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition tongueM3 = tongue2.addOrReplaceChild("tongueM3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition tongue3 = tongueM3.addOrReplaceChild("tongue3", CubeListBuilder.create().texOffs(42, 0).addBox(-1.0F, -0.5F, -6.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition lungs = partdefinition.addOrReplaceChild("lungs", CubeListBuilder.create().texOffs(44, 37).addBox(-1.5F, -4.0F, -1.0F, 3.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 18).addBox(-1.5F, -3.5F, 1.0F, 3.0F, 10.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-2.25F, 3.0F, 3.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition lungs2 = partdefinition.addOrReplaceChild("lungs2", CubeListBuilder.create().texOffs(0, 42).addBox(-1.5F, -4.0F, -1.0F, 3.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 47).addBox(-1.5F, -3.5F, 1.0F, 3.0F, 10.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(1.75F, 3.0F, 3.0F, 0.3054F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {


		 if (!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F)){
			this.RightArm.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
			this.LeftArm.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;

		} else{
			this.RightArm.xRot = Mth.sin(ageInTicks/8)/10;
			this.LeftArm.xRot = -Mth.sin(ageInTicks/8)/10;
		}

		this.LeftLeg.xRot = Mth.cos(limbSwing * 0.8F) * 0.8F * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing * 0.8F) * -0.8F * limbSwingAmount;
		if (LeftLeg.xRot < 0){
			this.LeftLeg.getChild("leftForLeg").xRot = -LeftLeg.xRot;}
		if (RightLeg.xRot < 0){
			this.RightLeg.getChild("rightForLeg").xRot = -RightLeg.xRot;}


		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch /  ( 90F / (float) Math.PI);
		this.lungs.zScale = (1F  + Mth.sin(ageInTicks/10)/10);
		this.lungs.yScale = (1F + Mth.sin(ageInTicks/10)/10);
		this.lungs.xScale = (1F -  Mth.sin(ageInTicks/10)/10);
		this.lungs2.zScale = (1F +  Mth.sin(ageInTicks/10)/10);
		this.lungs2.yScale = (1F +  Mth.sin(ageInTicks/10)/10);
		this.lungs2.xScale = (1F -  Mth.sin(ageInTicks/10)/10);



		this.tongueM.xRot = (Mth.sin(ageInTicks/6)/6);
		this.tongueM.getChild("tongue").getChild("tongueM2").xRot = (Mth.sin(ageInTicks/6)/6);
		this.tongueM.getChild("tongue").getChild("tongueM2").getChild("tongue2").getChild("tongueM3").xRot = (Mth.sin(ageInTicks/6)/6);

		this.head.getChild("FangJoint").xRot = (Mth.sin(ageInTicks/7)/6);
		this.head.getChild("FangJoint2").xRot = (Mth.sin(ageInTicks/6)/8);

		this.head.getChild("FangJoint").yRot = (Mth.sin(ageInTicks/6)/7);
		this.head.getChild("FangJoint2").yRot = (Mth.sin(ageInTicks/6)/6);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tongueM.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lungs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lungs2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}