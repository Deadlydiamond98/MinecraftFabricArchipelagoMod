package net.deadlydiamond98.archipelago.archipelago.items.type.progression;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.server.network.ServerPlayerEntity;

public class RubyAPItem extends AbstractAPItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        APPersistentState state = APPersistentState.get();
        state.setCurrentRubyCount(state.getCollectedRubies() + 1);
    }
}
