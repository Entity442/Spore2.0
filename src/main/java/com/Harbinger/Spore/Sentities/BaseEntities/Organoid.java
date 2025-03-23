package com.Harbinger.Spore.Sentities.BaseEntities;

import com.Harbinger.Spore.Sentities.Organoids.Mound;
import com.Harbinger.Spore.Sentities.Organoids.Proto;
import com.Harbinger.Spore.Sentities.Projectile.AcidBall;
import com.Harbinger.Spore.Sentities.Projectile.Vomit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Organoid extends UtilityEntity implements Enemy {
    public static final EntityDataAccessor<Integer> BORROW = SynchedEntityData.defineId(Organoid.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> EMERGE = SynchedEntityData.defineId(Organoid.class, EntityDataSerializers.INT);
    protected Organoid(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.xpReward = 25;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.onGround){
            this.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0, 1, 0));
        }
        if (this.isEmerging()){
            this.tickEmerging();
        } else if (this.isBurrowing()){
            this.tickBurrowing();
        }
        if (tickCount % 200 == 0 && !(this instanceof Proto || this instanceof Mound)){
            regulateSpawns();
        }
    }

    @Override
    public boolean dampensVibrations() {
        return true;
    }


    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        if (source.getDirectEntity() instanceof AcidBall || source.getDirectEntity() instanceof Vomit){
            return  false;
        }
        return super.hurt(source, p_21017_);
    }

    public int getEmerge_tick(){
        return 20;
    }
    public  int getBorrow_tick(){return 20;}

    public boolean isEmerging(){
        return this.entityData.get(EMERGE) > 0;
    }
    public void tickEmerging(){
        int emerging = this.entityData.get(EMERGE);
        if (emerging > this.getEmerge_tick())
            emerging = -1;
        this.entityData.set(EMERGE, emerging + 1);
    }
    public boolean isBurrowing(){
        return this.entityData.get(BORROW) > 0;
    }
    public void tickBurrowing(){
        int burrowing = this.entityData.get(BORROW);
        if (burrowing > this.getBorrow_tick()) {
            burrowing = -1;
        }
        this.entityData.set(BORROW, burrowing + 1);
    }

    public int getEmerge(){
        return entityData.get(EMERGE);
    }
    public int getBorrow(){return entityData.get(BORROW);}

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BORROW, 0);
        this.entityData.define(EMERGE, 0);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @org.jetbrains.annotations.Nullable SpawnGroupData p_21437_, @org.jetbrains.annotations.Nullable CompoundTag p_21438_) {
        this.tickEmerging();
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isEmerging() || this.isBurrowing()) {
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();
            Level world = this.level;
            RandomSource randomsource = this.getRandom();
            for (int l = 0 ;l<this.getNumberOfParticles();l++){
                if (level instanceof ServerLevel serverLevel) {
                    int xi = randomsource.nextInt(-1,1);
                    int zi = randomsource.nextInt(-1,1);
                    if (world.getBlockState(new BlockPos((int) x, (int) y - 1, (int) z)).getBlock().asItem() != ItemStack.EMPTY.getItem()) {

                        serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y - 1, (int) z))).getBlock())), x + xi, y - 0.1D, z + zi, 3,
                                ((double) randomsource.nextFloat() - 1D) * 0.08D, ((double) randomsource.nextFloat() - 1D) * 0.08D, ((double) randomsource.nextFloat() - 1D) * 0.08D, 0.15F);
                    }
                }
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.tickCount % 20 == 0 && this.getHealth() < this.getMaxHealth() && this.getTicksFrozen() <=0){
            setHealth(getHealth()+1);
        }
    }

    public int getNumberOfParticles(){
        return 2;
    }

    @Override
    public boolean addEffect(MobEffectInstance instance, @Nullable Entity entity) {
        if (instance.getEffect().getCategory() == MobEffectCategory.HARMFUL && instance.getAmplifier() < 1){
            return false;
        }
        return super.addEffect(instance, entity);
    }


    public void regulateSpawns(){
        AABB aabb = this.getBoundingBox().inflate(6);
        List<Entity> entityList = level.getEntities(this,aabb, entity -> {return entity instanceof Organoid && !(entity instanceof Proto || entity instanceof Mound);});
        if (entityList.size() > 4){
            tickBurrowing();
        }
    }

    public boolean isCloseCombatant(){
        return false;
    }
}
