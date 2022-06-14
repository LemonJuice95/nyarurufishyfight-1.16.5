package io.lemonjuice.nyaruru.entities.misc;

import io.lemonjuice.nyaruru.utils.EntityUtil;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;


public class FishCrossSlashEntity extends Entity {

    public FishCrossSlashEntity(EntityType<? extends FishCrossSlashEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {

    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        PacketBuffer pack = new PacketBuffer(Unpooled.buffer());
        pack.writeDouble(getX());
        pack.writeDouble(getY());
        pack.writeDouble(getZ());
        pack.writeInt(getId());
        pack.writeUUID(getUUID());
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        if (! level.isClientSide && this.tickCount >= 60) {
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
                target.hurt(DamageSource.MAGIC, 10.0F);
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