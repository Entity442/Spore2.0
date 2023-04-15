package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

public class Mound extends UtilityEntity{
    private static final EntityDataAccessor<Integer> TENDRILS = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MAX_AGE = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> STRUCTURE = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.BOOLEAN);
    private int counter;
    private final  int maxCounter = SConfig.SERVER.mound_cooldown.get();
    private int attack_counter = 0;
    public Mound(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        setPersistenceRequired();
    }
    protected int calculateFallDamage(float p_149389_, float p_149390_) {
        return super.calculateFallDamage(p_149389_, p_149390_) - 30;
    }
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
    @Override
    public void tick() {
        super.tick();
        Entity entity = this;
        if (entity.isAlive() && entityData.get(AGE) < entityData.get(MAX_AGE)){
            this.getPersistentData().putInt("age", 1 + this.getPersistentData().getInt("age"));
            if (this.getPersistentData().getInt("age") >= SConfig.SERVER.mound_age.get()) {
                this.getPersistentData().putInt("age",0);
                entityData.set(AGE,entityData.get(AGE) + 1);
            }
        }
        if (entity.isOnGround()){
            entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0, 1, 0));
        }
        if (counter < maxCounter){
            this.setCounter(this.getCounter() + 1);
        }
        if (entity.isAlive() && this.getCounter() >= maxCounter && !level.isClientSide){
            Spread(entity , entity.level);
            this.addEffect(new MobEffectInstance(MobEffects.REGENERATION,60,1));
            this.setCounter(0);
            if (entityData.get(TENDRILS)>0 && entityData.get(AGE) == 3 && checkForTendrils(entity)){
                SpreadKin(entity,entity.level);
            }
        }
        if (entity.isAlive() && attack_counter > 0){
            attack_counter = attack_counter - 1;
        }
        if (this.getCounter() > (maxCounter - 40) && this.getCounter() < maxCounter && this.level instanceof ServerLevel serverLevel){
                double x0 = this.getX() - (random.nextFloat() - 0.2) * 0.2D;
                double y0 = this.getY() + (random.nextFloat() - 0.5) * 0.5D * 10;
                double z0 = this.getZ() + (random.nextFloat() - 0.2) * 0.2D;
                serverLevel.sendParticles(Sparticles.SPORE_PARTICLE.get(), x0, y0, z0, 3,0, 0, 0,1);
        }
        if (this.getCounter() == (maxCounter - 40)){
            this.playSound(Ssounds.PUFF.get());
        }
    }
    public int getAge(){
        return entityData.get(AGE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("tendrils",entityData.get(TENDRILS));
        tag.putInt("age",entityData.get(AGE));
        tag.putInt("max_age",entityData.get(MAX_AGE));
        tag.putBoolean("structure",entityData.get(STRUCTURE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(TENDRILS, tag.getInt("tendrils"));
        entityData.set(AGE, tag.getInt("age"));
        entityData.set(MAX_AGE, tag.getInt("max_age"));
        entityData.set(STRUCTURE, tag.getBoolean("structure"));
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setMaxAge(int maxAge){
        entityData.set(MAX_AGE,maxAge);
    }

    public  int getMaxAge(){
        return entityData.get(MAX_AGE);
    }

    private void Spread(Entity entity , LevelAccessor level) {
        double range;
        if (entityData.get(AGE) == 2){
            range = SConfig.SERVER.mound_range_age2.get();
        } else if (entityData.get(AGE) == 3){
            range = SConfig.SERVER.mound_range_age3.get();
        } else {
            range = SConfig.SERVER.mound_range_default.get();
        }

        AABB aabb = entity.getBoundingBox().inflate(range);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {

            BlockState block1 =  (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:ground_foliage")))
                    .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();
            BlockState block2 =  (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:roof_foliage")))
                    .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();
            BlockState block3 =  (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:wall_foliage")))
                    .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();
            BlockState block4 =  (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:block_st")))
                    .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();
            BlockState block5 =  (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:underwater_blocks")))
                    .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();


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



            if (blockstate.is(BlockTags.create(new ResourceLocation("minecraft:logs"))) && blockstate.getDestroySpeed(level ,blockpos) < 5 && Math.random() < 0.3){
                BlockState _bs = Sblocks.ROTTEN_LOG.get().defaultBlockState();
                for (Map.Entry<Property<?>, Comparable<?>> entry : blockstate.getValues().entrySet()) {
                    Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                    if (_property != null && _bs.getValue(_property) != null)
                        try {
                            _bs = _bs.setValue(_property, (Comparable) entry.getValue());
                        } catch (Exception e) {
                        }
                }
                level.setBlock(blockpos, _bs, 3);
            }

            if (blockstate.isSolidRender(level,blockpos )&& above.getFluidState().is(Fluids.WATER) && Math.random() < 0.01){ level.setBlock(blockpos.above(),block5,3);}
            if (above.isAir() && blockstate.isSolidRender(level ,blockpos) && Math.random() < 0.01){level.setBlock(blockpos.above(),block1,3);}
            if (above.isAir() && blockstate.isSolidRender(level ,blockpos) && Math.random() < 0.01 && entityData.get(STRUCTURE) && entityData.get(AGE) >= entityData.get(MAX_AGE) && this.distanceToSqr(blockpos.getX(),blockpos.getY(),blockpos.getZ()) > 80){
                level.setBlock(blockpos.above(),block4,3);
                entityData.set(STRUCTURE,false);
            }
            if (below.isAir() && blockstate.isSolidRender(level ,blockpos) && Math.random() < 0.01){
                if (block2.getBlock().getStateDefinition().getProperty("hanging") instanceof BooleanProperty property){
                    level.setBlock(blockpos.below(),block2.setValue(property, true),3);
                }else {
                    level.setBlock(blockpos.below(),block2,3);}}


            if (blockstate.isSolidRender(level , blockpos) && (nordT || southT || westT || eastT || aboveT || belowT)){
                Direction direction = Direction.NORTH;
                Direction direction2 = Direction.SOUTH;
                Direction direction3 = Direction.EAST;
                Direction direction4 = Direction.WEST;
                Property<?> property = block3.getBlock().getStateDefinition().getProperty("facing");
                if (property instanceof DirectionProperty directionProperty && Math.random() < 0.01) {
                    if (nord.isAir()){
                        level.setBlock(blockpos.north(),block3.setValue(directionProperty,direction),3);
                    }
                    if (south.isAir()){
                        level.setBlock(blockpos.south(),block3.setValue(directionProperty,direction2),3);
                    }
                    if (west.isAir()){
                        level.setBlock(blockpos.west(),block3.setValue(directionProperty,direction4),3);
                    }
                    if (east.isAir()){
                        level.setBlock(blockpos.east(),block3.setValue(directionProperty,direction3),3);
                    }
                }
            }
        }
    }
    boolean checkForTendrils(Entity entity){
        AABB boundingBox = entity.getBoundingBox().inflate(80);
        List<Entity> entities = entity.level.getEntities(entity, boundingBox , EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        for (Entity en : entities) {
            if (en instanceof InfectionTendril){
                return false;
            }
        }
        return true;
    }


    private void SpreadKin(Entity entity , Level level) {
        AABB aabb = entity.getBoundingBox().inflate(80);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockState = level.getBlockState(blockpos);
            if (blockState.is(Sblocks.REMAINS.get())){
                InfectionTendril tendril = new InfectionTendril(Sentities.TENDRIL.get(),level);
                tendril.setSearch(blockpos);
                tendril.setPos(this.getX(),this.getY()+0.5D,this.getZ());
                level.addFreshEntity(tendril);
                this.entityData.set(TENDRILS , this.entityData.get(TENDRILS) -1);
                break;
            }
            }
        }
    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (getAge() > 1){
            AttributeInstance health = this.getAttribute(Attributes.MAX_HEALTH);
            assert health != null;
            health.setBaseValue(SConfig.SERVER.mound_hp.get() * ((double)entityData.get(AGE) / 0.75) * SConfig.SERVER.global_health.get());
        }
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if (attack_counter == 0){
            LivingEntity entity = this;
            if (!entity.level.isClientSide) {
                AreaEffectCloud areaeffectcloud = new AreaEffectCloud(entity.level, entity.getX(), entity.getY(), entity.getZ());
                areaeffectcloud.setOwner(entity);

                areaeffectcloud.setParticle(Sparticles.SPORE_PARTICLE.get());
                areaeffectcloud.setRadius(2.0F);
                areaeffectcloud.setDuration(300);
                areaeffectcloud.setRadiusPerTick(((2.5F * entityData.get(AGE)) - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
                areaeffectcloud.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(), 200, 1));
                entity.level.addFreshEntity(areaeffectcloud);
                this.playSound(Ssounds.PUFF.get() ,0.5f ,0.5f);
                attack_counter = 300;
            }
        }
        return super.hurt(p_21016_, p_21017_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.mound_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, SConfig.SERVER.mound_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TENDRILS, 5);
        this.entityData.define(AGE, 1);
        this.entityData.define(MAX_AGE, 3);
        this.entityData.define(STRUCTURE, true);
    }


}
