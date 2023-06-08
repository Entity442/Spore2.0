package com.Harbinger.Spore.Sentities.BaseEntities;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;

public class UtilityEntity extends PathfinderMob {
    protected UtilityEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (ModList.get().isLoaded("ad_astra") && source == DamageSource.DROWN){
            return false;
        }
        return super.hurt(source, amount);
    }
}
