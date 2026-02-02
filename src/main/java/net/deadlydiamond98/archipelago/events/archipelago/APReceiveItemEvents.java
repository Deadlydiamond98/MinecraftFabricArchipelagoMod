package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ReceiveItemEvent;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.items.ArchipelagoItems;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.APServerUtil;

public class APReceiveItemEvents {

    @ArchipelagoEventListener
    public void receiveItem(ReceiveItemEvent event) {
        APServerUtil.runOnServer(server -> {
            AbstractAPItem item = ArchipelagoItems.ITEMS.get(event.getItemName());
            APPersistentState states = APPersistentState.get();
            long index = event.getIndex();

            if (item != null) {
                if (!states.getReceivedItems().containsKey(index)) {
                    item.receiveItem(event.getItem(), server, index);
                    states.putItemIndex(index, event.getItemName());
                }
            }
        });
    }
}
