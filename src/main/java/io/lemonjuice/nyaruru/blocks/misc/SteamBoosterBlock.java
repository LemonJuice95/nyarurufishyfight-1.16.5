package io.lemonjuice.nyaruru.blocks.misc;

import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import io.lemonjuice.nyaruru.tileentity.SteamBoosterTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SteamBoosterBlock extends Block {
    public static final VoxelShape SHAPE1 = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 1.0F, 14.0F);
    public static final VoxelShape SHAPE2 = Block.box(3.0F, 1.0F, 3.0F, 13.0F, 3.0F, 13.0F);
    public static final VoxelShape SHAPE = VoxelShapes.or(SHAPE1, SHAPE2);
    public static final IntegerProperty TICK = IntegerProperty.create("tick", 0, 80);

    public SteamBoosterBlock() {
        super(AbstractBlock.Properties.of(Material.DECORATION).strength(0.3F).sound(SoundType.STONE).isSuffocating(NBlockRegister::never).isViewBlocking(NBlockRegister::never));
        this.registerDefaultState(this.stateDefinition.any().setValue(TICK, 0));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    /*@Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
        if(SteamBoosterTileEntity.particleFlag) {
            p_180655_2_.addParticle(ParticleTypes.CLOUD, p_180655_3_.getX() + 0.5, p_180655_3_.getY() + 0.2 * SteamBoosterTileEntity.onTick + 1.0, p_180655_3_.getZ() + 0.5, 0.0, 0.0, 0.0);
        }
    }*/

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if(state.getValue(TICK) == 2) {
            entity.push(0.0D, 1.6D, 0.0D);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SteamBoosterTileEntity();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(TICK);
    }
}
