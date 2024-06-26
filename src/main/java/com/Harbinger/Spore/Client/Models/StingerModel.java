package com.Harbinger.Spore.Client.Models;// Made with Blockbench 4.10.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.Harbinger.Spore.Sentities.Projectile.StingerProjectile;
import com.Harbinger.Spore.Spore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class StingerModel<T extends StingerProjectile> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Spore.MODID, "stingermodel"), "main");
	private final ModelPart Stinger;

	public StingerModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.Stinger = root.getChild("Stinger");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Stinger = partdefinition.addOrReplaceChild("Stinger", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = Stinger.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -8.0F, -1.5F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = Stinger.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, -8.0F, -1.5F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Stinger.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}