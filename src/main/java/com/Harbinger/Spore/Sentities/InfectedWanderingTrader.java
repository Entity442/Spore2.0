package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.FollowOthersGoal;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class InfectedWanderingTrader extends Infected{
    public InfectedWanderingTrader(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_INVISIBILITY), SoundEvents.WANDERING_TRADER_DRINK_POTION, (p_35882_) -> {
            return !this.isInvisible() && isAggressive();
        }));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING), SoundEvents.WANDERING_TRADER_DRINK_POTION, (p_35882_) -> {
            return this.getHealth() < this.getMaxHealth()/2 && !isAggressive();
        }));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 3.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });

        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        super.registerGoals();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_van_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inf_van_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.inf_van_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 24)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3);

    }
    protected SoundEvent getAmbientSound() {
        return Ssounds.INF_GROWL.get();
    }

    protected SoundEvent getHurtSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void playStepSound() {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
}
