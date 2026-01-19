package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.network.ConnectionResult;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.world.GameRules;

public class APConnectEvents {

    @ArchipelagoEventListener
    public void onConnectionEvent(ConnectionResultEvent event) {
        if (event.getResult() == ConnectionResult.Success) {
            Archipelago.MCSlotData slot = Archipelago.initSlotData(event);

            triggerSlotDataCheck("swim", slot.randomize_swim);
            triggerSlotDataCheck("sprint", slot.randomize_sprint);
            triggerSlotDataCheck("jump", slot.randomize_jump);
            triggerSlotDataCheck("chests", slot.randomize_chests);

            if (slot.keep_inventory == 1) {
                APServerUtil.runOnServer(server -> server.getGameRules().get(GameRules.KEEP_INVENTORY).set(true, server));
            }

            APAdvancementHelper.resyncAdvancements();
        }
    }

    public void triggerSlotDataCheck(String id, int slotValue) {
        if (slotValue == 0) {
            APPersistentState.get().triggerCheck(id);
        }
    }
}
