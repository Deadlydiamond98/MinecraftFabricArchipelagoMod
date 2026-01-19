package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ReceiveItemEvent;
import net.deadlydiamond98.archipelago.archipelago.items.ArchipelagoItems;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.networking.s2c.SendArchipelagoInfoS2CPacket;
import net.deadlydiamond98.archipelago.util.APServerUtil;

public class APReceiveItemEvents {

    @ArchipelagoEventListener
    public void receiveItem(ReceiveItemEvent event) {
        APServerUtil.runOnServer(server -> {
            server.getPlayerManager().getPlayerList().forEach(serverPlayer -> {
                AbstractAPItem item = ArchipelagoItems.ITEMS.get(event.getItemName());
                if (item != null) {
                    APPersistentState states = APPersistentState.get();
                    long index = event.getIndex();
                    if (!states.getItemIndexes().contains(index)) {
                        item.apply(event.getItem(), serverPlayer);
                        states.putItemIndex(index);
                    }
                }
                SendArchipelagoInfoS2CPacket.send(serverPlayer);
            });
        });
    }
}
