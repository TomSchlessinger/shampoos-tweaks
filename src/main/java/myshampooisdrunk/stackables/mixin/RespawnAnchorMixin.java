package myshampooisdrunk.stackables.mixin;

import myshampooisdrunk.stackables.config.ModConfigs;
import myshampooisdrunk.stackables.world.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.FluidState;
import net.minecraft.world.explosion.Explosion.DestructionType;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.stream.Stream;

import static myshampooisdrunk.stackables.config.ModConfigs.ANCHOR_MULTIPLIER;
import static myshampooisdrunk.stackables.mixin.RespawnAnchorBlockAccessor.callHasStillWater;
@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorMixin {

    @Shadow @Final public static IntProperty CHARGES;

    @Shadow
    protected static boolean hasStillWater(BlockPos pos, World world) {
        return callHasStillWater(pos,world);
    }

    @Inject(method="explode",at=@At("HEAD"),cancellable = true)
    private void explode(BlockState state, World world, BlockPos explodedPos, CallbackInfo ci) {
        ModConfigs.registerConfigs();
        int level = state.get(CHARGES);
        world.removeBlock(explodedPos, false);
        Stream<Direction> horizontalDirections = Direction.Type.HORIZONTAL.stream();
        boolean bl = horizontalDirections.map(explodedPos::offset).anyMatch((pos) -> hasStillWater(pos,world));
        final boolean bl2 = bl || world.getFluidState(explodedPos.up()).isIn(FluidTags.WATER);
        ExplosionBehavior explosionBehavior = new ExplosionBehavior() {
            public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
                return pos.equals(explodedPos) && bl2 ? Optional.of(Blocks.WATER.getBlastResistance()) : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
            }
        };
        WorldUtils.createExplosion(world,null, DamageSource.badRespawnPoint(), explosionBehavior, (double)explodedPos.getX() + 0.5D, (double)explodedPos.getY() + 0.5D, (double)explodedPos.getZ() + 0.5D, (float) (3 + level * 0.5), true, DestructionType.DESTROY,(float)(level)/4 * ANCHOR_MULTIPLIER);
        ci.cancel();
        world.createExplosion(null, DamageSource.badRespawnPoint(), explosionBehavior, (double)explodedPos.getX() + 0.5D, (double)explodedPos.getY() + 0.5D, (double)explodedPos.getZ() + 0.5D, 0f, false, DestructionType.DESTROY);
    }

}