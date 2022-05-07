package com.nyaruru;

import com.nyaruru.blocks.NBlockRegister;
import com.nyaruru.capability.CapabilityHandler;
import com.nyaruru.entities.NEntityRegister;
import com.nyaruru.items.NItemRegister;
import com.nyaruru.network.NPacketHandler;
import com.nyaruru.potion.NEffectRegister;
import com.nyaruru.proxy.ClientProxy;
import com.nyaruru.proxy.CommonProxy;
import com.nyaruru.tileentity.NTileEntityRegister;
import com.nyaruru.world.biome.NBiomeRegister;
import com.nyaruru.world.feature.NFeatureRegister;
import com.nyaruru.world.feature.NStructureFeatures;
import com.nyaruru.world.structure.NStructureGenerator;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.ArrayList;
import java.util.List;


@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NyaruruFishyFight {
    public static final Logger LOGGER = LogManager.getLogger();
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public NyaruruFishyFight(){
        NItemRegister.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        NEntityRegister.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        NEffectRegister.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        NBlockRegister.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        NBiomeRegister.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        NStructureFeatures.STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        NFeatureRegister.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        NTileEntityRegister.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, NStructureGenerator::addDimensionalSpacing);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, NBiomeRegister::biomeModification);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

        PROXY.init();
    }

    private void enqueueIMC(final InterModEnqueueEvent ev) {
        SlotTypePreset[] slots = {
//                SlotTypePreset.HEAD, SlotTypePreset.NECKLACE, SlotTypePreset.BACK, SlotTypePreset.BODY, SlotTypePreset.HANDS, SlotTypePreset.RING,
                SlotTypePreset.CHARM
        };
        List<SlotTypeMessage.Builder> builders = new ArrayList<>();
        for (SlotTypePreset slot : slots) {
            SlotTypeMessage.Builder builder = slot.getMessageBuilder();
            if (slot == SlotTypePreset.CHARM) {
                builder.size(10);
            }
            builders.add(builder);
        }
        for (SlotTypeMessage.Builder builder : builders) {
            SlotTypeMessage message = builder.build();
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                    ()->message);
        }
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent ev) {
        NBiomeRegister.registerBiomes(ev);
        CapabilityHandler.registerCapabilities();
        NPacketHandler.init();
        ev.enqueueWork(() -> {
            PROXY.setUp();
        });
    }

    @SubscribeEvent
    public static void setUpComplete(FMLLoadCompleteEvent event) {
        PROXY.postInit();
    }

    @SubscribeEvent
    public static void setUpClient(FMLClientSetupEvent event) {
        PROXY.setUpClient();
    }
}