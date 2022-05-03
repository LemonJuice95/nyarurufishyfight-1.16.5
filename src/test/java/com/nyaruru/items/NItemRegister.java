package com.nyaruru.items;

import com.nyaruru.Reference;
import com.nyaruru.blocks.NBlockRegister;
import com.nyaruru.groups.NGroupRegister;
import com.nyaruru.items.accessories.ItemEmergencyPotion;
import com.nyaruru.items.accessories.ItemThornDagger;
import com.nyaruru.items.coins.ItemCatDiamond;
import com.nyaruru.items.coins.ItemCopperCoin;
import com.nyaruru.items.coins.ItemGoldCoin;
import com.nyaruru.items.coins.ItemSilverCoin;
import com.nyaruru.items.fishes.ItemDeepSeaTuna;
import com.nyaruru.items.fishes.ItemEsoxAmericanus;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    //Weapons
    public static final RegistryObject<Item> DEEP_SEA_TUNA = ITEMS.register("deep_sea_tuna", ItemDeepSeaTuna::new);
    public static final RegistryObject<Item> ESOX_AMERICANUS = ITEMS.register("esox_americanus", ItemEsoxAmericanus::new);

    //Accessories
    public static final RegistryObject<Item> THORN_DAGGER = ITEMS.register("thorn_dagger", ItemThornDagger::new);
    public static final RegistryObject<Item> EMERGENCY_POTION = ITEMS.register("emergency_potion", ItemEmergencyPotion::new);

    //Blocks
    public static final RegistryObject<Item> THORNS = ITEMS.register("thorns", () -> new BlockItem(NBlockRegister.THORNS.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));
    public static final RegistryObject<Item> SPIKES = ITEMS.register("spikes", () -> new BlockItem(NBlockRegister.SPIKES.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));
    public static final RegistryObject<Item> BOUNCY_MUSHROOM = ITEMS.register("bouncy_mushroom", () -> new BlockItem(NBlockRegister.BOUNCY_MUSHROOM.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));

    //Misc
    public static final RegistryObject<Item> COPPER_COIN = ITEMS.register("copper_coin", ItemCopperCoin::new);
    public static final RegistryObject<Item> SILVER_COIN = ITEMS.register("silver_coin", ItemSilverCoin::new);
    public static final RegistryObject<Item> GOLDEN_COIN = ITEMS.register("golden_coin", ItemGoldCoin::new);
    public static final RegistryObject<Item> CAT_DIAMOND = ITEMS.register("cat_diamond", ItemCatDiamond::new);

    //Foods
    public static final RegistryObject<Item> CABBAGE = ITEMS.register("cabbage",() -> new NFoodItem(NFoodItem.CABBAGE));
    public static final RegistryObject<Item> CANNED_CAT_FOOD = ITEMS.register("canned_cat_food", () -> new NFoodItem(NFoodItem.CANNED_CAT_FOOD));
    public static final RegistryObject<Item> MUSTARD = ITEMS.register("mustard", () -> new NFoodItem(NFoodItem.MUSTARD));
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic", () -> new NFoodItem(NFoodItem.GARLIC));
    public static final RegistryObject<Item> CHILLI = ITEMS.register("chilli", () -> new NFoodItem(NFoodItem.CHILLI));
    public static final RegistryObject<Item> SURSTROMMING = ITEMS.register("surstromming", () -> new NFoodItem(NFoodItem.SURSTROMMING));
}