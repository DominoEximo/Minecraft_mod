package com.dominoEximo.newSword.items;


import net.minecraft.ChatFormatting;


import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;



public class CustomSwordItem extends SwordItem {
    public CustomSwordItem(Tier tier, Item.Properties properties) {
        super(tier,properties);
    }

    @Override
    public float getAttackDamageBonus(Entity p_345227_, float p_327880_, DamageSource p_342960_) {
        return getTier().getAttackDamageBonus();
    }

    @Override
    public ItemAttributeModifiers getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext p_333372_, List<Component> components, TooltipFlag flag) {

        Float actualDamage = getTier().getAttackDamageBonus() + 3.0F;

        components.add(Component.literal("When in Main Hand: ").withStyle(ChatFormatting.GRAY));
        components.add(Component.literal(" " + actualDamage.intValue() + " Attack Damage").withStyle(ChatFormatting.DARK_GREEN));
        components.add(Component.literal(" " + getTier().getSpeed() + " Attack Speed").withStyle(ChatFormatting.DARK_GREEN));

        super.appendHoverText(stack, p_333372_, components, flag);
    }
}
