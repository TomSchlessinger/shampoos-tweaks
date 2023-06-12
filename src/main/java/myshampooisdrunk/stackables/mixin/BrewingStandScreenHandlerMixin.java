//Not my code, it's CursedFlames' https://github.com/CursedFlame
//https://github.com/CursedFlames/StackablePotions/blob/master/src/main/java/cursedflames/stackablepotions/mixin/BrewingStandScreenHandlerMixin.java

package myshampooisdrunk.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

@Mixin(BrewingStandScreenHandler.class)
public abstract class BrewingStandScreenHandlerMixin extends ScreenHandler {
    private BrewingStandScreenHandlerMixin(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "quickMove",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;matches(Lnet/minecraft/item/ItemStack;)Z"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true)
    private void onTransferSlot(PlayerEntity player, int index, CallbackInfoReturnable<ItemStack> info,
                                ItemStack itemStack, Slot slot, ItemStack itemStack2) {
        if (slot.canInsert(itemStack)) {
            boolean movedItems = false;
            for (int i = 0; i < 3; i++) {
                Slot slot2 = this.getSlot(i);
                if (slot2.getStack().isEmpty() && slot.canInsert(itemStack2)) {
                    if (itemStack2.getCount() > slot2.getMaxItemCount()) {
                        slot2.setStack(itemStack2.split(slot2.getMaxItemCount()));
                    } else {
                        slot2.setStack(itemStack2.split(itemStack2.getCount()));
                    }
                    movedItems = true;
                    slot2.markDirty();
                    if (itemStack2.isEmpty()) break;
                }
            }
            if (movedItems) {
                info.setReturnValue(ItemStack.EMPTY);
            }
        }
    }

    @Redirect(method = "quickMove",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/screen/BrewingStandScreenHandler$PotionSlot;matches(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean onTransferSlotRedirect(ItemStack stack, PlayerEntity player, int index) {
        return false;
    }
}

