package com.nyaruru.blocks.misc;

import com.nyaruru.blocks.NBlockRegister;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class SteamBoosterBlock extends Block {
    public static final VoxelShape SHAPE1 = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 1.0F, 14.0F);
    public static final VoxelShape SHAPE2 = Block.box(3.0F, 1.0F, 3.0F, 13.0F, 3.0F, 13.0F);
    public static final VoxelShape SHAPE = VoxelShapes.or(SHAPE1, SHAPE2);
    private static int onTick = 0;
    private static boolean particleFlag;

    public SteamBoosterBlock() {
        super(AbstractBlock.Properties.of(Material.DECORATION).strength(0.3F).sound(SoundType.STONE).isSuffocating(NBlockRegister::never).isViewBlocking(NBlockRegister::never));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        switch (onTick) {
            case 1:
                particleFlag = true;
                break;
            case 15:
                particleFlag = false;
                break;
            case 80:
                onTick = 0;
                break;
            default:
                onTick++;
                break;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
        if(particleFlag) {
            p_180655_2_.addParticle(ParticleTypes.CLOUD, p_180655_3_.getX(), p_180655_3_.getY() + 0.2 * onTick, p_180655_3_.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if(world.isClientSide) {
            if(onTick == 2) {
                entity.push(0.0D, 1.6D, 0.0D);
            }
        }
    }
}
