package io.lemonjuice.nyaruru.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SteamBoosterTileEntity extends TileEntity implements ITickableTileEntity {
    public static final IntegerProperty TICK = IntegerProperty.create("tick", 0, 80);

    public SteamBoosterTileEntity() {
        super(NTileEntityRegister.STEAM_BOOSTER.get());
    }

    @Override
    public void tick() {
        BlockState state = this.getBlockState();
        if(this.level != null) {
            if(this.level.isClientSide) {
                if (state.getValue(TICK) >= 1 && state.getValue(TICK) <= 15) {
                    this.level.addParticle(ParticleTypes.CLOUD, this.getBlockPos().getX() + 0.5, this.getBlockPos().getY() + 0.2 * state.getValue(TICK), this.getBlockPos().getZ() + 0.5, 0.0, 0.0, 0.0);
                }
            }
        }
        if(state.getValue(TICK) >= 80) {
            state = state.setValue(TICK, 0);
        } else {
            state = state.setValue(TICK, state.getValue(TICK) + 1);
        }
        this.level.setBlock(this.getBlockPos(), state, 3);
    }
}
