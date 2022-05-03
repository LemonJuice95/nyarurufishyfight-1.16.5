package com.nyaruru.groups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class NAccessoriesGroup extends ItemGroup {
    public NAccessoriesGroup(){
        super("nyaruruAccessories");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(Items.AIR);
    }
}
