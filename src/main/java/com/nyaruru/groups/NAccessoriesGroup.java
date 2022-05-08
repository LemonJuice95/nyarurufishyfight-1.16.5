package com.nyaruru.groups;

import com.nyaruru.items.NItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class NAccessoriesGroup extends ItemGroup {
    public NAccessoriesGroup(){
        super("nyaruruAccessories");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(NItemRegister.RED_JADE_FISH_EYE.get());
    }
}
