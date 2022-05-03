package com.nyaruru.events;

import com.nyaruru.Reference;
import com.nyaruru.capability.CapabilityHandler;
import com.nyaruru.capability.player.PlayerDataManager;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import com.nyaruru.utils.StringUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.List;
import java.util.Random;

public class PlayerEventHandler {
    /**
     * {@link NPlayerEvents#onPlayerLogin(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent)}
     */
    public static void onPlayerLogin(PlayerEntity player) {
        PlayerUtil.getOptManager(player).ifPresent(l -> {
            l.init();

            if(l.getResource(Resources.FIRST_GET_INTO_THE_WORLD) == 0) {
                l.setResource(Resources.POWER, 50);
                l.setResource(Resources.SP, 100);
            }

            l.setResource(Resources.FIRST_GET_INTO_THE_WORLD, 1);
        });
    }


    /**
     * {@link NPlayerEvents#onPlayerLogout(net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent)}
     */
    public static void onPlayerLogout(PlayerEntity player) {
    }

    /**
     * {@link NPlayerEvents#tickPlayer(TickEvent.PlayerTickEvent)}
     */
    public static void HandleCatGazeSkill(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.WEAKNESS, 2, 0));
        float f2 = 32.0F;
        int k1 = MathHelper.floor(player.getX() - (double)f2 - 1.0D);
        int l1 = MathHelper.floor(player.getX() + (double)f2 + 1.0D);
        int i2 = MathHelper.floor(player.getY() - (double)f2 - 1.0D);
        int i1 = MathHelper.floor(player.getY() + (double)f2 + 1.0D);
        int j2 = MathHelper.floor(player.getZ() - (double)f2 - 1.0D);
        int j1 = MathHelper.floor(player.getZ() + (double)f2 + 1.0D);
        List<Entity> list = player.level.getEntities(player, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
        for(int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            if(entity != player && (entity instanceof LivingEntity)){
                ((LivingEntity) entity).addEffect(new EffectInstance(Effects.SLOW_FALLING, 40, 0));
                ((LivingEntity) entity).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 0));
            }
        }
    }

    /**
     * {@link NPlayerEvents#onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone)}
     */
    public static void clonePlayerData(PlayerEntity oldPlayer, PlayerEntity newPlayer, boolean died) {
        oldPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
            newPlayer.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(r -> {
                r.getPlayerData().cloneFromExistingPlayerData(l.getPlayerData(), died);
            });
        });
    }

    public static void onPlayerKillEntity(PlayerEntity player, LivingEntity living) {
        int add_value = new Random().nextInt(7) + 6;
        PlayerUtil.getOptManager(player).ifPresent(l -> {
            if(add_value + l.getResource(Resources.POWER) <= 100)
                l.addResource(Resources.ADDING_POWER, add_value);
            else
                l.setResource(Resources.ADDING_POWER, 100 - l.getResource(Resources.POWER));
        });
    }
}