package com.nyaruru.loot;

import com.nyaruru.items.NItemRegister;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> t) {
        t.accept(NLoots.DARK_FOREST_LOOT_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(RandomValueRange.between(3.0F, 5.0F))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.CABBAGE.get()).setWeight(16))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.CANNED_CAT_FOOD.get()).setWeight(16))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.COPPER_COIN.get()).setWeight(20)).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.SILVER_COIN.get()).setWeight(18))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.GOLD_COIN.get()).setWeight(14))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.CAT_DIAMOND.get()).setWeight(12))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.THORN_DAGGER.get()).setWeight(12))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.EMERGENCY_POTION.get()).setWeight(12))
                        .add(EmptyLootEntry.emptyItem().setWeight(5))));
    }
}
