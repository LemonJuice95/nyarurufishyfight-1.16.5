package io.lemonjuice.nyaruru.items.accessories;

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

public class ItemForceFieldShield extends Item implements ICurioItem {
    public ItemForceFieldShield() {
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

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if(!livingEntity.level.isClientSide) {
            if(livingEntity instanceof PlayerEntity) {
                int tick = PlayerUtil.getResource((PlayerEntity) livingEntity, Resources.SHIELD_TICK);
                if(tick < 1200) {
                    PlayerUtil.setResource((PlayerEntity) livingEntity, Resources.SHIELD_TICK, tick + 1);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onUnequip(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if(!livingEntity.level.isClientSide) {
            if (livingEntity instanceof PlayerEntity) {
                PlayerUtil.setResource((PlayerEntity) livingEntity, Resources.HAS_SHIELD, 0);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEquip(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if(!livingEntity.level.isClientSide) {
            if (livingEntity instanceof PlayerEntity) {
                PlayerUtil.setResource((PlayerEntity) livingEntity, Resources.HAS_SHIELD, 1);
            }
        }
    }
}
