package io.lemonjuice.nyaruru.entities.monster;

import io.lemonjuice.nyaruru.entities.NEntityRegister;
import io.lemonjuice.nyaruru.entities.api.INyaruruEnemy;
import io.lemonjuice.nyaruru.entities.misc.BubbleEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

public class ForestMonster01Entity extends MonsterEntity implements IRangedAttackMob, INyaruruEnemy {
    public ForestMonster01Entity(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 29.0D).add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(3, new RangedAttackGoal(this, 0.0D, 40, 60, 8));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float f) {
        BubbleEntity bubble = new BubbleEntity(NEntityRegister.BUBBLE.get(), entity.level, this);
        float f7 = entity.yRot;
        float f8 = entity.xRot;
        float f1 = -MathHelper.sin(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f8 * ((float)Math.PI / 180F));
        float f3 = MathHelper.cos(f7 * ((float)Math.PI / 180F)) * MathHelper.cos(f8 * ((float)Math.PI / 180F));
        bubble.setDeltaMovement(-0.3F * f1, 0.0D, -0.3F * f3);
        bubble.setPosRaw(this.getX(), this.getY() + 0.3F, this.getZ());
        this.level.addFreshEntity(bubble);
    }
}
