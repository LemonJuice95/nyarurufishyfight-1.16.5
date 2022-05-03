package com.nyaruru.potion;

import com.nyaruru.Reference;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NEffectRegister {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Reference.MODID);

    public static final RegistryObject<Effect> SP_REGENERATING = EFFECTS.register("sp_regenerating", () -> {
        return new NEffect(EffectType.BENEFICIAL, 6737151);
    });

    public static final RegistryObject<Effect> POWER_REGENERATING = EFFECTS.register("power_regenerating", () -> {
        return new NEffect(EffectType.BENEFICIAL, 16776960);
    });
}