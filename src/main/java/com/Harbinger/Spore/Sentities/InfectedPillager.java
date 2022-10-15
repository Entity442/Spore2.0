package com.Harbinger.Spore.Sentities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class InfectedPillager extends Infected implements CrossbowAttackMob , InventoryCarrier {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(InfectedPillager.class, EntityDataSerializers.BOOLEAN);
    private static final int INVENTORY_SIZE = 5;
    private static final int SLOT_OFFSET = 300;
    private static final float CROSSBOW_POWER = 1.6F;
    private final SimpleContainer inventory = new SimpleContainer(5);

    protected InfectedPillager(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public void setChargingCrossbow(boolean p_32339_) {

    }

    @Override
    public void shootCrossbowProjectile(LivingEntity p_32328_, ItemStack p_32329_, Projectile p_32330_, float p_32331_) {

    }

    @Override
    public void onCrossbowAttackPerformed() {

    }

    @Override
    public void performRangedAttack(LivingEntity p_33317_, float p_33318_) {

    }

    @Override
    public SimpleContainer getInventory() {
        return null;
    }
}
