package net.deadlydiamond98.archipelago.archipelago.locations;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.deadlydiamond98.archipelago.archipelago.locations.advancement.VanillaAdvancements;
import net.deadlydiamond98.archipelago.archipelago.locations.itemsanity.VanillaItemsanity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class APLocations {
    public static final BiMap<Identifier, Long> ADVANCEMENT_LOCATIONS = HashBiMap.create();
    public static final BiMap<Identifier, Long> ITEMSANITY_LOCATIONS = HashBiMap.create();
    public static final Map<Identifier, Integer> LOCATION_TYPE_CHECKER = new HashMap<>();
    public static final int HARD = 1;
    public static final int EXPLORATION = 2;
    public static final int UNREASONABLE = 3;

    private static long i = 1;

    static {
        // Vanilla Locations
        VanillaAdvancements.addVanillaAdvancements();
        VanillaAdvancements.addLegacyAdvancements();
        VanillaItemsanity.addVanillaItemsanity();
    }


    public static void addItemsanityLocation(Identifier itemID) {
        ITEMSANITY_LOCATIONS.put(itemID, i++);
    }

    public static void addAdvancmentLocation(Identifier id) {
        addAdvancmentLocation(id, 0);
    }

    public static void addAdvancmentLocation(Identifier id, int advancementType) {
        ADVANCEMENT_LOCATIONS.put(id, i++);
        LOCATION_TYPE_CHECKER.put(id, advancementType);
    }
}
