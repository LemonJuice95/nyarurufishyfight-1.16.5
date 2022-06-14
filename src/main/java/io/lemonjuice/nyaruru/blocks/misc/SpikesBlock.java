package io.lemonjuice.nyaruru.blocks.misc;

import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SpikesBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 4.0D, 15.0D);
    private static int inside_tick = 0;

    public SpikesBlock() {
        super(AbstractBlock.Properties.of(Material.DECORATION).strength(0.2F).sound(SoundType.STONE).isSuffocating(NBlockRegister::never).isViewBlocking(NBlockRegister::never));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            if (!world.isClientSide) {
                if(this.getInsideTick() == 0) {
                    entity.hurt(DamageSource.CACTUS.bypassArmor(), 4);
                }
            }
        }
    }

    private int getInsideTick() {
        if(inside_tick > 40) {
            inside_tick = 0;
            return 0;
        }
        return inside_tick++;
    }
}