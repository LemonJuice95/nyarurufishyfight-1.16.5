package io.lemonjuice.nyaruru.world.biome;

import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.utils.BiomeUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NBiomeRegister {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Reference.MODID);

    public static final RegistryObject<Biome> DARK_FOREST = BIOMES.register("dark_forest", () -> NDarkForestBiome.getDarkForestBiome());
    public static final RegistryObject<Biome> CRYSTAL_CAVE = BIOMES.register("crystal_cave", () -> NCrystalCaveBiome.getCrystalCaveBiome());

    public static void registerBiomes(final FMLCommonSetupEvent ev) {
        registerBiome(DARK_FOREST.get(), BiomeManager.BiomeType.COOL, 30, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        registerBiome(CRYSTAL_CAVE.get(), BiomeManager.BiomeType.COOL, 30, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
    }

    public static void biomeModification(final BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if(biome != null) {
            final RegistryKey<Biome> biomeKey = BiomeUtil.getKey(biome);
        }
    }

    public static Biome makeBiome(Biome.Category category, Biome.RainType rainType, float depth, float scale, float temperature, float downFall, BiomeAmbience effect, MobSpawnInfo mobInfo, BiomeGenerationSettings generateSettings) {
        return new Biome.Builder()
                .biomeCategory(category)
                .precipitation(rainType)
                .depth(depth)
                .scale(scale)
                .temperature(temperature)
                .downfall(downFall)
                .specialEffects(effect)
                .mobSpawnSettings(mobInfo)
                .generationSettings(generateSettings)
                .build();
    }

    private static void registerBiome(final Biome biome, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(BiomeUtil.getKey(biome), types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(BiomeUtil.getKey(biome), weight));
    }
}
