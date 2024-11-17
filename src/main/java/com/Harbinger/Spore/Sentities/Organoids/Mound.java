package com.Harbinger.Spore.Sentities.Organoids;

import com.Harbinger.Spore.Core.*;
import com.Harbinger.Spore.ExtremelySusThings.SporeSavedData;
import com.Harbinger.Spore.SBlockEntities.LivingStructureBlocks;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.Organoid;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.FoliageSpread;
import com.Harbinger.Spore.Sentities.Utility.InfectionTendril;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Mound extends Organoid implements Enemy, FoliageSpread {
    private static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COUNTER = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MAX_AGE = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> STRUCTURE = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LINKED = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.BOOLEAN);
    public int maxCounter =  SConfig.SERVER.mound_cooldown.get();
    private int attack_counter = 0;
    public Mound(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }
    protected int calculateFallDamage(float p_149389_, float p_149390_) {
        return super.calculateFallDamage(p_149389_, p_149390_) - 30;
    }
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return this.getLinked() && this.getMaxAge() <=2;
    }

    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.mound_loot.get();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount % 20 == 0){
            if (this.isAlive() && entityData.get(AGE) < entityData.get(MAX_AGE)){
                this.getPersistentData().putInt("age", 1 + this.getPersistentData().getInt("age"));
                if (this.getPersistentData().getInt("age") >= SConfig.SERVER.mound_age.get()) {
                    this.getPersistentData().putInt("age",0);
                    entityData.set(AGE,entityData.get(AGE) + 1);
                }
            }

            if (entityData.get(COUNTER) < maxCounter){
                this.setCounter(this.getCounter() + 1);
            }
            if (this.isAlive() && this.getCounter() >= maxCounter && !level.isClientSide){
                double range = switch (entityData.get(AGE)) {
                    case 2 -> SConfig.SERVER.mound_range_age2.get();
                    case 3 -> SConfig.SERVER.mound_range_age3.get();
                    case 4 -> SConfig.SERVER.mound_range_age4.get();
                    default -> SConfig.SERVER.mound_range_default.get();
                };
                SpreadInfection(level,range,this.getOnPos());
                this.setCounter(0);
                if (this.random.nextInt(10) == 0 && entityData.get(AGE) >= 3 && checkForExtraTendrils(this,this.level)){
                    SpreadKin(this,this.level);
                }

            }
            if (this.getCounter() > (maxCounter - 2) && this.getCounter() < maxCounter && this.level instanceof ServerLevel serverLevel){
                double x0 = this.getX() - (random.nextFloat() - 0.2) * 0.2D;
                double y0 = this.getY() + (random.nextFloat() - 0.5) * 0.5D * 10;
                double z0 = this.getZ() + (random.nextFloat() - 0.2) * 0.2D;
                serverLevel.sendParticles(Sparticles.SPORE_PARTICLE.get(), x0, y0, z0, 9,0, 0, 0,1);
            }
            if (this.getCounter() == (maxCounter - 2)){
                this.playSound(Ssounds.PUFF.get());
            }
        }
        if (this.isAlive() && attack_counter > 0){
            attack_counter = attack_counter - 1;
        }
    }
    public int getAge(){
        return entityData.get(AGE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("age",entityData.get(AGE));
        tag.putInt("counter",entityData.get(COUNTER));
        tag.putInt("max_age",entityData.get(MAX_AGE));
        tag.putBoolean("structure",entityData.get(STRUCTURE));
        tag.putBoolean("linked",entityData.get(LINKED));
    }

    public int getAgeCounter(){
        return this.getPersistentData().getInt("age");
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(AGE, tag.getInt("age"));
        entityData.set(COUNTER, tag.getInt("counter"));
        entityData.set(MAX_AGE, tag.getInt("max_age"));
        entityData.set(STRUCTURE, tag.getBoolean("structure"));
        entityData.set(LINKED, tag.getBoolean("linked"));
    }

    public void setCounter(int counter) {
        this.entityData.set(COUNTER,counter);
    }

    public int getCounter() {
        return this.entityData.get(COUNTER);
    }
    public int getMaxCounter() {
        return this.maxCounter;
    }
    public void setAge(int maxAge){
        entityData.set(AGE,maxAge);
    }
    public void setMaxAge(int maxAge){
        entityData.set(MAX_AGE,maxAge);
    }

    public  int getMaxAge(){
        return entityData.get(MAX_AGE);
    }

    public void setLinked(boolean value){
        this.entityData.set(LINKED,value);
    }

    public boolean getLinked(){
        return this.entityData.get(LINKED);
    }

    @Override
    public void additionPlacers(Level level, BlockPos pos, double range) {
        AABB aabb = this.getBoundingBox().inflate(range);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = level.getBlockState(blockpos);
            BlockState above = level.getBlockState(blockpos.above());
            if (Math.random() < 0.1){
                if (above.isAir() && blockstate.isSolidRender(level ,blockpos) && entityData.get(STRUCTURE) && entityData.get(AGE) >= entityData.get(MAX_AGE) && this.distanceToSqr(blockpos.getX(),blockpos.getY(),blockpos.getZ()) > 80){
                    BlockState block4 = (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:block_st")))
                            .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();
                    level.setBlock(blockpos.above(),block4,3);
                    entityData.set(STRUCTURE,false);
                }
            }
        }
    }

    @Override
    public void additionIgnoreConfigPlacers(Level level, BlockPos pos,double range) {
        AABB aabb = this.getBoundingBox().inflate(range);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = level.getBlockState(blockpos);
            BlockState above = level.getBlockState(blockpos.above());
            if (Math.random() < 0.1){
                if (above.isAir() && blockstate.isSolidRender(level ,blockpos) && entityData.get(STRUCTURE) && entityData.get(AGE) >= entityData.get(MAX_AGE) && this.distanceToSqr(blockpos.getX(),blockpos.getY(),blockpos.getZ()) > 80){
                    BlockState block4 = (ForgeRegistries.BLOCKS.tags().getTag(BlockTags.create(new ResourceLocation("spore:block_st")))
                            .getRandomElement(RandomSource.create()).orElse(Blocks.AIR)).defaultBlockState();
                    level.setBlock(blockpos.above(),block4,3);
                    entityData.set(STRUCTURE,false);
                }
            }
        }
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, aabb);
        for (LivingEntity en : entities) {
            if (!(en instanceof Infected || en instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(en.getEncodeId()) || en.getItemBySlot(EquipmentSlot.HEAD).getItem() == Sitems.GAS_MASK.get())){
                en.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(),600,1));
            }
        }
    }

    private void SpreadKin(Entity entity , Level level) {
        AABB aabb = entity.getBoundingBox().inflate(SConfig.SERVER.mound_tendril_checker.get());
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockState = level.getBlockState(blockpos);
            if (isStucture(blockpos) || (isChestWithFood(blockpos) && SConfig.SERVER.tendril_chest.get()) || (blockState.is(Sblocks.REMAINS.get()) && SConfig.SERVER.tendril_corpse.get()) || (blockState.is(Blocks.SPAWNER) && SConfig.SERVER.tendril_spawner.get())){
                InfectionTendril tendril = new InfectionTendril(Sentities.TENDRIL.get(),level);
                tendril.setAgeM(this.getMaxAge() -1);
                tendril.setSearchArea(blockpos);
                tendril.setPos(this.getX(),this.getY()+0.5D,this.getZ());
                level.addFreshEntity(tendril);
                break;
            }
        }
    }
    private boolean isChestWithFood(BlockPos pos){
        BlockEntity blockEntity = this.level.getBlockEntity(pos);
        if (blockEntity instanceof Container container){
            return container.hasAnyMatching((ItemStack::isEdible));
        }
        return false;
    }
    private boolean isStucture(BlockPos pos){
        BlockEntity blockEntity = this.level.getBlockEntity(pos);
        return blockEntity instanceof LivingStructureBlocks;
    }

    private boolean checkForExtraTendrils(Entity entity ,Level level){
        AABB aabb = entity.getBoundingBox().inflate(SConfig.SERVER.mound_tendril_checker.get());
        List<InfectionTendril> entities = level.getEntitiesOfClass(InfectionTendril.class, aabb);
        return entities.size() <= 4;
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if (attack_counter == 0){
            LivingEntity entity = this;
            if (!entity.level.isClientSide) {
                AreaEffectCloud areaeffectcloud = new AreaEffectCloud(entity.level, entity.getX(), entity.getY(), entity.getZ());
                areaeffectcloud.setOwner(entity);
                areaeffectcloud.setRadius(2.0F);
                areaeffectcloud.setDuration(300);
                areaeffectcloud.setRadiusPerTick(((1.5F * entityData.get(AGE)) - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
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
        this.entityData.define(AGE, 1);
        this.entityData.define(COUNTER, 0);
        this.entityData.define(MAX_AGE, 4);
        this.entityData.define(STRUCTURE, true);
        this.entityData.define(LINKED, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
        double health = SConfig.SERVER.mound_hp.get() * entityData.get(AGE) * SConfig.SERVER.global_health.get();
        double armor = SConfig.SERVER.mound_armor.get() * entityData.get(AGE) * SConfig.SERVER.global_armor.get();
        if (AGE.equals(dataAccessor)){
            AttributeInstance hp = this.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance def = this.getAttribute(Attributes.ARMOR);
            if (hp != null){
                hp.setBaseValue(health);
            }
            if (def != null){
                def.setBaseValue(armor);
            }
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(dataAccessor);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(this.getAge() >= 1 ? (1.0F * this.getAge()) : 1.0F);
    }

    @Override
    public void die(DamageSource source) {
        if(this.getLinked() && source.getEntity() != null && this.getAge() > 3){
            if (this.isInPowderSnow || this.Cold() || (this.getLastDamageSource() != null && this.getLastDamageSource() == DamageSource.FREEZE)){
                return;
            } else
            {
            AABB searchbox = this.getBoundingBox().inflate(SConfig.SERVER.proto_range.get());
            List<Proto> entities = this.level.getEntitiesOfClass(Proto.class,searchbox , EntitySelector.NO_CREATIVE_OR_SPECTATOR);
                for (Proto proto : entities) {
                    int y = source.getDirectEntity() != null ? (int)  source.getDirectEntity().getY() :(int)  this.getY();
                    proto.setSignal(true);
                    proto.setPlace(new BlockPos((int) this.getX(),y,(int) this.getZ()));
                }
            }
        }
        for (int i = 0;i <= this.getAge(); i++){
            super.die(source);
        }
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        setDefaultLinkage(this.level);
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    public void setDefaultLinkage(Level level){
        if (level instanceof ServerLevel serverLevel){
            SporeSavedData data = SporeSavedData.getDataLocation(serverLevel);
            if (data != null && data.getAmountOfHiveminds() >= SConfig.SERVER.proto_spawn_world_mod.get()){
                this.setLinked(true);
            }
        }
    }


    @Override
    public int getEmerge_tick() {
        return 40;
    }
}
