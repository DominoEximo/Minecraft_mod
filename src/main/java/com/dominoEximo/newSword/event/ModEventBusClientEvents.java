package com.dominoEximo.newSword.event;

import com.dominoEximo.newSword.SwordModClass;
import com.dominoEximo.newSword.entity.client.ModModelLayers;
import com.dominoEximo.newSword.entity.client.SnakeModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SwordModClass.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.SNAKE_LAYER, SnakeModel::createBodyLayer);
    }
}
