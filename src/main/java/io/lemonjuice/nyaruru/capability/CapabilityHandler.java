package io.lemonjuice.nyaruru.capability;

import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.capability.player.IPlayerDataCapability;
import io.lemonjuice.nyaruru.capability.player.PlayerDataCapability;
import io.lemonjuice.nyaruru.capability.player.PlayerDataProvider;
import io.lemonjuice.nyaruru.capability.player.PlayerDataStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler {
    @CapabilityInject(IPlayerDataCapability.class)
    public static final Capability<IPlayerDataCapability> PLAYER_DATA_CAPABILITY = null;

    public static void registerCapabilities(){
        CapabilityManager.INSTANCE.register(IPlayerDataCapability.class, new PlayerDataStorage(), PlayerDataCapability::new);
        MinecraftForge.EVENT_BUS.register(CapabilityHandler.class);
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event){
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity){
            event.addCapability(new ResourceLocation(Reference.MODID, "player_data"), new PlayerDataProvider((PlayerEntity) entity));
        }
    }
}
