package com.nyaruru.items.fishes;

import com.nyaruru.groups.NGroupRegister;
import com.nyaruru.items.NItemTiers;
import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemHyneriaLindae extends SwordItem {
    public ItemHyneriaLindae(){
        super(NItemTiers.FISH, 9, 0.0F, new Item.Properties().tab(NGroupRegister.WEAPONS));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }

    @Override
    public void onUseTick(World world, LivingEntity player, ItemStack stack, int tick) {
        if(tick == 1) {
            if(player instanceof PlayerEntity) {
                float f2 = 32.0F;
                int k1 = MathHelper.floor(player.getX() - (double)f2 - 1.0D);
                int l1 = MathHelper.floor(player.getX() + (double)f2 + 1.0D);
                int i2 = MathHelper.floor(player.getY() - (double)f2 - 1.0D);
                int i1 = MathHelper.floor(player.getY() + (double)f2 + 1.0D);
                int j2 = MathHelper.floor(player.getZ() - (double)f2 - 1.0D);
                int j1 = MathHelper.floor(player.getZ() + (double)f2 + 1.0D);
                List<Entity> list = player.level.getEntities(player, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
                for(int i = 0; i < list.size(); i++) {
                    Entity entity = list.get(i);
                    if(entity != player)
                        entity.hurt(DamageSource.playerAttack((PlayerEntity) player), 10);
                }
                for(int j = 0; j <= 100; j++) {
                    Random random = new Random();
                    player.level.addParticle(ParticleTypes.EXPLOSION, player.getX() + random.nextInt() % 32, player.getY() + random.nextInt() % 32, player.getZ() + random.nextInt() % 32, 0.0D, 0.0D, 0.0D);
                    player.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, player.getX() + random.nextInt() % 32, player.getY() + random.nextInt() % 32, player.getZ() + random.nextInt() % 32, 0.0D, 0.0D, 0.0D);
                }
                if (!((PlayerEntity) player).isCreative()) {
                    ItemStack stack1 = player.getItemInHand(player.getUsedItemHand());
                    stack1.hurtAndBreak(2, player, (playerEntity) -> {
                        playerEntity.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    player.setItemInHand(player.getUsedItemHand(), stack1);
                }
                PlayerUtil.setResource((PlayerEntity) player, Resources.ADDING_POWER, -75);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(PlayerUtil.getResource(player, Resources.POWER) >= 75) {
            player.startUsingItem(hand);
            return ActionResult.consume(stack);
        } else {
            return ActionResult.pass(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.hyneria_lindae_skill").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("tooltip.nyaruru.hyneria_lindae_power").withStyle(TextFormatting.GRAY));
    }
}