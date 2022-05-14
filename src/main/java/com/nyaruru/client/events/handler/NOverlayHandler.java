package com.nyaruru.client.events.handler;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.nyaruru.proxy.ClientProxy;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import com.nyaruru.utils.StringUtil;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jline.utils.Colors;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("deprecation")
public class NOverlayHandler {
    private static final ResourceLocation RESOURCES = StringUtil.resPrefix("textures/gui/overlay/resources.png");
    private static final ResourceLocation GAZE = StringUtil.resPrefix("textures/misc/a_cats_gaze.png");

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

    public static void renderGazeOverlay(MatrixStack stack, int width, int height) {
        double d0 = MathHelper.lerp(1.0D, 2.0D, 1.0D);
        double d1 = (double)width * d0;
        double d2 = (double)height * d0;
        double d3 = ((double)width - d1) / 2.0D;
        double d4 = ((double)height - d2) / 2.0D;

        stack.pushPose();
        stack.scale(0.6F, 0.6F, 1.0F);

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.7F * (1.0F - PlayerUtil.getResource(ClientProxy.MC.player, Resources.GAZE_OVERLAY_SCALE) * 0.1F));

        ClientProxy.MC.getTextureManager().bind(GAZE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(d3, d4 + d2, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(d3 + d1, d4 + d2, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(d3 + d1, d4, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(d3, d4, -90.0D).uv(0.0F, 0.0F).endVertex();
        tessellator.end();

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();

        stack.popPose();
    }
}