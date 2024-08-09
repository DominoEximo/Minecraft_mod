package com.dominoEximo.newSword.event;

import com.dominoEximo.newSword.SwordModClass;
import com.dominoEximo.newSword.entity.ModEntities;
import com.dominoEximo.newSword.entity.custom.MimicEntity;
import com.dominoEximo.newSword.entity.custom.SnakeEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SwordModClass.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.SNAKE.get(), SnakeEntity.createAttributes().build());
        event.put(ModEntities.MIMIC.get(), MimicEntity.createAttributes().build());
    }
}
