package com.Harbinger.Spore.Sentities.Calamities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Sitems;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.CalamityInfectedCommand;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.ScatterShotRangedGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SporeBurstSupport;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SummonScentInCombat;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.FallenMultipart.Licker;
import com.Harbinger.Spore.Sentities.Projectile.BileProjectile;
import com.Harbinger.Spore.Sentities.TrueCalamity;
import com.Harbinger.Spore.Sentities.WaterInfected;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class Gazenbrecher extends Calamity implements WaterInfected , RangedAttackMob, TrueCalamity {
    public static final EntityDataAccessor<Float> TONGUE = SynchedEntityData.defineId(Gazenbrecher.class, EntityDataSerializers.FLOAT);
    private int radar;
    private final CalamityMultipart[] subEntities;
    public final CalamityMultipart lowerbody;
    public final CalamityMultipart head;
    public final CalamityMultipart tongue;
    public Gazenbrecher(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.lowerbody = new CalamityMultipart(this, "lowerbody", 3F, 3F);
        this.tongue = new CalamityMultipart(this, "tongue", 2F, 2F);
        this.head = new CalamityMultipart(this, "head", 3F, 3F);
        this.subEntities = new CalamityMultipart[]{ this.lowerbody, this.head,this.tongue};
        this.maxUpStep =1.5F;
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    public void travel(Vec3 vec) {
        if (this.isEffectiveAi() && this.isInFluidType()) {
            this.moveRelative(0.1F, vec);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.95D));
        } else {
            super.travel(vec);
        }
    }

    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
    }

    @Override
    public double setInflation() {
        return 1.0;
    }


    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() >= this.getMaxHealth() && this.getTongueHp() < this.getMaxTongueHp()){
            if (this.tickCount % 40 == 0){
                this.setTongueHp(this.getTongueHp() +1);
            }
        }
        if (this.isInFluidType()){
            if (this.getTarget() == null &&  this.radar >= 1200){
                this.playSound(Ssounds.SONAR.get());
                this.radar = 0;
                AABB boundingBox = this.getBoundingBox().inflate(64);
                List<Entity> entities = this.level.getEntities(this, boundingBox);
                for (Entity entity : entities) {
                    if (SConfig.SERVER.whitelist.get().contains(entity.getEncodeId()) || entity instanceof Player player && !player.getAbilities().instabuild){
                        if (entity instanceof LivingEntity livingEntity  && livingEntity.isAlive()){
                            this.playSound(Ssounds.SIGNAL.get(),2f,1f);
                            this.setTarget(livingEntity);
                        }
                    }
                }
            }else{
                this.radar++;
            }
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TONGUE, this.getMaxTongueHp());
    }
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("tongue_hp", entityData.get(TONGUE));
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(TONGUE, tag.getFloat("tongue_hp"));
    }
    public float getTongueHp(){
        return entityData.get(TONGUE);
    }
    public void setTongueHp(float i){
        entityData.set(TONGUE,i);
    }

    public float getMaxTongueHp(){
        return (float) (SConfig.SERVER.gazen_hp.get()/4.0f);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.gazen_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.gazen_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.gazen_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 2);

    }


    @Override
    public int getDestroySpeed() {
        return SConfig.SERVER.gazen_block_damage.get();
    }


    @Override
    public void aiStep() {
        float f14 = this.getYRot() * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        Vec3[] avec3 = new Vec3[this.subEntities.length];
        for(int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }
        this.tickPart(this.lowerbody, (double)(f2 * 2.5F), 0.0D, (double)(-f15 * 2.5F));
        this.tickPart(this.head, (double)(f2 * -3F), 0.0D, (double)(-f15 * -3F));
        this.tickPart(this.tongue, (double)(f2 * -5F), 0.3D, (double)(-f15 * -5F));
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


    private void tickPart(CalamityMultipart part, double e, double i, double o) {
        part.setPos(this.getX() + e, this.getY() + i, this.getZ() + o);
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

    public void recreateFromPacket(ClientboundAddEntityPacket p_218825_) {
        super.recreateFromPacket(p_218825_);
        if (true) return;
        CalamityMultipart[] calamityMultiparts = this.getSubEntities();

        for(int i = 0; i < calamityMultiparts.length; ++i) {
            calamityMultiparts[i].setId(i + p_218825_.getId());
        }

    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(amount > SConfig.SERVER.gazen_dpsr.get() && SConfig.SERVER.gazen_dpsr.get() > 0){
            return super.hurt(source, (float) (SConfig.SERVER.gazen_dpsr.get() * 1F));
        }
        return super.hurt(source, amount);
    }


    private void chemAttack() {
        AABB boundingBox = this.getBoundingBox().inflate(16);
        List<Entity> entities = this.level.getEntities(this, boundingBox);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity && !(entity instanceof Infected || entity instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(entity.getEncodeId()) || livingEntity.getItemBySlot(EquipmentSlot.HEAD).getItem() == Sitems.GAS_MASK.get())) {
                for (String str : SConfig.SERVER.gazen_debuffs.get()){
                    String[] string = str.split("\\|" );
                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(string[0]));
                    if (effect != null && !livingEntity.hasEffect(effect)){
                        livingEntity.addEffect(new MobEffectInstance(effect , Integer.parseUnsignedInt(string[1]), Integer.parseUnsignedInt(string[2])));
                    }
                }
            }else if (entity instanceof LivingEntity livingEntity && (entity instanceof Infected || entity instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(entity.getEncodeId()))){
                for (String str : SConfig.SERVER.gazen_buffs.get()){
                    String[] string = str.split("\\|" );
                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(string[0]));
                    if (effect != null && !livingEntity.hasEffect(effect)){
                        livingEntity.addEffect(new MobEffectInstance(effect , Integer.parseUnsignedInt(string[1]), Integer.parseUnsignedInt(string[2])));
                    }
                }
            }
        }
    }


    @Override
    public void registerGoals() {


        this.goalSelector.addGoal(3, new ScatterShotRangedGoal(this,1.3,60,32,1,3){
            @Override
            public boolean canUse() {
                if (Gazenbrecher.this.getTongueHp() <= 0){
                    return false;
                }
                return super.canUse() && (calculateHeight() || calculateDistance());
            }
        });
        this.goalSelector.addGoal(4, new AOEMeleeAttackGoal(this, 1.5, false,2.5 ,6){
            protected double getAttackReachSqr(LivingEntity entity) {
                float f = Gazenbrecher.this.getBbWidth();
                return (double)(f * 1.5F * f * 1.5F + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.2));
        this.goalSelector.addGoal(6,new CalamityInfectedCommand(this));
        this.goalSelector.addGoal(7,new SummonScentInCombat(this));
        this.goalSelector.addGoal(8,new SporeBurstSupport(this){
            @Override
            public void start() {
                super.start();
                chemAttack();
            }
        });
        super.registerGoals();
    }

    public boolean hurt(CalamityMultipart calamityMultipart, DamageSource source, float value) {
        if (calamityMultipart == this.tongue){
            if (this.getTongueHp() > 0 && value > this.getTongueHp()){
                if (this.getTongueHp() > 0 && value > this.getTongueHp()){
                    this.playSound(Ssounds.LIMB_SLASH.get());
                    SummonDetashedTongue();
                }
                this.playSound(Ssounds.LIMB_SLASH.get());
            }
            this.hurt(source,value * 1.5f);
            this.setTongueHp(value > this.getTongueHp() ? 0 : this.getTongueHp() - value);
        } else{
            this.hurt(source,value );
        }
        return true;
    }

    boolean calculateHeight(){
        return this.getTarget() != null && this.getTarget().getY() > this.getY() && Math.abs(Math.abs(this.getTarget().getY()) - Math.abs(this.getY())) > 5;
    }

    boolean calculateDistance(){
        return this.getTarget() != null && this.distanceToSqr(this.getTarget()) > 300.0D;
    }

    @Override
    public boolean hasLineOfSight(Entity entity) {
        if (calculateDistance() || calculateHeight()){
            return true;
        }
        return super.hasLineOfSight(entity);
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float p_33318_) {
        if(!level.isClientSide){
            BileProjectile tumor = new BileProjectile(level, this);
            Vec3  vec3 = (new Vec3(3D, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            double dx = livingEntity.getX() - this.getX();
            double dy = livingEntity.getY() + livingEntity.getEyeHeight();
            double dz = livingEntity.getZ() - this.getZ();
            tumor.setDamage((float) (SConfig.SERVER.gazen_ranged_damage.get() * 1f));
            tumor.setOwner(this);
            tumor.moveTo(this.getX() + vec3.x, this.getY()+1D ,this.getZ()+ vec3.z);
            tumor.shoot(dx, dy - tumor.getY() + Math.hypot(dx, dz) * 0.001F, dz, 2f, 6.0F);
            level.addFreshEntity(tumor);
        }
    }

    private void SummonDetashedTongue(){
        Licker licker = new Licker(Sentities.LICKER.get(),this.level);
        Vec3 vec3 = (new Vec3(4D, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
        licker.moveTo(this.getX() + vec3.x, this.getY() + 1.6,this.getZ()+ vec3.z);
        this.level.addFreshEntity(licker);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.playSound(Ssounds.SIEGER_BITE.get());
        return super.doHurtTarget(entity);
    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getStepSound() {
        if (this.onGround){
            return SoundEvents.RAVAGER_STEP;
        }
        return SoundEvents.GENERIC_SWIM;
    }

    protected SoundEvent getAmbientSound() {
        if (this.getTarget() != null && this.distanceToSqr(this.getTarget()) > 200){
            return null;
        }
        return Ssounds.GAZEN_AMBIENT.get();
    }
}
