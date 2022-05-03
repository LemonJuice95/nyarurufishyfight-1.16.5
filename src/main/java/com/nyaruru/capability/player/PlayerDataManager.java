package com.nyaruru.capability.player;

import com.nyaruru.events.PlayerEventHandler;
import com.nyaruru.network.NPacketHandler;
import com.nyaruru.network.toclient.PlayerStatsPacketToClient;
import com.nyaruru.network.toserver.PlayerStatsPacketToServer;
import com.nyaruru.utils.Resources;
import com.nyaruru.utils.StringUtil;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PlayerDataManager {
    private final PlayerEntity player;
    private final HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
    private static final Logger LOGGER = LogManager.getLogger();
    public String lastVersion = StringUtil.INIT_VERSION;

    public PlayerDataManager(PlayerEntity player) {
        this.player = player;
        {
            for(Resources res : Resources.values()) {
                resources.put(res, Resources.getInitialValue(res));
            }
        }
    }

    public void loadFromNBT(CompoundNBT baseTag) {
        {
            if (baseTag.contains("player_stats")) {
                CompoundNBT statsTag = baseTag.getCompound("player_stats");
                for (Resources res : Resources.values()) {
                    if (statsTag.contains("player_" + res.toString())) {
                        this.resources.put(res, statsTag.getInt("player_" + res.toString()));
                    }
                }
            }
            if (baseTag.contains("player_resources")) {
                CompoundNBT statsTag = baseTag.getCompound("player_resources");
                for (Resources res : Resources.values()) {
                    if (statsTag.contains(res.toString().toLowerCase())) {
                        this.resources.put(res, statsTag.getInt(res.toString().toLowerCase()));
                    }
                }
            }
        }
    }

    public void setResource(Resources res, int num){
        resources.put(res, num);
        this.addResource(res, 0);
    }

    /**
     * write {@link PlayerDataStorage#writeNBT(net.minecraftforge.common.capabilities.Capability, IPlayerDataCapability, net.minecraft.util.Direction)}.
     */
    public CompoundNBT saveToNBT() {
        CompoundNBT baseTag = new CompoundNBT();
        {
            CompoundNBT statsNBT = new CompoundNBT();
            for (Resources res : Resources.values()) {
                statsNBT.putInt(res.toString().toLowerCase(), resources.get(res));
            }
            baseTag.put("player_resources", statsNBT);
        }
        return baseTag;
    }

    public void cloneFromExistingPlayerData(PlayerDataManager data, boolean died) {
        this.loadFromNBT(data.saveToNBT());
    }

    /**
     * run when player join world.
     * {@link PlayerEventHandler#onPlayerLogin(PlayerEntity)}
     */

    public void init() {
        this.syncBounds();
        this.syncToClient();
    }

    /**
     * sync data to client side.
     * {@link #init()}
     */
    public void syncToClient() {
        {// player resources.
            for(Resources res : Resources.values()) {
                this.sendResourcePacket(player, res);
            }
        }
    }

    private void syncBounds() {
        {// player resources.
            for(Resources res : Resources.values()) {
                this.addResource(res, 0);
            }
        }
    }

    public void addResource(Resources res, int num) {
        int now = resources.get(res);
        final int old = now;

        if(res == Resources.ADDING_MONEY)
            resources.put(Resources.ADDING_MONEY2, now + num);
        
        resources.put(res, now + num);
        this.sendResourcePacket(player, res);
    }


    private void sendResourcePacket(PlayerEntity player, Resources res){
        if (player instanceof ServerPlayerEntity) {
            NPacketHandler.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> {
                        return (ServerPlayerEntity) player;
                    }),
                    new PlayerStatsPacketToClient(res.ordinal(), resources.get(res))
            );
        }
    }

    public int getResource(Resources res){
        return this.resources.get(res);
    }
}
