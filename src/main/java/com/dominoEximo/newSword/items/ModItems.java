package com.dominoEximo.newSword.items;

import com.dominoEximo.newSword.SwordModClass;
import com.dominoEximo.newSword.utils.ModItemTier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
   public static final DeferredRegister<Item> ITEMS =
           DeferredRegister.create(ForgeRegistries.ITEMS, SwordModClass.MODID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new CustomSwordItem(ModItemTier.SAPPHIRE,new SwordItem.Properties()
                    .rarity(Rarity.EPIC)
                    ));


   public static void register(IEventBus eventBus){
       ITEMS.register(eventBus);
   }
}
