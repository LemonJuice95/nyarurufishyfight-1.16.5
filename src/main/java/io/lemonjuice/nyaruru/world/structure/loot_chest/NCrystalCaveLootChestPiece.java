package io.lemonjuice.nyaruru.world.structure.loot_chest;

import io.lemonjuice.nyaruru.loot.NLoots;
import io.lemonjuice.nyaruru.world.feature.NStructureFeatures;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class NCrystalCaveLootChestPiece extends ScatteredStructurePiece {
    public static final ResourceLocation STRUCTURE_LOCATION_CHEST = new ResourceLocation("crystal_cave_loot_chest/crystal_cave_loot_chest");

    public NCrystalCaveLootChestPiece(Random random, int x, int y, int z) {
        super(NStructureFeatures.CRYSTAL_CAVE_LOOT_CHEST_TYPE, random, x, y, z, 1, 1, 1);
    }

    public NCrystalCaveLootChestPiece(TemplateManager manager, CompoundNBT nbt) {
        super(NStructureFeatures.CRYSTAL_CAVE_LOOT_CHEST_TYPE, nbt);
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_143011_1_) {
        super.addAdditionalSaveData(p_143011_1_);
    }

    @Override
    public boolean postProcess(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
        int height = p_230383_3_.getFirstOccupiedHeight(p_230383_6_.x, p_230383_6_.z, Heightmap.Type.WORLD_SURFACE_WG);
        this.createChest(p_230383_1_, p_230383_5_, p_230383_4_, new BlockPos(p_230383_7_.getX(), height + 1, p_230383_7_.getZ()), NLoots.DARK_FOREST_LOOT_CHEST, null);
        return true;
    }
}
