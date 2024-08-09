package com.dominoEximo.newSword.entity.client.mimic;

import com.dominoEximo.newSword.SwordModClass;
import com.dominoEximo.newSword.entity.client.ModModelLayers;
import com.dominoEximo.newSword.entity.client.snake.SnakeModel;
import com.dominoEximo.newSword.entity.custom.MimicEntity;
import com.dominoEximo.newSword.entity.custom.SnakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MimicRenderer extends MobRenderer<MimicEntity,MimicModel<MimicEntity>> {


    public MimicRenderer(EntityRendererProvider.Context pContext) {
        super(pContext,new MimicModel(pContext.bakeLayer(ModModelLayers.MIMIC_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(MimicEntity p_114482_) {
        return ResourceLocation.fromNamespaceAndPath(SwordModClass.MODID,"textures/entity/mimic.png");
    }

    @Override
    public void render(MimicEntity p_115308_, float p_115309_, float p_115310_, PoseStack p_115311_, MultiBufferSource p_115312_, int p_115313_) {
        if(p_115308_.isBaby()){
            p_115311_.scale(0.5f,0.5f,0.5f);
        }

        super.render(p_115308_, p_115309_, p_115310_, p_115311_, p_115312_, p_115313_);
    }
}
