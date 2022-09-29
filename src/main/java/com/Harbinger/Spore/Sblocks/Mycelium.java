package com.Harbinger.Spore.Sblocks;

import com.Harbinger.Spore.Core.Seffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class Mycelium extends MultifaceBlock {
    public Mycelium() {
        super(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion());
    }



    @Override
    public MultifaceSpreader getSpreader() {
        return null;
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity _entity)
            _entity.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(), 600, 1));
        super.entityInside(blockState, level, pos, entity);
    }
}
