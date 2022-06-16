package io.lemonjuice.nyaruru.network.toclient;

import io.lemonjuice.nyaruru.NyaruruFishyFight;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BowknotHandlerPacket {
    private static double x;
    private static double y;
    private static double z;

    public BowknotHandlerPacket(double x1, double y1, double z1) {
        x = x1;
        y = y1;
        z = z1;
    }

    public BowknotHandlerPacket(PacketBuffer buffer) {
    }

    public void encode(PacketBuffer buffer) {
    }

    public static class Handler {
        public static void onMessage(BowknotHandlerPacket message, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                Random random = new Random();
                for(int i = 0; i < 15; i++)
                    NyaruruFishyFight.PROXY.getPlayer().level.addParticle(ParticleTypes.CLOUD, x + random.nextDouble() - 0.5, y + 0.3, z + random.nextDouble() - 0.5, 0.0, 0.0, 0.0);
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
