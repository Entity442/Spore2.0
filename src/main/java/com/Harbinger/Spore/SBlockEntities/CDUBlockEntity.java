package com.Harbinger.Spore.SBlockEntities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.SblockEntities;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Spore;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CDUBlockEntity extends BlockEntity{
    public final int maxFuel = 12000;
    public int fuel;
    public CDUBlockEntity(BlockPos pos, BlockState state) {
        super(SblockEntities.CDU.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("fuel",this.getFuel());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.setFuel(tag.getInt("fuel"));
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    public void setFuel(int i){
        this.fuel = i;
    }
    public int getFuel(){
        return this.fuel;
    }

    public List<BlockState> stateList(){
        List<BlockState> states = new ArrayList<>();
        states.add(Sblocks.BIOMASS_BLOCK.get().defaultBlockState());
        states.add(Sblocks.SICKEN_BIOMASS_BLOCK.get().defaultBlockState());
        states.add(Sblocks.CALCIFIED_BIOMASS_BLOCK.get().defaultBlockState());
        states.add(Sblocks.MEMBRANE_BLOCK.get().defaultBlockState());
        states.add(Sblocks.ROOTED_BIOMASS.get().defaultBlockState());
        states.add(Sblocks.ROOTED_MYCELIUM.get().defaultBlockState());
        return states;
    }

    public static <E extends BlockEntity> void serverTick(Level level, BlockPos blockPos, BlockState blockState, CDUBlockEntity e) {
        if (e.getFuel() > 0 && !level.isClientSide){
            e.fuel--;
            if (e.getFuel() % 60 == 0){
                int range =2 * SConfig.DATAGEN.cryo_range.get();
                AABB aabb = AABB.ofSize(new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()), range, range, range);
                List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, aabb);

                for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                    BlockState state = level.getBlockState(blockpos);
                    if (state.is(TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(),new ResourceLocation(Spore.MODID,"removable_foliage")))){
                        if (Math.random() < 0.2)
                        level.removeBlock(blockpos,false);
                    }
                    if (Math.random() < 0.1){
                        for (String str : SConfig.DATAGEN.block_cleaning.get()){
                            String[] string = str.split("\\|" );
                            ItemStack stack = new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string[0])));
                            if (stack != ItemStack.EMPTY && state.getBlock().asItem() == stack.getItem()){
                                ItemStack itemStack = new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string[1])));
                                if (itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof BlockItem blockItem){
                                    level.setBlock(blockpos,blockItem.getBlock().defaultBlockState(),3);
                                }
                            }
                        }
                    }
                    if (Math.random() < 0.1){
                        if (e.stateList().contains(state)){
                            level.setBlock(blockpos,Sblocks.FREEZE_BURNED_BIOMASS.get().defaultBlockState(),3);
                        }
                    }
                    if (Math.random() < 0.001){
                        BlockState blockState1 = level.getBlockState(blockpos.above());
                        if (state.isSolidRender(level,blockPos) && blockState1.isAir()){
                            RandomSource randomSource = RandomSource.create();
                            int layer = randomSource.nextInt(1,4);
                            BlockState snowState = Blocks.SNOW.defaultBlockState();
                            if (snowState.getBlock().getStateDefinition().getProperty("layers") instanceof IntegerProperty property)
                            level.setBlock(blockpos.above(),snowState.setValue(property,layer),3);
                        }
                    }
                }
                for (Entity entity : entities){
                    if (entity instanceof LivingEntity livingEntity &&
                            (livingEntity.getType().is(TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(),
                                    new ResourceLocation("minecraft:freeze_hurts_extra_types"))))){
                        livingEntity.hurt(DamageSource.FREEZE,5);
                    }
                }
            }
         }
    }
}
