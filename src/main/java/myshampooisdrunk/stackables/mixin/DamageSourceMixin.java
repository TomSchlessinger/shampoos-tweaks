package myshampooisdrunk.stackables.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(DamageSource.class)
abstract class DamageSourceMixin {
    @Inject(method="getDeathMessage",at=@At("HEAD"),cancellable = true)
    public void getDeathMessage(LivingEntity killed, CallbackInfoReturnable<Text> cir) {
        if (((DamageSource) (Object) this).getAttacker() != null) {
            Text stuff = null;
            DamageSource source = (DamageSource) (Object) this;
            boolean killedInvis = killed.isInvisible();
            boolean attackerInvis = source.getAttacker().isInvisible();
            String bozoString = "????????????????????";
            String string = "death.attack." + source.getType().msgId();
            if (source.getAttacker() == null && source.getSource() == null) {
                LivingEntity livingEntity2 = killed.getPrimeAdversary();
                String string2 = string + ".player";
                cir.setReturnValue(livingEntity2 != null ? Text.translatable(string2, killed.getDisplayName(), livingEntity2.getDisplayName()) : Text.translatable(string, killed.getDisplayName()));
            } else {
                Entity var6 = source.getAttacker();
                Text text2 = source.getAttacker() == null ? source.getSource().getDisplayName() : source.getAttacker().getDisplayName();

                Text text = source.getAttacker() == null ? killedInvis ? Text.literal(bozoString.substring(0, (int) (Math.random() * 17 + 3))).setStyle(Style.EMPTY.withObfuscated(true)) : source.getSource().getDisplayName() : attackerInvis ? Text.literal(bozoString.substring(0, (int) (Math.random() * 17 + 3))).setStyle(Style.EMPTY.withObfuscated(true)) : source.getAttacker().getDisplayName();
                ItemStack var10000;
                if (var6 instanceof LivingEntity livingEntity) {
                    var10000 = livingEntity.getMainHandStack();
                } else {
                    var10000 = ItemStack.EMPTY;
                }

                ItemStack itemStack = var10000;
                stuff = !itemStack.isEmpty() && itemStack.hasCustomName() ? Text.translatable(string + ".item", killed.getDisplayName(), text2, itemStack.toHoverableText()) : Text.translatable(string, killed.getDisplayName(), text2);
                if (attackerInvis) {
                    cir.setReturnValue(!itemStack.isEmpty() && itemStack.hasCustomName() ? Text.translatable(string + ".item", killed.getDisplayName(), text, Text.literal(bozoString.substring(0, (int) (Math.random() * 17 + 3))).setStyle(Style.EMPTY.withObfuscated(true))) : Text.translatable(string, killed.getDisplayName(), text));
                } else {
                    cir.setReturnValue(!itemStack.isEmpty() && itemStack.hasCustomName() ? Text.translatable(string + ".item", killed.getDisplayName(), text, itemStack.toHoverableText()) : Text.translatable(string, killed.getDisplayName(), text));
                }
            }
            System.out.println(stuff.getString());
            cir.cancel();
        }
    }
}