package com.nyaruru.blocks.misc;

import com.nyaruru.blocks.NBlockRegister;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class CoinPileBlock extends Block {
    public static final IntegerProperty STATE = IntegerProperty.create("state", 0, 4);

    public static final VoxelShape[] SHAPE_BY_STATE = {
        Block.box(1.0F, 0.0F, 1.0F, 15.0F, 16.0F, 15.0F),
        Block.box(1.0F, 0.0F, 1.0F, 15.0F, 16.0F, 15.0F),
        Block.box(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F),
        Block.box(2.0F, 0.0F, 2.0F, 14.0F, 13.0F, 14.0F),
        Block.box(3.0F, 0.0F, 3.0F, 13.0F, 8.0F, 13.0F)
    };

    public CoinPileBlock() {
        super(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.5F).isSuffocating(NBlockRegister::never).isViewBlocking(NBlockRegister::never));
        this.registerDefaultState(this.stateDefinition.any().setValue(STATE, 0));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE_BY_STATE[p_220053_1_.getValue(STATE)];
    }

    @Override
    public void playerDestroy(World p_180657_1_, PlayerEntity p_180657_2_, BlockPos p_180657_3_, BlockState p_180657_4_, @Nullable TileEntity p_180657_5_, ItemStack p_180657_6_) {
        if(!p_180657_1_.isClientSide) {
            BlockState state = p_180657_4_;
            if(state.getValue(STATE) < 4) {
                state = state.setValue(STATE, p_180657_4_.getValue(STATE) + 1);
                p_180657_1_.setBlock(p_180657_3_, state, 3);
            }
        }
        super.playerDestroy(p_180657_1_, p_180657_2_, p_180657_3_, p_180657_4_, p_180657_5_, p_180657_6_);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(STATE);
    }
}
