package com.dominoEximo.newSword.entity.client;

import com.dominoEximo.newSword.SwordModClass;
import com.dominoEximo.newSword.entity.custom.SnakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.portal.DimensionTransition;

public class SnakeRenderer extends MobRenderer<SnakeEntity,SnakeModel<SnakeEntity>> {

    public SnakeRenderer(EntityRendererProvider.Context pContext){
        super(pContext,new SnakeModel<>(pContext.bakeLayer(ModModelLayers.SNAKE_LAYER)), 1f);
    }
    @Override
    public ResourceLocation getTextureLocation(SnakeEntity p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(SwordModClass.MODID,"textures/entity/snake.png");
    }

    @Override
    public void render(SnakeEntity p_115308_, float p_115309_, float p_115310_, PoseStack poseStack,
                       MultiBufferSource p_115312_, int p_115313_) {
        if(p_115308_.isBaby()){
            poseStack.scale(0.5f,0.5f,0.5f);
        }

        super.render(p_115308_, p_115309_, p_115310_, poseStack, p_115312_, p_115313_);
    }
}
