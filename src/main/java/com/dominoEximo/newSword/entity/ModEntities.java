package com.dominoEximo.newSword.entity;

import com.dominoEximo.newSword.SwordModClass;
import com.dominoEximo.newSword.entity.custom.SnakeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SwordModClass.MODID);

    public static final RegistryObject<EntityType<SnakeEntity>> SNAKE =
            ENTITY_TYPES.register("snake",
                    () -> EntityType.Builder.of(SnakeEntity::new,
                            MobCategory.CREATURE)
                                    .sized(1f,1f)
                            .build("snake"));
    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
