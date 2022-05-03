package com.nyaruru.world.structure;

import com.mojang.serialization.Codec;
import com.nyaruru.NyaruruFishyFight;
import com.nyaruru.Reference;
import com.nyaruru.world.biome.NBiomeRegister;
import com.nyaruru.world.feature.NStructureFeatures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.spongepowered.tools.obfuscation.ObfuscationData;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class NStructureGenerator {
    private static Method GETCODEC_METHOD;

    @SuppressWarnings("resource")
    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load ev) {
        if(ev.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) ev.getWorld();
            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                @SuppressWarnings("unchecked")
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch (Exception a) {
                NyaruruFishyFight.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            NStructureFeatures.addStructuresToMap(serverWorld, tempMap);
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
