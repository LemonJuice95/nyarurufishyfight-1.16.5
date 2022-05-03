package com.nyaruru.world.feature;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.nyaruru.blocks.NBlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;


public class NDarkForestFeatures {
    public static final BlockClusterFeatureConfig THORNS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.THORNS.get().defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build();
    public static final BlockClusterFeatureConfig SPIKES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.SPIKES.get().defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock(), Blocks.STONE.getBlock(), Blocks.DIRT.getBlock())).noProjection().build();
    public static final BlockClusterFeatureConfig BOUNCY_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(NBlockRegister.BOUNCY_MUSHROOM.get().defaultBlockState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock(), Blocks.DIRT.getBlock(), Blocks.STONE.getBlock())).noProjection().build();

    public static final ConfiguredFeature<?, ?> THORNS = register("thorns", Feature.RANDOM_PATCH.configured(THORNS_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32));
    public static final ConfiguredFeature<?, ?> SPIKES = register("spikes", Feature.RANDOM_PATCH.configured(SPIKES_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(24));
    public static final ConfiguredFeature<?, ?> BOUNCY_MUSHROOMS = register("bouncy_mushrooms", Feature.RANDOM_PATCH.configured(BOUNCY_MUSHROOM_CONFIG)).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(16);

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_,
            ConfiguredFeature<FC, ?> p_243968_1_) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
    }
}