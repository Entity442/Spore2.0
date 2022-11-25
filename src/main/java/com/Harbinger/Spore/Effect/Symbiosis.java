package com.Harbinger.Spore.Effect;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.extensions.IForgeMobEffect;

import java.util.ArrayList;
import java.util.List;

public class Symbiosis extends MobEffect implements IForgeMobEffect {
    public Symbiosis() {
        super(MobEffectCategory.BENEFICIAL, 8412043);
    }
    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(ItemStack.EMPTY);
        return ret;
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.horizontalCollision && Screen.hasShiftDown()) {
            Vec3 initialVec = pLivingEntity.getDeltaMovement();
            Vec3 climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
            pLivingEntity.setDeltaMovement(climbVec.x * 0.91D,
                    climbVec.y * 0.98D, climbVec.z * 0.91D);


        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }




}
