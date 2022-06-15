package io.lemonjuice.nyaruru.client.events;

import io.lemonjuice.nyaruru.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class ModelEvent {
    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent ev) {

    }
}
