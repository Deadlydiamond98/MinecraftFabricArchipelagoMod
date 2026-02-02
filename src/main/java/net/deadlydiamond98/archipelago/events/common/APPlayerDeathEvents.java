package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.util.mixinterfaces.IPlayerReceivedItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class APPlayerDeathEvents {
    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            IPlayerReceivedItems oldIDs = (IPlayerReceivedItems) oldPlayer;
            IPlayerReceivedItems newIDs = (IPlayerReceivedItems) newPlayer;
            newIDs.archipelago$setItemIDs(oldIDs.archipelago$getItemIDs());
        });
    }
}
