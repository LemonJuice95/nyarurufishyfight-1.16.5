package io.lemonjuice.nyaruru.command;

import com.mojang.brigadier.CommandDispatcher;
import io.lemonjuice.nyaruru.utils.PlayerUtil;
import io.lemonjuice.nyaruru.utils.Resources;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;


public class NHUDCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("nyaruruhud").executes(command -> {
            return cycleHUDRenderStat(command.getSource());
        }));
    }

    public static int cycleHUDRenderStat(CommandSource source) {
        Entity entity = source.getEntity();
        if(entity instanceof ServerPlayerEntity) {
            PlayerUtil.cycleResource((PlayerEntity) entity, Resources.RENDER_HUD);
            String stat = PlayerUtil.getResource((PlayerEntity) entity, Resources.RENDER_HUD) == 0 ? "message.nyaruru.off" : "message.nyaruru.on";
            source.sendSuccess(new StringTextComponent(new TranslationTextComponent("message.nyaruru.renderHUD").getString() + ": " + new TranslationTextComponent(stat).getString()), true);
        }
        return 1;
    }
}
