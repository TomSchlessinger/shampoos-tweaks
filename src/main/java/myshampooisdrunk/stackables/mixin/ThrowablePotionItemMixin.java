package myshampooisdrunk.stackables.mixin;

import myshampooisdrunk.stackables.config.ModConfigs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThrowablePotionItem.class)
public abstract class ThrowablePotionItemMixin extends PotionItem {
    private ThrowablePotionItemMixin(Settings settings){
        super(settings);
    }
    @Inject(method="use", at=@At("TAIL"))
    private void onUse(World world, PlayerEntity user, Hand hand,CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
        user.getItemCooldownManager().set(this, ModConfigs.THROWABLE_POTION_COOLDOWN);
    }

}
