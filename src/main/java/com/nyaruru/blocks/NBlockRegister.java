package com.nyaruru.blocks;

import com.nyaruru.Reference;
import com.nyaruru.blocks.misc.CoinPileBlock;
import com.nyaruru.blocks.misc.CrystalClusterBlock;
import com.nyaruru.blocks.misc.SpikesBlock;
import com.nyaruru.blocks.plants.BouncyMushroomBlock;
import com.nyaruru.blocks.plants.ThornsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NBlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<Block> THORNS = BLOCKS.register("thorns", ThornsBlock::new);
    public static final RegistryObject<Block> SPIKES = BLOCKS.register("spikes", SpikesBlock::new);
    public static final RegistryObject<Block> BOUNCY_MUSHROOM = BLOCKS.register("bouncy_mushroom", BouncyMushroomBlock::new);
    public static final RegistryObject<Block> COIN_PILE = BLOCKS.register("coin_pile", CoinPileBlock::new);
    public static final RegistryObject<Block> CRYSTAL_CLUSTER = BLOCKS.register("crystal_cluster", CrystalClusterBlock::new);

    public static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }
}