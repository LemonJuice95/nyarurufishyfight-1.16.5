package io.lemonjuice.nyaruru.items;

import io.lemonjuice.nyaruru.groups.NGroupRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;

public class ItemCatBell extends Item {
    public ItemCatBell() {
        super(new Item.Properties().tab(NGroupRegister.MISC));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(!player.isCreative()) {
            stack.shrink(1);
            player.setItemInHand(hand, stack);
        }
        if(player instanceof ServerPlayerEntity)
            CuriosApi.getSlotHelper().growSlotType("charm", 1, player);
        return ActionResult.consume(stack);
    }
}
