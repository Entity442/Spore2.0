package com.Harbinger.Spore.Screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class InvisibleButton extends Button {


    public InvisibleButton(int p_93721_, int p_93722_, int p_93723_, int p_93724_, Component p_93725_, OnPress p_93726_) {
        super(p_93721_, p_93722_, p_93723_, p_93724_, p_93725_, p_93726_);
    }

    @Override
    public void render(PoseStack p_93657_, int p_93658_, int p_93659_, float p_93660_) {

    }
}
