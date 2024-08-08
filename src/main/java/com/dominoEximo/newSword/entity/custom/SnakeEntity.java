package com.dominoEximo.newSword.entity.custom;

import com.dominoEximo.newSword.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

public class SnakeEntity extends Animal {
    public SnakeEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {

        super(p_27557_, p_27558_);

    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1,new BreedGoal(this,1.3));
        this.goalSelector.addGoal(2,new TemptGoal(this,1.3D, Ingredient.of(Items.APPLE), false));
        this.goalSelector.addGoal(3,new FollowParentGoal(this,1.3));
        this.goalSelector.addGoal(4,new WaterAvoidingRandomStrollGoal(this,1.3D));
        this.goalSelector.addGoal(5,new LookAtPlayerGoal(this, Player.class,3f));
        this.goalSelector.addGoal(6,new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,10)
                .add(Attributes.FOLLOW_RANGE,240)
                .add(Attributes.MOVEMENT_SPEED,0.250)
                .add(Attributes.ARMOR_TOUGHNESS,0.1f)
                .add(Attributes.ATTACK_KNOCKBACK,0.5f)
                .add(Attributes.ATTACK_DAMAGE,1f);
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {

        return p_27600_.is(Items.APPLE);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {

        return ModEntities.SNAKE.get().create(p_146743_);
    }
}
