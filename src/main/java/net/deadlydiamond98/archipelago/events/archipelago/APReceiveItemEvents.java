package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ReceiveItemEvent;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoItems;
import net.deadlydiamond98.archipelago.archipelago.apitem.AbstractAPItem;
import net.minecraft.server.MinecraftServer;

public class APReceiveItemEvents {

    @ArchipelagoEventListener
    public void receiveItem(ReceiveItemEvent event) {
        MinecraftServer server = APMod.server;

        if (server != null) {
            server.execute(() -> {
                server.getPlayerManager().getPlayerList().forEach(serverPlayer -> {
                    AbstractAPItem item = ArchipelagoItems.ITEMS.get(event.getItemName());
                    if (item != null) {
                        item.applyReward(serverPlayer);
                    }
                });
            });
        }
    }
}
