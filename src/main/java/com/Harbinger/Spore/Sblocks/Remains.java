package com.Harbinger.Spore.Sblocks;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Sentities.Infected;
import com.Harbinger.Spore.Sentities.Utility.UtilityEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Remains extends Block {
    public Remains() {
        super(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.SLIME_BLOCK).noOcclusion().strength(3f));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(world, pos);
        return box(0, 0, 0, 16, 10, 16).move(offset.x, offset.y, offset.z);
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity && !(entity instanceof Infected || entity instanceof UtilityEntity || SConfig.SERVER.blacklist.get().contains(entity.getEncodeId()) || SConfig.SERVER.mycelium.get().contains(entity.getEncodeId()))) {
            if (!livingEntity.hasEffect(Seffects.MYCELIUM.get())){
                livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM.get(),400,1));
            }
        }
    }
}
