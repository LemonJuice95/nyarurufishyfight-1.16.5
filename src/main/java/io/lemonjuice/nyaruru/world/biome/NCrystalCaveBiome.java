package io.lemonjuice.nyaruru.world.biome;

import io.lemonjuice.nyaruru.utils.BiomeUtil;
import io.lemonjuice.nyaruru.world.feature.NCrystalCaveFeatures;
import io.lemonjuice.nyaruru.world.feature.NDarkForestFeatures;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

public class NCrystalCaveBiome {
    public static Biome getCrystalCaveBiome() {
        final BiomeAmbience.Builder effectBuilder = new BiomeAmbience.Builder()
                .waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                .skyColor(BiomeUtil.getSkyColor(0.1F))
                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);

        final MobSpawnInfo.Builder mobBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generations = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.STONE);
        DefaultBiomeFeatures.addDefaultCarvers(generations);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(generations);
        DefaultBiomeFeatures.addDefaultMonsterRoom(generations);
        DefaultBiomeFeatures.addDefaultOres(generations);

        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NCrystalCaveFeatures.CRYSTAL_CLUSTERS.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NCrystalCaveFeatures.TORCHES.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NCrystalCaveFeatures.SKULLS.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NCrystalCaveFeatures.BOOSTERS.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
        generations.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDarkForestFeatures.COIN_PILE.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

        BiomeGenerationSettings generationSettings = generations.build();
        BiomeGenerationSettingsBuilder wrapper = new BiomeGenerationSettingsBuilder(generationSettings);

        return NBiomeRegister.makeBiome(Biome.Category.EXTREME_HILLS, Biome.RainType.RAIN, 10.0F, 10F, 0.6F, 10.0F,
                effectBuilder.build(), mobBuilder.build(), wrapper.build());
    }
}
