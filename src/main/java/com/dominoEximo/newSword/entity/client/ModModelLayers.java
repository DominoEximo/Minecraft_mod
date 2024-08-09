package com.dominoEximo.newSword.entity.client;

import com.dominoEximo.newSword.SwordModClass;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    public static final ModelLayerLocation SNAKE_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(SwordModClass.MODID,"snake_layer"),"main");

    public static final ModelLayerLocation MIMIC_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(SwordModClass.MODID,"mimic_layer"),"main");

}
