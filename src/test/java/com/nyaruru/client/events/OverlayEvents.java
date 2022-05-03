package com.nyaruru.client.events;

import com.nyaruru.Reference;
import com.nyaruru.client.events.handler.NOverlayHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class OverlayEvents {
    private static Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onPostRenderOverlay(RenderGameOverlayEvent.Post ev){
        if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL || ! canRender() || mc.options.hideGui) {
            return;
        }

        if (mc.screen == null) {
            if(!mc.options.renderDebug) {
                NOverlayHandler.renderMoneyBar(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
                NOverlayHandler.renderBars(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
            }
        }
    }

    private static boolean canRender() {
        return mc.player != null && ! mc.player.isSpectator();
    }
}