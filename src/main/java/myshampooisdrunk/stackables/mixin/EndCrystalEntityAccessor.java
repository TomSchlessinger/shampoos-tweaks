package myshampooisdrunk.stackables.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EndCrystalEntity.class)
public interface EndCrystalEntityAccessor {
    @Invoker
    void callCrystalDestroyed(DamageSource source);
}
