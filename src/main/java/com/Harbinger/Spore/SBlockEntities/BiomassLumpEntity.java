package com.Harbinger.Spore.SBlockEntities;

import com.Harbinger.Spore.Core.SblockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BiomassLumpEntity extends LivingStructureBlocks {
    public BiomassLumpEntity(BlockPos pos, BlockState state) {
        super(SblockEntities.BIOMASS_LUMP.get(), pos, state);
    }

}
