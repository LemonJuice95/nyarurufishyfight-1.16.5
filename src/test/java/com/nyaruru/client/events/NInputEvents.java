package com.nyaruru.client.events;

import com.nyaruru.Reference;
import com.nyaruru.network.NPacketHandler;
import com.nyaruru.network.toserver.PlayerStatsPacketToServer;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.MoverType;
import net.minecraft.item.TridentItem;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MODID)
public class NInputEvents {
    public static final KeyBinding SPRINT_KEY = new KeyBinding("key.nyaruru.sprint",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.category.nyaruru");
    public static final KeyBinding SPRINT_BACK_KEY = new KeyBinding("key.nyaruru.sprint_back",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            "key.category.nyaruru");

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent ev) {
        if(SPRINT_KEY.consumeClick()) {
            assert Minecraft.getInstance().player != null;
            final ClientPlayerEntity player = Minecraft.getInstance().player;
            if((player.isOnGround() || PlayerUtil.getResource(player, Resources.SP) >= 96) && PlayerUtil.getResource(player, Resources.SPRINT_TICKS) <= 2) {
                NPacketHandler.CHANNEL.sendToServer(new PlayerStatsPacketToServer(Resources.SPRINT_TICKS.ordinal(), 8));
                float f7 = player.yRot;
                float f = 0.0F;
                float f1 = -MathHelper.sin(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                float f3 = MathHelper.cos(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                if(!player.isOnGround()) {
                    player.push((double) 2.0F * f1, 0.3D, (double) 2.0F * f3);
                    NPacketHandler.CHANNEL.sendToServer(new PlayerStatsPacketToServer(Resources.SPRINT_TIMES.ordinal(), PlayerUtil.getResource(player,Resources.SPRINT_TIMES) + 1));
                }
                else {
                    player.push((double) 2.0F * f1, 0.0D, (double) 2.0F * f3);
                    NPacketHandler.CHANNEL.sendToServer(new PlayerStatsPacketToServer(Resources.SPRINT_TIMES.ordinal(), 0));
                }
            }
        }
        if(SPRINT_BACK_KEY.consumeClick()) {
            assert Minecraft.getInstance().player != null;
            final ClientPlayerEntity player = Minecraft.getInstance().player;
            if((player.isOnGround() || PlayerUtil.getResource(player, Resources.SP) >= 96) && PlayerUtil.getResource(player, Resources.SPRINT_TICKS) <= 2) {
                NPacketHandler.CHANNEL.sendToServer(new PlayerStatsPacketToServer(Resources.SPRINT_TICKS.ordinal(), 8));
                float f7 = player.yRot;
                float f = 0.0F;
                float f1 = -MathHelper.sin(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                float f3 = MathHelper.cos(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                if(!player.isOnGround()) {
                    player.push((double) -2.0F * f1, 0.2D, (double) -2.0F * f3);
                    NPacketHandler.CHANNEL.sendToServer(new PlayerStatsPacketToServer(Resources.SPRINT_TIMES.ordinal(), PlayerUtil.getResource(player,Resources.SPRINT_TIMES) + 1));
                }
                else {
                    player.push((double) -2.0F * f1, 0.0D, (double) -2.0F * f3);
                    NPacketHandler.CHANNEL.sendToServer(new PlayerStatsPacketToServer(Resources.SPRINT_TIMES.ordinal(), 0));
                }
            }
        }
    }
}