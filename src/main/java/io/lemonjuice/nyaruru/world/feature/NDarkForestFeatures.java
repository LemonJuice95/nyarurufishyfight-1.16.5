package io.lemonjuice.nyaruru.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;


public class NDarkForestFeatures {
    public static final BlockClusterFeatureConfig THORNS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.THORNS.get().defaultBlockState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK, Blocks.DIRT)).noProjection().build();
    public static final BlockClusterFeatureConfig SPIKES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.SPIKES.get().defaultBlockState()), new SimpleBlockPlacer())).tries(16).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK, Blocks.DIRT)).noProjection().build();
    public static final BlockClusterFeatureConfig BOUNCY_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.BOUNCY_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer())).tries(8).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK, Blocks.DIRT)).noProjection().build();
    public static final BlockClusterFeatureConfig COIN_PILE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.COIN_PILE.get().defaultBlockState()), new SimpleBlockPlacer())).tries(4).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK, Blocks.DIRT)).noProjection().build();

    public static final ConfiguredFeature<?, ?> THORNS = register("thorns", Feature.RANDOM_PATCH.configured(THORNS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(5));
    public static final ConfiguredFeature<?, ?> SPIKES = register("spikes", Feature.RANDOM_PATCH.configured(SPIKES_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(5));
    public static final ConfiguredFeature<?, ?> BOUNCY_MUSHROOMS = register("bouncy_mushrooms", Feature.RANDOM_PATCH.configured(BOUNCY_MUSHROOM_CONFIG)).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(2);
    public static final ConfiguredFeature<?, ?> COIN_PILE = register("coin_pile", Feature.RANDOM_PATCH.configured(COIN_PILE_CONFIG)).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(5);
    public static final ConfiguredFeature<?, ?> TREES = register("trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.MEGA_JUNGLE_TREE.weighted(0.5F)), Features.JUNGLE_TREE_NO_VINE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_,
            ConfiguredFeature<FC, ?> p_243968_1_) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
    }
}