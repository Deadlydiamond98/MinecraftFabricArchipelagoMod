package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.networking.s2c.SendArchipelagoInfoS2CPacket;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class APServerTickEvents {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.execute(() -> {
                // Updates the Client Tracker every 20 ticks
                if (server.getTicks() % 20 == 0) {
                    server.getPlayerManager().getPlayerList().forEach(SendArchipelagoInfoS2CPacket::send);
                }
            });
        });
    }
}
