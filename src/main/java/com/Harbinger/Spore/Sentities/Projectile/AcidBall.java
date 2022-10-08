package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Core.Sitems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class AcidBall extends AbstractArrow implements ItemSupplier {
    public AcidBall(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public AcidBall(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(Sentities.ACID_BALL.get(), level);
    }

    public AcidBall(EntityType<AcidBall> acidBallEntityType, LivingEntity entity, Level level) {
        super(acidBallEntityType , entity, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }


    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return new ItemStack(Sitems.ACID_BALL.get());
    }


    @Override
    public void tick() {
        super.tick();
        if (this.inGround)
            this.discard();
    }

    public static AcidBall shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
        AcidBall entityarrow = new AcidBall(Sentities.ACID_BALL.get(), entity, world);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        return entityarrow;
    }

    public static AcidBall shoot(LivingEntity entity, LivingEntity target) {
        AcidBall entityarrow = new AcidBall(Sentities.ACID_BALL.get(), entity, entity.level);
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 2;
        double dz = target.getZ() - entity.getZ();
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
        entityarrow.setBaseDamage(SConfig.SERVER.spit_damage_l.get() * SConfig.SERVER.global_damage.get());
        entityarrow.setKnockback(1);
        entity.level.addFreshEntity(entityarrow);

        return entityarrow;
    }

}
