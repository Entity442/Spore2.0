package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.Sparticles;
import com.Harbinger.Spore.Module.SmobType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ScentEntity extends PathfinderMob {

    private int dissipate = 6000;

    public ScentEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
        this.noPhysics = true;
    }

    @Override
    public void tick() {
        super.tick();
        dissipate = dissipate - 1;
        if (dissipate <= 0) {
            this.discard();
        }

    }
    public MobType getMobType() {
        return SmobType.INFECTED;
    }

    @Override
    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,1);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return  false;
    }

    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
        int i = Mth.floor(this.getX());
        int j =Mth.floor(this.getY());
        int k = Mth.floor(this.getZ());
        Level world = this.level;
        RandomSource randomSource = this.random;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        for(int l = 0; l < 14; ++l) {
            blockpos$mutableblockpos.set(i + Mth.nextInt(randomSource, -6, 6), j + randomSource.nextInt(6), k + Mth.nextInt(randomSource, -6, 6));
            BlockState blockstate = world.getBlockState(blockpos$mutableblockpos);
            if (!blockstate.isCollisionShapeFullBlock(world, blockpos$mutableblockpos)) {
                world.addParticle(Sparticles.SPORE_PARTICLE.get(), (double)blockpos$mutableblockpos.getX() + randomSource.nextDouble(), (double)blockpos$mutableblockpos.getY() + randomSource.nextDouble(), (double)blockpos$mutableblockpos.getZ() + randomSource.nextDouble(), 0.0D, 0.1D, 0.0D);
            }
        }
    }

}