package com.nyaruru.entities.misc.coin;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SilverCoinEntity extends NCoinEntityBase{
    public SilverCoinEntity(EntityType<? extends SilverCoinEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public int getAmount() {
        return 5;
    }
}