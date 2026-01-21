package net.deadlydiamond98.archipelago.util;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoGoalHelper;
import net.deadlydiamond98.archipelago.archipelago.locations.ArchipelagoLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.networking.s2c.UpdatePlayerAbilitiesS2CPacket;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class APAdvancementHelper {

    /**
     * Grants all advancements that are checked from Archipelago to all Players
     */
    public static void resyncAdvancements() {
        // Sends all Found Advancements
        APPersistentState.get().getAdvancementIds().forEach(APAdvancementHelper::grantAdvancement);
        // Grant Root Advancements
        grantRootAdvancements();
        // Attempts to trigger goal
        ArchipelagoGoalHelper.tryTriggerGoal();
    }

    /**
     * Grants All Root Advancements to players
     */
    private static void grantRootAdvancements() {
        APServerUtil.runOnServer(server -> {
            server.getAdvancementLoader().getAdvancements().forEach(advancement -> {
                if (advancement.getRoot() == advancement) {
                    grantAdvancement(advancement.getId());
                }
            });
        });
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

        Archipelago.run(archipelago -> archipelago.checkLocation(id));
        grantAdvancement(advancementID);
    }

    /**
     * Grants an Advancement for all players on the server
     * @param id the advancement ID
     */
    public static void grantAdvancement(Identifier id) {
        APServerUtil.runOnServer(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                Advancement advancement = server.getAdvancementLoader().get(id);
                AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancement);
                if (!progress.isDone()) {
                    progress.getUnobtainedCriteria().forEach(s -> {
                        player.getAdvancementTracker().grantCriterion(advancement, s);
                    });
                }
            }
        });
    }

    public static boolean isValidAdvancement(Identifier id) {
        if (ArchipelagoLocations.LOCATIONS.containsKey(id)) {
            int type = ArchipelagoLocations.LOCATION_TYPE_CHECKER.getOrDefault(id, 0);
            return switch (type) {
                case ArchipelagoLocations.HARD -> Archipelago.getFromSlot(mcSlotData -> mcSlotData.exclude_hard) == 0;
                case ArchipelagoLocations.EXPLORATION -> Archipelago.getFromSlot(mcSlotData -> mcSlotData.exclude_exploration) == 0;
                default -> true;
            };
        }
        return false;
    }
}
