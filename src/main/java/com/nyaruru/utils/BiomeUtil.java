package com.nyaruru.utils;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class BiomeUtil {
    public static final Method GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER = ObfuscationReflectionHelper.findMethod(BiomeMaker.class, /* getSkyColorWithTemperatureModifier */ "func_244206_a", float.class);

    public static int getSkyColor(float temp) {
        final int skyColour;
        try {
            skyColour = (int) GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER.invoke(null, temp);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error: Unable to get sky colour", e);
        }
        return skyColour;
    }

    public static RegistryKey<Biome> getKey(final Biome biome) {
        return RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome), "Biome registry name was null"));
    }
}
