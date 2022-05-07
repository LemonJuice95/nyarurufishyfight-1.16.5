package com.nyaruru.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SteamBoosterTileEntity extends TileEntity implements ITickableTileEntity {
    public static int onTick = 0;
    public static boolean particleFlag = false;

    public SteamBoosterTileEntity() {
        super(NTileEntityRegister.STEAM_BOOSTER.get());
    }

    @Override
    public void tick() {
        if(onTick == 1) {
            particleFlag = true;
        }
        if(onTick == 15) {
            particleFlag = false;
        }
        if(onTick >= 80) {
            onTick = 0;
        } else {
            onTick++;
        }
    }
}
