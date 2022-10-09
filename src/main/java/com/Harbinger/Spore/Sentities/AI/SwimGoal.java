package com.Harbinger.Spore.Sentities.AI;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;

public class SwimGoal extends MoveToBlockGoal {
    private final LivingEntity target;
    public SwimGoal(PathfinderMob mob, double speed, int range) {
        super(mob, speed, range);
        target = mob.getTarget();
    }
    public boolean requiresUpdateEveryTick() {
        return true;
    }
    @Override
    public boolean canUse() {
        return this.mob.isInWater() && target ==  null;
    }


    @Override
    protected boolean isValidTarget(LevelReader reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return reader.isEmptyBlock(blockpos) && reader.isEmptyBlock(blockpos.above()) && reader.getBlockState(pos).entityCanStandOn(reader, pos, this.mob);

    }

    public boolean canContinueToUse() {
        return this.mob.isInWater();
    }

    @Override
    public void tick() {
        super.tick();
        double d0 = this.mob.distanceToSqr(Vec3.atCenterOf(this.blockPos));
        if ((this.mob.getRandom().nextFloat() < 0.8F) && (d0 < 8.0D)) {
            this.mob.getJumpControl().jump();
        }
    }
}
