package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class APServerPlayConnectionEvents {
    public static void register() {
        // Re-Syncs advancements so that joining players get the advancements
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            APAdvancementHelper.resyncAdvancements();
        });
    }
}
