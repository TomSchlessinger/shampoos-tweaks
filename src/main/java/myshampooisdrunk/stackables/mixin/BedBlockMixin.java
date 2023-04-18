package myshampooisdrunk.stackables.mixin;

import myshampooisdrunk.stackables.world.WorldUtils;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.BedBlock.*;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {
    @Inject(method="onUse",at=@At("HEAD"),cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (world.isClient) {
            cir.setReturnValue(ActionResult.CONSUME);
        } else {
            if (state.get(PART) != BedPart.HEAD) {
                pos = pos.offset(state.get(FACING));
                state = world.getBlockState(pos);
                if (!state.isOf((BedBlock)(Object)this)) {
                    cir.setReturnValue(ActionResult.CONSUME);
                }
            }

            if (!isBedWorking(world)) {
                world.removeBlock(pos, false);
                BlockPos blockPos = pos.offset(((Direction)state.get(FACING)).getOpposite());
                if (world.getBlockState(blockPos).isOf((BedBlock)(Object)this)) {
                    world.removeBlock(blockPos, false);
                }

                Vec3d vec3d = pos.toCenterPos();
                WorldUtils.createExplosion(world,null, world.getDamageSources().badRespawnPoint(vec3d), (ExplosionBehavior)null, vec3d, 4.5f, true, World.ExplosionSourceType.BLOCK,0.25f);
                cir.setReturnValue(ActionResult.SUCCESS);
                world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), null, vec3d, 0f, false, World.ExplosionSourceType.BLOCK);
            } else if (state.get(OCCUPIED)) {
                if (!((BedBlockAccessor)this).callWakeVillager(world, pos)) {
                    player.sendMessage(Text.translatable("block.minecraft.bed.occupied"), true);
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            } else {
                player.trySleep(pos).ifLeft((reason) -> {
                    if (reason.getMessage() != null) {
                        player.sendMessage(reason.getMessage(), true);
                    }

                });
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
