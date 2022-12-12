package com.Harbinger.Spore.Sentities.Utility;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.entity.PartEntity;

public class InfectedMultipart extends PartEntity {
    public final Mob parentMob;
    public final String name;
    private final EntityDimensions size;
    public InfectedMultipart(Mob parent, String name, float s , float s2) {
        super(parent);
        this.size = EntityDimensions.scalable(s, s2);
        this.refreshDimensions();
        this.parentMob = parent;
        this.name = name;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    public boolean isPickable() {
        return true;
    }

    public boolean is(Entity entity) {
        return this == entity || this.parentMob == entity;
    }

    public Packet<?> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    public EntityDimensions getDimensions(Pose p_31023_) {
        return this.size;
    }

    public boolean shouldBeSaved() {
        return false;
    }
}
