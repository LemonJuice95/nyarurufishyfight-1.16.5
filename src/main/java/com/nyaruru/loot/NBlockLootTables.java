package com.nyaruru.loot;

import com.nyaruru.blocks.NBlockRegister;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NBlockLootTables extends BlockLootTables {
    private static final ILootCondition.IBuilder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item()
            .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder HAS_SHEARS = MatchTool
            .toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final ILootCondition.IBuilder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);

    @Override
    protected void addTables() {
        this.dropSelf(NBlockRegister.SPIKES.get());
        this.dropSelf(NBlockRegister.BOUNCY_MUSHROOM.get());

        this.add(NBlockRegister.THORNS.get(), NBlockLootTables::createThornsDrop);
        this.add(NBlockRegister.CRYSTAL_CLUSTER.get(), NBlockLootTables::createCrystalClusterDrop);
    }

    private static LootTable.Builder createThornsDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .when(HAS_SHEARS_OR_SILK_TOUCH).setRolls(ConstantRange.exactly(1))
                    .add(ItemLootEntry.lootTableItem(block)));
    }

    private  static LootTable.Builder createCrystalClusterDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .when(HAS_SILK_TOUCH).setRolls(ConstantRange.exactly(1))
                    .add(ItemLootEntry.lootTableItem(block)));
    }
}