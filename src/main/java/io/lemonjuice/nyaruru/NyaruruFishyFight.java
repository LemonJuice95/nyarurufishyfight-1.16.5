package io.lemonjuice.nyaruru;

import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import io.lemonjuice.nyaruru.capability.CapabilityHandler;
import io.lemonjuice.nyaruru.entities.NEntityRegister;
import io.lemonjuice.nyaruru.items.NItemRegister;
import io.lemonjuice.nyaruru.network.NPacketHandler;
import io.lemonjuice.nyaruru.potion.NEffectRegister;
import io.lemonjuice.nyaruru.proxy.ClientProxy;
import io.lemonjuice.nyaruru.proxy.CommonProxy;
import io.lemonjuice.nyaruru.tileentity.NTileEntityRegister;
import io.lemonjuice.nyaruru.world.biome.NBiomeRegister;
import io.lemonjuice.nyaruru.world.feature.NFeatureRegister;
import io.lemonjuice.nyaruru.world.feature.NStructureFeatures;
import io.lemonjuice.nyaruru.world.structure.NStructureGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
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


@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NyaruruFishyFight {
    public static final Minecraft MC = Minecraft.getInstance();
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
        SlotTypeMessage.Builder builder$charms = SlotTypePreset.CHARM.getMessageBuilder().size(1).priority(998);
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, builder$charms::build);

        /*SlotTypeMessage.Builder builder$token = new SlotTypeMessage.Builder("token").priority(999).size(1)
                                                .icon(new ResourceLocation(Reference.MODID, "item/slots/empty_token_slot.png"));
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, builder$token::build);*/

        SlotTypeMessage.Builder builder$gems = new SlotTypeMessage.Builder("gemstone").priority(997).size(8)
                .icon(new ResourceLocation(Reference.MODID, "item/slots/empty_gem_slot"));
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, builder$gems::build);

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