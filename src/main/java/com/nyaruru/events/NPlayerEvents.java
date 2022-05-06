package com.nyaruru.events;

import com.nyaruru.NyaruruFishyFight;
import com.nyaruru.Reference;
import com.nyaruru.capability.CapabilityHandler;
import com.nyaruru.entities.api.IHasOwner;
import com.nyaruru.entities.api.INyaruruNPC;
import com.nyaruru.items.fishes.ItemEsoxAmericanus;
import com.nyaruru.network.NPacketHandler;
import com.nyaruru.network.toserver.PlayerStatsPacketToServer;
import com.nyaruru.proxy.ClientProxy;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.system.libc.LibCStdio;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class NPlayerEvents {

    /*
    * I hate my trash codes
    */
    @SubscribeEvent
    public static void tickPlayer(TickEvent.PlayerTickEvent ev) {
        if(! ev.player.level.isClientSide) {
            PlayerUtil.getOptManager(ev.player).ifPresent(l -> {
                if(l.getResource(Resources.SP) < Resources.SP.max && !(l.getResource(Resources.SPRINT_TICKS) != 0 && ev.player.level.getBlockState(ev.player.blockPosition().below()).getBlock() == Blocks.AIR)) {
                    l.addResource(Resources.SP, 1);
                }
                if(l.getResource(Resources.ADDING_MONEY2) > 0) {
                    if(l.getResource(Resources.ADDING_MONEY) < 30){
                        l.addResource(Resources.ADDING_MONEY2, -1);
                        l.addResource(Resources.MONEY, 1);
                    } else {
                        int speed = l.getResource(Resources.ADDING_MONEY) / 30;
                        if (l.getResource(Resources.ADDING_MONEY2) >= speed) {
                            l.addResource(Resources.ADDING_MONEY2, -speed);
                            l.addResource(Resources.MONEY, speed);
                        } else {
                            l.addResource(Resources.MONEY, l.getResource(Resources.ADDING_MONEY2));
                            l.setResource(Resources.ADDING_MONEY2, 0);
                        }
                    }
                } else {
                    l.setResource(Resources.ADDING_MONEY, 0);
                }
                if(l.getResource(Resources.SPRINT_TICKS) > 0) {
                    l.addResource(Resources.SPRINT_TICKS, -1);
                    if(ev.player.level.getBlockState(ev.player.blockPosition().below()).getBlock() == Blocks.AIR && l.getResource(Resources.SPRINT_TIMES) > 1)
                        l.addResource(Resources.SP, -12);
                }
                if(l.getResource(Resources.DOUBLE_SLASH_TICK) > 0) {
                    l.addResource(Resources.DOUBLE_SLASH_TICK, -1);
                    l.addResource(Resources.SP, -12);
                }
                if(l.getResource(Resources.ADDING_POWER) > 0 && l.getResource(Resources.POWER) < Resources.POWER.max) {
                    l.addResource(Resources.POWER, 1);
                    l.addResource(Resources.ADDING_POWER, -1);
                }
                if(l.getResource(Resources.ADDING_POWER) <= -10) {
                    l.addResource(Resources.POWER, -10);
                    l.addResource(Resources.ADDING_POWER, 10);
                } else if(l.getResource(Resources.ADDING_POWER) < 0) {
                    l.addResource(Resources.POWER, -1);
                    l.addResource(Resources.ADDING_POWER, 1);
                }
                if(l.getResource(Resources.DRAW_FISH_CHOPPING_TICKS) > 0) {
                    l.addResource(Resources.DRAW_FISH_CHOPPING_TICKS, -1);
                    ev.player.startAutoSpinAttack(1);
                }
                if(l.getResource(Resources.CAT_GAZE_FLAG) == 1) {
                    if(l.getResource(Resources.POWER) > 0) {
                        if(l.getResource(Resources.GAZE_TICK) >= 4) {
                            l.setResource(Resources.GAZE_TICK, 0);
                            l.addResource(Resources.POWER, -1);
                        } else {
                            l.addResource(Resources.GAZE_TICK, 1);
                        }
                        PlayerEventHandler.HandleCatGazeSkill(ev.player);
                        if(l.getResource(Resources.GAZE_OVERLAY_SCALE) > 0) {
                            l.addResource(Resources.GAZE_OVERLAY_SCALE, -1);
                        }
                    } else {
                        l.setResource(Resources.CAT_GAZE_FLAG, 0);
                    }
                } else if(l.getResource(Resources.GAZE_OVERLAY_SCALE) < 10) {
                    l.addResource(Resources.GAZE_OVERLAY_SCALE, 1);
                } if(l.getResource(Resources.FLYING_FLAG) == 1) {
                    ev.player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 3, 1));
                }
                if(l.getResource(Resources.HAS_SHIELD) == 1 && l.getResource(Resources.SHIELD_TICK) >= 1200) {
                    ev.player.addEffect(new EffectInstance(Effects.GLOWING, 2, 0));
                }
            });
        }
        NyaruruFishyFight.PROXY.climbUp();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent ev) {
        if (! ev.getPlayer().level.isClientSide) {
            PlayerEventHandler.onPlayerLogin(ev.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent ev) {
        if (! ev.getPlayer().level.isClientSide) {
            PlayerEventHandler.onPlayerLogout(ev.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone ev) {
        PlayerEventHandler.clonePlayerData(ev.getOriginal(), ev.getPlayer(), ev.isWasDeath());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent ev) {
        if(! ev.getPlayer().level.isClientSide) {
            PlayerUtil.getOptManager(ev.getPlayer()).ifPresent(l -> l.syncToClient());
        }
    }

    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent ev) {
        if(! ev.getPlayer().level.isClientSide) {
            if(PlayerUtil.getResource(ev.getPlayer(), Resources.SPRINT_TICKS) > 0 && PlayerUtil.getResource(ev.getPlayer(), Resources.RED_JADE_FISH_EYE) == 1 && PlayerUtil.getResource(ev.getPlayer(), Resources.SP) > 96) {
                PlayerUtil.setResource(ev.getPlayer(), Resources.ADDING_POWER, 8);
                ev.getTarget().hurt(DamageSource.playerAttack(ev.getPlayer()),ev.getPlayer().getItemInHand(Hand.MAIN_HAND).getMaxDamage());
            }
        } else {
            Entity target = ev.getTarget();
            if(ev.getPlayer().xRot >= 70 && !(target == null)) {
                ev.getPlayer().setDeltaMovement(0.0D, 0.6D, 0.0D);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(final LivingHurtEvent ev) {
        Entity attacker = ev.getSource().getEntity();
        if(!ev.getEntityLiving().level.isClientSide){
            if(ev.getEntityLiving() instanceof PlayerEntity) {
                if(PlayerUtil.getResource((PlayerEntity) ev.getEntityLiving(), Resources.HAS_DAGGER) == 1) {
                    if(attacker instanceof IHasOwner && !(attacker instanceof INyaruruNPC)) {
                        if(((IHasOwner) attacker).getOwner() != null)
                            ((IHasOwner) attacker).getOwner().hurt(DamageSource.thorns(ev.getEntityLiving()), ev.getAmount());
                    } else {
                        if(ev.getSource().getEntity() != null)
                            ev.getSource().getEntity().hurt(DamageSource.thorns(ev.getEntityLiving()), ev.getAmount());
                    }
                }
                if(ev.getAmount() >= ev.getEntityLiving().getHealth()) {
                    if(PlayerUtil.getResource((PlayerEntity) ev.getEntityLiving(), Resources.HAS_POTION) == 1 && ev.getEntityLiving().getHealth() > 1) {
                        ev.setAmount(1);
                        ev.getEntityLiving().setHealth(1);
                        ev.getEntityLiving().addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 50, 4));
                    }
                }
                if(PlayerUtil.getResource((PlayerEntity) ev.getEntityLiving(), Resources.HAS_SHIELD) == 1 && PlayerUtil.getResource((PlayerEntity) ev.getEntityLiving(), Resources.SHIELD_TICK) >= 1200) {
                    if(!ev.getSource().isBypassArmor()) {
                        ev.setAmount(0);
                        PlayerUtil.setResource((PlayerEntity) ev.getEntityLiving(), Resources.SHIELD_TICK, 0);
                    }
                }
            }
        }
    }

}