package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Sentities.AI.HurtTargetGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Proto extends UtilityEntity{

    public Proto(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        setPersistenceRequired();
    }
    int counter;
    int breakCounter;

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }



    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.proto_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, SConfig.SERVER.proto_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new HurtTargetGoal(this , entity -> {return !SConfig.SERVER.blacklist.get().contains(entity.getEncodeId());}, Infected.class).setAlertOthers(Infected.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, Player.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 5, false, true, (en) -> {
            return SConfig.SERVER.whitelist.get().contains(en.getEncodeId());
        }));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 5, false, true, (en) -> {
            return !(en instanceof Animal || en instanceof AbstractFish || en instanceof Infected || en instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(en.getEncodeId())) && SConfig.SERVER.at_mob.get();
        }));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Animal.class, 5, false, true, (en) -> {
            return !SConfig.SERVER.blacklist.get().contains(en.getEncodeId()) && SConfig.SERVER.at_an.get();
        }));
        this.goalSelector.addGoal(3,new ProtoScentDefense(this));
        this.goalSelector.addGoal(2,new ProtoTargeting(this));
        super.registerGoals();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isOnGround()){
            this.makeStuckInBlock(Blocks.AIR.defaultBlockState(),new Vec3(0,1,0));
        }
        if (counter <1200){
            counter= counter +1;
        }else{
            AABB searchbox = AABB.ofSize(new Vec3(this.getX(), this.getY(), this.getZ()), 300, 200, 300);
            List<Entity> entities = this.level.getEntities(this, searchbox , EntitySelector.NO_CREATIVE_OR_SPECTATOR);
            entityData.set(HOSTS,0);
            for (Entity en : entities) {
                if (en instanceof Infected infected){
                    if (!infected.getLinked()){
                        infected.setLinked(true);
                    }
                    counter = 0;
                }
                if (en instanceof Infected infected){
                    if (infected.getLinked()){
                        setHosts(getHosts() + 1);
                    }
                    counter = 0;
                }
            }
        }
        if (breakCounter < 40){
            breakCounter = breakCounter +1;
        }else{
            if (this.getLastDamageSource() == DamageSource.IN_WALL && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)){
                AABB aabb = this.getBoundingBox().inflate(0.2);
                boolean flag = false;
                for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                    BlockState blockstate = this.level.getBlockState(blockpos);
                    if (blockstate.getDestroySpeed(level ,blockpos) < 10 && blockstate.getDestroySpeed(level ,blockpos) > 0) {
                        flag =  this.level.destroyBlock(blockpos, true, this) || flag;
                        breakCounter = 0;
                    }
                }
            }
        }
    }



    static class ProtoScentDefense extends Goal{
        public Proto proto;
        public ProtoScentDefense(Proto proto1){
            this.proto = proto1;
        }

        @Override
        public boolean canUse() {
            return this.proto.getTarget() != null && checkForScent() && this.proto.random.nextInt(0,20) == 10;
        }

        private boolean checkForScent() {
            AABB hitbox = this.proto.getBoundingBox().inflate(3);
            List<Entity> entities = this.proto.level.getEntities(this.proto, hitbox ,EntitySelector.NO_CREATIVE_OR_SPECTATOR);
            for (Entity en : entities) {
                if (en instanceof ScentEntity){
                    return false;
                }
            }
            AABB aabb = this.proto.getBoundingBox().inflate(8);
            List<Entity> entities1 = this.proto.level.getEntities(this.proto, aabb ,EntitySelector.NO_CREATIVE_OR_SPECTATOR);
            for (Entity en : entities1) {
                if (en instanceof Infected){
                    return false;
                }
            }
            return true;
        }

        @Override
        public void start() {
            SummonScent();
            super.start();
        }

        private void SummonScent() {
            ScentEntity scent = new ScentEntity(Sentities.SCENT.get(), this.proto.level);
            scent.setOvercharged(true);
            scent.moveTo(this.proto.getX(),this.proto.getY(),this.proto.getZ());
            this.proto.level.addFreshEntity(scent);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isOnFire() && !hasEffect(MobEffects.FIRE_RESISTANCE)){
            this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,200,0));
        }else if (this.getLastDamageSource() == DamageSource.DROWN && !hasEffect(MobEffects.WATER_BREATHING)){
            this.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0));
        }else if (this.getLastDamageSource() == DamageSource.FREEZE){
            this.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,400,0));
        }else if (this.getHealth() < (this.getMaxHealth()/2) && !(hasEffect(MobEffects.WEAKNESS) || hasEffect(MobEffects.DAMAGE_RESISTANCE))){
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,100,0));
        }else if (this.getHealth() < this.getMaxHealth() && !this.hasEffect(MobEffects.REGENERATION)){
            this.addEffect(new MobEffectInstance(MobEffects.REGENERATION,100,0));
        }
    }

    static class ProtoTargeting extends Goal{
        public Proto proto;
        public ProtoTargeting(Proto p){
            this.proto = p;
        }

        @Override
        public boolean canUse() {
            return proto.getTarget() != null  && this.proto.getRandom().nextInt(0,5) == 3;
        }

        @Override
        public boolean canContinueToUse() {
            return proto.getTarget() != null;
        }

        @Override
        public void start() {
            Targeting(proto);
            super.start();
        }

        public void Targeting(Entity entity){
            AABB boundingBox = entity.getBoundingBox().inflate(64);
            List<Entity> entities = entity.level.getEntities(entity, boundingBox , EntitySelector.NO_CREATIVE_OR_SPECTATOR);

            for (Entity entity1 : entities) {
                if(entity1 instanceof Infected livingEntity) {
                    if (livingEntity.getTarget() == null && this.proto.getTarget() != null && this.proto.getTarget().isAlive() && !this.proto.getTarget().isInvulnerable()){
                        livingEntity.setTarget(proto.getTarget());
                    }
                }
            }
        }
    }
    protected int calculateFallDamage(float p_149389_, float p_149390_) {
        return super.calculateFallDamage(p_149389_, p_149390_) - 60;
    }
    private static final EntityDataAccessor<Integer> HOSTS = SynchedEntityData.defineId(Mound.class, EntityDataSerializers.INT);

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("hosts",entityData.get(HOSTS));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(HOSTS, tag.getInt("hosts"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(HOSTS,0);
    }
    public int getHosts(){
        return entityData.get(HOSTS);
    }
    public void setHosts(int i){
        entityData.set(HOSTS,i);
    }


    @Override
    public boolean dampensVibrations() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (amount > 20){
            return super.hurt(source,20f);
        }
        return super.hurt(source, amount);
    }
}
