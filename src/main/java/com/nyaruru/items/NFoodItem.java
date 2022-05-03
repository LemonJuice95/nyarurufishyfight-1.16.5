package com.nyaruru.items;

import com.nyaruru.groups.NGroupRegister;
import com.nyaruru.potion.NEffectRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class NFoodItem extends Item {
    public static final Food CABBAGE = (new Food.Builder()).saturationMod(0.1F).nutrition(1).alwaysEat().build();
    public static final Food CANNED_CAT_FOOD = (new Food.Builder()).saturationMod(0.1F).nutrition(1).alwaysEat().build();
    public static final Food MUSTARD = (new Food.Builder()).saturationMod(0.1F).nutrition(1).alwaysEat().effect(() -> new EffectInstance(Effects.REGENERATION, 2400, 0), 1).build();
    public static final Food GARLIC = (new Food.Builder()).saturationMod(0.1F).nutrition(1).alwaysEat().effect(() -> new EffectInstance(NEffectRegister.SP_REGENERATING.get(), 2400, 0), 1).build();
    public static final Food CHILLI = (new Food.Builder()).saturationMod(0.1F).nutrition(1).alwaysEat().effect(() -> new EffectInstance(NEffectRegister.POWER_REGENERATING.get(), 2400, 0), 1).build();
    public static final Food SURSTROMMING = (new Food.Builder()).saturationMod(0.1F).nutrition(1).alwaysEat().effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 400, 0), 1).build();

    public NFoodItem(Food food){
        super(new Item.Properties().food(food).tab(NGroupRegister.MISC));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        if(entity instanceof PlayerEntity) {
            if(this == NItemRegister.MUSTARD.get()) {
                entity.hurt(DamageSource.GENERIC, entity.getMaxHealth() * 0.15F);
            } else if(this == NItemRegister.CABBAGE.get()) {
                entity.heal(entity.getMaxHealth() * 0.3F);
            } else if(this == NItemRegister.CANNED_CAT_FOOD.get()) {
                entity.heal(entity.getMaxHealth() * 0.8F);
            }
        }
        return super.finishUsingItem(stack, world, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(this == NItemRegister.MUSTARD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.nyaruru.food.mustard_1").withStyle(TextFormatting.RED));
            tooltip.add(new TranslationTextComponent("tooltip.nyaruru.food.mustard_2").withStyle(TextFormatting.YELLOW));
        } else if(this == NItemRegister.CABBAGE.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.nyaruru.food.cabbage").withStyle(TextFormatting.GREEN));
        } else if(this == NItemRegister.CANNED_CAT_FOOD.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.nyaruru.food.canned_cat_food").withStyle(TextFormatting.GREEN));
        } else if(this == NItemRegister.GARLIC.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.nyaruru.food.garlic").withStyle(TextFormatting.YELLOW));
        } else if(this == NItemRegister.CHILLI.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.nyaruru.food.pepper").withStyle(TextFormatting.YELLOW));
        } else if(this == NItemRegister.SURSTROMMING.get()) {
            tooltip.add(new TranslationTextComponent("tooltip.nyaruru.food.surstromming").withStyle(TextFormatting.YELLOW));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}