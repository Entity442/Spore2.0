package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.*;
import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.Organoids.Usurper;
import com.Harbinger.Spore.Sentities.Organoids.Verwa;
import com.Harbinger.Spore.Sentities.Projectile.FleshBomb;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class ArenaEntity extends UtilityEntity {
    public static final EntityDataAccessor<Integer> BORROW = SynchedEntityData.defineId(ArenaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> EMERGE = SynchedEntityData.defineId(ArenaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> WAVE_SIZE = SynchedEntityData.defineId(ArenaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> WAVE_LEVEL = SynchedEntityData.defineId(ArenaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SPECIAL_SPAWNS = SynchedEntityData.defineId(ArenaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> START = SynchedEntityData.defineId(ArenaEntity.class, EntityDataSerializers.BOOLEAN);
    private List<Entity> waveHosts = new ArrayList<>();
    public List<FleshBomb.BombType> bombTypes = new ArrayList<>(){{add(FleshBomb.BombType.BASIC);add(FleshBomb.BombType.FLAME);add(FleshBomb.BombType.BILE);add(FleshBomb.BombType.ACID);}};
    public List<Enchantment> enchantmentList = new ArrayList<>(){{add(Senchantments.CRYOGENIC_ASPECT.get());add(Senchantments.SYMBIOTIC_RECONSTITUTION.get());add(Senchantments.CORROSIVE_POTENCY.get());add(Senchantments.GASTRIC_SPEWAGE.get());}};
    public ArenaEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(BORROW,0);
        entityData.define(EMERGE,0);
        entityData.define(WAVE_SIZE,0);
        entityData.define(WAVE_LEVEL,0);
        entityData.define(SPECIAL_SPAWNS,0);
        entityData.define(START,false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        setWaveSize(compoundTag.getInt("size"));
        setWaveLevel(compoundTag.getInt("level"));
        setAmountOfSpecialSpawns(compoundTag.getInt("special"));
        startWave(compoundTag.getBoolean("start"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("size",getWaveSize());
        compoundTag.putInt("level",getWaveLevel());
        compoundTag.putInt("special",getSpecialSpawns());
        compoundTag.putBoolean("start",isWaveActive());
    }

    public void setWaveSize(int size){entityData.set(WAVE_SIZE,size);}
    public void setWaveLevel(int level){entityData.set(WAVE_LEVEL,level);}
    public void setAmountOfSpecialSpawns(int amount){entityData.set(SPECIAL_SPAWNS,amount);}
    public int getWaveSize(){return entityData.get(WAVE_SIZE);}
    public int getWaveLevel(){return entityData.get(WAVE_LEVEL);}
    public int getSpecialSpawns(){return entityData.get(SPECIAL_SPAWNS);}
    public void startWave(boolean value){entityData.set(START,value);}
    public boolean isWaveActive(){return entityData.get(START);}

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .add(Attributes.ARMOR, 1)
                .add(Attributes.FOLLOW_RANGE, 8)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }
    public boolean isEmerging(){
        return this.entityData.get(EMERGE) > 0;
    }
    public void tickEmerging(){
        int emerging = this.entityData.get(EMERGE);
        if (emerging > 60){
            recalculateHosts();
            emerging = -1;
        }
        this.entityData.set(EMERGE, emerging + 1);
    }
    public boolean isBurrowing(){
        return this.entityData.get(BORROW) > 0;
    }
    public void tickBurrowing(){
        int burrowing = this.entityData.get(BORROW);
        if (burrowing > 60) {
            burrowing = -1;
            dropLoot();
            discard();
        }
        this.entityData.set(BORROW, burrowing + 1);
    }
    public int getEmerge(){return entityData.get(EMERGE);}
    public int getBorrow(){return entityData.get(BORROW);}

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        return false;
    }
    public void recalculateHosts(){
        waveHosts.clear();
        waveHosts = level.getEntities(this,this.getBoundingBox().inflate(16), entity -> {return entity instanceof LivingEntity && !(entity instanceof UtilityEntity || entity instanceof Infected);});
        if (!waveHosts.isEmpty()){
            if (!isWaveActive()){
                compareEntity(waveHosts);
            }
            for (Entity entity : waveHosts){
                if (entity.getY() > this.getY()+3 && Math.random() < 0.1f){
                    summonUsurper();
                }
            }
        }
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    public void compareEntity(List<Entity> entities){
        for (Entity entity : entities){
            if (entity instanceof LivingEntity living){
                for (int i = 0;i<living.getArmorValue();i++){
                    if (i % 5 == 0){
                        setWaveSize(getWaveSize()+1);
                    }
                    if (i % 7 == 0){
                        setWaveLevel(getWaveLevel()+1);
                    }
                }
                for (int i = 0;i<living.getMaxHealth();i++){
                    if (i % 4 == 0){
                        setWaveSize(getWaveSize()+1);
                    }
                    if (i % 50 == 0){
                        setWaveLevel(getWaveLevel()+1);
                    }
                }
                if (living.hasEffect(Seffects.SYMBIOSIS.get())){
                    setAmountOfSpecialSpawns(getSpecialSpawns()+1);
                }
                if (living instanceof InventoryCarrier carrier){
                    int l = 0;
                    int f = 0;
                    for (int e = 0; e<carrier.getInventory().getContainerSize();e++){
                        ItemStack stack = carrier.getInventory().getItem(e);
                        if (stack.getItem().isEdible()){
                            l = l+stack.getCount();
                        }
                        if (l % 32 == 0){
                            setAmountOfSpecialSpawns(getSpecialSpawns()+1);
                        }
                    }
                    for (int e = 0; e<carrier.getInventory().getContainerSize();e++){
                        ItemStack stack = carrier.getInventory().getItem(e);
                        for (Enchantment enchantment : enchantmentList){
                            if (stack.getEnchantmentLevel(enchantment) > 0){
                                f = f+stack.getCount();
                            }
                        }
                        if (f % 3 == 0){
                            setAmountOfSpecialSpawns(getSpecialSpawns()+1);
                        }
                    }
                }
                startWave(true);
            }
        }
    }
    public void summonVerva(boolean special,List<? extends String> mob){
        int X = random.nextInt(-16,16);
        int Z = random.nextInt(-16,16);
        String creature = mob.get(random.nextInt(mob.size()));
        Verwa verva = new Verwa(Sentities.VERVA.get(), level);
        verva.randomTeleport(this.getX()+X,this.getY(),this.getZ()+Z,false);
        verva.setStoredMob(creature);
        verva.tickEmerging();
        level.addFreshEntity(verva);
        if (special){
            this.setAmountOfSpecialSpawns(getSpecialSpawns()-1);
        }else {
            this.setWaveSize(getWaveSize()-1);
        }
        if (this.getWaveLevel() >= 2 && Math.random() < (getWaveLevel()-1) * 0.05f){
            this.summonBomb();
        }
    }
    public void summonUsurper(){
        int X = random.nextInt(-16,16);
        int Z = random.nextInt(-16,16);
        Usurper verva = new Usurper(Sentities.USURPER.get(), level);
        verva.randomTeleport(this.getX()+X,this.getY(),this.getZ()+Z,false);
        verva.tickEmerging();
        level.addFreshEntity(verva);
    }
    public void summonBomb(){
        int X = random.nextInt(-32,32);
        int Z = random.nextInt(-32,32);
        FleshBomb.BombType type = bombTypes.get(random.nextInt(bombTypes.size()));
        FleshBomb verva = new FleshBomb(level,this,10f, type,random.nextInt(2,5));
        verva.setLivingEntityPredicate(Utilities.TARGET_SELECTOR);
        verva.moveTo(this.getX()+X,this.getY()+100,this.getZ()+Z);
        level.addFreshEntity(verva);
    }
    public Map<Integer,List<? extends String>> getWaveSpawns(){
        Map<Integer,List<? extends String>> values = new HashMap<>();
        values.put(0, SConfig.DATAGEN.raid_level_1.get());
        values.put(1, SConfig.DATAGEN.raid_level_2.get());
        values.put(2, SConfig.DATAGEN.raid_level_3.get());
        return values;
    }
    public void calculateSummons(){
        int e = this.getWaveSize() > 3 ? random.nextInt(4) : this.getWaveSize();
        int wave = Math.min(this.getWaveLevel(), 2);
        if (getWaveSize() <= 0 && checkForInfected() && isWaveActive()){
            tickBurrowing();
            return;
        }
        for (int i = 0;i<e;i++){
            boolean special = this.getSpecialSpawns() > 0 && Math.random() < 0.1f;
            summonVerva(special,special ? SConfig.DATAGEN.special.get() : getWaveSpawns().get(wave));
        }
        this.playSound(SoundEvents.BELL_RESONATE);
    }
    public boolean checkForInfected(){
        AABB aabb = this.getBoundingBox().inflate(8);
        List<Entity> list = level.getEntities(this,aabb,entity -> {return entity instanceof Infected || entity instanceof UtilityEntity;});
        return list.size() < 4;
    }

    @Override
    public void tick() {
        super.tick();
        if (isBurrowing()){
            tickBurrowing();
        }
        if (isEmerging()){
            tickEmerging();
        }
        if (this.tickCount % 300 == 0){
            calculateSummons();
        }
        if (this.tickCount % 40 == 0){
            recalculateHosts();
        }
    }

    public void dropLoot(){
        for (String string : SConfig.DATAGEN.drops.get()){
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(string));
            if (item != null){
                int i = getWaveLevel() > 0 ? random.nextInt(getWaveLevel(),3 * getWaveLevel()) : 1;
                if (Math.random() < 0.2f * Math.min(1,getWaveLevel())){
                    ItemStack itemStack = new ItemStack(item,i);
                    ItemEntity itemEntity = new ItemEntity(this.level, this.getX() , this.getY(),this.getZ(),itemStack);
                    level.addFreshEntity(itemEntity);
                }
            }
        }
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }
    @Override
    public boolean isPushedByFluid(FluidType type) {
        return false;
    }
    @Override
    public boolean isPushable() {
        return false;
    }
}
