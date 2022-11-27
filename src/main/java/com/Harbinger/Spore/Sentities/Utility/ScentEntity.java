package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sparticles;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ScentEntity extends UtilityEntity {

    public ScentEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
        this.isNoGravity();
    }

    @Override
    public void tick() {
        if (this.isAlive()){
            this.getPersistentData().putInt("dissipate", 1 + this.getPersistentData().getInt("dissipate"));
            if (this.getPersistentData().getInt("dissipate") >= SConfig.SERVER.scent_life.get()) {
                this.discard();
            }
            this.getPersistentData().putInt("summon", 1 + this.getPersistentData().getInt("summon"));
            if (this.getPersistentData().getInt("summon") >= 300) {
                this.getPersistentData().putInt("summon", 0);
                this.Summon(this);
            }
        }
        super.tick();
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
        if (SConfig.SERVER.scent_particles.get()) {
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

    public void Summon(LivingEntity entity) {
        Level level = entity.level;
        Random rand = new Random();
        int d = random.nextInt(0 ,3);
        int r = random.nextInt(-12, 12);
        List<? extends String> ev = SConfig.SERVER.inf_summon.get();

        for (int i = 0; i < 1; ++i) {
            int randomIndex = rand.nextInt(ev.size());
            ResourceLocation randomElement1 = new ResourceLocation(ev.get(randomIndex));
            EntityType<?> randomElement = ForgeRegistries.ENTITY_TYPES.getValue(randomElement1);
            Entity waveentity = randomElement.create(level);
            waveentity.setPos(entity.getX() + r, entity.getY() + 0.5D + d, entity.getZ() + r);
            level.addFreshEntity(waveentity);
        }
    }
    public boolean addEffect(MobEffectInstance p_182397_, @Nullable Entity p_182398_) {
        return false;
    }
}