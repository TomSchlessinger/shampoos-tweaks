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
    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, double x, double y, double z, float power, World.ExplosionSourceType explosionSourceType, float multiplier) {
        return createExplosion(world,entity, (DamageSource)null, (ExplosionBehavior)null, x, y, z, power, false, explosionSourceType,multiplier);
    }
    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, float multiplier) {
        return createExplosion(world,entity, (DamageSource)null, (ExplosionBehavior)null, x, y, z, power, createFire, explosionSourceType,multiplier);
    }
    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, Vec3d pos, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, float multiplier) {
        return createExplosion(world,entity, damageSource, behavior, pos.getX(), pos.getY(), pos.getZ(), power, createFire, explosionSourceType,multiplier);
    }
    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, float multiplier) {
        return createExplosion(world, entity, damageSource, behavior, x, y, z, power, createFire, explosionSourceType, true,multiplier);
    }

    public static GoofyExplosion createExplosion(World world,@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, boolean particles, float multiplier) {
        Explosion.DestructionType var10000;
        switch (explosionSourceType) {
            case NONE:
                var10000 = Explosion.DestructionType.KEEP;
                break;
            case BLOCK:
                var10000 = ((WorldAccessor)world).callGetDestructionType(GameRules.BLOCK_EXPLOSION_DROP_DECAY);
                break;
            case MOB:
                var10000 = world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? ((WorldAccessor)world).callGetDestructionType(GameRules.MOB_EXPLOSION_DROP_DECAY) : Explosion.DestructionType.KEEP;
                break;
            case TNT:
                var10000 = ((WorldAccessor)world).callGetDestructionType(GameRules.TNT_EXPLOSION_DROP_DECAY);
                break;
            default:
                throw new IncompatibleClassChangeError();
        }
        Explosion.DestructionType destructionType = var10000;
        GoofyExplosion explosion = new GoofyExplosion(world, entity, damageSource, behavior, x, y, z, power * 1.25f, createFire, destructionType, multiplier);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(particles);
        return explosion;
    }
}
