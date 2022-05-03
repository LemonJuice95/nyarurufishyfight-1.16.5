package com.nyaruru.world.biome;

import com.nyaruru.utils.BiomeUtil;
import com.nyaruru.world.feature.NDarkForestFeatures;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class NDarkForestBiome {
    public static Biome getDarkForestBiome() {
        final BiomeAmbience.Builder effectBuilder = new BiomeAmbience.Builder()
                .waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                .grassColorModifier(BiomeAmbience.GrassColorModifier.SWAMP)
                .skyColor(BiomeUtil.getSkyColor(0.6F))
                .foliageColorOverride(0x028272).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);

        final MobSpawnInfo.Builder mobBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultCarvers(genBuilder);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(genBuilder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(genBuilder);
        DefaultBiomeFeatures.addPlainGrass(genBuilder);
        DefaultBiomeFeatures.addPlainVegetation(genBuilder);
        DefaultBiomeFeatures.addDefaultOres(genBuilder);

        genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.JUNGLE_TREE_NO_VINE);
        genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.MEGA_JUNGLE_TREE);
        genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.THORNS);
        genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.SPIKES);
        genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.BOUNCY_MUSHROOMS);


        return NBiomeRegister.makeBiome(Biome.Category.FOREST, Biome.RainType.RAIN, 0.125F, 1.5F, 0.6F, 0.4F,
                effectBuilder.build(), mobBuilder.build(), genBuilder.build());
    }
}