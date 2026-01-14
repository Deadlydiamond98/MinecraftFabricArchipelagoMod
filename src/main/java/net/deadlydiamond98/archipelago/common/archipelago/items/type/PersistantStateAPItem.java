package net.deadlydiamond98.archipelago.common.archipelago.items.type;

import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Consumer;

public class PersistantStateAPItem extends AbstractAPItem {
    private final Consumer<APPersistentState> consumer;

    public PersistantStateAPItem(String key) {
        this(state -> state.triggerCheck(key));
    }

    public PersistantStateAPItem(Consumer<APPersistentState> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        consumer.accept(APPersistentState.get());
    }
}
