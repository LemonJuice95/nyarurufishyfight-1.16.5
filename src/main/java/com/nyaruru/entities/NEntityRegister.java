package com.nyaruru.entities;

import com.nyaruru.Reference;
import com.nyaruru.entities.misc.FishCrossSlashEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NEntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MODID);

    public static final RegistryObject<EntityType<FishCrossSlashEntity>> FISH_CROSS_SLASH = ENTITIES.register("fish_cross_slash", () -> {
        return EntityType.Builder.of((EntityType<FishCrossSlashEntity> entityType, World worldIn) -> {
            return new FishCrossSlashEntity(entityType, worldIn);
        }, EntityClassification.MISC).sized(1.1F, 1.3F).build("fish_cross_slash");
    });
}
