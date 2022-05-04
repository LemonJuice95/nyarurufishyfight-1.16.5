package com.nyaruru.entities.misc;

import com.nyaruru.entities.api.IHasOwner;
import com.nyaruru.utils.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class BubbleEntity extends Entity implements IHasOwner {
    protected Entity owner = null;
    protected UUID ownerId = null;

    public BubbleEntity(EntityType<? extends BubbleEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BubbleEntity(EntityType<? extends BubbleEntity> type, World worldIn, Entity entity) {
        super(type, worldIn);
        this.owner = entity;
        this.ownerId = entity.getUUID();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        if (this.ownerId != null) {
            compound.put("owner", NBTUtil.createUUID(this.ownerId));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        this.owner = null;
        if (compound.contains("owner", 10)) {
            this.ownerId = NBTUtil.loadUUID(compound.getCompound("owner"));
        }
        if(this.level instanceof ServerWorld)
            this.owner = ((ServerWorld) this.level).getEntity(this.ownerId);
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
    public Entity getOwner() {
        return this.owner;
    }

    @Override
    public Optional<UUID> getOwnerUUID() {
        return Optional.ofNullable(this.uuid);
    }

    @Override
    public void tick() {
        if(!level.isClientSide && this.tickCount >= 120) {
            this.remove();
        }
        if(! level.isClientSide) {
            Vector3d start = this.position();
            Vector3d end = start.add(this.getDeltaMovement());
            RayTraceResult result = this.level.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));

            EntityRayTraceResult entityRay = this.rayTraceEntities(start, result.getLocation());
            if(entityRay != null) {
                result = entityRay;
            }
            if(result != null && result.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, result)) {//on hit
                this.onImpact(result);
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

        this.setPos(d0, d1, d2);
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity target = ((EntityRayTraceResult) result).getEntity();
            if (! (target instanceof PlayerEntity)) {
                target.invulnerableTime = 0;
                target.hurt(DamageSource.MAGIC, 1.8F);
            }
        }
        this.level.broadcastEntityEvent(this, (byte) 3);
    }

    @Nullable
    protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
        return EntityUtil.rayTraceEntities(level, this, startVec, endVec, entity ->
                entity.isPickable()
        );
    }
}
