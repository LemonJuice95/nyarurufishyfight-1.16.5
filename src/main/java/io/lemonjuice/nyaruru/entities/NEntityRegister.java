package io.lemonjuice.nyaruru.entities;

import io.lemonjuice.nyaruru.Reference;
import io.lemonjuice.nyaruru.entities.misc.BubbleEntity;
import io.lemonjuice.nyaruru.entities.misc.FishCrossSlashEntity;
import io.lemonjuice.nyaruru.entities.monster.ForestMonster01Entity;
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
    public static final RegistryObject<EntityType<BubbleEntity>> BUBBLE = ENTITIES.register("bubble", () -> {
        return EntityType.Builder.of((EntityType<BubbleEntity> entityType, World worldIn) -> {
            return new BubbleEntity(entityType, worldIn);
        }, EntityClassification.MISC).sized(0.25F, 0.25F).build("bubble");
    });
    public static final RegistryObject<EntityType<ForestMonster01Entity>> FOREST_MST_01 = ENTITIES.register("forest_mst_01", () -> {
        return EntityType.Builder.of((EntityType<ForestMonster01Entity> entityType, World worldIn) -> {
            return new ForestMonster01Entity(entityType, worldIn);
        }, EntityClassification.MONSTER).sized(0.875F, 0.65F).build("forest_mst_01");
    });
}
