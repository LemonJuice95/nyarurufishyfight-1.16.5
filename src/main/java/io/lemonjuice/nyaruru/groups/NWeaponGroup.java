package io.lemonjuice.nyaruru.groups;

import io.lemonjuice.nyaruru.items.NItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NWeaponGroup extends ItemGroup {
    public NWeaponGroup(){
        super("nyaruruWeapons");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(NItemRegister.DEEP_SEA_TUNA.get());
    }
}
