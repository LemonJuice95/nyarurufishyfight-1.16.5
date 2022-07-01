package io.lemonjuice.nyaruru.events;

import io.lemonjuice.nyaruru.NyaruruFishyFight;
import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.capability.CapabilityHandler;
import io.lemonjuice.nyaruru.entities.api.IHasOwner;
import io.lemonjuice.nyaruru.entities.api.INyaruruEntity;
import io.lemonjuice.nyaruru.entities.api.INyaruruNPC;
import io.lemonjuice.nyaruru.network.NPacketHandler;
import io.lemonjuice.nyaruru.network.toclient.BowknotHandlerPacket;
import io.lemonjuice.nyaruru.network.toserver.PlayerStatsPacketToServer;
import io.lemonjuice.nyaruru.utils.PlayerUtil;
import io.lemonjuice.nyaruru.utils.Resources;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class NPlayerEvents {

    /*
    * I hate my trash codes
    */
    @SubscribeEvent
    public static void tickPlayer(TickEvent.PlayerTickEvent ev) {
        if(ev.player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY) != null) {
            if (!ev.player.level.isClientSide) {
                PlayerUtil.getOptManager(ev.player).ifPresent(l -> {
                    if (ev.player.isOnGround() && l.getResource(Resources.SPRINT_UP_TIMES) > 0) {
                        l.setResource(Resources.SPRINT_UP_TIMES, 0);
                    }
                    if (l.getResource(Resources.SP) < Resources.SP.max && !(l.getResource(Resources.SPRINT_TICKS) != 0 && ev.player.level.getBlockState(ev.player.blockPosition().below()).getBlock() == Blocks.AIR)) {
                        l.addResource(Resources.SP, 1);
                    }
                    if (l.getResource(Resources.ADDING_MONEY2) > 0) {
                        if (l.getResource(Resources.ADDING_MONEY) < 30) {
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
                    if (l.getResource(Resources.SPRINT_TICKS) > 0) {
                        l.addResource(Resources.SPRINT_TICKS, -1);
                        if (ev.player.level.getBlockState(ev.player.blockPosition().below()).getBlock() == Blocks.AIR && l.getResource(Resources.SPRINT_TIMES) > 1)
                            l.addResource(Resources.SP, -12);
                    }
                    if (l.getResource(Resources.DOUBLE_SLASH_TICK) > 0) {
                        l.addResource(Resources.DOUBLE_SLASH_TICK, -1);
                        l.addResource(Resources.SP, -12);
                    }
                    if (l.getResource(Resources.ADDING_POWER) > 0 && l.getResource(Resources.POWER) < Resources.POWER.max) {
                        l.addResource(Resources.POWER, 1);
                        l.addResource(Resources.ADDING_POWER, -1);
                    }
                    if (l.getResource(Resources.ADDING_POWER) <= -10) {
                        l.addResource(Resources.POWER, -10);
                        l.addResource(Resources.ADDING_POWER, 10);
                    } else if (l.getResource(Resources.ADDING_POWER) < 0) {
                        l.addResource(Resources.POWER, -1);
                        l.addResource(Resources.ADDING_POWER, 1);
                    }
                    if (l.getResource(Resources.DRAW_FISH_CHOPPING_TICKS) > 0) {
                        l.addResource(Resources.DRAW_FISH_CHOPPING_TICKS, -1);
                        ev.player.startAutoSpinAttack(1);
                    }
                    if (l.getResource(Resources.CAT_GAZE_FLAG) == 1) {
                        if (l.getResource(Resources.POWER) > 0) {
                            if (l.getResource(Resources.GAZE_TICK) >= 4) {
                                l.setResource(Resources.GAZE_TICK, 0);
                                l.addResource(Resources.POWER, -1);
                            } else {
                                l.addResource(Resources.GAZE_TICK, 1);
                            }
                            PlayerEventHandler.HandleCatGazeSkill(ev.player);
                            if (l.getResource(Resources.GAZE_OVERLAY_SCALE) > 0) {
                                l.addResource(Resources.GAZE_OVERLAY_SCALE, -1);
                            }
                        } else {
                            l.setResource(Resources.CAT_GAZE_FLAG, 0);
                        }
                    } else if (l.getResource(Resources.GAZE_OVERLAY_SCALE) < 10) {
                        l.addResource(Resources.GAZE_OVERLAY_SCALE, 1);
                    }
                    if (l.getResource(Resources.FLYING_FLAG) == 1) {
                        ev.player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 3, 1));
                    }
                    if (l.getResource(Resources.HAS_SHIELD) == 1 && l.getResource(Resources.SHIELD_TICK) >= 1200) {
                        ev.player.addEffect(new EffectInstance(Effects.GLOWING, 2, 0));
                    }
                    if (l.getResource(Resources.SPRINT_TICKS) > 0) {
                        l.addResource(Resources.SPRINT_TICKS, -1);
                    }
                    if (l.getResource(Resources.SPRINT_UP_TICKS) > 0) {
                        l.addResource(Resources.SPRINT_UP_TICKS, -1);
                    }
                });
            } else {
                Vector3d vec3d = ev.player.getDeltaMovement();
                if (PlayerUtil.getResource(ev.player, Resources.SPRINT_UP_TICKS) > 0) {
                    if (PlayerUtil.getResource(ev.player, Resources.SPRINT_UP_SWITCH) == 1) {
                        ev.player.setDeltaMovement(vec3d.x, 1.0D, vec3d.z);
                    }
                } else {
                    if (PlayerUtil.getResource(ev.player, Resources.SPRINT_UP_SWITCH) == 1) {
                        NPacketHandler.CHANNEL.sendToServer(new PlayerStatsPacketToServer(Resources.SPRINT_UP_SWITCH.ordinal(), 0));
                        ev.player.setDeltaMovement(vec3d.x, -0.2D, vec3d.z);
                    }
                }
            }
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
            Entity target = ev.getTarget();
            if(PlayerUtil.getResource(ev.getPlayer(), Resources.SPRINT_TICKS) > 0 && PlayerUtil.getResource(ev.getPlayer(), Resources.RED_JADE_FISH_EYE) == 1 && PlayerUtil.getResource(ev.getPlayer(), Resources.SP) > 96) {
                PlayerUtil.setResource(ev.getPlayer(), Resources.ADDING_POWER, 8);
                ev.getTarget().hurt(DamageSource.playerAttack(ev.getPlayer()),ev.getPlayer().getItemInHand(Hand.MAIN_HAND).getMaxDamage());
            }
            if(ev.getPlayer().xRot <= -70 && !(target == null)) {
                Vector3d vec3d = ev.getTarget().getDeltaMovement();
                ev.getTarget().setDeltaMovement(vec3d.x, 0.8D, vec3d.z);
            }
        } else {
            Entity target = ev.getTarget();
            if(ev.getPlayer().xRot >= 70 && !(target == null)) {
                Vector3d vec3d = ev.getPlayer().getDeltaMovement();
                ev.getPlayer().setDeltaMovement(vec3d.x, 0.8D, vec3d.z);
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
                    ev.setAmount(0);
                    PlayerUtil.setResource((PlayerEntity) ev.getEntityLiving(), Resources.SHIELD_TICK, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent ev) {
        if(!ev.getEntityLiving().level.isClientSide) {
            if(ev.getEntityLiving() instanceof PlayerEntity) {
                if(PlayerUtil.getResource((PlayerEntity) ev.getEntityLiving(), Resources.HAS_BOWKNOT) == 1) {
                    float f2 = 4.0F;
                    int k1 = MathHelper.floor(ev.getEntityLiving().getX() - (double)f2 - 1.0D);
                    int l1 = MathHelper.floor(ev.getEntityLiving().getX() + (double)f2 + 1.0D);
                    int i2 = MathHelper.floor(ev.getEntityLiving().getY() - (double)f2 - 1.0D);
                    int i1 = MathHelper.floor(ev.getEntityLiving().getY() + (double)f2 + 1.0D);
                    int j2 = MathHelper.floor(ev.getEntityLiving().getZ() - (double)f2 - 1.0D);
                    int j1 = MathHelper.floor(ev.getEntityLiving().getZ() + (double)f2 + 1.0D);
                    List<Entity> list = ev.getEntityLiving().level.getEntities(ev.getEntityLiving(), new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
                    for (Entity entity : list) {
                        if (entity != ev.getEntityLiving() && (entity instanceof LivingEntity) && !(entity instanceof PlayerEntity) && entity.isOnGround()) {
                            if (entity instanceof INyaruruEntity) {
                                entity.hurt(DamageSource.mobAttack(ev.getEntityLiving()), 5);
                            } else {
                                entity.hurt(DamageSource.mobAttack(ev.getEntityLiving()), 1);
                            }
                            entity.push(0.0D, 0.4D, 0.0D);
                        }
                    }
                    NPacketHandler.CHANNEL.send(PacketDistributor.DIMENSION.with(() -> {
                        return ev.getEntityLiving().level.dimension();
                    }), new BowknotHandlerPacket(ev.getEntityLiving().getX(), ev.getEntityLiving().getY(), ev.getEntityLiving().getZ()));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerFlyableFall(PlayerFlyableFallEvent ev) {
        if(!ev.getPlayer().level.isClientSide) {
            if (PlayerUtil.getResource(ev.getPlayer(), Resources.HAS_BOWKNOT) == 1) {
                float f2 = 4.0F;
                int k1 = MathHelper.floor(ev.getPlayer().getX() - (double) f2 - 1.0D);
                int l1 = MathHelper.floor(ev.getPlayer().getX() + (double) f2 + 1.0D);
                int i2 = MathHelper.floor(ev.getPlayer().getY() - (double) f2 - 1.0D);
                int i1 = MathHelper.floor(ev.getPlayer().getY() + (double) f2 + 1.0D);
                int j2 = MathHelper.floor(ev.getPlayer().getZ() - (double) f2 - 1.0D);
                int j1 = MathHelper.floor(ev.getPlayer().getZ() + (double) f2 + 1.0D);
                List<Entity> list = ev.getPlayer().level.getEntities(ev.getPlayer(), new AxisAlignedBB((double) k1, (double) i2, (double) j2, (double) l1, (double) i1, (double) j1));
                for (Entity entity : list) {
                    if (entity != ev.getPlayer() && (entity instanceof LivingEntity) && !(entity instanceof PlayerEntity) && entity.isOnGround()) {
                        if (entity instanceof INyaruruEntity) {
                            entity.hurt(DamageSource.mobAttack(ev.getPlayer()), 5);
                        } else {
                            entity.hurt(DamageSource.mobAttack(ev.getPlayer()), 1);
                        }
                        entity.push(0.0D, 0.4D, 0.0D);
                    }
                }
                NPacketHandler.CHANNEL.send(PacketDistributor.DIMENSION.with(() -> {
                    return ev.getPlayer().level.dimension();
                }), new BowknotHandlerPacket(ev.getPlayer().getX(), ev.getPlayer().getY(), ev.getPlayer().getZ()));
            }
        }
    }
}