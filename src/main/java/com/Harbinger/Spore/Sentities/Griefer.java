package com.Harbinger.Spore.Sentities;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.AI.GrieferSwellGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class Griefer extends EvolvedInfected{
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(Griefer.class, EntityDataSerializers.INT);private int oldSwell;
    private int swell;
    private final int maxSwell = 30;
    private final int explosionRadius = SConfig.SERVER.explosion.get();


    public Griefer(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
    }
    public void addAdditionalSaveData(CompoundTag data) {
        super.addAdditionalSaveData(data);

        data.putShort("Fuse", (short)this.maxSwell);
        data.putByte("ExplosionRadius", (byte)this.explosionRadius);
    }

    public void tick() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.explodeGriefer();
            }
        }
        super.tick();
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    public boolean grieferExplosion(){
        return this.swell >= 1;
    }


    public void setSwellDir(int p_32284_) {
        this.entityData.set(DATA_SWELL_DIR, p_32284_);
    }

    public int getSwell(){return swell;}

    private void explodeGriefer() {
        if (!this.level.isClientSide) {
            if (SConfig.SERVER.explosion_on.get()){
            Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            this.dead = true;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius, explosion$blockinteraction);
            this.discard();}
            else {
                Explosion.BlockInteraction explosion$blockinteraction = Explosion.BlockInteraction.NONE;
                this.dead = true;
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius, explosion$blockinteraction);
                this.discard();}
        }

    }








    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new GrieferSwellGoal(this){
            @Override
            public boolean canUse() {
                return getHealth() <= (getMaxHealth()/2);
            }
        });
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal
                (this, Player.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal
                (this, Villager.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal
                (this, IronGolem.class,  true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, true, (en) -> {
            return en instanceof Enemy && !(en instanceof Creeper || en instanceof Infected);
        }));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(6, new RestrictSunGoal(this));


        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ARMOR, SConfig.SERVER.griefer_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.griefer_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.griefer_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3);

    }


}
