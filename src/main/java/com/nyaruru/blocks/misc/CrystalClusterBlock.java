package com.nyaruru.blocks.misc;

import com.nyaruru.blocks.NBlockRegister;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.world.NoteBlockEvent;

import javax.annotation.Nullable;
import java.util.Random;

public class CrystalClusterBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 15.0F, 12.0F);

    public CrystalClusterBlock() {
        super(AbstractBlock.Properties.of(Material.DECORATION).strength(0.2F).sound(SoundType.STONE).isSuffocating(NBlockRegister::never).isViewBlocking(NBlockRegister::never));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    public void playerDestroy(World p_180657_1_, PlayerEntity p_180657_2_, BlockPos p_180657_3_, BlockState p_180657_4_, @Nullable TileEntity p_180657_5_, ItemStack p_180657_6_) {
        if(!p_180657_1_.isClientSide) {
            if(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, p_180657_6_) == 0) {
                Random random = new Random();
                int power_now = PlayerUtil.getResource(p_180657_2_, Resources.POWER);
                int power = random.nextInt(10) + 20;
                int adding_power = PlayerUtil.getResource(p_180657_2_, Resources.ADDING_POWER);
                if (power_now + adding_power + power <= 100) {
                    PlayerUtil.setResource(p_180657_2_, Resources.ADDING_POWER, adding_power + power);
                } else {
                    PlayerUtil.setResource(p_180657_2_, Resources.ADDING_POWER, 100 - power_now);
                }
            }
        }
        super.playerDestroy(p_180657_1_, p_180657_2_, p_180657_3_, p_180657_4_, p_180657_5_, p_180657_6_);
    }
}