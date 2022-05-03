package com.nyaruru.groups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class NBlockGroup extends ItemGroup {
    public NBlockGroup(){
        super("nyaruruBlocks");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(Items.AIR);
    }
}
