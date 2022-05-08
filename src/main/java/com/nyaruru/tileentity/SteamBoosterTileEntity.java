package com.nyaruru.tileentity;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SteamBoosterTileEntity extends TileEntity implements ITickableTileEntity {
    public static int onTick = 0;

    public SteamBoosterTileEntity() {
        super(NTileEntityRegister.STEAM_BOOSTER.get());
    }

    @Override
    public void tick() {
        if(this.level != null) {
            if(this.level.isClientSide) {
                if (onTick >= 1 && onTick <= 15) {
                    this.level.addParticle(ParticleTypes.CLOUD, this.getBlockPos().getX() + 0.5, this.getBlockPos().getY() + 0.2 * onTick, this.getBlockPos().getZ() + 0.5, 0.0, 0.0, 0.0);
                }
            }
        }
        if(onTick >= 80) {
            onTick = 0;
        } else {
            onTick++;
        }
    }
}
