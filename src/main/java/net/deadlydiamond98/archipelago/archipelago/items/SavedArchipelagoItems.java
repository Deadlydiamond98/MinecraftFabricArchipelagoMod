package net.deadlydiamond98.archipelago.archipelago.items;


import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.items.type.progression.PersistantStateAPItem;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;

import java.util.*;

/**
 * Creates Checks that are saved to World Data and Checks that load things from datapacks
 */
public class SavedArchipelagoItems {
    public static final Map<String, String> ID_TO_NAME_MAP = new HashMap<>();
    public static final List<String> PERSISTENT_STATE_PROGRESSIVES = new ArrayList<>();
    public static final List<String> PERSISTENT_STATE_BOOLEANS = new ArrayList<>();
    
    public static void register() {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // VANILLA /////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Abilities
        register("Swim", "swim", true);
        register("Sprint", "sprint", true);
        register("Jump", "jump", true);

        registerWithJson("Chests & Barrels", "chests", true);
        registerWithJson("Sleeping", "spawn_point", true);
        register("Wither Summoning", "wither_summoning", true);
        register("Villager Trading", "trading", true);
        register("Piglin Bartering", "bartering", true);

        // Crafting Stations
        registerWithJson("Brewing", "brewing", true);
        registerWithJson("Enchanting", "enchanting", true);
        registerWithJson("Smithing", "smithing", true);
        registerWithJson("Other Crafting Stations", "misc_stations", true);

        // Single Use Recipes
        registerWithJson("Bucket Recipes", "bucket", true);
        registerWithJson("Flint and Steel Recipes", "igniter", true);
        registerWithJson("Minecart Recipes", "minecarts", true);
        registerWithJson("Brush Recipes", "brush", true);
        registerWithJson("Spyglass Recipes", "spyglass", true);
        registerWithJson("Shear Recipes", "shears", true);
        registerWithJson("Eye of Ender Recipes", "ender_eye", true);
        registerWithJson("Fishing Rod Recipes", "fishing", true);
        registerWithJson("Glass Bottle Recipes", "bottles", true);
        registerWithJson("Resource Compacting Recipes", "compacting", true);
        registerWithJson("Shield Recipes", "shield", true);
        registerWithJson("Bundle Recipes", "bundles", true);

        // Progressive Crafting
        registerWithJson("Progressive Tools", "tools", false);
        registerWithJson("Progressive Weapons", "weapons", false);
        registerWithJson("Progressive Archery", "archery", false);
        registerWithJson("Progressive Armor", "armor", false);
        registerWithJson("Progressive Smelting", "smelting", false);
        registerWithJson("Progressive Dye Recipes", "dyes", false);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CREATE //////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Crafting Stations
        if (APMod.isModLoaded("create")) {
            registerWithJson("Water Wheel Recipes", "water_wheel", true);
            registerWithJson("Windmill Recipes", "windmill", true);
            registerWithJson("Steam Engine Recipes", "steam_engine", true);
            registerWithJson("Mechanical Mixer Recipes", "mechanical_mixer", true);
            registerWithJson("Mechanical Press Recipes", "mechanical_press", true);
            registerWithJson("Cogwheel Recipes", "cogwheel", true);
        }
    }

    public static void registerWithJson(String name, String key, boolean isBool) {
        // Add Check to Progressive Items list for loading json data
        if (isBool) {
            APItemAccessUtil.BOOLEAN_ITEM_IDS.add(key);
            APItemAccessUtil.BOOLEAN_ITEMS.put(key, new HashSet<>());
        } else {
            APItemAccessUtil.PROGRESSIVE_ITEM_IDS.add(key);
            APItemAccessUtil.PROGRESSIVE_ITEMS.put(key, new HashMap<>());
        }
        register(name, key, isBool);
    }

    public static void register(String name, String key, boolean isBool) {
        // Add Check to Items
        ArchipelagoItems.ITEMS.put(name, new PersistantStateAPItem(key));
        ID_TO_NAME_MAP.put(key, name);

        if (isBool) {
            PERSISTENT_STATE_BOOLEANS.add(key);
        } else {
            PERSISTENT_STATE_PROGRESSIVES.add(key);
        }
    }
}
