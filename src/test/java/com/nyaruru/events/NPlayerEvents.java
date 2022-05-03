package com.nyaruru.events;

import com.nyaruru.NyaruruFishyFight;
import com.nyaruru.Reference;
import com.nyaruru.capability.CapabilityHandler;
import com.nyaruru.entities.api.IHasOwner;
import com.nyaruru.items.fishes.ItemEsoxAmericanus;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class NPlayerEvents {

    @SubscribeEvent
    public static void tickPlayer(TickEvent.PlayerTickEvent ev) {
        if(! ev.player.level.isClientSide) {
            PlayerEntity player = ev.player;
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
                    float f7 = player.yRot;
                    float f = 0.0F;
                    float f1 = -MathHelper.sin(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                    float f3 = MathHelper.cos(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                    player.setDeltaMovement((double) f1, 0.0D, (double) f3);
                    player.startAutoSpinAttack(1);
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
            PlayerEntity player = ev.getPlayer();
            Entity target = ev.getTarget();
            if(ev.getPlayer().xRot <= -80) {
                player.setDeltaMovement(0.0D, 2.0D, 0.0D);
            }
            if(ev.getPlayer().xRot >= 80) {
                target.setDeltaMovement(0.0D, 2.0D, 0.0D);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(final LivingHurtEvent ev) {
        Entity attacker = ev.getSource().getEntity();
        if(!ev.getEntityLiving().level.isClientSide){
            if(ev.getEntityLiving() instanceof PlayerEntity) {
                if(PlayerUtil.getResource((PlayerEntity) ev.getEntityLiving(), Resources.HAS_DAGGER) == 1) {
                    if(attacker instanceof IHasOwner) {
                        ((IHasOwner) attacker).getOwner().hurt(DamageSource.thorns(ev.getEntityLiving()), ev.getAmount());
                    } else {
                        attacker.hurt(DamageSource.thorns(ev.getEntityLiving()), ev.getAmount());
                    }
                }
                if(ev.getAmount() >= ev.getEntityLiving().getHealth()) {
                    if(PlayerUtil.getResource((PlayerEntity) ev.getEntityLiving(), Resources.HAS_POTION) == 1 && ev.getEntityLiving().getHealth() > 1) {
                        ev.setAmount(ev.getEntityLiving().getHealth() - 1);
                        ev.getEntityLiving().addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 50, 4));
                    }
                }
            }
        }
    }

}