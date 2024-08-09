package com.dominoEximo.newSword.entity.client.mimic;

import com.dominoEximo.newSword.entity.animations.ModAnimationDefinitions;
import com.dominoEximo.newSword.entity.custom.MimicEntity;
import com.dominoEximo.newSword.entity.custom.SnakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.joml.Quaternionf;

public class MimicModel<T extends Entity> extends HierarchicalModel<T> {

    private final ModelPart chest;
    private final ModelPart upper_part;
    private final ModelPart fangs;

    public MimicModel(ModelPart root) {
        this.chest = root.getChild("chest");
        this.upper_part = chest.getChild("upper_part");
        this.fangs = upper_part.getChild("fangs");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition chest = partdefinition.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, 3.1416F, -3.1416F));

        PartDefinition upper_part = chest.addOrReplaceChild("upper_part", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -9.0F, 7.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

        PartDefinition fangs = upper_part.addOrReplaceChild("fangs", CubeListBuilder.create().texOffs(2, 1).addBox(-3.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 1).addBox(6.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 1).addBox(6.0F, -2.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 1).addBox(-3.0F, -2.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 1).addBox(3.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 1).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -7.0F, 5.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw,headPitch,ageInTicks);

        this.animateWalk(ModAnimationDefinitions.MIMIC_MOVE,limbSwing,limbSwingAmount,1f,1f);
        this.animate(((MimicEntity) entity).idleAnimationState, ModAnimationDefinitions.MIMIC_ATTACK, ageInTicks, 1f);

        this.animate(((MimicEntity) entity).attackAnimationState, ModAnimationDefinitions.MIMIC_ATTACK, ageInTicks, 1f);
    }



    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.upper_part.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.upper_part.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int alpha) {
        chest.render(poseStack, vertexConsumer, packedLight, packedOverlay);

    }

    @Override
    public ModelPart root() {
        return this.chest;
    }
}
