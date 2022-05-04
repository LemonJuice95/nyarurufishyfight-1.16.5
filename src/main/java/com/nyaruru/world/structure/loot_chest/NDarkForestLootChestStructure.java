package com.nyaruru.world.structure.loot_chest;

import com.mojang.serialization.Codec;
import com.nyaruru.Reference;
import net.minecraft.block.BlockState;
import net.minecraft.block.StructureBlock;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;

public class NDarkForestLootChestStructure extends Structure<NoFeatureConfig> {
    public NDarkForestLootChestStructure(Codec<NoFeatureConfig> p_i231998_1_) {
        super(p_i231998_1_);
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public String getFeatureName() {
        return Reference.MODID + ":dark_forest_loot_chest";
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider provider, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        BlockPos center = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getBaseHeight(center.getX(), center.getZ(), Heightmap.Type.WORLD_SURFACE_WG);

        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(center.getX(), center.getZ());
        BlockState top = columnOfBlocks.getBlockState(center.above(landHeight));

        return top.getFluidState().isEmpty();
    }


    @Override
    @Nonnull
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> p_i225819_1_, int p_i225819_2_, int p_i225819_3_, MutableBoundingBox p_i225819_4_, int p_i225819_5_, long p_i225819_6_) {
            super(p_i225819_1_, p_i225819_2_, p_i225819_3_, p_i225819_4_, p_i225819_5_, p_i225819_6_);
        }

        public void generatePieces(DynamicRegistries p_230364_1_, ChunkGenerator p_230364_2_, TemplateManager p_230364_3_, int p_230364_4_, int p_230364_5_, Biome p_230364_6_, NoFeatureConfig p_230364_7_) {
            int height = p_230364_2_.getFirstOccupiedHeight(getChunkX(), getChunkZ(), Heightmap.Type.WORLD_SURFACE_WG);
            this.pieces.add(new NDarkForestLootChestPiece(this.random, getChunkX() * 16, height + 1, getChunkZ() * 16));
            this.calculateBoundingBox();
        }
    }
}
