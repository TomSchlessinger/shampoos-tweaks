package myshampooisdrunk.stackables.mixin;

import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(World.class)
public interface WorldAccessor {
    //@Invoker
    //Explosion.DestructionType callGetDestructionType(GameRules.Key<GameRules.BooleanRule> gameRuleKey);
}
