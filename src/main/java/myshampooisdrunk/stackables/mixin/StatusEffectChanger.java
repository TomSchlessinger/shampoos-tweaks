package myshampooisdrunk.stackables.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(FoodComponent.class)
public interface StatusEffectChanger {
    @Accessor("statusEffects")
    void setStatusEffects(List<Pair<StatusEffectInstance, Float>> effects);
}
