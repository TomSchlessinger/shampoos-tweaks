package myshampooisdrunk.stackables.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin {

    @Inject(method = "applyPlayerEffects", at = @At("HEAD"), cancellable = true)
    private static void applyPlayerEffectsModified(World world, BlockPos pos, int beaconLevel, StatusEffect primaryEffect, StatusEffect secondaryEffect, CallbackInfo ci) {
        if (!world.isClient && primaryEffect != null) {
            //
            double d = (12.5)*(beaconLevel*(beaconLevel-1) + 2);
            /*
            Level  RangeOld  RangeNew
            1         20        25
            2         30        50
            3         40        100
            4         50        175
            5         60        275
            */
            int i = 0;
            if (beaconLevel >= 4 && primaryEffect == secondaryEffect) {
                i = 1;
            }

            int j = (9 + beaconLevel * 2) * 20;
            Box box = (new Box(pos)).expand(d).stretch(0.0D, (double)world.getHeight(), 0.0D);
            List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
            Iterator var11 = list.iterator();

            PlayerEntity playerEntity;
            while(var11.hasNext()) {
                playerEntity = (PlayerEntity)var11.next();
                playerEntity.addStatusEffect(new StatusEffectInstance(primaryEffect, j, i, true, true));
            }

            if (beaconLevel >= 4 && primaryEffect != secondaryEffect && secondaryEffect != null) {
                var11 = list.iterator();

                while(var11.hasNext()) {
                    playerEntity = (PlayerEntity)var11.next();
                    playerEntity.addStatusEffect(new StatusEffectInstance(secondaryEffect, j, 0, true, true));
                }
            }
        }
    }
}
