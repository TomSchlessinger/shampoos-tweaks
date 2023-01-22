package myshampooisdrunk.stackables.mixin;

import myshampooisdrunk.stackables.config.ModConfigs;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Items.class,priority=1221)
public abstract class ItemsMixin {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/PotionItem;", ordinal = 0), to = @At("TAIL")))
    private static int onPotion(int i) {
        ModConfigs.registerConfigs();
        System.out.println("I should be working " + ModConfigs.MAX_POTION_STACK);
        return ModConfigs.MAX_POTION_STACK;
    }

    //mushroom
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice(from = @At(value = "NEW", target = "Lnet/minecraft/item/StewItem;", ordinal = 0), to = @At(value = "NEW", target = "Lnet/minecraft/item/StewItem;", ordinal = 2)))
    private static int new_size_stew0(int i) {
        ModConfigs.registerConfigs();
        return ModConfigs.MAX_STEW_STACK;
    }
    //rabbit
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice(from = @At(value = "NEW", target = "Lnet/minecraft/item/StewItem;", ordinal = 1), to = @At(value = "NEW", target = "Lnet/minecraft/item/StewItem;", ordinal = 2)))
    private static int new_size_stew1(int i) {
        ModConfigs.registerConfigs();
        return ModConfigs.MAX_STEW_STACK;
    }
    //beetroot
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice(from = @At(value = "NEW", target = "Lnet/minecraft/item/StewItem;", ordinal = 2), to = @At("TAIL")))
    private static int new_size_stew2(int i) {
        ModConfigs.registerConfigs();
        return ModConfigs.MAX_STEW_STACK;
    }
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice(from = @At(value = "NEW", target = "Lnet/minecraft/item/SuspiciousStewItem;", ordinal = 0), to = @At("TAIL")))
    private static int new_size_stew3(int i) {
        ModConfigs.registerConfigs();
        return ModConfigs.MAX_SUS_STACK;
    }
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/SplashPotionItem;", ordinal = 0), to = @At("TAIL")))
    private static int onSplashPotion(int i) {
        ModConfigs.registerConfigs();
        System.out.println("max splash: " + ModConfigs.MAX_SPLASH_POTION_STACK);
        return ModConfigs.MAX_SPLASH_POTION_STACK;
    }
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/LingeringPotionItem;", ordinal = 0), to = @At("TAIL")))
    private static int onLingeringPotion(int i) {
        ModConfigs.registerConfigs();
        System.out.println("max linger: " + ModConfigs.MAX_LINGERING_POTION_STACK);
        return ModConfigs.MAX_LINGERING_POTION_STACK;
    }
}
