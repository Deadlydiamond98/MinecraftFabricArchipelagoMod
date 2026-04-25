package net.deadlydiamond98.archipelago.archipelago.locations;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.locations.advancement.CreateAdvancements;
import net.deadlydiamond98.archipelago.archipelago.locations.advancement.HealPGoodAdvancements;
import net.deadlydiamond98.archipelago.archipelago.locations.advancement.VanillaAdvancements;
import net.deadlydiamond98.archipelago.archipelago.locations.itemsanity.CreateItemsanity;
import net.deadlydiamond98.archipelago.archipelago.locations.itemsanity.HealPGoodItemsanity;
import net.deadlydiamond98.archipelago.archipelago.locations.itemsanity.VanillaItemsanity;
import net.deadlydiamond98.archipelago.util.tracker.IAbilityCheck;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class APLocations {
    public static final BiMap<Identifier, Long> ADVANCEMENT_LOCATIONS = HashBiMap.create();
    public static final BiMap<Identifier, Long> ITEMSANITY_LOCATIONS = HashBiMap.create();
    private static long id = 1;

    static {
        // Vanilla Locations
        VanillaAdvancements.addVanillaAdvancements();
        VanillaAdvancements.addLegacyAdvancements();
        VanillaItemsanity.addVanillaItemsanity();
        // Create Locations
        CreateAdvancements.addAdvancements();
        CreateItemsanity.addItemsanity();
        // Healing Pretty Good Locations
        HealPGoodAdvancements.addAdvancements();
        HealPGoodItemsanity.addItemsanity();
    }

    // CREATE //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void advancement(Identifier advancemeentID) {
        ADVANCEMENT_LOCATIONS.put(advancemeentID, id++);
    }

    public static void itemsanity(Identifier itemID) {
        ITEMSANITY_LOCATIONS.put(itemID, id++);
    }
}
