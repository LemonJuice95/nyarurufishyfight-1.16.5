package io.lemonjuice.nyaruru.items.gemstones;

import io.lemonjuice.nyaruru.groups.NGroupRegister;
import io.lemonjuice.nyaruru.utils.PlayerUtil;
import io.lemonjuice.nyaruru.utils.Resources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;

public class ItemFluoriteFishEye extends Item implements ICurioItem {
    public ItemFluoriteFishEye() {
        super(new Item.Properties().tab(NGroupRegister.ACCESSORIES).stacksTo(1));
    }

    @Override
    @Nonnull
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 1.0F);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slot, ItemStack stack) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onUnequip(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if(!livingEntity.level.isClientSide) {
            if (livingEntity instanceof PlayerEntity) {
                PlayerUtil.setResource((PlayerEntity) livingEntity, Resources.FLUORITE_FISH_EYE, 0);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEquip(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if(!livingEntity.level.isClientSide) {
            if (livingEntity instanceof PlayerEntity) {
                PlayerUtil.setResource((PlayerEntity) livingEntity, Resources.FLUORITE_FISH_EYE, 1);
            }
        }
    }
}
