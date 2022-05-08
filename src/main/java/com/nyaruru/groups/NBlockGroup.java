package com.nyaruru.groups;

import com.nyaruru.items.NItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class NBlockGroup extends ItemGroup {
    public NBlockGroup(){
        super("nyaruruBlocks");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(NItemRegister.BOUNCY_MUSHROOM.get());
    }
}
