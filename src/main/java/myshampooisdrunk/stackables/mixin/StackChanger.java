package myshampooisdrunk.stackables.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//idea from https://github.com/Roundaround
@Mixin(Item.class)
public interface StackChanger {
    @Accessor("maxCount")
    void setMaxCount(int count);
}
