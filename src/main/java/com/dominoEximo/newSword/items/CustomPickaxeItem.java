package com.dominoEximo.newSword.items;

import com.dominoEximo.newSword.utils.HoverTextHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CustomPickaxeItem extends PickaxeItem {

    public CustomPickaxeItem(Tier p_42961_, Properties p_42964_) {
        super(p_42961_, p_42964_);
    }

    @Override
    public float getDestroySpeed(ItemStack p_41425_, BlockState p_41426_) {
        return super.getDestroySpeed(p_41425_, p_41426_);
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

        HoverTextHandler.handleText(components,getTier());

        super.appendHoverText(stack, p_333372_, components, flag);
    }
}
