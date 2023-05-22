package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.CalamityInfectedCommand;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SporeBurstSupport;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SummonScentInCombat;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class Sieger extends Calamity implements RangedAttackMob {
    private final CalamityMultipart[] subEntities;
    public final CalamityMultipart lowerbody;
    public final CalamityMultipart tailbody;
    public Sieger(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.lowerbody = new CalamityMultipart(this, "lowerbody", 3.0F, 3.0F);
        this.tailbody = new CalamityMultipart(this, "tail", 1.0F, 1.0F);
        this.subEntities = new CalamityMultipart[]{ this.lowerbody, this.tailbody};
        this.maxUpStep = 2.0F;
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }

    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
    }


    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() < this.getMaxHealth() && !this.hasEffect(MobEffects.REGENERATION) && this.getKills() > 0){
            this.addEffect(new MobEffectInstance(MobEffects.REGENERATION,600,0));
            this.setKills(this.getKills()-1);
        }
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

        this.tickPart(this.tailbody, (double)(f2 * 2.0F), 7.0D, (double)(-f15 * 2.0F));
        this.tickPart(this.lowerbody, (double)(f2 * 4.5F), 0.0D, (double)(-f15 * 4.5F));
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
    public void registerGoals() {
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this,0.4F));
        this.goalSelector.addGoal(1, new AOEMeleeAttackGoal(this, 1.5, false,2.5 ,6){
            protected double getAttackReachSqr(LivingEntity entity) {
                float f = Sieger.this.getBbWidth() - 0.1F;
                return (double)(f * 2.0F * f * 2.0F + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5,new CalamityInfectedCommand(this));
        this.goalSelector.addGoal(5,new SummonScentInCombat(this));
        this.goalSelector.addGoal(6,new SporeBurstSupport(this){
            @Override
            public void start() {
                super.start();
                chemAttack();
            }
        });
        super.registerGoals();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.sieger_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.sieger_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.sieger_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 2);

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
        if(amount > 70F){
            return super.hurt(source, 70F);
        }
        return super.hurt(source, amount);
    }

    public boolean hurt(CalamityMultipart calamityMultipart, DamageSource damageSource, float amount) {
        if (calamityMultipart == tailbody){
            amount = amount * 1.5F;
        }
        return super.hurt(damageSource, amount);
    }


    @Override
    public void performRangedAttack(LivingEntity p_33317_, float p_33318_) {

    }

    private void chemAttack() {
        AABB boundingBox = this.getBoundingBox().inflate(16);
        List<Entity> entities = this.level.getEntities(this, boundingBox);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity && !(entity instanceof Infected || entity instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(entity.getEncodeId()))) {
                for (String str : SConfig.SERVER.sieger_debuffs.get()){
                    String[] string = str.split("\\|" );
                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(string[0]));
                    if (effect != null && !livingEntity.hasEffect(effect)){
                        livingEntity.addEffect(new MobEffectInstance(effect , Integer.parseUnsignedInt(string[1]), Integer.parseUnsignedInt(string[2])));
                    }
                }
            }else if (entity instanceof LivingEntity livingEntity && (entity instanceof Infected || entity instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(entity.getEncodeId()))){
                for (String str : SConfig.SERVER.sieger_debuffs.get()){
                    String[] string = str.split("\\|" );
                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(string[0]));
                    if (effect != null && !livingEntity.hasEffect(effect)){
                        livingEntity.addEffect(new MobEffectInstance(effect , Integer.parseUnsignedInt(string[1]), Integer.parseUnsignedInt(string[2])));
                    }
                }
            }
        }
    }




}
