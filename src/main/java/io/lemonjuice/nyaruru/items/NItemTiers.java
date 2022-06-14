package io.lemonjuice.nyaruru.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum NItemTiers implements IItemTier {
    FISH(2,1500,0.0F,0.0F,15,()-> {
        return Ingredient.of(Items.COD);
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    private NItemTiers(int Level, int Uses, float Speed, float Damage, int EnchantmentValue, Supplier<Ingredient> RepairIngredient){
        this.level = Level;
        this.uses = Uses;
        this.damage = Damage;
        this.speed = Speed;
        this.enchantmentValue = EnchantmentValue;
        this.repairIngredient = new LazyValue<>(RepairIngredient);
    }

    @Override
    public int getUses(){
        return this.uses;
    }

    @Override
    public int getLevel(){
        return this.level;
    }

    @Override
    public float getSpeed(){
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus(){
        return this.damage;
    }

    @Override
    public int getEnchantmentValue(){
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient(){
        return this.repairIngredient.get();
    }
}
