package com.Harbinger.Spore.Sentities.Organoids;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;

public class BiomassReformator extends UtilityEntity {
    private static final EntityDataAccessor<Integer> COUNTER = SynchedEntityData.defineId(BiomassReformator.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BIOMASS = SynchedEntityData.defineId(BiomassReformator.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(BiomassReformator.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> LOCATION = SynchedEntityData.defineId(BiomassReformator.class, EntityDataSerializers.BLOCK_POS);
    public BiomassReformator(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }




    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COUNTER, 0);
        this.entityData.define(BIOMASS, 0);
        this.entityData.define(STATE, 0);
        this.entityData.define(LOCATION, BlockPos.ZERO);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isOnGround()){
            this.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0, 1, 0));
        }
        if (this.entityData.get(COUNTER) < (SConfig.SERVER.recontructor_clock.get() * 20)){
            this.entityData.set(COUNTER , this.entityData.get(COUNTER) + 1);
        }else{
            this.entityData.set(COUNTER,0);
            this.entityData.set(BIOMASS , this.entityData.get(BIOMASS) + 1);
        }
        if (this.entityData.get(BIOMASS) >= SConfig.SERVER.reconstructor_biomass.get()){
            this.Summon(this);
            this.discard();
        }
        if (this.random.nextInt(100) == 0){
            this.CallNearbyInfected();
        }
        if (this.random.nextInt(40) == 0){
            this.AssimilateNearbyInfected();
        }
    }

    public void setBiomass(int biomass){
        entityData.set(BIOMASS,biomass);
    }
    public int getBiomass(){
        return entityData.get(BIOMASS);
    }

    public void setState(int state){
        entityData.set(STATE,state);
    }
    public int getState(){
        return entityData.get(STATE);
    }
    public void setLocation(BlockPos pos){
        entityData.set(LOCATION,pos);
    }
    public BlockPos getLocation(){
        return entityData.get(LOCATION);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Counter", entityData.get(COUNTER));
        tag.putInt("Biomass", entityData.get(BIOMASS));
        tag.putInt("State", entityData.get(STATE));
        tag.putInt("LocationX", this.getLocation().getX());
        tag.putInt("LocationY", this.getLocation().getY());
        tag.putInt("LocationZ", this.getLocation().getZ());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(COUNTER, tag.getInt("Counter"));
        entityData.set(BIOMASS, tag.getInt("Biomass"));
        entityData.set(STATE, tag.getInt("State"));
        int i = tag.getInt("LocationX");
        int j = tag.getInt("LocationY");
        int k = tag.getInt("LocationZ");
        this.setLocation(new BlockPos(i, j, k));
    }
    private void CallNearbyInfected(){
        AABB hitbox = this.getBoundingBox().inflate(50);
        List<Entity> entities = this.level.getEntities(this, hitbox ,EntitySelector.NO_CREATIVE_OR_SPECTATOR);
        for (Entity en : entities){
            if (en instanceof Infected infected){
                infected.setSearchPos(new BlockPos(this.getX(),this.getY(),this.getZ()));
            }
        }
    }

    private void AssimilateNearbyInfected(){
        AABB hitbox = this.getBoundingBox().inflate(0.1);
        List<Entity> entities = this.level.getEntities(this, hitbox ,EntitySelector.NO_CREATIVE_OR_SPECTATOR);
        for (Entity en : entities){
            if (en instanceof Infected infected){
                this.setBiomass(this.getBiomass() + SConfig.SERVER.reconstructor_assimilation.get() + infected.getKills());
                infected.discard();
                break;
            }
        }
    }


    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
        if (BIOMASS.equals(dataAccessor)){
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(dataAccessor);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (this.getBiomass() <= (SConfig.SERVER.reconstructor_biomass.get()/4)){
            return super.getDimensions(pose).scale(0.5f);
        }if (this.getBiomass() <= (SConfig.SERVER.reconstructor_biomass.get()/2)){
            return super.getDimensions(pose);
        }if (this.getBiomass() <= (SConfig.SERVER.reconstructor_biomass.get()/2 + SConfig.SERVER.reconstructor_biomass.get()/4)){
            return super.getDimensions(pose).scale(2f);
        }if (this.getBiomass() <= SConfig.SERVER.reconstructor_biomass.get()){
            return super.getDimensions(pose).scale(3f);
        }
        return super.getDimensions(pose).scale(3f);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.reconstructor_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, SConfig.SERVER.reconstructor_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }


    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (getBiomass() > 1){
            int age = 0;
            if (this.getBiomass() <= (SConfig.SERVER.reconstructor_biomass.get()/4)){
                age = 1;
            }if (this.getBiomass() <= (SConfig.SERVER.reconstructor_biomass.get()/2)){
                age = 2;
            }if (this.getBiomass() <= (SConfig.SERVER.reconstructor_biomass.get()/2 + SConfig.SERVER.reconstructor_biomass.get()/4)){
                age = 3;
            }if (this.getBiomass() <= SConfig.SERVER.reconstructor_biomass.get()){
                age = 4;
            }
            if (age > 1){
                AttributeInstance health = this.getAttribute(Attributes.MAX_HEALTH);
                assert health != null;
                health.setBaseValue(SConfig.SERVER.mound_hp.get() * age * SConfig.SERVER.global_health.get());
                AttributeInstance armor = this.getAttribute(Attributes.ARMOR);
                assert armor != null;
                armor.setBaseValue(SConfig.SERVER.mound_armor.get() * age * SConfig.SERVER.global_armor.get());
            }
        }
    }

    private void Summon(Entity entity){
        List<? extends String> ev;
        if (entityData.get(STATE) == 1){
            ev = SConfig.SERVER.reconstructor_water.get();
        }else if (entityData.get(STATE) >=2){
            ev = SConfig.SERVER.reconstructor_air.get();
        }else {
            ev = SConfig.SERVER.reconstructor_terrain.get();
        }
        Random rand = new Random();
        for (int i = 0; i < 1; ++i) {
            int randomIndex = rand.nextInt(ev.size());
            ResourceLocation randomElement1 = new ResourceLocation(ev.get(randomIndex));
            EntityType<?> randomElement = ForgeRegistries.ENTITY_TYPES.getValue(randomElement1);
            Mob waveentity = (Mob) randomElement.create(level);
            assert waveentity != null;
            waveentity.setPos(entity.getX(), entity.getY(), entity.getZ());
            if (waveentity instanceof Calamity calamity){
                calamity.setSearchArea(this.getLocation());
            }
            level.addFreshEntity(waveentity);
        }
    }

}
