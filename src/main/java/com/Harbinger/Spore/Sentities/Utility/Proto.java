package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.AI.ProtoHivemindLinker;
import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Proto extends UtilityEntity{
    private int counter;
    private static final EntityDataAccessor<Integer> BIOMASS = SynchedEntityData.defineId(Proto.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ALERT_LEVEL = SynchedEntityData.defineId(Proto.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> LOCATION = SynchedEntityData.defineId(Proto.class, EntityDataSerializers.BLOCK_POS);

    @Nullable
    public BlockPos signalLocation;

    public Proto(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        setPersistenceRequired();
    }
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }


    public  void setLocation(BlockPos pos){
        this.entityData.set(LOCATION,pos);
    }
    public BlockPos getLocation(){
        return this.entityData.get(LOCATION);
    }

    public  void setSignal(BlockPos pos){
        this.signalLocation = pos;
    }

    public BlockPos getSignal(){
        return signalLocation;
    }

    public  void setBiomass(Integer bio){
        this.entityData.set(BIOMASS,bio);
    }
    public int getBiomass(){
        return entityData.get(BIOMASS);
    }

    public  void setAlert(Integer bio){
        this.entityData.set(ALERT_LEVEL,bio);
    }

    public int getAlert(){
        return entityData.get(ALERT_LEVEL);
    }



    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("biomass",entityData.get(BIOMASS));
        tag.putInt("alert_level",entityData.get(ALERT_LEVEL));
        tag.putInt("locationX",getLocation().getX());
        tag.putInt("locationY",getLocation().getY());
        tag.putInt("locationZ",getLocation().getZ());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(BIOMASS, tag.getInt("biomass"));
        entityData.set(ALERT_LEVEL, tag.getInt("alert_level"));
        int i = tag.getInt("locationX");
        int j = tag.getInt("locationY");
        int k = tag.getInt("locationZ");
        this.setLocation(new BlockPos(i,j,k));
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LOCATION, BlockPos.ZERO);
        this.entityData.define(BIOMASS,100);
        this.entityData.define(ALERT_LEVEL,0);
    }


    @Override
    public void tick() {
        super.tick();
        if (this.entityData.get(BIOMASS) < 50){
            if (counter > 60) {
                counter = 0;
                this.entityData.set(BIOMASS, this.entityData.get(BIOMASS) + 1);
            } else {
                counter = counter + 1;
            }
            }
        if (signalLocation != null && this.getBiomass() > 10){
            if (check()){
                SummonScent();
            }
        }
    }

    private boolean check(){
        assert signalLocation != null;
        AABB box = AABB.ofSize(new Vec3(signalLocation.getX(), signalLocation.getY(), signalLocation.getZ()), 5, 5, 5);
        List<Entity> entities = this.level.getEntities(this, box , EntitySelector.NO_CREATIVE_OR_SPECTATOR);
        for (Entity en : entities) {
            if (en instanceof LivingEntity && !(en instanceof Infected || en instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(en.getEncodeId()))){
                return true;
            }
        }
        return false;
    }


    private void SummonScent(){
        assert signalLocation != null;
                ScentEntity scent = new ScentEntity(Sentities.SCENT.get(),level);
                scent.moveTo(signalLocation.getX(),signalLocation.getY(),signalLocation.getZ(),level.getRandom().nextFloat() * 360F, 0);
                level.addFreshEntity(scent);
                this.setBiomass(this.getBiomass() -10);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.mound_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, SConfig.SERVER.mound_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new ProtoHivemindLinker(this));
        super.registerGoals();
    }
}
