package com.nyaruru.network.toclient;

import com.nyaruru.NyaruruFishyFight;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
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
    public BowknotHandlerPacket() {
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
                    NyaruruFishyFight.PROXY.getPlayer().level.addParticle(ParticleTypes.CLOUD, NyaruruFishyFight.PROXY.getPlayer().getX() + random.nextDouble(), NyaruruFishyFight.PROXY.getPlayer().getY(), NyaruruFishyFight.PROXY.getPlayer().getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
                float f2 = 4.0F;
                int k1 = MathHelper.floor(NyaruruFishyFight.PROXY.getPlayer().getX() - (double)f2 - 1.0D);
                int l1 = MathHelper.floor(NyaruruFishyFight.PROXY.getPlayer().getX() + (double)f2 + 1.0D);
                int i2 = MathHelper.floor(NyaruruFishyFight.PROXY.getPlayer().getY() - (double)f2 - 1.0D);
                int i1 = MathHelper.floor(NyaruruFishyFight.PROXY.getPlayer().getY() + (double)f2 + 1.0D);
                int j2 = MathHelper.floor(NyaruruFishyFight.PROXY.getPlayer().getZ() - (double)f2 - 1.0D);
                int j1 = MathHelper.floor(NyaruruFishyFight.PROXY.getPlayer().getZ() + (double)f2 + 1.0D);
                List<Entity> list = NyaruruFishyFight.PROXY.getPlayer().level.getEntities(NyaruruFishyFight.PROXY.getPlayer(), new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
                for (Entity entity : list) {
                    if (entity != NyaruruFishyFight.PROXY.getPlayer()) {
                        entity.push(0.0D, 0.4D, 0.0D);
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
