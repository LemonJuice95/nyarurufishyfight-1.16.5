package io.lemonjuice.nyaruru.client;

import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import io.lemonjuice.nyaruru.client.events.NInputEvents;
import io.lemonjuice.nyaruru.client.model.baked.FishBakedModel;
import io.lemonjuice.nyaruru.client.render.entity.misc.BubbleRender;
import io.lemonjuice.nyaruru.client.render.entity.misc.FishCrossSlashRender;
import io.lemonjuice.nyaruru.client.render.entity.monster.ForestMst01Render;
import io.lemonjuice.nyaruru.entities.NEntityRegister;
import io.lemonjuice.nyaruru.entities.monster.ForestMonster01Entity;
import io.lemonjuice.nyaruru.items.NItemRegister;
import io.lemonjuice.nyaruru.items.NSpawnEggBase;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        NSpawnEggBase.initUnaddedEggs();
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

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent ev) {
        ev.put(NEntityRegister.FOREST_MST_01.get(), ForestMonster01Entity.createAttributes().build());
    }

    public static void RegisterRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(NEntityRegister.FISH_CROSS_SLASH.get(), FishCrossSlashRender::new);
        RenderingRegistry.registerEntityRenderingHandler(NEntityRegister.BUBBLE.get(), BubbleRender::new);
        RenderingRegistry.registerEntityRenderingHandler(NEntityRegister.FOREST_MST_01.get(), ForestMst01Render::new);
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