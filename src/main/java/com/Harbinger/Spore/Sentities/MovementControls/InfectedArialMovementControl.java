package com.Harbinger.Spore.Sentities.MovementControls;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class InfectedArialMovementControl extends MoveControl {
    private final Mob mob;
    private int floatDuration;

    public InfectedArialMovementControl(Mob m) {
        super(m);
        this.mob = m;
    }

    public void tick() {
        if (this.operation == MoveControl.Operation.MOVE_TO) {
            if (this.floatDuration-- <= 0) {
                this.floatDuration += this.mob.getRandom().nextInt(5) + 2;
                Vec3 vec3 = new Vec3(this.wantedX - this.mob.getX(), this.wantedY - this.mob.getY(), this.wantedZ - this.mob.getZ());
                double d0 = vec3.length();
                vec3 = vec3.normalize();
                if (this.canReach(vec3, Mth.ceil(d0))) {
                    this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(vec3.scale(0.1D)));
                } else {
                    this.operation = MoveControl.Operation.WAIT;
                }
            }

        }
    }

    private boolean canReach(Vec3 p_32771_, int p_32772_) {
        AABB aabb = this.mob.getBoundingBox();

        for(int i = 1; i < p_32772_; ++i) {
            aabb = aabb.move(p_32771_);
            if (!this.mob.level.noCollision(this.mob, aabb)) {
                return false;
            }
        }

        return true;
    }
}
