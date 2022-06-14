package io.lemonjuice.nyaruru.groups;

import io.lemonjuice.nyaruru.items.NItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NAccessoriesGroup extends ItemGroup {
    public NAccessoriesGroup(){
        super("nyaruruAccessories");
    }

    @Override
    public ItemStack makeIcon(){
        return new ItemStack(NItemRegister.RED_JADE_FISH_EYE.get());
    }
}
