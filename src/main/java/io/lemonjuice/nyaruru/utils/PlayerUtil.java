package io.lemonjuice.nyaruru.utils;

import io.lemonjuice.nyaruru.capability.CapabilityHandler;
import io.lemonjuice.nyaruru.capability.player.IPlayerDataCapability;
import io.lemonjuice.nyaruru.capability.player.PlayerDataManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.FakePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Optional;

public class PlayerUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    @Nullable
    public static Optional<PlayerDataManager> getOptManager(PlayerEntity player) {
        return Optional.ofNullable(getManager(player));
    }

    @Nullable
    public static PlayerDataManager getManager(PlayerEntity player) {
        if(isValidPlayer(player)) {
            final IPlayerDataCapability cap = player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).orElse(null);
            return cap.getPlayerData();
        }
        return null;
    }

    public static boolean isValidPlayer(PlayerEntity player) {
        return player != null && ! (player instanceof FakePlayer);
    }

    public static int getResource(PlayerEntity player, Resources res) {
        final PlayerDataManager manager = getManager(player);
        return manager.getResource(res);
    }

    public static void setResource(PlayerEntity player, Resources res, int num) {
        getOptManager(player).ifPresent(m -> {
            m.setResource(res, num);
        });
    }
}
