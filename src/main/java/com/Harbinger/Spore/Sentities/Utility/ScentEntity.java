package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sparticles;
import com.Harbinger.Spore.Sentities.Carrier;
import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
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
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public void tick() {
        if (this.isAlive()){
            this.getPersistentData().putInt("dissipate", 1 + this.getPersistentData().getInt("dissipate"));
            if (this.getPersistentData().getInt("dissipate") >= SConfig.SERVER.scent_life.get()) {
                this.discard();
            }
            if (SConfig.SERVER.scent_summon.get()){
            this.getPersistentData().putInt("summon", 1 + this.getPersistentData().getInt("summon"));
            if (this.getPersistentData().getInt("summon") >= SConfig.SERVER.scent_summon_cooldown.get()) {
                if (!this.level.isClientSide && checkForNonInfected(this)){
                this.Summon(this);}
            }}
        }
        super.tick();
    }

    boolean checkForNonInfected(Entity entity){
        AABB boundingBox = entity.getBoundingBox().inflate(16);
        List<Entity> entities = entity.level.getEntities(entity, boundingBox ,EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        for (Entity en : entities) {
            if (en instanceof LivingEntity && !(SConfig.SERVER.blacklist.get().contains(en.getEncodeId()) || en instanceof Infected || en instanceof UtilityEntity)){
                return true;
            }
        }
        return false;
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
        return source.isExplosion();
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
        ServerLevelAccessor world = (ServerLevelAccessor) entity.level;
        Level level = entity.level;
        Random rand = new Random();
        int d = random.nextInt(0, 3);
        int r = random.nextInt(-12, 12);
        int c = random.nextInt(-12, 12);
        List<? extends String> ev = SConfig.SERVER.inf_summon.get();

        if (world.isEmptyBlock(new BlockPos(this.getX() + r, this.getY() + d, this.getZ() + c))){
            for (int i = 0; i < 1; ++i) {
                int randomIndex = rand.nextInt(ev.size());
                ResourceLocation randomElement1 = new ResourceLocation(ev.get(randomIndex));
                EntityType<?> randomElement = ForgeRegistries.ENTITY_TYPES.getValue(randomElement1);
                Mob waveentity = (Mob) randomElement.create(level);
                assert waveentity != null;
                waveentity.setPos(entity.getX() + r, entity.getY() + 0.5D + d, entity.getZ() + c);
                waveentity.finalizeSpawn(world, level.getCurrentDifficultyAt(new BlockPos(entity.getX(), entity.getY(), entity.getZ())), MobSpawnType.NATURAL, null, null);
                this.getPersistentData().putInt("summon", 0);
                level.addFreshEntity(waveentity);
            }
        }
    }
    public boolean addEffect(MobEffectInstance p_182397_, @Nullable Entity p_182398_) {
        return false;
    }
}