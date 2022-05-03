package com.nyaruru.network;

import com.nyaruru.Reference;
import com.nyaruru.network.toclient.PlayerStatsPacketToClient;
import com.nyaruru.network.toserver.PlayerStatsPacketToServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NPacketHandler {
    private static final ResourceLocation CHANNEL_NAME = new ResourceLocation(Reference.MODID + ":networking");
    private static final String PROTOCOL_VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            CHANNEL_NAME,
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        int id = 0;
        CHANNEL.registerMessage(id ++, PlayerStatsPacketToClient.class, PlayerStatsPacketToClient::encode, PlayerStatsPacketToClient::new, PlayerStatsPacketToClient.Handler::onMessage);
        CHANNEL.registerMessage(id ++, PlayerStatsPacketToServer.class, PlayerStatsPacketToServer::encode, PlayerStatsPacketToServer::new, PlayerStatsPacketToServer.Handler::onMessage);
    }
}
