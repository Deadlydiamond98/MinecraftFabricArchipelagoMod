package net.deadlydiamond98.archipelago.archipelago;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ArchipelagoLocations {
    public static final Map<Identifier, Long> LOCATIONS = new HashMap<>();
    private static long i = 1;

    static {
        // Vanilla Basic Advancements
        addLocation(new Identifier("story/mine_stone"));
        addLocation(new Identifier("adventure/kill_a_mob"));
        addLocation(new Identifier("adventure/walk_on_powder_snow_with_leather_boots"));
        addLocation(new Identifier("husbandry/breed_an_animal"));
        addLocation(new Identifier("husbandry/allay_deliver_item_to_player"));
        addLocation(new Identifier("husbandry/ride_a_boat_with_a_goat"));
        addLocation(new Identifier("husbandry/tame_an_animal"));
        addLocation(new Identifier("husbandry/plant_seed"));
    }

    private static void addLocation(Identifier id) {
        LOCATIONS.put(id, i++);
    }
}
