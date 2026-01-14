package net.deadlydiamond98.archipelago.archipelago.items;


import net.deadlydiamond98.archipelago.archipelago.items.type.PersistantStateAPItem;
import net.deadlydiamond98.archipelago.util.APItemUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Creates Checks that are saved to World Data and Checks that load things from datapacks
 */
public class SavedArchipelagoItems {
    public static final List<String> PERSISTENT_STATE_PROGRESSIVES = new ArrayList<>();
    public static final List<String> PERSISTENT_STATE_BOOLEANS = new ArrayList<>();
    
    public static void register() {
        // Abilities
        register("Swim", "swim", true);
        register("Sprint", "sprint", true);
        register("Villager Trading", "trading", true);
        register("Piglin Bartering", "bartering", true);
        register("Wither Summoning", "wither_summoning", true);

        registerWithJson("Sleeping", "spawn_point", true);

        // Single Use Recipes
        registerWithJson("Bucket Recipes", "bucket", true);
        registerWithJson("Flint and Steel Recipes", "igniter", true);
        registerWithJson("Brush Recipes", "brush", true);
        registerWithJson("Spyglass Recipes", "spyglass", true);
        registerWithJson("Shear Recipes", "shears", true);
        registerWithJson("Eye of Ender Recipes", "ender_eye", true);
        registerWithJson("Minecart Recipes", "minecarts", true);
        registerWithJson("Glass Bottle Recipes", "bottles", true);
        registerWithJson("Resource Compacting Recipes", "compacting", true);

        registerWithJson("Enchanting", "enchanting", true);
        registerWithJson("Brewing", "brewing", true);
        registerWithJson("Smithing", "smithing", true);

        // Progressive Crafting
        registerWithJson("Progressive Tools", "tools", false);
        registerWithJson("Progressive Weapons", "weapons", false);
        registerWithJson("Progressive Smelting", "smelting", false);
        registerWithJson("Progressive Armor", "armor", false);
    }

    public static void registerWithJson(String name, String key, boolean isBool) {
        // Add Check to Progressive Items list for loading json data
        if (isBool) {
            APItemUtil.BOOLEAN_ITEM_IDS.add(key);
            APItemUtil.BOOLEAN_ITEMS.put(key, new ArrayList<>());
        } else {
            APItemUtil.PROGRESSIVE_ITEM_IDS.add(key);
            APItemUtil.PROGRESSIVE_ITEMS.put(key, new HashMap<>());
        }
        register(name, key, isBool);
    }

    public static void register(String name, String key, boolean isBool) {
        // Add Check to Items
        ArchipelagoItems.ITEMS.put(name, new PersistantStateAPItem(key));

        if (isBool) {
            PERSISTENT_STATE_BOOLEANS.add(key);
        } else {
            PERSISTENT_STATE_PROGRESSIVES.add(key);
        }
    }
}
