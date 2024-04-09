package com.Harbinger.Spore.Sentities.BaseEntities;

import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.BufferAI;
import com.Harbinger.Spore.Sentities.AI.LocHiv.LocalTargettingGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.SearchAreaGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Hyper extends Infected{
    public static final EntityDataAccessor<BlockPos> NEST = SynchedEntityData.defineId(Infected.class, EntityDataSerializers.BLOCK_POS);
    public Hyper(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean canStarve() {
        return false;
    }

    @Override
    protected void addRegularGoals() {
        this.goalSelector.addGoal(3,new LocalTargettingGoal(this));
        this.goalSelector.addGoal(4,new GoBackToTheNest(this));
        this.goalSelector.addGoal(4, new SearchAreaGoal(this, 1.2));
        this.goalSelector.addGoal(5,new BufferAI(this));
        this.goalSelector.addGoal(6,new FloatDiveGoal(this));
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    @Override
    public boolean blockBreakingParameters(BlockState blockstate, BlockPos blockpos) {
        float value = blockstate.getDestroySpeed(this.level,blockpos);
        return this.tickCount % 20 == 0 && value > 0 && value <= 3;
    }
    protected List<BlockState> biomass(){
        List<BlockState> states = new ArrayList<>();
        states.add(Sblocks.BIOMASS_BLOCK.get().defaultBlockState());
        states.add(Sblocks.SICKEN_BIOMASS_BLOCK.get().defaultBlockState());
        states.add(Sblocks.CALCIFIED_BIOMASS_BLOCK.get().defaultBlockState());
        states.add(Sblocks.MEMBRANE_BLOCK.get().defaultBlockState());
        states.add(Sblocks.ROOTED_BIOMASS.get().defaultBlockState());
        states.add(Sblocks.ROOTED_MYCELIUM.get().defaultBlockState());
        return states;
    }

    @Override
    public boolean interactBlock(BlockPos blockPos, Level level) {
        BlockState state = level.getBlockState(blockPos);
        if (biomass().contains(state)){
            return level.setBlock(blockPos, Sblocks.MEMBRANE_BLOCK.get().defaultBlockState(), 3);
        }
        return super.interactBlock(blockPos, level);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("nestX",entityData.get(NEST).getX());
        tag.putInt("nestY",entityData.get(NEST).getY());
        tag.putInt("nestZ",entityData.get(NEST).getZ());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        int x = tag.getInt("nestX");
        int y = tag.getInt("nestY");
        int z = tag.getInt("nestZ");
        this.entityData.set(NEST,new BlockPos(x,y,z));
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(NEST, BlockPos.ZERO);
    }
    public BlockPos getNestLocation(){
        return entityData.get(NEST);
    }
    public void setNestLocation(BlockPos pos){entityData.set(NEST,pos);}

    @Override
    public boolean additionalBreakingTriggers() {
        return this.getLastDamageSource() == DamageSource.IN_WALL;
    }

    static class GoBackToTheNest extends Goal {
        protected Hyper hyper;
        public  int tryTicks;
        public GoBackToTheNest(Hyper hyper){
            this.hyper = hyper;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }
        @Override
        public boolean canContinueToUse() {
            return hyper.getTarget() == null;
        }

        @Override
        public boolean canUse() {
            return hyper.tickCount % 10 == 0 && hyper.getEvoPoints() > 1 && hyper.getNestLocation() != BlockPos.ZERO;
        }

        protected void moveMobToBlock(BlockPos pos) {
            double x = hyper.random.nextInt(-2,2)+ 0.5D;
            double z = hyper.random.nextInt(-2,2)+ 0.5D;
            this.hyper.getNavigation().moveTo(pos.getX() + x, pos.getY() + 1, pos.getZ() +z, 1);
        }
        protected void tryToLayCorpsesAround(){
            AABB aabb = this.hyper.getBoundingBox().inflate(10);
            for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                Level level = hyper.level;
                boolean isGround = level.getBlockState(blockpos).isCollisionShapeFullBlock(level,blockpos);
                boolean isAir = level.getBlockState(blockpos.above()).isAir();
                if (Math.random() < 0.01){
                    if (isGround && isAir && !level.isClientSide){
                        level.setBlock(blockpos.above(),Sblocks.REMAINS.get().defaultBlockState(), 3);
                        this.hyper.setEvoPoints(this.hyper.getEvoPoints()-1);
                        break;
                    }
                }
            }
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 10 == 0;
        }

        @Override
        public void tick() {
            super.tick();
            ++this.tryTicks;
            if (this.hyper.getNestLocation() != BlockPos.ZERO && shouldRecalculatePath()){
                this.moveMobToBlock(this.hyper.getNestLocation());
            }
        }

        @Override
        public void start() {
            moveMobToBlock(this.hyper.getNestLocation());
            BlockPos pos = this.hyper.getNestLocation();
            if (this.hyper.distanceToSqr(pos.getX(),pos.getY(),pos.getZ()) < 80d){
                tryToLayCorpsesAround();
            }
            super.start();
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
    @org.jetbrains.annotations.Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @org.jetbrains.annotations.Nullable SpawnGroupData p_21437_, @org.jetbrains.annotations.Nullable CompoundTag p_21438_) {
        setNestLocation(this.getOnPos());
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }
}
