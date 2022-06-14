package io.lemonjuice.nyaruru.network.toserver;

import io.lemonjuice.nyaruru.utils.PlayerUtil;
import io.lemonjuice.nyaruru.utils.Resources;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class PlayerStatsPacketToServer {
    private static final Logger LOGGER = LogManager.getLogger();
    private int type;
    private int data;

    public PlayerStatsPacketToServer(int x, int y) {
        this.type = x;
        this.data = y;
    }

    public PlayerStatsPacketToServer(PacketBuffer buffer) {
        this.type = buffer.readInt();
        this.data = buffer.readInt();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.type);
        buffer.writeInt(this.data);
    }

    public static class Handler {
        public static void onMessage(PlayerStatsPacketToServer message, Supplier<NetworkEvent.Context> ctx) {
            final ServerPlayerEntity player = ctx.get().getSender();
            ctx.get().enqueueWork(() -> {
                PlayerUtil.getOptManager(player).ifPresent(l -> {
                    l.setResource(Resources.values()[message.type], message.data);
                });
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
