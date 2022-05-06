package com.nyaruru.items.fishes;

import com.nyaruru.client.render.itemstack.FishISTER;
import com.nyaruru.groups.NGroupRegister;
import com.nyaruru.items.NItemTiers;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ItemEusthenopteron extends SwordItem {
    public ItemEusthenopteron(){
        super(NItemTiers.FISH, 9, 0.0F, new Item.Properties().tab(NGroupRegister.WEAPONS).setISTER(() -> FishISTER::new));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }

    @Override
    public void onUseTick(World world, LivingEntity player, ItemStack stack, int tick) {
        if(tick == 1) {
            if(player instanceof PlayerEntity && !player.level.isClientSide) {
                PlayerUtil.setResource((PlayerEntity) player, Resources.CAT_GAZE_FLAG, 1);
                if (!((PlayerEntity) player).isCreative()) {
                    ItemStack stack1 = player.getItemInHand(player.getUsedItemHand());
                    stack1.hurtAndBreak(2, player, (playerEntity) -> {
                        playerEntity.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    player.setItemInHand(player.getUsedItemHand(), stack1);
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(PlayerUtil.getResource(player, Resources.POWER) > 0) {
            player.startUsingItem(hand);
            return ActionResult.consume(stack);
        } else {
            return ActionResult.pass(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.eusthenopteron_skill").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.eusthenopteron_power").withStyle(TextFormatting.GRAY));
    }
}