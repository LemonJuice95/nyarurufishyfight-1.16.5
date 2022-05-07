package com.nyaruru.items.fishes;

import com.nyaruru.client.render.itemstack.FishISTER;
import com.nyaruru.groups.NGroupRegister;
import com.nyaruru.items.NItemTiers;
import com.nyaruru.network.NPacketHandler;
import com.nyaruru.network.toserver.PlayerStatsPacketToServer;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ItemEsoxAmericanus extends SwordItem {
    public ItemEsoxAmericanus() {
        super(NItemTiers.FISH, 9, 0.0F, new Item.Properties().tab(NGroupRegister.WEAPONS));
//                .setISTER(() -> FishISTER::new));
    }


    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }

    @Override
    public void onUseTick(World world, LivingEntity player, ItemStack stack, int tick) {
        if(tick == 1) {
            if(player instanceof PlayerEntity) {
                float f7 = player.yRot;
                float f = 0.0F;
                float f1 = -MathHelper.sin(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                float f3 = MathHelper.cos(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f * ((float)Math.PI / 180F));
                PlayerUtil.setResource((PlayerEntity) player, Resources.DRAW_FISH_CHOPPING_TICKS, 10);
                if (!((PlayerEntity) player).isCreative()) {
                    ItemStack stack1 = player.getItemInHand(player.getUsedItemHand());
                    stack1.hurtAndBreak(2, player, (playerEntity) -> {
                        playerEntity.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    player.setItemInHand(player.getUsedItemHand(), stack1);
                }
                player.push(2.0 * f1, 0.03, 2.0 * f3);
                PlayerUtil.setResource((PlayerEntity) player, Resources.ADDING_POWER, -50);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(PlayerUtil.getResource(player, Resources.POWER) >= 50) {
            player.startUsingItem(hand);
            return ActionResult.consume(stack);
        } else {
            return ActionResult.pass(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.esox_americanus_skill").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.esox_americanus_power").withStyle(TextFormatting.GRAY));
    }
}