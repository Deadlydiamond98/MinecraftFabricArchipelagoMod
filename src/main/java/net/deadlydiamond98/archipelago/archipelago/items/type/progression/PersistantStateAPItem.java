package net.deadlydiamond98.archipelago.archipelago.items.type.progression;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.server.network.ServerPlayerEntity;

public class PersistantStateAPItem extends AbstractAPItem {
    private final String key;

    public PersistantStateAPItem(String key) {
        this.key = key;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        APPersistentState.get().triggerCheck(this.key);
    }
}
