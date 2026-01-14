package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.network.ConnectionResult;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.common.archipelago.ArchipelagoSlotData;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;

public class APConnectEvents {

    @ArchipelagoEventListener
    public void onConnectionEvent(ConnectionResultEvent event) {
        if (event.getResult() == ConnectionResult.Success) {
            APPersistentState state = APPersistentState.get();
            APMod.slotData = event.getSlotData(ArchipelagoSlotData.class);
            ArchipelagoSlotData slotData = APMod.slotData;

            APAdvancementHelper.resyncAdvancements();
            if (!state.swim.get() && slotData.randomize_swim == 0) {
                state.swim.set(true);
            }
            if (!state.sprint.get() && slotData.randomize_sprint == 0) {
                state.sprint.set(true);
            }
        }
    }
}
