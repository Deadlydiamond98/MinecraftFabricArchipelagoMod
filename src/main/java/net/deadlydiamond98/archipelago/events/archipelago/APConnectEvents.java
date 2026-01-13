package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.network.ConnectionResult;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoSlotData;
import net.deadlydiamond98.archipelago.common.world.APPersistentStates;

public class APConnectEvents {

    @ArchipelagoEventListener
    public void onConnectionEvent(ConnectionResultEvent event) {
        if (event.getResult() == ConnectionResult.Success) {
            APPersistentStates states = APPersistentStates.getPersistentStates();
            APMod.slotData = event.getSlotData(ArchipelagoSlotData.class);
            ArchipelagoSlotData slotData = APMod.slotData;

            states.syncChecks();
            if (!states.swim.get() && slotData.randomize_swim == 0) {
                states.swim.set(true);
            }
            if (!states.sprint.get() && slotData.randomize_sprint == 0) {
                states.sprint.set(true);
            }
        }
    }
}
