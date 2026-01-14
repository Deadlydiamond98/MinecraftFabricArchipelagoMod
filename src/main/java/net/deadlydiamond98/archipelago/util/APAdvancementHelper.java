package net.deadlydiamond98.archipelago.util;

import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.locations.ArchipelagoLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class APAdvancementHelper {

    /**
     * Grants all advancements that are checked from Archipelago to all Players
     */
    public static void resyncAdvancements() {
        APPersistentState.get().getAdvancementIds().forEach(APAdvancementHelper::grantAdvancement);
    }

    /**
     * Grants an Advancement for all players on the server
     * @param id the location id corresponding to the advancement
     */
    public static void grantAdvancement(long id) {
        Identifier advancementID = ArchipelagoLocations.LOCATIONS.inverse().get(id);
        if (advancementID == null) {
            return;
        }

        Archipelago.run(archipelago -> {
            archipelago.checkLocation(id);
        });

        APServerUtil.runOnServer(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                Advancement advancement = server.getAdvancementLoader().get(advancementID);
                AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancement);
                if (!progress.isDone()) {
                    progress.getUnobtainedCriteria().forEach(s -> {
                        player.getAdvancementTracker().grantCriterion(advancement, s);
                    });
                }
            }
        });
    }
}
