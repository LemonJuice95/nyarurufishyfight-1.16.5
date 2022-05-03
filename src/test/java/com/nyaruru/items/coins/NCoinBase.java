package com.nyaruru.items.coins;

import com.nyaruru.groups.NGroupRegister;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class NCoinBase extends Item {

    public abstract int getAmount();

    public NCoinBase() {
        super(new Item.Properties().tab(NGroupRegister.MISC));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(!player.isCreative()) {
            stack.shrink(1);
            player.setItemInHand(hand, stack);
        }
        PlayerUtil.setResource(player, Resources.ADDING_MONEY, PlayerUtil.getResource(player, Resources.ADDING_MONEY) + getAmount());
        return ActionResult.consume(stack);
    }
}