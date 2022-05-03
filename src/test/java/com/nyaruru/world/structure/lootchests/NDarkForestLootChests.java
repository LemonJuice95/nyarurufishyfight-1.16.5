package com.nyaruru.world.structure.lootchests;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.nyaruru.Reference;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class NDarkForestLootChests extends Structure<NoFeatureConfig> {

    public NDarkForestLootChests(Codec<NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public String getFeatureName() {
        return Reference.MODID + ":dark_forest_loot_chest";
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries p_230364_1_, ChunkGenerator generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
                                   Biome biomeIn, NoFeatureConfig p_230364_7_) {
            this.pieces.add(new NDarkForestLootChestPiece(this.random, chunkX * 16, chunkZ * 16));
            this.calculateBoundingBox();
        }
    }
}
