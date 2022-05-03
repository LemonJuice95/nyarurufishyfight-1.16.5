package com.nyaruru.world.structure.lootchests;

import com.nyaruru.NyaruruFishyFight;
import com.nyaruru.loot.NLoots;
import com.nyaruru.utils.StringUtil;
import com.nyaruru.world.structure.NStructureRegister;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class NDarkForestLootChestPiece extends ScatteredStructurePiece {
    public NDarkForestLootChestPiece(Random random, int x, int z) {
        super(NStructureRegister.DARK_FOREST_LOOT_CHEST_PIECE, random, x, 90, z, 12, 10, 15);
    }

    public NDarkForestLootChestPiece(TemplateManager templateManager, CompoundNBT nbt) {
        super(NStructureRegister.DARK_FOREST_LOOT_CHEST_PIECE, nbt);
    }

    @Override
    public boolean postProcess(ISeedReader seedReader, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox box, ChunkPos pos, BlockPos blockPos) {
        this.createChest(seedReader, box, random, blockPos.getX(), blockPos.getY(), blockPos.getZ(), NLoots.DARK_FOREST_LOOT_CHEST);
        return true;
    }
}