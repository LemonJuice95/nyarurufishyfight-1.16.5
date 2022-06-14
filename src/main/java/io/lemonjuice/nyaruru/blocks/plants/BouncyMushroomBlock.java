package io.lemonjuice.nyaruru.blocks.plants;

import io.netty.util.internal.SuppressJava6Requirement;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BouncyMushroomBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    public BouncyMushroomBlock() {
        super(AbstractBlock.Properties.of(Material.CLAY).sound(SoundType.SLIME_BLOCK).noOcclusion().instabreak());
    }

    @Override
    public void fallOn(World world, BlockPos pos, Entity entity, float f) {
        entity.causeFallDamage(f, 0.0F);
    }

    @Override
    public void updateEntityAfterFallOn(IBlockReader reader, Entity entity) {
        entity.push(0.0D, 1.6D, 0.0D);
    }
}