package io.lemonjuice.nyaruru.items;

import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.blocks.NBlockRegister;
import io.lemonjuice.nyaruru.groups.NGroupRegister;
import io.lemonjuice.nyaruru.items.gemstones.ItemAquamarineFishEye;
import io.lemonjuice.nyaruru.items.gemstones.ItemFluoriteFishEye;
import io.lemonjuice.nyaruru.items.gemstones.ItemRedJadeFishEye;
import io.lemonjuice.nyaruru.items.accessories.ItemEmergencyPotion;
import io.lemonjuice.nyaruru.items.accessories.ItemForceFieldShield;
import io.lemonjuice.nyaruru.items.accessories.ItemGravityBowknot;
import io.lemonjuice.nyaruru.items.accessories.ItemThornDagger;
import io.lemonjuice.nyaruru.items.coins.*;
import io.lemonjuice.nyaruru.items.fishes.ItemDeepSeaTuna;
import io.lemonjuice.nyaruru.items.fishes.ItemEsoxAmericanus;
import io.lemonjuice.nyaruru.items.fishes.ItemEusthenopteron;
import io.lemonjuice.nyaruru.items.fishes.ItemHyneriaLindae;
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
    public static final RegistryObject<Item> HYNERIA_LINDAE = ITEMS.register("hyneria_lindae", ItemHyneriaLindae::new);
    public static final RegistryObject<Item> EUSTHENOPTERON = ITEMS.register("eusthenopteron", ItemEusthenopteron::new);

    //Accessories
    public static final RegistryObject<Item> THORN_DAGGER = ITEMS.register("thorn_dagger", ItemThornDagger::new);
    public static final RegistryObject<Item> EMERGENCY_POTION = ITEMS.register("emergency_potion", ItemEmergencyPotion::new);
    public static final RegistryObject<Item> FORCE_FIELD_SHIELD = ITEMS.register("force_field_shield", ItemForceFieldShield::new);
    public static final RegistryObject<Item> GRAVITY_BOWKNOT = ITEMS.register("gravity_bowknot", ItemGravityBowknot::new);

    //Blocks
    public static final RegistryObject<Item> THORNS = ITEMS.register("thorns", () -> new BlockItem(NBlockRegister.THORNS.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));
    public static final RegistryObject<Item> SPIKES = ITEMS.register("spikes", () -> new BlockItem(NBlockRegister.SPIKES.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));
    public static final RegistryObject<Item> BOUNCY_MUSHROOM = ITEMS.register("bouncy_mushroom", () -> new BlockItem(NBlockRegister.BOUNCY_MUSHROOM.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));
    public static final RegistryObject<Item> COIN_PILE = ITEMS.register("coin_pile", () -> new BlockItem(NBlockRegister.COIN_PILE.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));
    public static final RegistryObject<Item> CRYSTAL_CLUSTER = ITEMS.register("crystal_cluster", () -> new BlockItem(NBlockRegister.CRYSTAL_CLUSTER.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));
    public static final RegistryObject<Item> STEAM_BOOSTER = ITEMS.register("steam_booster", () -> new BlockItem(NBlockRegister.STEAM_BOOSTER.get(), new Item.Properties().tab(NGroupRegister.BLOCKS)));

    //Gemstones
    public static final RegistryObject<Item> RED_JADE_FISH_EYE = ITEMS.register("red_jade_fish_eye", ItemRedJadeFishEye::new);
    public static final RegistryObject<Item> FLUORITE_FISH_EYE = ITEMS.register("fluorite_fish_eye", ItemFluoriteFishEye::new);
    public static final RegistryObject<Item> AQUAMARINE_FISH_EYE = ITEMS.register("aquamarine_fish_eye", ItemAquamarineFishEye::new);

    //Misc
    public static final RegistryObject<Item> COPPER_CAT_COIN = ITEMS.register("copper_cat_coin", ItemCopperCatCoin::new);
    public static final RegistryObject<Item> SILVER_CAT_COIN = ITEMS.register("silver_cat_coin", ItemSilverCatCoin::new);
    public static final RegistryObject<Item> GOLD_CAT_COIN = ITEMS.register("gold_cat_coin", ItemGoldCatCoin::new);
    public static final RegistryObject<Item> CAT_DIAMOND = ITEMS.register("cat_diamond", ItemCatDiamond::new);

    public static final RegistryObject<Item> COPPER_COIN = ITEMS.register("copper_coin", ItemCopperCoin::new);
    public static final RegistryObject<Item> SILVER_COIN = ITEMS.register("silver_coin", ItemSilverCoin::new);
    public static final RegistryObject<Item> GOLD_COIN = ITEMS.register("gold_coin", ItemGoldCoin::new);

    //Foods
    public static final RegistryObject<Item> CABBAGE = ITEMS.register("cabbage",() -> new NFoodItem(NFoodItem.CABBAGE));
    public static final RegistryObject<Item> CANNED_CAT_FOOD = ITEMS.register("canned_cat_food", () -> new NFoodItem(NFoodItem.CANNED_CAT_FOOD));
    public static final RegistryObject<Item> MUSTARD = ITEMS.register("mustard", () -> new NFoodItem(NFoodItem.MUSTARD));
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic", () -> new NFoodItem(NFoodItem.GARLIC));
    public static final RegistryObject<Item> CHILLI = ITEMS.register("chilli", () -> new NFoodItem(NFoodItem.CHILLI));
    public static final RegistryObject<Item> SURSTROMMING = ITEMS.register("surstromming", () -> new NFoodItem(NFoodItem.SURSTROMMING));
}