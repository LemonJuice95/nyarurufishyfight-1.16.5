package io.lemonjuice.nyaruru.loot;

import io.lemonjuice.nyaruru.utils.StringUtil;
import net.minecraft.util.ResourceLocation;

public class NLoots {
    public static final ResourceLocation DARK_FOREST_LOOT_CHEST = getChestLootTable("dark_forest_loot_chest");
    public static final ResourceLocation CRYSTAL_CAVE_LOOT_CHEST = getChestLootTable("crystal_cave_loot_chest");

    private static ResourceLocation getChestLootTable(String name) {
        return StringUtil.resPrefix("chests/" + name);
    }
}
