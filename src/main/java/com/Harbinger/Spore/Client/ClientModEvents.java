package com.Harbinger.Spore.Client;

import com.Harbinger.Spore.Client.Models.*;
import com.Harbinger.Spore.Client.Renderers.*;
import com.Harbinger.Spore.Core.Sentities;
import com.Harbinger.Spore.Spore;
import com.Harbinger.Spore.sEvents.SItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Spore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    private ClientModEvents() {
    }
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(InfectedModel.LAYER_LOCATION, InfectedModel::createBodyLayer);
        event.registerLayerDefinition(KnightModel.LAYER_LOCATION, KnightModel::createBodyLayer);
        event.registerLayerDefinition(GrieferModel.LAYER_LOCATION, GrieferModel::createBodyLayer);
        event.registerLayerDefinition(BraionmilModel.LAYER_LOCATION, BraionmilModel::createBodyLayer);
        event.registerLayerDefinition(InfectedVillagerModel.LAYER_LOCATION, InfectedVillagerModel::createBodyLayer);
        event.registerLayerDefinition(InfectedWitchModel.LAYER_LOCATION, InfectedWitchModel::createBodyLayer);

    }




    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Sentities.INFECTED_HUMAN.get(), InfectedHumanRenderer::new);
        event.registerEntityRenderer(Sentities.KNIGHT.get(), KnightRenderer::new);
        event.registerEntityRenderer(Sentities.GRIEFER.get(), GrieferRenderer::new);
        event.registerEntityRenderer(Sentities.BRAIOMIL.get(), BraioRenderer::new);
        event.registerEntityRenderer(Sentities.INF_VILLAGER.get(), InfectedVillagerRenderer::new);
        event.registerEntityRenderer(Sentities.INF_WITCH.get(), InfectedWitchRenderer::new);
    }


    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {


        SItemProperties.addCustomItemProperties();
    }
}
