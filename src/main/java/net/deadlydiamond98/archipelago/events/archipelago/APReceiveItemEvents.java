package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ReceiveItemEvent;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoItems;
import net.deadlydiamond98.archipelago.archipelago.apitem.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentStates;
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
                        APPersistentStates states = APPersistentStates.getPersistentStates();
                        long index = event.getIndex();
                        if (!states.getItemIndexes().contains(index)) {
                            item.applyReward(serverPlayer);
                            states.putItemIndex(index);
                        }
                    }
                });
            });
        }
    }
}
