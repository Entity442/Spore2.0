package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Sparticles;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
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

import java.util.Objects;

public class Mound extends UtilityEntity{
    private int counter;
    private final  int maxCounter = SConfig.SERVER.mound_cooldown.get();
    private int attack_counter = 0;
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
            this.setCounter(this.getCounter() + 1);
        }
        if (entity.isAlive() && this.getCounter() >= maxCounter && !level.isClientSide){
            Spread(entity , entity.level);
            this.setCounter(0);
        }
        if (entity.isAlive() && attack_counter >= 0){
            attack_counter = attack_counter - 1;
        }
        if (this.getCounter() > (maxCounter - 50) && this.getCounter() < maxCounter && this.level instanceof ServerLevel serverLevel){
                double x0 = this.getX() - (random.nextFloat() - 0.2) * 0.2D;
                double y0 = this.getY() + (random.nextFloat() - 0.5) * 0.5D * 10;
                double z0 = this.getZ() + (random.nextFloat() - 0.2) * 0.2D;
                serverLevel.sendParticles(Sparticles.SPORE_PARTICLE.get(), x0, y0, z0, 3,0, 0, 0,1);
        }
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
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

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof LivingEntity livingEntity){
            if (attack_counter == 0 && Objects.equals(getLastDamageSource(), DamageSource.mobAttack(livingEntity))){
                LivingEntity entity = this;
                if (!entity.level.isClientSide) {
                    AreaEffectCloud areaeffectcloud = new AreaEffectCloud(entity.level, entity.getX(), entity.getY(), entity.getZ());
                    areaeffectcloud.setOwner(entity);

                    areaeffectcloud.setParticle(Sparticles.SPORE_PARTICLE.get());
                    areaeffectcloud.setRadius(2.0F);
                    areaeffectcloud.setDuration(600);
                    areaeffectcloud.setRadiusPerTick((4.0F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
                    areaeffectcloud.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(), 200, 1));
                    entity.level.addFreshEntity(areaeffectcloud);
                }
            }
    }
        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.mound_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, SConfig.SERVER.mound_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }
}
