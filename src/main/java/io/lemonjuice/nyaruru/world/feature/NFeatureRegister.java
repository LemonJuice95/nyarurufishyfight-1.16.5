package io.lemonjuice.nyaruru.world.feature;

import io.lemonjuice.nyaruru.Reference;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NFeatureRegister {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MODID);

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void setupStructures(final RegistryEvent.Register<Structure<?>> ev) {
        NStructureFeatures.setupStructures();
    }
}