package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.BraionmilSwellGoal;
import com.Harbinger.Spore.Sentities.Utility.UtilityEntity;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Braionmil extends EvolvedInfected  implements RangedAttackMob {
    public Braionmil(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(Braionmil.class, EntityDataSerializers.INT);
    private int swell;
    private final int maxSwell = 40;


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
    }
    public void addAdditionalSaveData(CompoundTag data) {
        super.addAdditionalSaveData(data);

        data.putShort("Fuse", (short)this.maxSwell);
    }

    public void tick() {

        if (this.isAlive()) {

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.SOUL_ESCAPE, 1.0F, 0.5F);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.setSwellDir(-1);
                this.chemAttack(this);
            }
        }
        super.tick();
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    public boolean BraioAttack(){
        return this.swell >= 1;
    }


    public void setSwellDir(int p_32284_) {
        this.entityData.set(DATA_SWELL_DIR, p_32284_);
    }

    public int BraioSwell(){return swell;}

    private void chemAttack(LivingEntity pLivingEntity) {
        AABB boundingBox = pLivingEntity.getBoundingBox().inflate(8);
        List<Entity> entities = pLivingEntity.level.getEntities(pLivingEntity, boundingBox);

        for (Entity entity : entities) {
            if ((entity instanceof LivingEntity livingEntity) && !(entity instanceof Infected || entity instanceof UtilityEntity)) {
                livingEntity.addEffect(new MobEffectInstance(Seffects.MARKER.get(), SConfig.SERVER.marker_duration.get(), SConfig.SERVER.marker_level.get(), false, false));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, SConfig.SERVER.poison_duration.get(), SConfig.SERVER.poison_level.get()));
                livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(), SConfig.SERVER.mycelium_duration.get(), SConfig.SERVER.mycelium_level.get()));
            }
        }
    }


    public void aiStep() {
        super.aiStep();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level;
        if ((swell >= 25)) {
            for (int i = 0; i < 360; i++) {
                if (i % 20 == 0) {
                    world.addParticle(ParticleTypes.SMOKE,
                            x , y + 1.2, z ,
                            Math.cos(i) * 0.15d, Math.sin(i) * Math.cos(i) * 0.15d, Math.sin(i) * 0.15d);
                }
            }
        }
    }




    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new BraionmilSwellGoal(this, 1.1));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this,1,3,3,6));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ARMOR, SConfig.SERVER.braio_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.braio_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 28)
                .add(Attributes.ATTACK_DAMAGE, 1);

    }

    @Override
    public void performRangedAttack(LivingEntity p_33317_, float p_33318_) {

    }


    protected SoundEvent getAmbientSound() {
        return Ssounds.INF_GROWL.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
}
