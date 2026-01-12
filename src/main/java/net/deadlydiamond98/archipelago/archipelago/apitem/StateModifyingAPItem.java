package net.deadlydiamond98.archipelago.archipelago.apitem;

import net.deadlydiamond98.archipelago.common.world.APPersistentStates;
import net.minecraft.server.network.ServerPlayerEntity;

public class StateModifyingAPItem extends AbstractAPItem {
    private final ModifyPersistState modifyPersistState;

    public StateModifyingAPItem(ModifyPersistState modifyPersistState) {
        this.modifyPersistState = modifyPersistState;
    }


    @Override
    public void applyReward(ServerPlayerEntity player) {
        modifyPersistState.set(APPersistentStates.getPersistentStates());
    }

    @FunctionalInterface
    public interface ModifyPersistState {
        void set(APPersistentStates states);
    }
}
