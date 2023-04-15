package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Spotion;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.BuffAlliesGoal;
import com.Harbinger.Spore.Sentities.AI.RangedBuff;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class InfectedWitch extends Infected implements RangedAttackMob , RangedBuff {

    private Potion potion = null;

    public InfectedWitch(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_FIRE_RESISTANCE), SoundEvents.WITCH_DRINK, (p_35882_) -> {
            return (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(MobEffects.FIRE_RESISTANCE);
        }));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING), SoundEvents.WITCH_DRINK, (p_35882_) -> {
            return this.getHealth() < this.getMaxHealth();
        }){
            @Override
            public void start() {
                setHunger(SConfig.SERVER.hunger.get());
                super.start();
            }
        });
        this.goalSelector.addGoal(2, new BuffAlliesGoal(this,Infected.class,1.2,35,45,3));
        this.goalSelector.addGoal(1, new BuffAlliesGoal(this,Mob.class,1.2,35,45,3,entity -> {
            return SConfig.SERVER.evolved.get().contains(entity.getEncodeId());
        }));
        this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.0D, 40, 10.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

    }

    @Override
    public void tick() {
        if (isAlive() && potion != null && this.getHealth() == this.getMaxHealth()){
            this.setItemSlot(EquipmentSlot.MAINHAND,PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
        }
        super.tick();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_witch_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, SConfig.SERVER.inf_witch_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 16);

    }

    @Override
    public void performRangedAttack(LivingEntity entity, float f) {
        Vec3 vec3 = entity.getDeltaMovement();
            double d0 = entity.getX() + vec3.x - this.getX();
            double d1 = entity.getEyeY() - (double)1.1F - this.getY();
            double d2 = entity.getZ() + vec3.z - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
             if (d3 >= 8.0D && !entity.hasEffect(Seffects.MARKER.get())) {
                potion = Spotion.MARKER_POTION.get();
            } else if (entity.getHealth() >= 8.0F && !entity.hasEffect(Seffects.MYCELIUM.get())) {
                potion = Spotion.MYCELIUM_POTION.get();
            } else if (d3 <= 3.0D && !entity.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                potion = Potions.WEAKNESS;
            }else if (entity.getHealth() >= 8.0F && !entity.hasEffect(MobEffects.POISON)) {
                potion = Potions.POISON;
            }else {
                 potion = Potions.HARMING;
             }
            ThrownPotion thrownpotion = new ThrownPotion(this.level, this);
            thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
            thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
            thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }

            this.level.addFreshEntity(thrownpotion);

  }

    @Override
    public void performRangedBuff(LivingEntity entity, float f) {
        Vec3 vec3 = entity.getDeltaMovement();
        double d0 = entity.getX() + vec3.x - this.getX();
        double d1 = entity.getEyeY() - (double)1.1F - this.getY();
        double d2 = entity.getZ() + vec3.z - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        if ((entity.getHealth() < entity.getMaxHealth()) && !entity.isOnFire()) {
            potion = Potions.REGENERATION;
        }else if (entity.isOnFire() && !entity.hasEffect(MobEffects.FIRE_RESISTANCE)) {
            potion = Potions.FIRE_RESISTANCE;
        } else if (d3 <= 4.0D && !entity.hasEffect(MobEffects.DAMAGE_BOOST)) {
            potion = Potions.STRENGTH;
        } else if (d3 <= 3.0D && !entity.hasEffect(MobEffects.INVISIBILITY) && this.random.nextFloat() < 0.25F) {
            potion = Potions.INVISIBILITY;
        }else if (d3 <= 2.0D && !entity.hasEffect(MobEffects.MOVEMENT_SPEED) && this.random.nextFloat() < 0.25F) {
            potion = Potions.SWIFTNESS;
        }else {
            potion = Potions.STRONG_HEALING;
        }
        ThrownPotion thrownpotion = new ThrownPotion(this.level, this);
        thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
        thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
        thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
        if (!this.isSilent()) {
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
        }

        this.level.addFreshEntity(thrownpotion);
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
