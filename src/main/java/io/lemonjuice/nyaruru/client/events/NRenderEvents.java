package io.lemonjuice.nyaruru.client.events;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.client.events.handler.NOverlayHandler;
import io.lemonjuice.nyaruru.proxy.ClientProxy;
import io.lemonjuice.nyaruru.utils.PlayerUtil;
import io.lemonjuice.nyaruru.utils.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class NRenderEvents {
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
            if(PlayerUtil.getResource(ClientProxy.MC.player, Resources.GAZE_OVERLAY_SCALE) < 10) {
                NOverlayHandler.renderGazeOverlay(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
            }
        }
    }

    private static boolean canRender() {
        return mc.player != null && ! mc.player.isSpectator();
    }
}