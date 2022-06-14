package io.lemonjuice.nyaruru.blocks.plants;

import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ThornsBlock extends BushBlock {
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    private static int inside_tick = 0;

    public ThornsBlock() {
        super(AbstractBlock.Properties.of(Material.PLANT).noCollission().sound(SoundType.GRASS).instabreak().isSuffocating(NBlockRegister::never).isViewBlocking(NBlockRegister::never));
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
            entity.makeStuckInBlock(state, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
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