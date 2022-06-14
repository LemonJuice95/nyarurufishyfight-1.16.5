package io.lemonjuice.nyaruru.world.feature;

import com.google.common.collect.ImmutableSet;
import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class NCrystalCaveFeatures {
    public static final BlockClusterFeatureConfig CRYSTALS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.CRYSTAL_CLUSTER.get().defaultBlockState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();
    public static final BlockClusterFeatureConfig TORCHES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.TORCH.defaultBlockState()), new SimpleBlockPlacer())).tries(8).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();
    public static final BlockClusterFeatureConfig SKULL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.SKELETON_SKULL.defaultBlockState()), new SimpleBlockPlacer())).tries(8).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();
    public static final BlockClusterFeatureConfig BOOSTER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.STEAM_BOOSTER.get().defaultBlockState()), new SimpleBlockPlacer())).tries(8).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();
    public static final BlockClusterFeatureConfig COIN_PILE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.COIN_PILE.get().defaultBlockState()), new SimpleBlockPlacer())).tries(4).whitelist(ImmutableSet.of(Blocks.STONE)).noProjection().build();


    public static final ConfiguredFeature<?, ?> CRYSTAL_CLUSTERS = register("crystal_clusters", Feature.RANDOM_PATCH.configured(CRYSTALS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(5));
    public static final ConfiguredFeature<?, ?> TORCHES = register("torches", Feature.RANDOM_PATCH.configured(TORCHES_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(5));
    public static final ConfiguredFeature<?, ?> SKULLS = register("skulls", Feature.RANDOM_PATCH.configured(SKULL_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(2));
    public static final ConfiguredFeature<?, ?> BOOSTERS = register("boosters", Feature.RANDOM_PATCH.configured(BOOSTER_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(5));
    public static final ConfiguredFeature<?, ?> COIN_PILE = register("coin_pile", Feature.RANDOM_PATCH.configured(COIN_PILE_CONFIG)).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(5);

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_, ConfiguredFeature<FC, ?> p_243968_1_) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
    }
}
