package com.Harbinger.Spore.Sblocks;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Sblocks;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Core.Ssounds;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class HangingPlantBub extends HangingPlant{


    @Override
    public void entityInside(BlockState state, Level level, BlockPos blockpos, Entity entity) {
        if (!level.isClientSide && !(entity instanceof Infected || entity instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(entity.getEncodeId()) || SConfig.SERVER.mycelium.get().contains(entity.getEncodeId()))) {
            BlockState block2 = Sblocks.BLOOM_G.get().defaultBlockState();
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(level, blockpos.getX()+0.4, blockpos.getY(), blockpos.getZ()+0.4);
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(), 100, 2));
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float) areaeffectcloud.getDuration());
            if (state.getBlock().getStateDefinition().getProperty("hanging") instanceof BooleanProperty property) {
                if (state.getValue(property)){
                level.setBlock(blockpos, block2.setValue(property, true), 3);
                }else {level.setBlock(blockpos, block2, 3);}}
            level.addFreshEntity(areaeffectcloud);
            level.playSound(null,blockpos, Ssounds.FUNGAL_BURST.get(), SoundSource.BLOCKS,1,1);
        }
    }
}
