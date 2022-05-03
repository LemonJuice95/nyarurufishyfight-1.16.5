package com.nyaruru.world.structure;

import com.google.common.collect.ImmutableMap;
import com.nyaruru.Reference;
import com.nyaruru.utils.StringUtil;
import com.nyaruru.world.biome.NBiomeRegister;
import com.nyaruru.world.structure.lootchests.NDarkForestLootChestPiece;
import com.nyaruru.world.structure.lootchests.NDarkForestLootChests;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Map;

public class NStructureRegister {
    public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Reference.MODID);

    public static final RegistryObject<Structure<NoFeatureConfig>> DARK_FOREST_LOOT_CHEST = STRUCTURE_FEATURES.register("dark_forest_loot_chest", () -> new NDarkForestLootChests(NoFeatureConfig.CODEC));
    public static StructureFeature<?, ?> CONFIGURED_DARK_FOREST_LOOT_CHEST;

    public static IStructurePieceType DARK_FOREST_LOOT_CHEST_PIECE;

    public static void addStructureToBiome(BiomeLoadingEvent ev, RegistryKey<Biome> biomeKey) {
        if(biomeKey.equals(NBiomeRegister.DARK_FOREST.get())) {
            ev.getGeneration().addStructureStart(NStructureRegister.CONFIGURED_DARK_FOREST_LOOT_CHEST);
        }
    }

    public static void setupStructures() {
        {
            addStructure(DARK_FOREST_LOOT_CHEST.get(), new StructureSeparationSettings(60, 60 / 2, 998244353));
            DARK_FOREST_LOOT_CHEST_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "bucket_house", NDarkForestLootChestPiece::new);
            CONFIGURED_DARK_FOREST_LOOT_CHEST = DARK_FOREST_LOOT_CHEST.get().configured(IFeatureConfig.NONE);
            Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, StringUtil.resPrefix("configured_dark_forest_loot_chest"), CONFIGURED_DARK_FOREST_LOOT_CHEST);
            FlatGenerationSettings.STRUCTURE_FEATURES.put(DARK_FOREST_LOOT_CHEST.get(), CONFIGURED_DARK_FOREST_LOOT_CHEST);
    }
    }

    public static void addStructuresToMap(ServerWorld server, Map<Structure<?>, StructureSeparationSettings> tempMap) {
        Arrays.asList(
                NStructureRegister.DARK_FOREST_LOOT_CHEST
        ).forEach(l -> {
            tempMap.putIfAbsent(l.get(), DimensionStructuresSettings.DEFAULTS.get(l.get()));
        });
    }

    public static <F extends Structure<NoFeatureConfig>> void addStructure(F structure, StructureSeparationSettings settings) {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, settings)
                        .build();
    }
}