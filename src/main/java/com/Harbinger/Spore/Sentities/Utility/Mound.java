package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Core.Sparticles;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class Mound extends UtilityEntity{
    private int counter;
    private final  int maxCounter = 1000;
    public Mound(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        Entity entity = this;
        if (entity.isOnGround()){
            entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0, 1, 0));
        }
        if (counter < maxCounter){
            counter = counter + 1;
        }
        if (entity.isAlive() && counter >= maxCounter && !level.isClientSide){
            counter = 0;
            Spread(entity , entity.level);
        }
    }

    public void aiStep() {
        super.aiStep();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (counter > (maxCounter - 50) && counter < maxCounter){
            for (int l = 0; l < 4; ++l) {
            double x0 = x - (random.nextFloat() - 0.5) * 0.5D;
            double y0 = y + (random.nextFloat() - 0.5) * 0.5D * 10;
            double z0 = z + (random.nextFloat() - 0.5) * 0.5D;
            this.level.addParticle(Sparticles.SPORE_PARTICLE.get(), x0, y0, z0, 0, 0, 0);}
        }
    }

    private void Spread(Entity entity , LevelAccessor level) {
        RandomSource randomSource = RandomSource.create();
        int a = randomSource.nextInt(-1,1);
        int c = randomSource.nextInt(-1,1);

        AABB aabb = entity.getBoundingBox().inflate(6);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {

            BlockState block1 =  (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:ground_foliage")))
                    .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();
            BlockState block2 =  (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:roof_foliage")))
                    .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();

            BlockState blockstate = level.getBlockState(blockpos);
            BlockState detectAir = level.getBlockState(blockpos.offset(a,0,c));
            BlockState above = level.getBlockState(blockpos.above());
            BlockState below = level.getBlockState(blockpos.below());
            if (Math.random() < 0.02 && !blockstate.is(BlockTags.create(new ResourceLocation("spore:infected_blocks"))) && (detectAir.canOcclude() || above.canOcclude() || below.canOcclude())){
                if ((blockstate.getMaterial() == Material.DIRT || blockstate.getMaterial() == Material.GRASS)){
                level.setBlock(blockpos,Sblocks.INFESTED_DIRT.get().defaultBlockState(),3);
                }
                if ((blockstate.getMaterial() == Material.SAND)){
                    if (blockstate.getBlock() == Blocks.GRAVEL){
                        level.setBlock(blockpos,Sblocks.INFESTED_GRAVEL.get().defaultBlockState(),3);
                    }
                    else {   level.setBlock(blockpos,Sblocks.INFESTED_SAND.get().defaultBlockState(),3);}
                }
                if ((blockstate.getMaterial() == Material.STONE && blockstate.getDestroySpeed(level ,blockpos) < 5)){
                    if (blockstate.getBlock() == Blocks.DEEPSLATE){
                        level.setBlock(blockpos,Sblocks.INFESTED_DEEPSLATE.get().defaultBlockState(),3);
                    }else {level.setBlock(blockpos,Sblocks.INFESTED_STONE.get().defaultBlockState(),3);}

                }


            }

            if (above.isAir() && blockstate.canOcclude() && Math.random() < 0.01){level.setBlock(blockpos.above(),block1,3);}
            if (below.isAir() && blockstate.canOcclude() && Math.random() < 0.01){
                if (block2.getBlock().getStateDefinition().getProperty("hanging") instanceof BooleanProperty property){
                    level.setBlock(blockpos.below(),block2.setValue(property, true),3);
                }else {
                    level.setBlock(blockpos.below(),block2,3);
                }
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_human_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, SConfig.SERVER.inf_human_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }
}
