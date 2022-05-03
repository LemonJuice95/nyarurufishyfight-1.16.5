package com.nyaruru.network.toclient;

import com.nyaruru.NyaruruFishyFight;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class PlayerStatsPacketToClient {
    private static final Logger LOGGER = LogManager.getLogger();
    private int type;
    private int data;

    public PlayerStatsPacketToClient(int x, int y) {
        this.type = x;
        this.data = y;
    }

    public PlayerStatsPacketToClient(PacketBuffer buffer) {
        this.type = buffer.readInt();
        this.data = buffer.readInt();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.type);
        buffer.writeInt(this.data);
    }

    public static class Handler {
        public static void onMessage(PlayerStatsPacketToClient message, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                PlayerUtil.setResource(NyaruruFishyFight.PROXY.getPlayer(), Resources.values()[message.type], message.data);
            });
            ctx.get().setPacketHandled(true);
        }
    }
}