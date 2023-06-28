package myshampooisdrunk.stackables.world;

import myshampooisdrunk.stackables.mixin.WorldAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

public class WorldUtils {
    public static GoofyExplosion createExplosion(World world, @Nullable Entity entity, double x, double y, double z, float power, Explosion.DestructionType explosionSourceType, float multiplier) {
        return createExplosion(world,entity, (DamageSource)null, (ExplosionBehavior)null, x, y, z, power, false, explosionSourceType,multiplier);
    }
    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, double x, double y, double z, float power, boolean createFire, Explosion.DestructionType explosionSourceType, float multiplier) {
        return createExplosion(world,entity, (DamageSource)null, (ExplosionBehavior)null, x, y, z, power, createFire, explosionSourceType,multiplier);
    }
    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, Vec3d pos, float power, boolean createFire, Explosion.DestructionType explosionSourceType, float multiplier) {
        return createExplosion(world,entity, damageSource, behavior, pos.getX(), pos.getY(), pos.getZ(), power, createFire, explosionSourceType,multiplier);
    }
    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, Explosion.DestructionType explosionSourceType, float multiplier) {
        return createExplosion(world, entity, damageSource, behavior, x, y, z, power, createFire, explosionSourceType, true,multiplier);
    }

    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, Explosion.DestructionType explosionSourceType, boolean particles, float multiplier) {

        Explosion.DestructionType destructionType = explosionSourceType;
        GoofyExplosion explosion = new GoofyExplosion(world, entity, damageSource, behavior, x, y, z, power * 1.25f, createFire, destructionType, multiplier);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(particles);
        return explosion;
    }
}
