package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.BouncedEvent;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.items.MultiworldTraps;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.util.APServerUtil;

public class APBouncedEvents {

    @ArchipelagoEventListener
    public void onBounced(BouncedEvent event) {
        Archipelago.MCSlotData slotData = Archipelago.getSlotData();

        Archipelago.run(archipelago -> {
            if (slotData != null) {
                if (event.tags.contains("TrapLink") && slotData.traplink != 0) {
                    if (!event.getString("source").equals(archipelago.getMyName())) {
                        String trapName = event.getString("trap_name");
                        AbstractAPItem abstractAPItem = MultiworldTraps.TRAPS.get(trapName);
                        if (abstractAPItem != null) {
                            APServerUtil.runOnServer(server -> server.getPlayerManager().getPlayerList().forEach(player -> {
                                abstractAPItem.apply(trapName, player);
                            }));
                        }
                    }
                }
            }
        });
    }
}
