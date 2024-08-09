package com.dominoEximo.newSword.entity.ai;


import com.dominoEximo.newSword.entity.custom.SnakeEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SnakeAttackGoal extends MeleeAttackGoal {
    private static final int ATTACK_RANGE = 5;
    private final SnakeEntity entity;
    private int attackDelay = 15;
    private int ticksUntilNextAttack = 64;
    private boolean shouldCountTillNextAttack = false;

    public SnakeAttackGoal(PathfinderMob p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
        entity = (SnakeEntity) p_25552_;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 15;
        ticksUntilNextAttack = 45;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity p_25557_) {
        if (isEnemyWithinAttackDistance(p_25557_)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(p_25557_.getX(), p_25557_.getEyeY(), p_25557_.getZ());
                performAttack(p_25557_);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        int diffX = Math.abs(pEnemy.getBlockX() - entity.getBlockX());
        int diffY = Math.abs(pEnemy.getBlockY() - entity.getBlockY());
        int diffZ = Math.abs(pEnemy.getBlockZ() - entity.getBlockZ());
        return diffX + diffY + diffZ <= ATTACK_RANGE;
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay( (attackDelay * 2));
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }


    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }


    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack){
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }
}
