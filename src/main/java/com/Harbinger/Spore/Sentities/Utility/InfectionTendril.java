package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.AI.BreakBlockGoal;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedWallMovementControl;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class InfectionTendril extends UtilityEntity{
    private static final EntityDataAccessor<Integer> LIFE = SynchedEntityData.defineId(InfectionTendril.class, EntityDataSerializers.INT);
    @Nullable
    BlockPos search;
    public InfectionTendril(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.moveControl = new InfectedWallMovementControl(this);
        this.navigation = new WallClimberNavigation(this,level);
    }

    int getPos() {
        return this.entityData.get(LIFE);
    }
    public void setPosi(int blockPos) {
        this.entityData.set(LIFE, blockPos);
    }
    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        return false;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LIFE, 4800);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("life", this.getPos());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        this.setPosi(tag.getInt("life"));
        super.readAdditionalSaveData(tag);
    }

    @Nullable
    public BlockPos getSearch() {
        return search;
    }

    public void setSearch(@Nullable BlockPos search) {
        this.search = search;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new BreakBlockGoal(Sblocks.REMAINS.get(),this,1,80));
        this.goalSelector.addGoal(2,new GoToArea(this));
        super.registerGoals();
    }

    static class GoToArea extends Goal {
        InfectionTendril tendril;
        public GoToArea(InfectionTendril t){
            this.tendril = t;
        }
        @Override
        public boolean canUse() {
            return this.tendril.getSearch() != null;
        }

        @Override
        public void tick() {
            super.tick();
            if (this.tendril.getSearch() != null){
                this.tendril.getNavigation().moveTo(this.tendril.getSearch().getX(),this.tendril.getSearch().getY(),this.tendril.getSearch().getZ(),1);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive() && this.entityData.get(LIFE)>0){
            this.entityData.set(LIFE, this.entityData.get(LIFE) - 1);
        }
    }

    @Override
    public void aiStep() {
        if (entityData.get(LIFE) == 0){
            this.discard();
        }
        if (this.random.nextInt(0,10) == 7){
        Spread(this,this.level);
        }
        super.aiStep();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.1);

    }

    private void Spread(Entity entity , Level level){
        AABB aabb = entity.getBoundingBox().inflate(0.3);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState nord = level.getBlockState(blockpos.north());
            BlockState south = level.getBlockState(blockpos.south());
            BlockState west = level.getBlockState(blockpos.west());
            BlockState east = level.getBlockState(blockpos.east());
            BlockState above = level.getBlockState(blockpos.above());
            BlockState below = level.getBlockState(blockpos.below());
            boolean nordT = !nord.isSolidRender(level,blockpos.north());
            boolean southT = !south.isSolidRender(level,blockpos.south());
            boolean westT = !west.isSolidRender(level,blockpos.west());
            boolean eastT = !east.isSolidRender(level,blockpos.east());
            boolean aboveT = !above.isSolidRender(level,blockpos.above());
            boolean belowT = !below.isSolidRender(level,blockpos.below());

            BlockState blockstate = level.getBlockState(blockpos);

            if (Math.random() < 0.02 && blockstate.isSolidRender(level,blockpos)
                    && (nordT || southT || westT || eastT || aboveT || belowT)){
                for (String str : SConfig.DATAGEN.block_infection.get()){
                    String[] string = str.split("\\|" );
                    ItemStack stack = new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string[0])));
                    if (stack != ItemStack.EMPTY && blockstate.getBlock().asItem() == stack.getItem()){
                        ItemStack itemStack = new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string[1])));
                        if (itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof BlockItem blockItem){
                            level.setBlock(blockpos,blockItem.getBlock().defaultBlockState(),3);
                        }
                    }
                }
            }
            if (above.isAir() && blockstate.isSolidRender(level ,blockpos) && Math.random() < 0.1){level.setBlock(blockpos.above(),Sblocks.MYCELIUM_VEINS.get().defaultBlockState(),3);}

            if (blockstate.is(Sblocks.REMAINS.get())){
                Mound mound = new Mound(Sentities.MOUND.get(),level);
                mound.setMaxAge(2);
                mound.setPos(blockpos.getX() + 0.5,blockpos.getY(),blockpos.getZ() + 0.5);
                level.addFreshEntity(mound);
                level.removeBlock(blockpos,false);
                this.discard();
            }
        }
    }

}
