package myshampooisdrunk.stackables.mixin;

import myshampooisdrunk.stackables.config.ModConfigs;
import myshampooisdrunk.stackables.world.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.explosion.Explosion.DestructionType;

import static myshampooisdrunk.stackables.config.ModConfigs.CRYSTAL_MULTIPLIER;

@Mixin(EndCrystalEntity.class)
public class EndCrystalEntityMixin {
    @Inject(method="damage",at=@At("HEAD"),cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ModConfigs.registerConfigs();
        if ((((EndCrystalEntity)(Object)this).isInvulnerableTo(source))) {
            cir.setReturnValue(false);
        } else if (source.getAttacker() instanceof EnderDragonEntity) {
            cir.setReturnValue(false);
        } else {
            if (!((EndCrystalEntity)(Object)this).isRemoved() && !((EndCrystalEntity)(Object)this).world.isClient) {
                ((EndCrystalEntity)(Object)this).remove(Entity.RemovalReason.KILLED);
                if (!source.isExplosive()) {
                    DamageSource damageSource = null;
                    Vec3d pos = ((EndCrystalEntity)(Object)this).getPos();
                    if ( source.getAttacker() instanceof LivingEntity l){
                        damageSource = DamageSource.explosion((LivingEntity) source.getAttacker());
                    }
                    WorldUtils.createExplosion(((EndCrystalEntity)(Object)this).world,(EndCrystalEntity)(Object)this, damageSource, (ExplosionBehavior)null, pos, 5.75F, false, DestructionType.DESTROY,CRYSTAL_MULTIPLIER/2);
                    ((EndCrystalEntity)(Object)this).world.createExplosion((EndCrystalEntity)(Object)this, damageSource, (ExplosionBehavior)null, pos.getX(), pos.getY(), pos.getZ(), 0F, false, DestructionType.DESTROY);

                }

                ((EndCrystalEntityAccessor)this).callCrystalDestroyed(source);
            }

            cir.setReturnValue(true);
        }
    }
    /*
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source.getAttacker() instanceof EnderDragonEntity) {
            return false;
        } else {
            if (!this.isRemoved() && !this.world.isClient) {
                this.remove(RemovalReason.KILLED);
                if (!source.isExplosive()) {
                    this.world.createExplosion((Entity)null, this.getX(), this.getY(), this.getZ(), 6.0F, DestructionType.DESTROY);
                }

                this.crystalDestroyed(source);
            }

            return true;
        }
    }
    */
}
