package com.Harbinger.Spore.ExtremelySusThings;

import net.minecraft.world.damagesource.DamageSource;

public class CoolDamageSources extends DamageSource {
    public CoolDamageSources(String value) {
        super(value);
    }
    public static final DamageSource MYCELIUM_OVERTAKE = new DamageSource("mycelium.overtake").bypassArmor();
    public static final DamageSource CORROSION = new DamageSource("corrosion").bypassArmor();


}
