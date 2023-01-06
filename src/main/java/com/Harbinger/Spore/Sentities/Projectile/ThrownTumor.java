package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.PlayMessages;

public class ThrownTumor extends ThrowableItemProjectile {
    public ThrownTumor(EntityType<? extends ThrownTumor> type, Level level) {
        super(type, level);
    }

    public ThrownTumor(Level level, LivingEntity entity) {
        super(Sentities.THROWN_TUMOR.get(), entity, level);
    }

    public ThrownTumor(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(Sentities.THROWN_TUMOR.get(), level);
    }

    @Override
    protected Item getDefaultItem() {
        return Sitems.TUMOR.get();
    }

    public void handleEntityEvent(byte p_37484_) {
        if (p_37484_ == 3) {
            double d0 = 0.08D;

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }

    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Explosion.BlockInteraction explosion$blockinteraction = Explosion.BlockInteraction.NONE;
        hitResult.getEntity().level.explode(this, this.getX(), this.getY(), this.getZ(), (float)2, explosion$blockinteraction);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        Explosion.BlockInteraction explosion$blockinteraction = Explosion.BlockInteraction.NONE;
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)2, explosion$blockinteraction);
    }


}
