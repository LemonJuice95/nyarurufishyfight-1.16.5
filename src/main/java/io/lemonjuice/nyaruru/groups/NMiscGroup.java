package io.lemonjuice.nyaruru.groups;

import io.lemonjuice.nyaruru.items.NItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NMiscGroup extends ItemGroup {
    public NMiscGroup(){
        super("nyaruruMisc");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(NItemRegister.GOLD_CAT_COIN.get());
    }
}
