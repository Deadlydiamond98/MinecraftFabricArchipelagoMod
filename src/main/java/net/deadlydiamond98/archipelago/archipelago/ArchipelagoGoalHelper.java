package net.deadlydiamond98.archipelago.archipelago;

import io.github.archipelagomw.ClientStatus;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;

public class ArchipelagoGoalHelper {
    /**
     * Updates Persistent states based on goalID
     * @param goalID the goalID
     */
    public static void updateGoal(int goalID) {
        switch (goalID) {
            case 0 -> APPersistentState.get().hasKilledEnderDragon = true;
            case 1 -> APPersistentState.get().hasKilledWither = true;
        }
        tryTriggerGoal();
    }

    /**
     * Attempts to trigger a goal!
     */
    public static void tryTriggerGoal() {
        boolean killedDragon = APPersistentState.get().hasKilledEnderDragon;
        boolean killedWither = APPersistentState.get().hasKilledWither;

        Archipelago.MCSlotData slot = Archipelago.getSlotData();

        if (slot != null) {
            switch (slot.goal_condition) {
                case 0 -> goal(killedDragon);
                case 1 -> goal(killedWither);
                case 2 -> goal(killedDragon && killedWither);
            }
        }
    }

    /**
     * Triggers Goal if given variable is true
     * @param bl trigger goal?
     */
    private static void goal(boolean bl) {
        Archipelago.run(archipelago -> {
            if (bl) {
                archipelago.setGameState(ClientStatus.CLIENT_GOAL);
            }
        });
    }
}
