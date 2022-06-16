package io.lemonjuice.nyaruru.client.events;

import io.lemonjuice.nyaruru.NyaruruFishyFight;
import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.client.model.entity.ModifiedPlayerModel;
import io.lemonjuice.nyaruru.proxy.ClientProxy;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class NModelEvents {
    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent.Pre ev) {
        PlayerModel playerModel = ev.getRenderer().getModel();
        ev.getRenderer().model = new ModifiedPlayerModel<>(0.0F, playerModel.slim);
        ev.getRenderer().setModelProperties(ClientProxy.MC.player);
    }
}
