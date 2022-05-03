package com.nyaruru.client.events.handler;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.nyaruru.proxy.ClientProxy;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import com.nyaruru.utils.StringUtil;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jline.utils.Colors;

@OnlyIn(Dist.CLIENT)
public class NOverlayHandler {
    private static final ResourceLocation RESOURCES = StringUtil.resPrefix("textures/gui/overlay/resources.png");

    public static void renderMoneyBar(MatrixStack stack, int width, int height) {
        final int num = PlayerUtil.getResource(ClientProxy.MC.player, Resources.MONEY);
        stack.pushPose();
        RenderSystem.enableBlend();
        stack.scale(0.5F,0.5F,1.0F);

        ClientProxy.MC.getTextureManager().bind(RESOURCES);
        ClientProxy.MC.gui.blit(stack, 3, 50, 0, 0, 123, 33);
        StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, num + "", 75, 56, 16777215, 2.5F);

        RenderSystem.disableBlend();
        stack.popPose();
    }

    public static void renderBars(MatrixStack stack, int width, int height) {
        final int SP = PlayerUtil.getResource(ClientProxy.MC.player, Resources.SP);
        final int SP_len = (int)(223.0 * (SP / 200.0)) + 3;
        final int power = PlayerUtil.getResource(ClientProxy.MC.player, Resources.POWER);
        final int power_len = (int)(217.0 * (power / 100.0)) + 3;

        stack.pushPose();
        RenderSystem.enableBlend();
        stack.scale(0.6F,0.6F,1.0F);

        ClientProxy.MC.getTextureManager().bind(RESOURCES);
        ClientProxy.MC.gui.blit(stack, 0, 0, 0, 35, 232, 35);
        ClientProxy.MC.gui.blit(stack, 0, 19, 0, 96, power_len, 16);
        ClientProxy.MC.gui.blit(stack, 0, 0, 0, 77, SP_len, 22);

        RenderSystem.disableBlend();
        stack.popPose();
    }
}