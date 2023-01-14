package com.Harbinger.Spore.Sentities.MovementControls;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

public class InfectedUnderWaterMovementControl extends MoveControl {
    private final Mob mob;

    public InfectedUnderWaterMovementControl(Mob m) {
        super(m);
        this.mob = m;
    }

    public void tick() {
        if (this.operation == MoveControl.Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
            Vec3 vec3 = new Vec3(this.wantedX - this.mob.getX(), this.wantedY - this.mob.getY(), this.wantedZ - this.mob.getZ());
            double d0 = vec3.length();
            double d1 = vec3.x / d0;
            double d2 = vec3.y / d0;
            double d3 = vec3.z / d0;
            float f = (float)(Mth.atan2(vec3.z, vec3.x) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 90.0F));
            this.mob.yBodyRot = this.mob.getYRot();
            float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            float f2 = Mth.lerp(0.125F, this.mob.getSpeed(), f1);
            this.mob.setSpeed(f2);
            double d4 = Math.sin((double)(this.mob.tickCount + this.mob.getId()) * 0.5D) * 0.05D;
            double d5 = Math.cos((double)(this.mob.getYRot() * ((float)Math.PI / 180F)));
            double d6 = Math.sin((double)(this.mob.getYRot() * ((float)Math.PI / 180F)));
            double d7 = Math.sin((double)(this.mob.tickCount + this.mob.getId()) * 0.75D) * 0.05D;
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(d4 * d5, d7 * (d6 + d5) * 0.25D + (double)f2 * d2 * 0.1D, d4 * d6));
            LookControl lookcontrol = this.mob.getLookControl();
            double d8 = this.mob.getX() + d1 * 2.0D;
            double d9 = this.mob.getEyeY() + d2 / d0;
            double d10 = this.mob.getZ() + d3 * 2.0D;
            double d11 = lookcontrol.getWantedX();
            double d12 = lookcontrol.getWantedY();
            double d13 = lookcontrol.getWantedZ();
            if (!lookcontrol.isLookingAtTarget()) {
                d11 = d8;
                d12 = d9;
                d13 = d10;
            }

            this.mob.getLookControl().setLookAt(Mth.lerp(0.125D, d11, d8), Mth.lerp(0.125D, d12, d9), Mth.lerp(0.125D, d13, d10), 10.0F, 40.0F);
        } else {
            this.mob.setSpeed(0.0F);
        }
    }
}
