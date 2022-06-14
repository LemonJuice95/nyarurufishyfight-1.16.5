package io.lemonjuice.nyaruru.groups;

import io.lemonjuice.nyaruru.items.NItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NBlockGroup extends ItemGroup {
    public NBlockGroup(){
        super("nyaruruBlocks");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(NItemRegister.BOUNCY_MUSHROOM.get());
    }
}
