package com.nyaruru.tileentity;

import com.nyaruru.Reference;
import com.nyaruru.blocks.NBlockRegister;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NTileEntityRegister {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final RegistryObject<TileEntityType<SteamBoosterTileEntity>> STEAM_BOOSTER = TILE_ENTITIES.register("steam_booster", () -> {
        return TileEntityType.Builder.of(SteamBoosterTileEntity::new, NBlockRegister.STEAM_BOOSTER.get()).build(null);
    });
}
