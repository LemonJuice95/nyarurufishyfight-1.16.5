package io.lemonjuice.nyaruru.command;

import com.mojang.brigadier.CommandDispatcher;
import io.lemonjuice.nyaruru.Reference;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class NCommandHandler {
    @SubscribeEvent
    public static void init(RegisterCommandsEvent ev) {
        CommandDispatcher<CommandSource> dispatcher = ev.getDispatcher();
        NResourceCommand.register(dispatcher);
    }
}
