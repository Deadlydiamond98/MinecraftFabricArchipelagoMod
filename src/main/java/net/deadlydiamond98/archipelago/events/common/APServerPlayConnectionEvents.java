package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoServerConnector;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;


public class APServerPlayConnectionEvents {
    public static boolean syncData = false;

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (Archipelago.archipelago != null && !Archipelago.archipelago.isConnected()) {
                ArchipelagoServerConnector.connectToServer();
            }
            // sets this to true to use elsewhere since players can't be modified here!
            syncData = true;
        });
    }
}
