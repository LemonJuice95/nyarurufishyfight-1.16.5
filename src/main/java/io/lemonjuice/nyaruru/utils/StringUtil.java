package io.lemonjuice.nyaruru.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.lemonjuice.nyaruru.Reference;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class StringUtil {
    public static final String INIT_VERSION = "0.0.0";

    public static ResourceLocation resPrefix(String location){
        return new ResourceLocation(Reference.MODID, location);
    }

    public static void drawCenteredScaledString(MatrixStack stack, FontRenderer render, String string, int x, int y, int color, float scale) {
        int width = render.width(string);
        stack.pushPose();
        stack.scale(scale, scale, scale);
        render.draw(stack, string, (x - width / 2 * scale) / scale, y / scale, color);
        stack.popPose();
    }
}
