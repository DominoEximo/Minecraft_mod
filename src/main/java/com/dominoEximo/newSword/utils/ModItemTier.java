package com.dominoEximo.newSword.utils;

import com.dominoEximo.newSword.items.ModItems;
import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import com.dominoEximo.newSword.items.ModItems;
import java.util.function.Supplier;

public enum ModItemTier implements Tier {
    SAPPHIRE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 2.0F, 24.0F, 15, () -> Ingredient.of(ModItems.SAPPHIRE.get()));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModItemTier(
            final TagKey<Block> incorrect, final int uses, final float speed, final float damage, final int enhancementValue, final Supplier<Ingredient> p_43337_
    )
    {
        this.incorrectBlocksForDrops = incorrect;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enhancementValue;
        this.repairIngredient = Suppliers.memoize(p_43337_::get);
    }


    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return null;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
