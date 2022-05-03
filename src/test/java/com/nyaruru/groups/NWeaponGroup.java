package com.nyaruru.groups;

import com.nyaruru.items.NItemRegister;
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
