package io.lemonjuice.nyaruru.network;

import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.network.toclient.BowknotHandlerPacket;
import io.lemonjuice.nyaruru.network.toclient.PlayerStatsPacketToClient;
import io.lemonjuice.nyaruru.network.toserver.PlayerStatsPacketToServer;
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
        CHANNEL.registerMessage(id ++, BowknotHandlerPacket.class, BowknotHandlerPacket::encode, BowknotHandlerPacket::new, BowknotHandlerPacket.Handler::onMessage);
    }
}
