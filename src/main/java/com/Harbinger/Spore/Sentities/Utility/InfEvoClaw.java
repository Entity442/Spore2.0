package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.AI.PullGoal;
import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class InfEvoClaw extends UtilityEntity{
    public InfEvoClaw(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }
    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, Player.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, Villager.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>
                (this, IronGolem.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, true, (en) -> {
            return (en instanceof Enemy && !(en instanceof Creeper || en instanceof Infected) && SConfig.SERVER.at_mob.get());
        }));
        this.goalSelector.addGoal(3,new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this ,0 ,false){
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 8.0 + entity.getBbWidth() * entity.getBbWidth();}});
        this.goalSelector.addGoal(1, new PullGoal(this, 64, 4));

        super.registerGoals();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.sla_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.sla_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }

    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return Ssounds.INF_DAMAGE.get();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.get();
    }


    public boolean doHurtTarget(Entity p_32257_) {
        if (super.doHurtTarget(p_32257_)) {
            if (p_32257_ instanceof LivingEntity) {

                ((LivingEntity)p_32257_).addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(),  600, 0), this);

            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        Entity entity = this;
        if (entity.isOnGround()){
            entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0, 1, 0));
        }
    }

    

    public void aiStep() {
        super.aiStep();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level;
        RandomSource randomsource = this.getRandom();
        if (!level.isClientSide) {
            ((ServerLevel)level).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack((world.getBlockState(new BlockPos(x, y - 1, z))).getBlock())), x - 0.2D, y - 0.1D, z - 0.2D, 3,
                    ((double)randomsource.nextFloat() - 0.5D) * 0.08D, ((double)randomsource.nextFloat() - 0.5D) * 0.08D, ((double)randomsource.nextFloat() - 0.5D) * 0.08D, (double)0.15F);
        }
    }
}
