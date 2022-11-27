package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class InfectedPanicGoal extends Goal {
    protected final PathfinderMob mob;
    protected final double speedModifier;
    protected double posX;
    protected double posY;
    protected double posZ;

    public InfectedPanicGoal(PathfinderMob p_25691_, double p_25692_) {
        this.mob = p_25691_;
        this.speedModifier = p_25692_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        if (!this.shouldPanic()) {
            return false;
        } else if (this.mob.isOnFire()){
        BlockPos blockpos = this.lookForWater(this.mob.level, this.mob, 15);
        if (blockpos != null) {
            this.posX = (double)blockpos.getX();
            this.posY = (double)blockpos.getY();
            this.posZ = (double)blockpos.getZ();
            return true;}
        } else {
            BlockPos blockpos = this.lookForDarkness(this.mob.level, this.mob, 15);
            if (blockpos != null) {
                this.posX = (double) blockpos.getX();
                this.posY = (double) blockpos.getY();
                this.posZ = (double) blockpos.getZ();
                return true;
            }
        }
            return this.findRandomPosition();
    }

    protected boolean shouldPanic() {
        return  this.mob.isFreezing() || this.mob.isOnFire();
    }

    protected boolean findRandomPosition() {
        Vec3 vec3 = DefaultRandomPos.getPos(this.mob, 5, 4);
        if (vec3 == null) {
            return false;
        } else {
            this.posX = vec3.x;
            this.posY = vec3.y;
            this.posZ = vec3.z;
            return true;
        }
    }

    public void start() {
        this.mob.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
    }



    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone();
    }

    @Nullable
    protected BlockPos lookForWater(BlockGetter p_198173_, Entity p_198174_, int p_198175_) {
        BlockPos blockpos = p_198174_.blockPosition();
        return !p_198173_.getBlockState(blockpos).getCollisionShape(p_198173_, blockpos).isEmpty() ? null : BlockPos.findClosestMatch(p_198174_.blockPosition(), p_198175_, 1, (p_196649_) -> {
            return p_198173_.getFluidState(p_196649_).is(FluidTags.WATER);
        }).orElse((BlockPos)null);
    }

    @Nullable
    protected BlockPos lookForDarkness(BlockGetter getter, Entity entity, int p_198175_) {
        BlockPos blockpos = entity.blockPosition();
        return !getter.getBlockState(blockpos).getCollisionShape(getter, blockpos).isEmpty() ? null : BlockPos.findClosestMatch(entity.blockPosition(), p_198175_, 1, (p_196649_) -> {
            return getter.getMaxLightLevel() < 15;
        }).orElse((BlockPos)null);
    }
}
