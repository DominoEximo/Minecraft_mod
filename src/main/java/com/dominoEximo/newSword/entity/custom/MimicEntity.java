package com.dominoEximo.newSword.entity.custom;


import com.dominoEximo.newSword.entity.ai.SnakeAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MimicEntity extends Monster {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MimicEntity.class, EntityDataSerializers.BOOLEAN);
    public MimicEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();


        if(this.level().isClientSide){
            setUpAnimationStates();
        }
    }


    private void setUpAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0){
            attackAnimationTimeout = 40;
            attackAnimationState.start(this.tickCount);
        }else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()){
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float p_268283_) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(p_268283_ * 6F,1f);
        }else{
            f = 0f;
        }

        this.walkAnimation.update(f,0.2f);
    }

    public void setAttacking(boolean attacking){
        this.entityData.set(ATTACKING,attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_333447_) {
        p_333447_.define(ATTACKING,false);
        super.defineSynchedData(p_333447_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1,new MeleeAttackGoal(this,1.0D,true));
        this.goalSelector.addGoal(4,new WaterAvoidingRandomStrollGoal(this,1.3D));
        this.goalSelector.addGoal(5,new LookAtPlayerGoal(this, Player.class,3f));
        this.goalSelector.addGoal(6,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH,50)
                .add(Attributes.FOLLOW_RANGE,240)
                .add(Attributes.MOVEMENT_SPEED,0.250)
                .add(Attributes.ARMOR_TOUGHNESS,0.1f)
                .add(Attributes.ATTACK_KNOCKBACK,0.5f)
                .add(Attributes.ATTACK_DAMAGE,1f);
    }


    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SHULKER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.SHULKER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SHULKER_DEATH;
    }

}
