package com.Harbinger.Spore.Sentities.MovementControls;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

public class InfectedMovementControl extends MoveControl {
    private final Mob mob;

    public InfectedMovementControl(Mob mob) {
        super(mob);
        this.mob = mob;
    }


    public void tick() {
            if (mob.horizontalCollision && mob.isInWater()) {
                Vec3 initialVec = mob.getDeltaMovement();
                Vec3 climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
                mob.setDeltaMovement(climbVec.x * 0.91D,
                        climbVec.y * 0.98D, climbVec.z * 0.91D);
            }

        if (this.operation == MoveControl.Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getY();
            double d2 = this.wantedZ - this.mob.getZ();
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d1 /= d3;
            float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 90.0F));
            this.mob.yBodyRot = this.mob.getYRot();
            float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            this.mob.setSpeed(Mth.lerp(0.2F, this.mob.getSpeed(), f1));
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, (double)this.mob.getSpeed() * d1 * 0.1D, 0.0D));
        } else {this.mob.setSpeed(0.0F);}
        if (this.operation == Operation.JUMPING && this.mob.horizontalCollision && this.mob.isOnGround()){
            this.mob.getJumpControl().jump();
        }
    }
}
