package com.Harbinger.Spore.Sentities.Calamities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.CalamityInfectedCommand;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.ScatterShotRangedGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SporeBurstSupport;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SummonScentInCombat;
import com.Harbinger.Spore.Sentities.AI.HurtTargetGoal;
import com.Harbinger.Spore.Sentities.AI.LeapGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.FallenMultipart.HowitzerArm;
import com.Harbinger.Spore.Sentities.Projectile.FleshBomb;
import com.Harbinger.Spore.Sentities.TrueCalamity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class Howitzer extends Calamity implements TrueCalamity, RangedAttackMob {
    public static final EntityDataAccessor<Float> RIGHT_ARM = SynchedEntityData.defineId(Howitzer.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> LEFT_ARM = SynchedEntityData.defineId(Howitzer.class, EntityDataSerializers.FLOAT);
    private final CalamityMultipart[] subEntities;
    public final CalamityMultipart rightArm;
    public final CalamityMultipart leftArm;
    public final CalamityMultipart mouth;
    public int getLeapTime = 0;
    public Howitzer(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.rightArm = new CalamityMultipart(this, "rightarm", 2F, 4F);
        this.leftArm = new CalamityMultipart(this, "leftarm", 2F, 4F);
        this.mouth = new CalamityMultipart(this, "mouth", 4F, 3F);
        this.subEntities = new CalamityMultipart[]{ this.rightArm, this.leftArm,this.mouth};
        this.maxUpStep = 1.5F;
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }
    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
    }

    @Override
    public double getDamageCap() {
        return SConfig.SERVER.howit_dpsr.get();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.howit_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.howit_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.howit_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 128)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 2);

    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.playSound(Ssounds.LANDING.get(),0.5f,0.5f);
        return super.doHurtTarget(entity);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() != null && this.random.nextFloat() <0.2f){setTarget(null);}
        return super.hurt(source, amount);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3,new LeapGoal(this,0.9f){
            @Override
            public boolean canUse() {
                return Howitzer.this.getGetLeapTime() <= 0 && Howitzer.this.hasBothArms() && Howitzer.this.isInMeleeRange() && super.canUse();
            }
            @Override
            public void start() {
                super.start();
                Howitzer.this.setLeapTicks(200);
            }
        });
        this.goalSelector.addGoal(4,new AOEMeleeAttackGoal(this,1,true,2,5,e-> {return this.TARGET_SELECTOR.test(e);}){
            @Override
            public boolean canUse() {
                return Howitzer.this.isInMeleeRange() && super.canUse();
            }
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                float f = Howitzer.this.getBbWidth();
                return (double)(f * 1.5F * f * 1.5F + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(4, new ScatterShotRangedGoal(this,1,80,64,1,5){
            @Override
            public boolean canUse() {
                return !Howitzer.this.isInMeleeRange() && super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.2));
        this.goalSelector.addGoal(6,new CalamityInfectedCommand(this));
        this.goalSelector.addGoal(7,new SummonScentInCombat(this));
        this.goalSelector.addGoal(8,new SporeBurstSupport(this));
        this.goalSelector.addGoal(9,new RandomStrollGoal(this , 1));
    }

    @Override
    public void aiStep() {
        Vec3[] avec3 = new Vec3[this.subEntities.length];
        for(int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }
        this.tickPart(this.mouth, Vec3.ZERO,5);
        if (getRightArmHp()>0){
            this.tickPart(this.rightArm, new Vec3(-3.85D,0D,4D));
        }else{
            this.tickPart(this.rightArm, Vec3.ZERO);
            rightArm.getBoundingBox().inflate(1,0.3,1);
        }
        if (getLeftArmHp() >0){
            this.tickPart(this.leftArm, new Vec3(3.85D,0D,-4D));
        }else{
            this.tickPart(this.leftArm, Vec3.ZERO);
            leftArm.getBoundingBox().inflate(1,0.3,1);
        }
        for(int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }
        super.aiStep();
    }
    public boolean hasBothArms(){
        return this.getRightArmHp()>0 && this.getLeftArmHp()>0;
    }
    public boolean isInMeleeRange(){
        LivingEntity living = this.getTarget();
        return living != null && this.distanceToSqr(living) < 200;
    }
    public int getGetLeapTime(){
        return getLeapTime;
    }
    public void setLeapTicks(int i){
        getLeapTime = i;
    }

    public CalamityMultipart[] getSubEntities() {
        return this.subEntities;
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public net.minecraftforge.entity.PartEntity<?>[] getParts() {
        return this.subEntities;
    }

    public void recreateFromPacket(ClientboundAddEntityPacket entityPacket) {
        super.recreateFromPacket(entityPacket);
        if (true) return;
        CalamityMultipart[] calamityMultiparts = this.getSubEntities();

        for(int i = 0; i < calamityMultiparts.length; ++i) {
            calamityMultiparts[i].setId(i + entityPacket.getId());
        }
    }
    public boolean hurt(CalamityMultipart calamityMultipart, DamageSource source, float value) {
        if (calamityMultipart == this.mouth){
            this.hurt(source,value*2f);
        }else if (calamityMultipart == this.rightArm){
            this.hurt(source,value *1.5f);
            float lostHealth = getRightArmHp()-value;
            this.setRightArmHp(lostHealth > 0 ? lostHealth : summonDetashedPart(true));
        }else if (calamityMultipart == this.leftArm){
            this.hurt(source,value*1.5f);
            float lostHealth = getLeftArmHp()-value;
            this.setLeftArmHp(lostHealth > 0 ? lostHealth : summonDetashedPart(false));
        } else{
            this.hurt(source,value );
        }
        return true;
    }

    @Override
    public int chemicalRange() {
        return 16;
    }

    @Override
    public List<? extends String> buffs() {
        return SConfig.SERVER.howit_buffs.get();
    }

    @Override
    public List<? extends String> debuffs() {
        return SConfig.SERVER.howit_debuffs.get();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RIGHT_ARM, this.getMaxArmHp());
        this.entityData.define(LEFT_ARM, this.getMaxArmHp());
    }
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("right_arm", entityData.get(RIGHT_ARM));
        tag.putFloat("left_arm",entityData.get(LEFT_ARM));
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(RIGHT_ARM, tag.getFloat("right_arm"));
        entityData.set(LEFT_ARM,tag.getFloat("left_arm"));
    }
    public float getRightArmHp(){
        return entityData.get(RIGHT_ARM);
    }
    public void setRightArmHp(float i){
        entityData.set(RIGHT_ARM,i);
    }
    public float getLeftArmHp(){
        return entityData.get(LEFT_ARM);
    }
    public void setLeftArmHp(float i){
        entityData.set(LEFT_ARM,i);
    }
    public float getMaxArmHp(){
        return (float) (SConfig.SERVER.howit_hp.get()/4.0f);
    }

    @Override
    public boolean hasLineOfSight(Entity entity) {
        if (canEntitySeeTheSky(entity) && canEntitySeeTheSky(this) || entity.distanceToSqr(this) < 200){
            return true;
        }else
        return super.hasLineOfSight(entity) || calculateHouseThiccness(entity);
    }

    private boolean canEntitySeeTheSky(Entity entity){
        return entity.level.canSeeSky(entity.getOnPos());
    }
    private boolean calculateHouseThiccness(Entity entity){
        List<BlockPos> floorPositions = new ArrayList<>();
        List<BlockPos> roofPositions = new ArrayList<>();
        AABB floorAABB = entity.getBoundingBox().inflate(1,8,1).move(0,-4,0);
        AABB roofAABB = entity.getBoundingBox().inflate(1,8,1).move(0,4,0);
        for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(floorAABB.minX), Mth.floor(floorAABB.minY), Mth.floor(floorAABB.minZ), Mth.floor(floorAABB.maxX), Mth.floor(floorAABB.maxY), Mth.floor(floorAABB.maxZ))) {
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.isSolidRender(entity.level,blockpos)){
                floorPositions.add(blockpos);
            }
        }
        for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(roofAABB.minX), Mth.floor(roofAABB.minY), Mth.floor(roofAABB.minZ), Mth.floor(roofAABB.maxX), Mth.floor(roofAABB.maxY), Mth.floor(roofAABB.maxZ))) {
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.isSolidRender(entity.level,blockpos)){
                roofPositions.add(blockpos);
            }
        }
        return floorPositions.size() < 4 || roofPositions.size() < 4;
    }

    @Override
    protected int calculateFallDamage(float p_149389_, float p_149390_) {
        if (this.getLeapTime >140){
            damageStomp(this.level,this.getOnPos(),12,8);
        }
        return super.calculateFallDamage(p_149389_, p_149390_)-25;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getGetLeapTime() > 0){
            getLeapTime--;
        }
        if (this.tickCount % 20 == 0 && this.getHealth() == this.getMaxHealth()){
            if (this.getRightArmHp() < this.getMaxArmHp()){
                this.setRightArmHp(getRightArmHp()+1);
            }
            if (this.getLeftArmHp() < this.getMaxArmHp()){
                this.setLeftArmHp(getLeftArmHp()+1);
            }
        }
    }
    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.howit_loot.get();
    }

    public float summonDetashedPart(boolean isRight){
        double offset = isRight ? 3D : -3D;
        Vec3 vec3 = (new Vec3(0.0D, 0.0D, offset)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
        HowitzerArm arm = new HowitzerArm(Sentities.HOWIT_ARM.get(),this.level);
        arm.setRight(isRight);
        arm.moveTo(this.getX() + vec3.x, this.getY() + 1.6,this.getZ()+ vec3.z);
        level.addFreshEntity(arm);
        this.playSound(Ssounds.LIMB_SLASH.get());
        return 0;
    }

    protected void damageStomp(Level level, BlockPos pos, double range, double damageRange){
        AABB aabb = this.getBoundingBox().inflate(damageRange);
        List<Entity> entities = level.getEntities(this,aabb,entity -> {return entity instanceof LivingEntity living && TARGET_SELECTOR.test(living);});
        for(int i = 0; i <= 2*range; ++i) {
            for(int j = 0; j <= 2*range; ++j) {
                for(int k = 0; k <= 2*range; ++k) {
                    double distance = Mth.sqrt((float) ((i-range)*(i-range) + (j-range)*(j-range) + (k-range)*(k-range)));
                    if (Math.abs(i) != 2 || Math.abs(j) != 2 || Math.abs(k) != 2) {
                        if (distance<range+(0.5)){
                            BlockPos blockpos = pos.offset( i-(int)range,j-(int)range,k-(int)range);
                            BlockState state = level.getBlockState(blockpos);
                            boolean airBelow = level.getBlockState(blockpos.below()).isAir();
                            if (level instanceof ServerLevel serverLevel){
                                if (airBelow && state.getDestroySpeed(level,pos) >= 0){
                                    FallingBlockEntity.fall(serverLevel,blockpos,state);
                                    serverLevel.removeBlock(blockpos,false);
                                }
                            }
                        }}}}}
        for (Entity entity : entities){
            if (entity instanceof LivingEntity living)
            for (int i = 0;i<2;i++){
                this.doHurtTarget(living);
                living.hurtTime = 0;
                living.invulnerableTime = 0;
            }
        }
        this.playSound(Ssounds.LANDING.get());
    }
    private FleshBomb.BombType compareEntity(LivingEntity living){
        AABB aabb = living.getBoundingBox().inflate(4);
        List<Entity> extra_targets = level.getEntities(living,aabb,entity -> {return entity instanceof LivingEntity livingEntity && TARGET_SELECTOR.test(living);});
        List<BlockPos> burnable_material = new ArrayList<>();
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            if (level.getBlockState(blockpos).isFlammable(level,blockpos, Direction.UP)){
                burnable_material.add(blockpos);
            }
        }
        if (burnable_material.size() > 8){
            return Math.random() < 0.3f ? FleshBomb.BombType.BILE : FleshBomb.BombType.FLAME;
        }
        if (SConfig.SERVER.corrosion.get().contains(living.getEncodeId())){
            return FleshBomb.BombType.ACID;
        }
        if (extra_targets.size() > 1 || living.getArmorValue() >=10){
            return FleshBomb.BombType.BILE;
        }
        return FleshBomb.BombType.BASIC;

    }

    @Override
    public void performRangedAttack(LivingEntity entity, float p_33318_) {
        float damage = (float) (SConfig.SERVER.howit_ranged_damage.get() * SConfig.SERVER.global_damage.get());
        FleshBomb bomb = new FleshBomb(level,this,damage,compareEntity(entity),random.nextInt(4,7));
        bomb.setLivingEntityPredicate(TARGET_SELECTOR);
        bomb.setCarrier(Math.random() < 0.2f);
        double dx = entity.getX() - this.getX();
        double dz = entity.getZ() - this.getZ();
        double dy = entity.getY() - this.getY();
        float value = random.nextFloat() * 0.5f;
        bomb.moveTo(this.getX() + value,this.getY()+7,this.getZ()+ value);
        bomb.shoot(dx * 0.085F,6.5f+ Math.hypot(dx, dz) * 0.02F +(dy>0?dy:0*0.5),dz  * 0.085F, 2f, 14.0F);
        level.addFreshEntity(bomb);
        this.playSound(Ssounds.FALLING_BOMB.get());
    }
    protected SoundEvent getAmbientSound() {
        return Ssounds.HOWITZER_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.RAVAGER_STEP;
    }
}
