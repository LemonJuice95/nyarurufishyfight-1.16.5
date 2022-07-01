package io.lemonjuice.nyaruru.client.events;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.client.events.handler.NOverlayHandler;
import io.lemonjuice.nyaruru.client.model.entity.ModifiedPlayerModel;
import io.lemonjuice.nyaruru.proxy.ClientProxy;
import io.lemonjuice.nyaruru.utils.PlayerUtil;
import io.lemonjuice.nyaruru.utils.Resources;
import jdk.jfr.internal.tool.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.Mixin;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class NRenderEvents {
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onPostRenderOverlay(RenderGameOverlayEvent.Post ev){
        if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL || ! canRender() || mc.options.hideGui) {
            return;
        }

        if (mc.screen == null) {
            if(!mc.options.renderDebug && PlayerUtil.getResource(ClientProxy.MC.player, Resources.RENDER_HUD) == 1) {
                NOverlayHandler.renderMoneyBar(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
                NOverlayHandler.renderBars(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
            }
            if(!mc.options.renderDebug && PlayerUtil.getResource(ClientProxy.MC.player, Resources.GAZE_OVERLAY_SCALE) < 10) {
                NOverlayHandler.renderGazeOverlay(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent.Pre ev) {
        assert ClientProxy.MC.player != null;
        if(PlayerUtil.getResource(ev.getPlayer(), Resources.RENDER_AS_CAT) == 1) {
            PlayerModel playerModel = ev.getRenderer().getModel();
            ev.getRenderer().model = new ModifiedPlayerModel<>(0.0F, playerModel.slim);
            ev.getRenderer().setModelProperties(ClientProxy.MC.player);
        } else {
            PlayerModel playerModel = ev.getRenderer().getModel();
            ev.getRenderer().model = new PlayerModel<>(0.0F, playerModel.slim);
            ev.getRenderer().setModelProperties(ClientProxy.MC.player);
        }
    }

    private static boolean canRender() {
        return mc.player != null && ! mc.player.isSpectator();
    }
}