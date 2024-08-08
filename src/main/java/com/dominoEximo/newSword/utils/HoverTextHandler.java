package com.dominoEximo.newSword.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Tier;

import java.util.List;

public class HoverTextHandler {

    public static void handleText(List<Component> components, Tier tier){
        float actualDamage = tier.getAttackDamageBonus() + 3.0F;

        components.add(Component.literal("When in Main Hand: ").withStyle(ChatFormatting.GRAY));
        components.add(Component.literal(" " + (int) actualDamage + " Attack Damage").withStyle(ChatFormatting.DARK_GREEN));
        components.add(Component.literal(" " + tier.getSpeed() + " Attack Speed").withStyle(ChatFormatting.DARK_GREEN));


    }
}
