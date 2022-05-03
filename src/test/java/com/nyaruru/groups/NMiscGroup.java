package com.nyaruru.groups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class NMiscGroup extends ItemGroup {
    public NMiscGroup(){
        super("nyaruruMisc");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(Items.AIR);
    }
}
