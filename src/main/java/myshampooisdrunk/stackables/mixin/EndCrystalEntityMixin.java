package myshampooisdrunk.stackables.mixin;

import myshampooisdrunk.stackables.world.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndCrystalEntity.class)
public class EndCrystalEntityMixin {
    @Inject(method="damage",at=@At("HEAD"),cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if ((((EndCrystalEntity)(Object)this).isInvulnerableTo(source))) {
            cir.setReturnValue(false);
        } else if (source.getAttacker() instanceof EnderDragonEntity) {
            cir.setReturnValue(false);
        } else {
            if (!((EndCrystalEntity)(Object)this).isRemoved() && !((EndCrystalEntity)(Object)this).world.isClient) {
                ((EndCrystalEntity)(Object)this).remove(Entity.RemovalReason.KILLED);
                if (!source.isIn(DamageTypeTags.IS_EXPLOSION)) {
                    Vec3d pos = ((EndCrystalEntity)(Object)this).getPos();
                    DamageSource damageSource = source.getAttacker() != null ? ((EndCrystalEntity)(Object)this).getDamageSources().explosion(((EndCrystalEntity)(Object)this), source.getAttacker()) : null;
                    WorldUtils.createExplosion(((EndCrystalEntity)(Object)this).world,(EndCrystalEntity)(Object)this, damageSource, (ExplosionBehavior)null, pos, 5.75F, false, World.ExplosionSourceType.BLOCK,0.5f);
                    ((EndCrystalEntity)(Object)this).world.createExplosion((EndCrystalEntity)(Object)this, damageSource, (ExplosionBehavior)null, pos.getX(), pos.getY(), pos.getZ(), 0F, false, World.ExplosionSourceType.BLOCK);

                }

                ((EndCrystalEntityAccessor)this).callCrystalDestroyed(source);
            }

            cir.setReturnValue(true);
        }
    }
}
