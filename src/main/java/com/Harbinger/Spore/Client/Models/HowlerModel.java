package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Howler;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class HowlerModel<T extends Howler> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "howlermodel"), "main");
	private final ModelPart Body;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart Head;
	private final ModelPart tail;

	public HowlerModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.Head = root.getChild("Head");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, 8.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition spine = Body.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(13, 31).addBox(-2.0F, -4.5F, -4.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 8.0F, 2.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition back = spine.addOrReplaceChild("back", CubeListBuilder.create().texOffs(52, 0).addBox(0.0F, -4.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back2 = spine.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(42, 50).addBox(-2.0F, -4.0F, -2.25F, 3.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition spine2 = Body.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -5.5F, -5.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 4.0F, 1.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition back3 = spine2.addOrReplaceChild("back3", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, -4.0F, -1.75F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition back4 = spine2.addOrReplaceChild("back4", CubeListBuilder.create().texOffs(23, 21).addBox(-3.0F, -4.0F, -2.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition throat = Body.addOrReplaceChild("throat", CubeListBuilder.create().texOffs(45, 32).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(51, 38).addBox(-1.25F, 0.0F, -1.25F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-2.0F, -3.0F, 0.25F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 4).addBox(-2.0F, -3.0F, -1.25F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 0).addBox(-1.5F, -3.0F, -2.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 0).addBox(0.25F, -3.0F, -2.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -4.0F, 0.2618F, -0.7854F, -0.1745F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(14, 41).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 8.0F, 2.5F));

		PartDefinition leftForLeg = LeftLeg.addOrReplaceChild("leftForLeg", CubeListBuilder.create().texOffs(31, 31).addBox(-1.25F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(41, 20).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 8.0F, 2.5F));

		PartDefinition rightForLeg = RightLeg.addOrReplaceChild("rightForLeg", CubeListBuilder.create().texOffs(0, 37).addBox(-1.75F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(28, 46).addBox(-1.0F, -2.0F, -2.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 3.0F, -1.0F));

		PartDefinition LeftForArm = LeftArm.addOrReplaceChild("LeftForArm", CubeListBuilder.create().texOffs(41, 39).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 4.0F, -0.5F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 49).addBox(-2.0F, -2.0F, -2.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 3.0F, -1.0F));

		PartDefinition RightForArm = RightArm.addOrReplaceChild("RightForArm", CubeListBuilder.create().texOffs(42, 7).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 4.0F, -0.5F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(22, 14).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition tooth = Head.addOrReplaceChild("tooth", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 7).addBox(-0.5F, 0.5F, -0.25F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, -3.0F));

		PartDefinition cube_r1 = tooth.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 7).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, -0.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tooth2 = Head.addOrReplaceChild("tooth2", CubeListBuilder.create().texOffs(3, 3).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 7).addBox(-0.5F, 0.5F, -0.25F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -2.0F, -3.25F));

		PartDefinition cube_r2 = tooth2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, -0.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tooth3 = Head.addOrReplaceChild("tooth3", CubeListBuilder.create().texOffs(3, 1).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 6).addBox(-0.5F, 0.5F, -0.25F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -3.0F, 0.0F, -1.1345F, 0.0F));

		PartDefinition cube_r3 = tooth3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(6, 5).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, -0.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tooth4 = Head.addOrReplaceChild("tooth4", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 6).addBox(-0.5F, 0.5F, -0.25F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.0F, -3.0F));

		PartDefinition cube_r4 = tooth4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(6, 3).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, -0.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tooth5 = Head.addOrReplaceChild("tooth5", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 6).addBox(-0.5F, 0.5F, -0.25F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -2.0F, -3.0F, 0.0F, 1.0472F, 0.0F));

		PartDefinition cube_r5 = tooth5.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(6, 1).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, -0.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(14, 53).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.25F, -0.8727F, 0.0F, 0.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(52, 45).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.15F)), PartPose.offset(0.0F, 0.0F, 4.75F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(51, 13).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 4.5F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(51, 53).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}