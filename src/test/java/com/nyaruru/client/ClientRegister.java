package com.nyaruru.client;

import com.nyaruru.Reference;
import com.nyaruru.blocks.NBlockRegister;
import com.nyaruru.client.events.NInputEvents;
import com.nyaruru.client.render.entity.misc.FishCrossSlashRender;
import com.nyaruru.entities.NEntityRegister;
import com.nyaruru.entities.misc.FishCrossSlashEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import sun.java2d.loops.RenderLoops;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class ClientRegister {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent ev) {
        RegisterKeyBindings();
        RegisterRenderers();
        RegisterRenderTypes();
    }

    public static void RegisterRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(NEntityRegister.FISH_CROSS_SLASH.get(),
                (EntityRendererManager manager) -> {
                    return new FishCrossSlashRender(manager);
                });
    }

    public static void RegisterKeyBindings() {
        ClientRegistry.registerKeyBinding(NInputEvents.SPRINT_KEY);
        ClientRegistry.registerKeyBinding(NInputEvents.SPRINT_BACK_KEY);
    }

    public static void RegisterRenderTypes() {
        RenderTypeLookup.setRenderLayer(NBlockRegister.THORNS.get(), RenderType.cutout());
    }
}