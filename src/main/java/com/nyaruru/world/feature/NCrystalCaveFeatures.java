package com.nyaruru.world.feature;

import com.google.common.collect.ImmutableSet;
import com.nyaruru.blocks.NBlockRegister;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import org.lwjgl.system.CallbackI;

public class NCrystalCaveFeatures {
    public static final BlockClusterFeatureConfig CRYSTALS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.CRYSTAL_CLUSTER.get().defaultBlockState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();
    public static final BlockClusterFeatureConfig TORCHES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.TORCH.defaultBlockState()), new SimpleBlockPlacer())).tries(8).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();
    public static final BlockClusterFeatureConfig SKULL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.SKELETON_SKULL.defaultBlockState()), new SimpleBlockPlacer())).tries(8).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();
    public static final BlockClusterFeatureConfig BOOSTER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.STEAM_BOOSTER.get().defaultBlockState()), new SimpleBlockPlacer())).tries(8).whitelist(ImmutableSet.of(Blocks.STONE, Blocks.GRAVEL)).noProjection().build();


    public static final ConfiguredFeature<?, ?> CRYSTAL_CLUSTERS = register("crystal_clusters", Feature.RANDOM_PATCH.configured(CRYSTALS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(5));
    public static final ConfiguredFeature<?, ?> TORCHES = register("torches", Feature.RANDOM_PATCH.configured(TORCHES_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(5));
    public static final ConfiguredFeature<?, ?> SKULLS = register("skulls", Feature.RANDOM_PATCH.configured(SKULL_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(2));
    public static final ConfiguredFeature<?, ?> BOOSTERS = register("boosters", Feature.RANDOM_PATCH.configured(BOOSTER_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(5));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_,
                                                                                 ConfiguredFeature<FC, ?> p_243968_1_) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
    }
}
