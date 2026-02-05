package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.network.ConnectionResult;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.archipelago.locations.APLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.networking.s2c.SendArchipelagoInfoS2CPacket;
import net.deadlydiamond98.archipelago.networking.s2c.SendUncheckedItemsS2CPacket;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;

public class APConnectEvents {

    @ArchipelagoEventListener
    public void onConnectionEvent(ConnectionResultEvent event) {
        if (event.getResult() == ConnectionResult.Success) {
            APPersistentState state = APPersistentState.get();
            Archipelago.MCSlotData slot = Archipelago.initSlotData(event);

            // Unlocks Optional Abilities that aren't randomized
            slot.possible_randomized_abilities.forEach(ability -> {
                if (!slot.randomized_abilities.contains(ability)) {
                    state.triggerCheck(ability.toLowerCase());
                }
            });

            APServerUtil.runOnServer(server -> {
                // Enable Keep Inventory
                if (slot.keep_inventory == 1) {
                    server.getGameRules().get(GameRules.KEEP_INVENTORY).set(true, server);
                }

                // Sync Some Data
                server.getPlayerManager().getPlayerList().forEach(player -> {
                    server.getPlayerManager().getAdvancementTracker(player).reload(server.getAdvancementLoader());
                    SendArchipelagoInfoS2CPacket.send(player);
                    SendUncheckedItemsS2CPacket.send(player);
                });
            });

            Archipelago.run(archipelago -> {
                // Enable Deathlink
                if (slot.deathlink != 0) {
                    archipelago.setDeathLinkEnabled(true);
                }
                // Enable Traplink
                if (slot.traplink != 0) {
                    archipelago.addTag("TrapLink");
                }
                // Unlock Advancements that are already received
                archipelago.getLocationManager().getCheckedLocations().forEach(aLong -> {
                    if (APLocations.ADVANCEMENT_LOCATIONS.containsValue(aLong)) {
                        state.putAdvancementId(aLong);
                    }
                });
            });
            APAdvancementHelper.resyncAdvancements();
        }
    }
}
