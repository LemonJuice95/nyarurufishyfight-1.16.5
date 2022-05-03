package com.nyaruru.world.biome;

import com.nyaruru.utils.BiomeUtil;
import com.nyaruru.world.feature.NDarkForestFeatures;
import com.nyaruru.world.feature.NStructureFeatures;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

public class NDarkForestBiome {
    public static Biome getDarkForestBiome() {
        final BiomeAmbience.Builder effectBuilder = new BiomeAmbience.Builder()
                .waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                .grassColorModifier(BiomeAmbience.GrassColorModifier.SWAMP)
                .skyColor(BiomeUtil.getSkyColor(0.6F))
                .foliageColorOverride(0x028272).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);

        final MobSpawnInfo.Builder mobBuilder = new MobSpawnInfo.Builder();



        BiomeGenerationSettings.Builder generations = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultCarvers(generations);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(generations);
        DefaultBiomeFeatures.addDefaultMonsterRoom(generations);
        DefaultBiomeFeatures.addDefaultOres(generations);

//        generations.addStructureStart(NStructureFeatures.CONFIGURED_DARK_FOREST_LOOT_CHEST);
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_FOREST.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.TREES.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(30, 0.1F, 1))).decorated(Features.Placements.HEIGHTMAP_SQUARE));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.THORNS.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.SPIKES.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(30, 0.1F, 1))));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.BOUNCY_MUSHROOMS.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(20, 0.1F, 1))));

        BiomeGenerationSettings generationSettings = generations.build();
        BiomeGenerationSettingsBuilder wrapper = new BiomeGenerationSettingsBuilder(generationSettings);
        wrapper.getStructures().add(() -> NStructureFeatures.CONFIGURED_DARK_FOREST_LOOT_CHEST);

        return NBiomeRegister.makeBiome(Biome.Category.FOREST, Biome.RainType.RAIN, 0.1F, 0.3F, 0.6F, 0.8F,
                effectBuilder.build(), mobBuilder.build(), generationSettings);
    }
}