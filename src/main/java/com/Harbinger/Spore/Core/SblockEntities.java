package com.Harbinger.Spore.Core;

import com.Harbinger.Spore.SBlockEntities.ContainerBlockEntity;
import com.Harbinger.Spore.Spore;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SblockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Spore.MODID);


    public static final RegistryObject<BlockEntityType<ContainerBlockEntity>> CONTAINER =
            BLOCK_ENTITIES.register("container", () ->
                    BlockEntityType.Builder.of(ContainerBlockEntity::new,
                            Sblocks.CONTAINER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
