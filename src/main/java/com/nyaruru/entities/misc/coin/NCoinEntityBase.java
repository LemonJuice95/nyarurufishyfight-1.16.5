package com.nyaruru.entities.misc.coin;

import com.nyaruru.utils.PlayerUtil;
import com.nyaruru.utils.Resources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class NCoinEntityBase extends Entity {
    public NCoinEntityBase(EntityType<? extends NCoinEntityBase> type, World worldIn) {
        super(type, worldIn);
    }

    public abstract int getAmount();

    @Override
    public void playerTouch(PlayerEntity player) {
        if(! level.isClientSide) {
            PlayerUtil.setResource(player, Resources.ADDING_MONEY, PlayerUtil.getResource(player, Resources.ADDING_MONEY) + getAmount());
            this.remove();
        }
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void defineSynchedData() {
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
    }

    @Override
    public void tick() {
        if(!level.isClientSide){
            if(this.tickCount >= 6000){
                this.remove();
            }
        }
        Vector3d vec3d = this.getDeltaMovement();
        double d0 = this.getX() + vec3d.x;
        double d1 = this.getY() + vec3d.y;
        double d2 = this.getZ() + vec3d.z;

        while (this.xRot - this.xRotO >= 180.0F) {
            this.xRotO += 360.0F;
        }
        while (this.yRot - this.yRotO < -180.0F) {
            this.yRotO -= 360.0F;
        }
        while (this.yRot - this.yRotO >= 180.0F) {
            this.yRotO += 360.0F;
        }

        if(! this.isOnGround()){
            this.setDeltaMovement(vec3d.x != 0 ? (vec3d.x > 0 ? vec3d.x - 0.03 : vec3d.x + 0.03) : vec3d.x, vec3d.y - 0.03D, vec3d.z != 0 ? (vec3d.z > 0 ? vec3d.z - 0.03 : vec3d.z + 0.03) : d2);
        }
        else{
            this.setDeltaMovement(vec3d.x != 0 ? (vec3d.x > 0 ? vec3d.x - 0.03 : vec3d.x + 0.03) : vec3d.x, vec3d.y, vec3d.z != 0 ? (vec3d.z > 0 ? vec3d.z - 0.03 : vec3d.z + 0.03) : d2);
        }
        this.setPos(d0, d1 ,d2);
    }
}