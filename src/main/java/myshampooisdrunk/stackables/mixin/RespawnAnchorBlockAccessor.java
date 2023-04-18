package myshampooisdrunk.stackables.mixin;

import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RespawnAnchorBlock.class)
public interface RespawnAnchorBlockAccessor {
    @Invoker
    static boolean callHasStillWater(BlockPos pos, World world) {
        throw new UnsupportedOperationException();
    }
}
