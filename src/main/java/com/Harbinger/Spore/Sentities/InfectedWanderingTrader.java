package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;

public class InfectedWanderingTrader extends Infected {
    public InfectedWanderingTrader(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_INVISIBILITY), SoundEvents.WANDERING_TRADER_DRINK_POTION, (p_35882_) -> {
            return !this.isInvisible() && isAggressive() && SConfig.SERVER.inf_van_potion.get();
        }));
        this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING), SoundEvents.WANDERING_TRADER_DRINK_POTION, (p_35882_) -> {
            return this.getHealth() < this.getMaxHealth()/2 && !isAggressive() && SConfig.SERVER.inf_van_potion.get();
        }){
            @Override
            public void start() {
                setHunger(0);
                super.start();
            }
        });
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


    @Override
    public void baseTick() {
        super.baseTick();
        if (entityData.get(EVOLUTION) >= (20 * SConfig.SERVER.evolution_age_human.get()) && this.entityData.get(KILLS) >= SConfig.SERVER.min_kills.get()) {
            this.entityData.set(KILLS,this.entityData.get(KILLS) - SConfig.SERVER.min_kills.get());
            Evolve(this);
        }else{
            if (!isFreazing() && this.entityData.get(KILLS) >= SConfig.SERVER.min_kills.get()) {
                this.entityData.set(EVOLUTION,entityData.get(EVOLUTION) + 1);
            }
        }
    }

    public void Evolve(LivingEntity entity) {
        if (Math.random() < 0.9) {
            Random rand = new Random();
            List<? extends String> ev = SConfig.SERVER.villager_ev.get();
            for (int i = 0; i < 1; ++i) {
                int randomIndex = rand.nextInt(ev.size());
                ResourceLocation randomElement1 = new ResourceLocation(ev.get(randomIndex));
                EntityType<?> randomElement = ForgeRegistries.ENTITY_TYPES.getValue(randomElement1);
                Entity waveentity = randomElement.create(level);
                waveentity.setPos(entity.getX(), entity.getY() + 0.5D, entity.getZ());
                waveentity.setCustomName(entity.getCustomName());
                if (waveentity instanceof Infected infected){infected.setKills(entityData.get(KILLS));}
                level.addFreshEntity(waveentity);
                if (this.level instanceof ServerLevel serverLevel){
                    double x0 = this.getX() - (random.nextFloat() - 0.1) * 0.1D;
                    double y0 = this.getY() + (random.nextFloat() - 0.25) * 0.15D * 5;
                    double z0 = this.getZ() + (random.nextFloat() - 0.1) * 0.1D;
                    serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x0, y0, z0, 2, 0, 0, 0, 1);
                }
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

}
