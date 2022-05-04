package com.nyaruru.entities.misc.coin;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CopperCoinEntity extends NCoinEntityBase{
    public CopperCoinEntity(EntityType<? extends CopperCoinEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public int getAmount() {
        return 1;
    }
}
