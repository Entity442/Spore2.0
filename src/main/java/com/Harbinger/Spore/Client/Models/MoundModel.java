package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Utility.Mound;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class MoundModel<T extends Mound> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "mound"), "main");
	private final ModelPart trunk;
	private final ModelPart parts;
	private final ModelPart parts2;
	private final ModelPart parts3;
	private final ModelPart parts4;
	private final ModelPart parts5;

	public MoundModel(ModelPart root) {
		this.trunk = root.getChild("trunk");
		this.parts = root.getChild("parts");
		this.parts2 = root.getChild("parts2");
		this.parts3 = root.getChild("parts3");
		this.parts4 = root.getChild("parts4");
		this.parts5 = root.getChild("parts5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition trunk = partdefinition.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(24, 31).addBox(-2.5F, -6.0F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition parts = partdefinition.addOrReplaceChild("parts", CubeListBuilder.create().texOffs(0, 30).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.75F, 0.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition parts2 = partdefinition.addOrReplaceChild("parts2", CubeListBuilder.create().texOffs(18, 24).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.75F, 0.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition parts3 = partdefinition.addOrReplaceChild("parts3", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.75F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition parts4 = partdefinition.addOrReplaceChild("parts4", CubeListBuilder.create().texOffs(18, 17).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.75F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition parts5 = partdefinition.addOrReplaceChild("parts5", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(36, 16).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.75F, 0.0F, 0.0F, -0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		trunk.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		parts.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		parts2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		parts3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		parts4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		parts5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}