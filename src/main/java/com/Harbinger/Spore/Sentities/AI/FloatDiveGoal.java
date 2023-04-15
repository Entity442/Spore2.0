package com.Harbinger.Spore.Sentities.AI;

import com.Harbinger.Spore.Sentities.WaterInfected;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraftforge.common.ForgeMod;

import java.util.EnumSet;

public class FloatDiveGoal extends Goal {
    protected Mob mob;
    public FloatDiveGoal(Mob m){
        mob = m;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold() && !(mob instanceof WaterInfected);
    }
    public void tick() {
        if (this.mob.getRandom().nextFloat() < 0.8F && (this.mob.getAirSupply() < this.mob.getMaxAirSupply()/2 || mob.getXRot() < -5 || this.mob.getTarget() == null || (this.mob.getTarget() != null && !this.mob.getTarget().isEyeInFluidType(ForgeMod.WATER_TYPE.get())))) {
            this.mob.getJumpControl().jump();
            mob.getNavigation().setCanFloat(true);
        } else {
            mob.getNavigation().setCanFloat(false);
        }
    }
}
