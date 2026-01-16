package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoServerConnector;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;


public class APServerPlayConnectionEvents {
    public static void register() {
        // Re-Syncs advancements so that joining players get the advancements
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (Archipelago.archipelago != null && !Archipelago.archipelago.isConnected()) {
                ArchipelagoServerConnector.connectToServer();
            }

            APAdvancementHelper.resyncAdvancements();
        });
    }
}
