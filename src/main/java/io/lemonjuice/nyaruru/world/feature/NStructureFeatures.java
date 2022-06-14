package io.lemonjuice.nyaruru.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.utils.StringUtil;
import io.lemonjuice.nyaruru.world.structure.loot_chest.NDarkForestLootChestPiece;
import io.lemonjuice.nyaruru.world.structure.loot_chest.NDarkForestLootChestStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NStructureFeatures {
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Reference.MODID);

    public static final RegistryObject<Structure<NoFeatureConfig>> DARK_FOREST_LOOT_CHEST = STRUCTURES.register("dark_forest_loot_chest", () -> (new NDarkForestLootChestStructure(NoFeatureConfig.CODEC)));
    public static StructureFeature<?, ?> CONFIGURED_DARK_FOREST_LOOT_CHEST;

    public static IStructurePieceType DARK_FOREST_LOOT_CHEST_TYPE = setPieceId(NDarkForestLootChestPiece::new, "dark_forest_loot_chest");
    public static IStructurePieceType CRYSTAL_CAVE_LOOT_CHEST_TYPE = setPieceId(NDarkForestLootChestPiece::new, "crystal_cave_loot_chest");

    static IStructurePieceType setPieceId(IStructurePieceType p_214750_0_, String p_214750_1_) {
        return Registry.register(Registry.STRUCTURE_PIECE, p_214750_1_.toLowerCase(Locale.ROOT), p_214750_0_);
    }

    public static void setupStructures() {
        {
            addStructure(DARK_FOREST_LOOT_CHEST.get(), new StructureSeparationSettings(6, 3, 1200013));
            CONFIGURED_DARK_FOREST_LOOT_CHEST = DARK_FOREST_LOOT_CHEST.get().configured(IFeatureConfig.NONE);
            Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, StringUtil.resPrefix("configured_dark_forest_loot_chest"), CONFIGURED_DARK_FOREST_LOOT_CHEST);
            FlatGenerationSettings.STRUCTURE_FEATURES.put(DARK_FOREST_LOOT_CHEST.get(), CONFIGURED_DARK_FOREST_LOOT_CHEST);
        }
    }

    public static <F extends Structure<NoFeatureConfig>> void addStructure(F structure, StructureSeparationSettings settings) {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
        Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder()
                .addAll(Structure.NOISE_AFFECTING_FEATURES)
                .add(structure)
                .build();
        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, settings)
                        .build();
        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(setting -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = setting.getValue().structureSettings().structureConfig();
            if(structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, settings);
                setting.getValue().structureSettings().structureConfig = tempMap;
            } else {
                structureMap.put(structure, settings);
            }
        });
    }

    public static void addStructuresToMap(ServerWorld server, Map<Structure<?>, StructureSeparationSettings> tempMap) {
        Arrays.asList(
                NStructureFeatures.DARK_FOREST_LOOT_CHEST
        ).forEach(l -> {
            tempMap.putIfAbsent(l.get(), DimensionStructuresSettings.DEFAULTS.get(l.get()));
        });
    }
}