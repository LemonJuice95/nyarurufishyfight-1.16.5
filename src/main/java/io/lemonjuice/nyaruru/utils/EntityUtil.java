package io.lemonjuice.nyaruru.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class EntityUtil {
    public static EntityRayTraceResult rayTraceEntities(World world, Entity entity, Vector3d startVec, Vector3d endVec, Predicate<Entity> predicate) {
        return ProjectileHelper.getEntityHitResult(world, entity, startVec, endVec,
                entity.getBoundingBox().expandTowards(entity.getDeltaMovement()).inflate(1.0D), predicate);
    }

    public static boolean isEntityValid(Entity target) {
        return target != null && target.isAlive();
    }
}
