package com.nyaruru.entities.misc.coin;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GoldCoinEntity extends NCoinEntityBase {
    public GoldCoinEntity(EntityType<? extends GoldCoinEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public int getAmount() {
        return 20;
    }
}