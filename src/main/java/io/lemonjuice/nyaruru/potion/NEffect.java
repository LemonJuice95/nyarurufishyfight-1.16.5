package io.lemonjuice.nyaruru.potion;

import io.lemonjuice.nyaruru.utils.PlayerUtil;
import io.lemonjuice.nyaruru.utils.Resources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class NEffect extends Effect {
    public NEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int level) {
        if (this == NEffectRegister.SP_REGENERATING.get()) {
            if(entity instanceof PlayerEntity) {
                if(PlayerUtil.getResource((PlayerEntity) entity, Resources.SP) < Resources.SP.max - 1) {
                    PlayerUtil.setResource((PlayerEntity) entity, Resources.SP, PlayerUtil.getResource((PlayerEntity) entity, Resources.SP) + 1);
                }
            }
        }
        if(this == NEffectRegister.POWER_REGENERATING.get()) {
            if(entity instanceof PlayerEntity) {
                if(PlayerUtil.getResource((PlayerEntity) entity, Resources.POWER) + PlayerUtil.getResource((PlayerEntity) entity, Resources.ADDING_POWER) < Resources.POWER.max) {
                    PlayerUtil.setResource((PlayerEntity) entity, Resources.POWER, PlayerUtil.getResource((PlayerEntity) entity, Resources.POWER) + 1);
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int tick, int level) {
        if(this == NEffectRegister.SP_REGENERATING.get()) {
            return true;
        }
        if(this == NEffectRegister.POWER_REGENERATING.get()) {
            int j = 8 >> level;
            if (j > 0) {
                return tick % j == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
