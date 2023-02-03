package com.Harbinger.Spore.Sentities.Utility;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.dimension.DimensionType;

public class UtilityEntity extends PathfinderMob {
    protected UtilityEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }


    public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_219010_, BlockPos p_219011_, RandomSource p_219012_) {
        if (p_219010_.getBrightness(LightLayer.SKY, p_219011_) > p_219012_.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = p_219010_.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit();
            if (i < 15 && p_219010_.getBrightness(LightLayer.BLOCK, p_219011_) > i) {
                return false;
            } else {
                int j = p_219010_.getLevel().isThundering() ? p_219010_.getMaxLocalRawBrightness(p_219011_, 10) : p_219010_.getMaxLocalRawBrightness(p_219011_);
                return j <= dimensiontype.monsterSpawnLightTest().sample(p_219012_);
            }
        }
    }
}
