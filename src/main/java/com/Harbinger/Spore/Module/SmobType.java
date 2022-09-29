package com.Harbinger.Spore.Module;

import com.Harbinger.Spore.Spore;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spore.MODID)
public class SmobType extends MobType {
    public static final MobType INFECTED = new MobType();
}
