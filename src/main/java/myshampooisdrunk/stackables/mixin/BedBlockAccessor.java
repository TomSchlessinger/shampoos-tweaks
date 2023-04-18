package myshampooisdrunk.stackables.mixin;

import net.minecraft.block.BedBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BedBlock.class)
public interface BedBlockAccessor {
    @Invoker
    boolean callWakeVillager(World world, BlockPos pos);
}
