package myshampooisdrunk.stackables.config;

import com.mojang.datafixers.util.Pair;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int MAX_POTION_STACK;
    public static int MAX_SPLASH_POTION_STACK;
    public static int MAX_LINGERING_POTION_STACK;
    public static int MAX_STEW_STACK;
    public static int MAX_SUS_STACK;
    public static int THROWABLE_POTION_COOLDOWN;
    public static int MINECART_STACK;
    public static int BOAT_STACK;
    public static int DISC_STACK;
    public static int CAKE_STACK;
    public static int MAX_BOOK_STACK;
    public static int TOTEM_COOLDOWN;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of( "stackables_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("potion.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("splash_potion.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("lingering_potion.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("stew.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("suspicious_stew.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("music_disc.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("minecart.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("boat.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("cake.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("enchanted_book.max.stack", 16), "int");
        configs.addKeyValuePair(new Pair<>("throwable_potion.cooldown", 20), "int");
        configs.addKeyValuePair(new Pair<>("totem_of_undying.cooldown", 20), "int");

    }

    private static void assignConfigs() {
        MAX_POTION_STACK = CONFIG.getOrDefault("potion.max.stack", 16);
        MAX_SPLASH_POTION_STACK = CONFIG.getOrDefault("splash_potion.max.stack", 16);
        MAX_LINGERING_POTION_STACK = CONFIG.getOrDefault("lingering_potion.max.stack", 16);
        MAX_STEW_STACK = CONFIG.getOrDefault("stew.max.stack", 16);
        MAX_SUS_STACK = CONFIG.getOrDefault("suspicious_stew.max.stack", 16);
        MINECART_STACK = CONFIG.getOrDefault("minecart.max.stack", 16);
        DISC_STACK = CONFIG.getOrDefault("music_disc.max.stack", 16);
        BOAT_STACK = CONFIG.getOrDefault("boat.max.stack", 16);
        CAKE_STACK = CONFIG.getOrDefault("cake.max.stack", 16);
        MAX_BOOK_STACK = CONFIG.getOrDefault("enchanted_book.max.stack", 16);
        THROWABLE_POTION_COOLDOWN = CONFIG.getOrDefault("throwable_potion.cooldown", 20);
        TOTEM_COOLDOWN = CONFIG.getOrDefault("totem_of_undying.cooldown", 20);
       // System.out.println("All " + configs.getConfigList().size() + " have been set properly");
    }
}
