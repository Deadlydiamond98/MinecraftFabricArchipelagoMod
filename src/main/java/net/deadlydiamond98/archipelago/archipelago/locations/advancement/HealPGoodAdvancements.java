package net.deadlydiamond98.archipelago.archipelago.locations.advancement;

import net.minecraft.util.Identifier;

import static net.deadlydiamond98.archipelago.archipelago.locations.APLocations.advancement;

public class HealPGoodAdvancements {
    public static void addAdvancements() {
        advancement(new Identifier("healpgood:crystal_heart_use"));
        advancement(new Identifier("healpgood:empty_heart_container_use"));
        advancement(new Identifier("healpgood:heart_container_use"));
        advancement(new Identifier("healpgood:heart_crystal_break"));
        advancement(new Identifier("healpgood:max_health"));
    }
}
