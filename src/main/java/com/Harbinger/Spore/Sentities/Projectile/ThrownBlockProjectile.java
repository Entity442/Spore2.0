package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.Core.Sentities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class ThrownBlockProjectile extends Projectile {
    private Predicate<LivingEntity> victim = livingEntity -> {return true;};
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(ThrownBlockProjectile.class, EntityDataSerializers.FLOAT);
    private BlockState STATE = Blocks.GRASS_BLOCK.defaultBlockState();

    public ThrownBlockProjectile(Level level) {
        super(Sentities.THROWN_BLOCK.get(), level);
    }
    public ThrownBlockProjectile(Level level,LivingEntity livingEntity,Float damage,BlockState state,Predicate<LivingEntity> livingEntityPredicate) {
        super(Sentities.THROWN_BLOCK.get(), level);
        setOwner(livingEntity);
        entityData.set(DAMAGE,damage);
        STATE = state;
        victim = livingEntityPredicate;
    }
    public BlockState state(){
        return STATE;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DAMAGE,5f);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DAMAGE,tag.getFloat("damage"));
        STATE = NbtUtils.readBlockState(tag.getCompound("state"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("damage",this.entityData.get(DAMAGE));
        tag.put("state", NbtUtils.writeBlockState(STATE));
    }
    @Override
    protected boolean canHitEntity(Entity entity) {
        return entity != getOwner() || (entity instanceof LivingEntity livingEntity && victim.test(livingEntity));
    }
    @Override
    public void tick() {
        super.tick();
        if (this.tickCount >= 300) {
            this.remove(RemovalReason.DISCARDED);
            FallingBlockEntity.fall(level,new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()),STATE);
        }
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        Vec3 vec3 = this.getDeltaMovement().add(0,-0.1,0);
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.setPos(d0, d1, d2);

        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!level.isClientSide && result.getEntity() instanceof LivingEntity livingEntity){
            BlockPos pos = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
            DamageSource damageSource = this.getOwner() instanceof LivingEntity livingEntity1 ? DamageSource.mobAttack(livingEntity1) : DamageSource.GENERIC;
            livingEntity.hurt(damageSource,entityData.get(DAMAGE)*STATE.getDestroySpeed(level,pos));
            FallingBlockEntity.fall(level,pos,STATE);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos pos = result.getBlockPos().relative(result.getDirection()).above();
        FallingBlockEntity.fall(level,pos,STATE);
        this.discard();
    }
}
