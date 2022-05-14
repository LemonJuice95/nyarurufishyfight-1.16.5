package com.nyaruru.client;

import com.nyaruru.Reference;
import com.nyaruru.blocks.NBlockRegister;
import com.nyaruru.client.events.NInputEvents;
import com.nyaruru.client.model.baked.FishBakedModel;
import com.nyaruru.client.render.entity.misc.BubbleRender;
import com.nyaruru.client.render.entity.misc.FishCrossSlashRender;
import com.nyaruru.entities.NEntityRegister;
import com.nyaruru.entities.misc.FishCrossSlashEntity;
import com.nyaruru.items.NItemRegister;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class ClientRegister {
    private static final List<RegistryObject<Item>> FISHES = Arrays.asList(
            NItemRegister.DEEP_SEA_TUNA,
            NItemRegister.HYNERIA_LINDAE,
            NItemRegister.ESOX_AMERICANUS,
            NItemRegister.EUSTHENOPTERON
    );

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent ev) {
        RegisterKeyBindings();
        RegisterRenderers();
        RegisterRenderTypes();
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent ev) {
        Map<ResourceLocation, IBakedModel> modelRegistry = ev.getModelRegistry();
        ModelResourceLocation location;
        for (RegistryObject<Item> fish : FISHES){
            location = new ModelResourceLocation(fish.get().getRegistryName(), "inventory");
            IBakedModel existingModel = modelRegistry.get(location);
            if (existingModel == null) {
                throw new RuntimeException("Did not find original model in registry");
            } else if (existingModel instanceof FishBakedModel) {
                throw new RuntimeException("Tried to replace model twice");
            } else {
                FishBakedModel fishBakedModel = new FishBakedModel(existingModel);
                ev.getModelRegistry().put(location, fishBakedModel);
            }
        }
    }

    public static void RegisterRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(NEntityRegister.FISH_CROSS_SLASH.get(),
                (EntityRendererManager manager) -> {
                    return new FishCrossSlashRender(manager);
                });
        RenderingRegistry.registerEntityRenderingHandler(NEntityRegister.BUBBLE.get(),
                (EntityRendererManager manager) -> {
                    return new BubbleRender(manager);
                });
    }

    public static void RegisterKeyBindings() {
        ClientRegistry.registerKeyBinding(NInputEvents.SPRINT_KEY);
        ClientRegistry.registerKeyBinding(NInputEvents.SPRINT_BACK_KEY);
        ClientRegistry.registerKeyBinding(NInputEvents.FISH_SKILL_KEY);
    }

    public static void RegisterRenderTypes() {
        RenderTypeLookup.setRenderLayer(NBlockRegister.THORNS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(NBlockRegister.CRYSTAL_CLUSTER.get(), RenderType.cutout());
    }

}