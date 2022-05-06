package com.nyaruru.items.fishes;

import com.nyaruru.client.render.itemstack.FishISTER;
import com.nyaruru.entities.NEntityRegister;
import com.nyaruru.entities.misc.FishCrossSlashEntity;
import com.nyaruru.groups.NGroupRegister;
import com.nyaruru.items.NItemTiers;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ItemDeepSeaTuna extends SwordItem {
    public ItemDeepSeaTuna() {
        super(NItemTiers.FISH, 9, 0.0F, new Item.Properties().tab(NGroupRegister.WEAPONS).setISTER(() -> FishISTER::new));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }

    @Override
    public void onUseTick(World world, LivingEntity player, ItemStack stack, int tick) {
        if (tick == 1) {
            if (player instanceof PlayerEntity && !player.level.isClientSide) {
                SkillFishCrossSlash((PlayerEntity) player, world);
                if (!((PlayerEntity) player).isCreative()) {
                    ItemStack stack1 = player.getItemInHand(player.getUsedItemHand());
                    stack1.hurtAndBreak(2, player, (playerEntity) -> {
                        playerEntity.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    player.setItemInHand(player.getUsedItemHand(), stack1);
                }
                PlayerUtil.setResource((PlayerEntity) player, Resources.ADDING_POWER, -100);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (PlayerUtil.getResource(player, Resources.POWER) >= 100) {
            player.startUsingItem(hand);
            return ActionResult.consume(stack);
        } else {
            return ActionResult.pass(stack);
        }
    }

    private void SkillFishCrossSlash(PlayerEntity player, World world) {
        FishCrossSlashEntity slash = new FishCrossSlashEntity(NEntityRegister.FISH_CROSS_SLASH.get(), world);
        float f7 = player.yRot;
        float f = player.xRot;
        float f1 = -MathHelper.sin(f7 * ((float) Math.PI / 180F)) * MathHelper.cos(f * ((float) Math.PI / 180F));
        float f2 = -MathHelper.sin(f * ((float) Math.PI / 180F));
        float f3 = MathHelper.cos(f7 * ((float) Math.PI / 180F)) * MathHelper.cos(f * ((float) Math.PI / 180F));

        slash.xRot = player.xRot;
        slash.yRot = 180 - player.yRot;
        slash.setPos(player.position().x + 0.5 * f1, player.position().y + 0.6, player.position().z + 0.5 * f3);
        slash.setDeltaMovement(0.05 * f1, 0.05 * f2, 0.05 * f3);
        world.addFreshEntity(slash);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.deep_sea_tuna_skill").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.deep_sea_tuna_power").withStyle(TextFormatting.GRAY));
    }
}