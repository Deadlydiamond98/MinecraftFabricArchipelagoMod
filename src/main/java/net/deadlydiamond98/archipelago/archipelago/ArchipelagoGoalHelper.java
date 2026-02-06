package net.deadlydiamond98.archipelago.archipelago;

import io.github.archipelagomw.ClientStatus;
import net.deadlydiamond98.archipelago.archipelago.locations.APLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.text.Text;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ArchipelagoGoalHelper {
    /**
     * Updates Persistent states based on goalID
     * @param goalID the goalID
     */
    public static void updateBossKillGoal(int goalID) {
        switch (goalID) {
            case 0 -> APPersistentState.get().setHasKilledEnderDragon(true);
            case 1 -> APPersistentState.get().setHasKilledWither(true);
        }
        tryTriggerGoal();
    }

    /**
     * Attempts to trigger a goal!
     */
    public static void tryTriggerGoal() {
        APPersistentState state = APPersistentState.get();
        boolean killedDragon = state.hasKilledEnderDragon();
        boolean killedWither = state.hasKilledWither();
        int advancements = getCurrentAdvancements();
        int advancementsNeeded = getAdvancementsNeeded();
        int rubies = state.getCollectedRubies();

        if (advancements >= advancementsNeeded) {
            switch (getGoalID()) {
                case 0 -> goal(killedDragon);
                case 1 -> goal(killedWither);
                case 2 -> goal(killedDragon && killedWither);
                case 3 -> goal(true);
                case 4 -> goal(getRubiesNeeded() <= rubies);
            }
        }
    }

    public static int getGoalID() {
        return Archipelago.getFromSlot(mcSlotData -> mcSlotData.goal_condition);
    }

    public static int getCurrentAdvancements() {
        AtomicInteger advancements = new AtomicInteger();
        Archipelago.run(archipelago -> {
            for (Long checkedLocation : archipelago.getLocationManager().getCheckedLocations()) {
                APPersistentState state = APPersistentState.get();
                if (state.getAdvancementIds().contains(checkedLocation)) {
                    advancements.set(advancements.get() + 1);
                }
            }
        });
        return advancements.get();
    }

    public static int getAdvancementsNeeded() {
        return Archipelago.getFromSlot(mcSlotData -> Math.min(APLocations.ADVANCEMENT_LOCATIONS.size(), mcSlotData.advancements_to_goal));
    }

    public static int getRubiesNeeded() {
        return Archipelago.getFromSlot(mcSlotData -> {
            if (mcSlotData.goal_condition == 4) {
                return (int) Math.floor(mcSlotData.total_rubies * (mcSlotData.rubies_to_goal * 0.01));
            }
            return 0;
        });
    }

    /**
     * Triggers Goal if given variable is true
     * @param bl trigger goal?
     */
    private static void goal(boolean bl) {
        Archipelago.run(archipelago -> {
            if (bl) {
                APServerUtil.sendMessage(Text.translatable("archipelago.connection.goal"));
                archipelago.setGameState(ClientStatus.CLIENT_GOAL);
            }
        });
    }
}
