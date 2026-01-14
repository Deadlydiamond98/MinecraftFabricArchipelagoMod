package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.network.ConnectionResult;
import net.deadlydiamond98.archipelago.common.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;

public class APConnectEvents {

    @ArchipelagoEventListener
    public void onConnectionEvent(ConnectionResultEvent event) {
        if (event.getResult() == ConnectionResult.Success) {
            Archipelago.MCSlotData slot = Archipelago.initSlotData(event);

            APAdvancementHelper.resyncAdvancements();
            triggerSlotDataCheck("swim", slot.randomize_swim);
            triggerSlotDataCheck("sprint", slot.randomize_sprint);
        }
    }

    public void triggerSlotDataCheck(String id, int slotValue) {
        if (slotValue != 0) {
            APPersistentState.get().triggerCheck(id);
        }
    }
}
