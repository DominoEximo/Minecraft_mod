package com.dominoEximo.newSword.items;

import com.dominoEximo.newSword.SwordModClass;
import com.dominoEximo.newSword.utils.ModItemTier;
import net.minecraft.world.item.*;
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
            () -> new SwordItem(ModItemTier.SAPPHIRE,new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .attributes(
                            SwordItem.createAttributes(
                                    ModItemTier.SAPPHIRE,
                                    4,
                                    -2.2f
                    ))));
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModItemTier.SAPPHIRE, new Item.Properties()
                    .rarity(Rarity.EPIC)

                    .attributes(
                            PickaxeItem.createAttributes(
                                    ModItemTier.SAPPHIRE,
                                    3f,
                                    -2.8f
                            ))));
    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModItemTier.SAPPHIRE,new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .attributes(
                            AxeItem.createAttributes(
                                    ModItemTier.SAPPHIRE,
                                    7,
                                    -3.0f
                            ))));
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            () -> new ShovelItem(ModItemTier.SAPPHIRE,new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .attributes(
                            ShovelItem.createAttributes(
                                    ModItemTier.SAPPHIRE,
                                    -3.0f,
                                    -3.0f
                            ))));
    public static final RegistryObject<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            () -> new HoeItem(ModItemTier.SAPPHIRE,new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .attributes(
                            HoeItem.createAttributes(
                                    ModItemTier.SAPPHIRE,
                                    -3.0f,
                                    -3.0f
                            ))));

   public static void register(IEventBus eventBus){
       ITEMS.register(eventBus);
   }
}
