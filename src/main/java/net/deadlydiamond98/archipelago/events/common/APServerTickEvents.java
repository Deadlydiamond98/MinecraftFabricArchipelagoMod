package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.networking.s2c.UpdatePlayerAbilitiesS2CPacket;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class APServerTickEvents {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (APServerPlayConnectionEvents.syncData) {
                APServerPlayConnectionEvents.syncData = false;
                APAdvancementHelper.resyncAdvancements();
                server.getPlayerManager().getPlayerList().forEach(UpdatePlayerAbilitiesS2CPacket::send);
            }
        });
    }
}
