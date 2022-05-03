package com.nyaruru;

import com.nyaruru.loot.NLootTableGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDataGeneratorHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent ev) {
        ExistingFileHelper helper = ev.getExistingFileHelper();
        if(ev.includeServer()) {
            ev.getGenerator().addProvider(new NLootTableGenerator(ev.getGenerator()));
        }
    }
}
