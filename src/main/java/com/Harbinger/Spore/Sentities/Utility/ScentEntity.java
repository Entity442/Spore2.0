package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sparticles;
import com.Harbinger.Spore.Module.SmobType;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class ScentEntity extends PathfinderMob {

    private int dissipate = SConfig.SERVER.scent_life.get();

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
        if (SConfig.SERVER.scent_particles.get() && level instanceof ClientLevel) {
            int i = Mth.floor(this.getX());
            int j = Mth.floor(this.getY());
            int k = Mth.floor(this.getZ());
            Level world = this.level;
            RandomSource randomSource = this.random;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            for (int l = 0; l < 14; ++l) {
                blockpos$mutableblockpos.set(i + Mth.nextInt(randomSource, -6, 6), j + Mth.nextInt(randomSource, -6, 6), k + Mth.nextInt(randomSource, -6, 6));
                BlockState blockstate = world.getBlockState(blockpos$mutableblockpos);
                if (!blockstate.isCollisionShapeFullBlock(world, blockpos$mutableblockpos)) {
                    world.addParticle(Sparticles.SPORE_PARTICLE.get(), (double) blockpos$mutableblockpos.getX() + randomSource.nextDouble(), (double) blockpos$mutableblockpos.getY() + randomSource.nextDouble(), (double) blockpos$mutableblockpos.getZ() + randomSource.nextDouble(), 0.0D, 0.1D, 0.0D);
                }
            }
        }
    }

    public boolean addEffect(MobEffectInstance p_182397_, @Nullable Entity p_182398_) {
        return false;
    }
}