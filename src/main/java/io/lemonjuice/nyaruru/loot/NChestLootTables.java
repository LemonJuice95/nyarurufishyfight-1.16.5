package io.lemonjuice.nyaruru.loot;

import io.lemonjuice.nyaruru.items.NItemRegister;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> t) {
        t.accept(NLoots.DARK_FOREST_LOOT_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(RandomValueRange.between(4.0F, 8.0F))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.CABBAGE.get()).setWeight(6))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.CANNED_CAT_FOOD.get()).setWeight(6))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.COPPER_CAT_COIN.get()).setWeight(10)).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.SILVER_CAT_COIN.get()).setWeight(8))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.GOLD_CAT_COIN.get()).setWeight(4))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.CAT_DIAMOND.get()).setWeight(2))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.THORN_DAGGER.get()).setWeight(2))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.EMERGENCY_POTION.get()).setWeight(2))
                        .add(EmptyLootEntry.emptyItem().setWeight(6))));

        t.accept(NLoots.CRYSTAL_CAVE_LOOT_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(RandomValueRange.between(3.0F, 7.0F))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.CABBAGE.get()).setWeight(6))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.COPPER_CAT_COIN.get()).setWeight(10))
                        .add(ItemLootEntry.lootTableItem(NItemRegister.SILVER_CAT_COIN.get()).setWeight(8))
                        .add(EmptyLootEntry.emptyItem().setWeight(6))));
    }
}
