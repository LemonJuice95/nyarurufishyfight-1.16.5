package com.nyaruru.world.structure.loot_chest;

import com.google.common.collect.ImmutableMap;
import com.nyaruru.loot.NLoots;
import com.nyaruru.world.feature.NStructureFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class NDarkForestLootChestPiece extends TemplateStructurePiece {
    public static final ResourceLocation STRUCTURE_LOCATION_CHEST = new ResourceLocation("dark_forest_loot_chest/dark_forest_loot_chest");
    public static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(STRUCTURE_LOCATION_CHEST, BlockPos.ZERO);
    public static final Map<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.of(STRUCTURE_LOCATION_CHEST, new BlockPos(0, 0, 0));

    private final ResourceLocation templateLocation;
    private final Rotation rotation;

//    public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random random) {
//        list.add(new NDarkForestLootChestPiece(manager, STRUCTURE_LOCATION_CHEST, pos1, rotation, 0));
//    }

    public NDarkForestLootChestPiece(TemplateManager p_i49313_1_, ResourceLocation p_i49313_2_, BlockPos p_i49313_3_, Rotation p_i49313_4_, int p_i49313_5_) {
        super(NStructureFeatures.DARK_FOREST_LOOT_CHEST_TYPE, 0);
        templateLocation = p_i49313_2_;
        BlockPos blockpos = OFFSETS.get(p_i49313_2_);
        this.templatePosition = p_i49313_3_.offset(blockpos.getX(), blockpos.getY() - p_i49313_5_, blockpos.getZ());
        rotation = p_i49313_4_;
        this.loadTemplate(p_i49313_1_);
    }

    public NDarkForestLootChestPiece(TemplateManager p_i51340_1_, CompoundNBT nbt) {
        super(NStructureFeatures.DARK_FOREST_LOOT_CHEST_TYPE, nbt);
        this.templateLocation = new ResourceLocation(nbt.getString("Template"));
        this.rotation = Rotation.valueOf(nbt.getString("Rot"));
    }

    private void loadTemplate(TemplateManager p_207614_1_) {
        Template template = p_207614_1_.getOrCreate(this.templateLocation);
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(PIVOTS.get(this.templateLocation)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
        this.setup(template, this.templatePosition, placementsettings);
    }

    @Override
    protected void handleDataMarker(String p_186175_1_, BlockPos p_186175_2_, IServerWorld p_186175_3_, Random p_186175_4_, MutableBoundingBox p_186175_5_) {
        if ("dark_forest_loot_chest".equals(p_186175_1_)) {
            p_186175_3_.setBlock(p_186175_2_, Blocks.AIR.defaultBlockState(), 3);
            TileEntity tileentity = p_186175_3_.getBlockEntity(p_186175_2_.below());
            if (tileentity instanceof ChestTileEntity) {
                ((ChestTileEntity)tileentity).setLootTable(NLoots.DARK_FOREST_LOOT_CHEST, p_186175_4_.nextLong());
            }

        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_143011_1_) {
        super.addAdditionalSaveData(p_143011_1_);
        p_143011_1_.putString("Template", this.templateLocation.toString());
        p_143011_1_.putString("Rot", this.rotation.name());
    }

    @Override
    public boolean postProcess(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
        return super.postProcess(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
    }
}