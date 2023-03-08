package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class InfectedHuman extends Infected {
    private int ev;

    public InfectedHuman(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);

    }


    @Override
    protected void registerGoals() {


        this.goalSelector.addGoal(1, new CustomMeleeAttackGoal(this, 1.5, false) {
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
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inf_human_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inf_human_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.inf_human_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3);

    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!isFreazing() && this.entityData.get(KILLS) >= SConfig.SERVER.min_kills.get()) {
            this.ev = this.ev + 1;
        }
        if (this.ev >= (20 * SConfig.SERVER.evolution_age_human.get()) && this.entityData.get(KILLS) >= SConfig.SERVER.min_kills.get()) {
            this.entityData.set(KILLS,entityData.get(KILLS) - SConfig.SERVER.min_kills.get());
           Evolve(this);
        }
    }



    public void Evolve(LivingEntity entity) {
        if (Math.random() < 0.9) {
            Random rand = new Random();
            List<? extends String> ev = SConfig.SERVER.human_ev.get();
            for (int i = 0; i < 1; ++i) {
                int randomIndex = rand.nextInt(ev.size());
                ResourceLocation randomElement1 = new ResourceLocation(ev.get(randomIndex));
                EntityType<?> randomElement = ForgeRegistries.ENTITY_TYPES.getValue(randomElement1);
                Entity waveentity = randomElement.create(level);
                waveentity.setPos(entity.getX(), entity.getY() + 0.5D, entity.getZ());
                waveentity.setCustomName(entity.getCustomName());
                if (waveentity instanceof Infected infected){infected.setKills(entityData.get(KILLS));}
                level.addFreshEntity(waveentity);
                entity.discard();
            }
        }else {
            Scamper scamper = new Scamper(Sentities.SCAMPER.get(), level);
            scamper.setPos(entity.getX(), entity.getY() + 0.5D, entity.getZ());
            scamper.setCustomName(entity.getCustomName());
            scamper.setKills(entityData.get(KILLS));
            level.addFreshEntity(scamper);
            entity.discard();
        }
    }

    public boolean evolution() {
        int i = SConfig.SERVER.evolution_age_human.get() * 20;
        return ev >= (i / 4) * 3;
    }


    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33282_, DifficultyInstance p_33283_, MobSpawnType p_33284_, @Nullable SpawnGroupData p_33285_, @Nullable CompoundTag p_33286_) {
        RandomSource randomsource = p_33282_.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, p_33283_);
        this.populateDefaultEquipmentEnchantments(randomsource, p_33283_);
        return super.finalizeSpawn(p_33282_, p_33283_, p_33284_, p_33285_, p_33286_);
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
