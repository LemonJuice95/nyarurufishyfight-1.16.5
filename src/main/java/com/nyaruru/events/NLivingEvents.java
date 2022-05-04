package com.nyaruru.events;

import com.nyaruru.Reference;
import com.nyaruru.entities.api.INyaruruBoss;
import com.nyaruru.entities.api.INyaruruEnemy;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class NLivingEvents {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent ev) {
        final Entity entity = ev.getEntity();
        if(! entity.level.isClientSide) {
            Entity killer = ev.getSource().getEntity();
            if(killer instanceof PlayerEntity) {
                PlayerEventHandler.onPlayerKillEntity( (PlayerEntity) killer, ev.getEntityLiving());
            }
        }

        if((entity instanceof INyaruruEnemy) && !(entity instanceof INyaruruBoss)) {
            Random random = new Random();
            for(int i = 0; i <= random.nextInt(2); i++) {

            }
        }
    }

}