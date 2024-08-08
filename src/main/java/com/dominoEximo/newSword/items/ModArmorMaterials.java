package com.dominoEximo.newSword.items;



import com.dominoEximo.newSword.SwordModClass;
import net.minecraft.Util;
import net.minecraft.core.Holder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.event.sound.SoundEvent;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterials extends ArmorMaterials{
    public static final Holder<ArmorMaterial> LEATHER = register(
            "leather",
            Util.make(new EnumMap<>(ArmorItem.Type.class), p_327101_ -> {
                p_327101_.put(ArmorItem.Type.BOOTS, 1);
                p_327101_.put(ArmorItem.Type.LEGGINGS, 2);
                p_327101_.put(ArmorItem.Type.CHESTPLATE, 3);
                p_327101_.put(ArmorItem.Type.HELMET, 1);
                p_327101_.put(ArmorItem.Type.BODY, 3);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.of(Items.LEATHER),
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace("leather"), "", true),
                    new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace("leather"), "_overlay", false)
            )
    );
    public static final Holder<ArmorMaterial> SAPPHIRE = ModArmorMaterials.register("sapphire", Util.make(new EnumMap<>(ArmorItem.Type.class), p_327103_ -> {
        p_327103_.put(ArmorItem.Type.BOOTS, 3);
        p_327103_.put(ArmorItem.Type.LEGGINGS, 6);
        p_327103_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_327103_.put(ArmorItem.Type.HELMET, 3);
        p_327103_.put(ArmorItem.Type.BODY, 11);}),
            15,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            3.0F,
            0.1F,
            () -> Ingredient.of(ModItems.SAPPHIRE.get()),
            List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(SwordModClass.MODID,"sapphire"), "", true)
            ));
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private static final int[] BASE_DURABILITY = {11, 16, 16, 13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }


    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }


    public int getEnchantmentValue() {
        return enchantmentValue;
    }


    public SoundEvent getEquipSound() {
        return this.equipSound;
    }


    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }


    public String getName() {
        return SwordModClass.MODID + ":" + this.name;
    }


    public float getToughness() {
        return this.toughness;
    }


    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    private static Holder<ArmorMaterial> register(
            String p_332406_,
            EnumMap<ArmorItem.Type, Integer> p_331524_,
            int p_331490_,
            Holder<net.minecraft.sounds.SoundEvent> p_331648_,
            float p_327988_,
            float p_328616_,
            Supplier<Ingredient> p_334412_,
            List<ArmorMaterial.Layer> p_330855_
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, p_331524_.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.withDefaultNamespace(p_332406_),
                new ArmorMaterial(enummap, p_331490_, p_331648_, p_334412_, p_330855_, p_327988_, p_328616_)
        );
    }

}
