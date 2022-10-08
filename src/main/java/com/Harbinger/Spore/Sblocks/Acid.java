package com.Harbinger.Spore.Sblocks;

import com.Harbinger.Spore.Core.Seffects;
import com.Harbinger.Spore.Sentities.Infected;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Acid extends FallingBlock {
    public Acid() {
        super(BlockBehaviour.Properties.of(Material.PLANT).noOcclusion());
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(world, pos);
        {
            return box(0, 0, 0, 16, 1, 16).move(offset.x, offset.y, offset.z);
        }
    }
    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 1);
    }


    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity _entity && !(entity instanceof Infected))
            _entity.addEffect(new MobEffectInstance(Seffects.CORROSION.get(), 100, 0));
        super.entityInside(blockState, level, pos, entity);
    }
}
