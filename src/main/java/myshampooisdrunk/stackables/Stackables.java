//holy shit why is this so similar to Roundaround's mod I didn't actually mean for this to happen sorry Roundaround.
//Here's his github: https://github.com/Roundaround/ his mod is much better than mine

package myshampooisdrunk.stackables;

import com.mojang.datafixers.util.Pair;
import myshampooisdrunk.stackables.config.ModConfigs;
import myshampooisdrunk.stackables.mixin.StackChanger;
import myshampooisdrunk.stackables.mixin.StatusEffectChanger;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class Stackables implements ModInitializer {
    public static final String MOD_ID = "stackable_unstackables";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize(){
        ModConfigs.registerConfigs();
        stackInit();
    }

    private void stackInit(){
        ((StatusEffectChanger) FoodComponents.ENCHANTED_GOLDEN_APPLE).setStatusEffects(getStatusEffects(new StatusEffectInstance[] {
                new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,12000,0),
                new StatusEffectInstance(StatusEffects.RESISTANCE,600,1),
                new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 1),
                new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1)}
                ,new float[]{1F,1F,1F,1F}
        ));
        ((StackChanger) Items.POTION).setMaxCount(ModConfigs.MAX_POTION_STACK);
        ((StackChanger) Items.LINGERING_POTION).setMaxCount(ModConfigs.MAX_LINGERING_POTION_STACK);
        ((StackChanger) Items.SPLASH_POTION).setMaxCount(ModConfigs.MAX_SPLASH_POTION_STACK);
        ((StackChanger) Items.MINECART).setMaxCount(ModConfigs.MINECART_STACK);
        ((StackChanger) Items.CHEST_MINECART).setMaxCount(ModConfigs.MINECART_STACK);
        ((StackChanger) Items.HOPPER_MINECART).setMaxCount(ModConfigs.MINECART_STACK);
        ((StackChanger) Items.COMMAND_BLOCK_MINECART).setMaxCount(ModConfigs.MINECART_STACK);
        ((StackChanger) Items.FURNACE_MINECART).setMaxCount(ModConfigs.MINECART_STACK);
        ((StackChanger) Items.TNT_MINECART).setMaxCount(ModConfigs.MINECART_STACK);
        ((StackChanger) Items.OAK_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.OAK_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.SPRUCE_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.SPRUCE_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.BIRCH_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.BIRCH_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.JUNGLE_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.JUNGLE_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.ACACIA_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.ACACIA_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.DARK_OAK_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.DARK_OAK_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.MANGROVE_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.MANGROVE_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.CHERRY_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.CHERRY_CHEST_BOAT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger) Items.MUSIC_DISC_13).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_CAT).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_BLOCKS).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_CHIRP).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_FAR).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_MALL).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_MELLOHI).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_STAL).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_STRAD).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_WARD).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_11).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_WAIT).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_OTHERSIDE).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_5).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.MUSIC_DISC_PIGSTEP).setMaxCount(ModConfigs.DISC_STACK);
        ((StackChanger) Items.CAKE).setMaxCount(ModConfigs.CAKE_STACK);
        ((StackChanger) Items.MUSHROOM_STEW).setMaxCount(ModConfigs.MAX_STEW_STACK);
        ((StackChanger) Items.RABBIT_STEW).setMaxCount(ModConfigs.MAX_STEW_STACK);
        ((StackChanger) Items.BEETROOT_SOUP).setMaxCount(ModConfigs.MAX_STEW_STACK);
        ((StackChanger) Items.SUSPICIOUS_STEW).setMaxCount(ModConfigs.MAX_SUS_STACK);
        ((StackChanger) Items.ENCHANTED_BOOK).setMaxCount(ModConfigs.MAX_BOOK_STACK);
        ((StackChanger)Items.BAMBOO_CHEST_RAFT).setMaxCount(ModConfigs.BOAT_STACK);
        ((StackChanger)Items.BAMBOO_RAFT).setMaxCount(ModConfigs.BOAT_STACK);

    }

    public static List<Pair<StatusEffectInstance,Float>> getStatusEffects(StatusEffectInstance[] effects, float[] chance) {
        List list = new ArrayList<Pair<StatusEffectInstance,Float>>();
        for(int i = 0; i< effects.length;i++){
            list.add(new Pair<>(effects[i], chance[i]));
        }
        return list;
    }
}
