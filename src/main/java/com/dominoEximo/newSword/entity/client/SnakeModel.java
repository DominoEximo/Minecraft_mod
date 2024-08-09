package com.dominoEximo.newSword.entity.client;



import com.dominoEximo.newSword.entity.animations.ModAnimationDefinitions;
import com.dominoEximo.newSword.entity.custom.SnakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.geom.PartPose;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class SnakeModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart Snake;
	private final ModelPart body;
	private final ModelPart bodypart_1;
	private final ModelPart bodypart_2;
	private final ModelPart bodypart_3;
	private final ModelPart head;
	private final ModelPart fangs;

	public SnakeModel(ModelPart root) {
		this.Snake = root.getChild("Snake");
		this.body = Snake.getChild("body");
		this.bodypart_1 = Snake.getChild("body").getChild("bodypart_1");
		this.bodypart_2 = Snake.getChild("body").getChild("bodypart_2");
		this.bodypart_3 = Snake.getChild("body").getChild("bodypart_3");
		this.head = Snake.getChild("body").getChild("head");
		this.fangs = head.getChild("fangs");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Snake = partdefinition.addOrReplaceChild("Snake", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = Snake.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-2.0F, -3.0F, -0.75F));

		PartDefinition bodypart_1 = body.addOrReplaceChild("bodypart_1", CubeListBuilder.create().texOffs(38, 38).addBox(-3.0F, -4.0F, 17.0F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bodypart_2 = body.addOrReplaceChild("bodypart_2", CubeListBuilder.create().texOffs(25, 25).addBox(-3.0F, -4.0F, -10.0F, 4.0F, 4.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bodypart_3 = body.addOrReplaceChild("bodypart_3", CubeListBuilder.create().texOffs(41, 41).addBox(-3.0F, -4.0F, -21.0F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.0F, -24.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(-5.0F, -7.0F, -26.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 3.0F, 0.75F));

		PartDefinition fangs = head.addOrReplaceChild("fangs", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.0F, -25.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw,headPitch,ageInTicks);

		this.animateWalk(ModAnimationDefinitions.move,limbSwing,limbSwingAmount+10,2f,2.5f);
		this.animate(((SnakeEntity) entity).idleAnimationState, ModAnimationDefinitions.idle, ageInTicks, 1f);
		this.animate(((SnakeEntity) entity).attackAnimationState, ModAnimationDefinitions.attack, ageInTicks, 1f);

	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
		Snake.render(poseStack, vertexConsumer, packedLight, packedOverlay);

	}

	@Override
	public ModelPart root() {
		return Snake;
	}
}