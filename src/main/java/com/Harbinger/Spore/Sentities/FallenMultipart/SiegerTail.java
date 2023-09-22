package com.Harbinger.Spore.Sentities.FallenMultipart;

import com.Harbinger.Spore.Core.SConfig;
import com.Harbinger.Spore.Sentities.BaseEntities.FallenMultipartEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class SiegerTail extends FallenMultipartEntity {
    public SiegerTail(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, (SConfig.SERVER.sieger_hp.get()/4) * SConfig.SERVER.global_health.get())
                .add(Attributes.ARMOR, (SConfig.SERVER.sieger_armor.get()/4) * SConfig.SERVER.global_armor.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

}
