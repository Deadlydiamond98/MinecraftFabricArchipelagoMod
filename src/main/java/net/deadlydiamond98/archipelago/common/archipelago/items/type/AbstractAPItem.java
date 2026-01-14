package net.deadlydiamond98.archipelago.common.archipelago.items.type;

import net.minecraft.server.network.ServerPlayerEntity;

public abstract class AbstractAPItem {
    // TODO: MAKE SOME SORT OF EXTRA ACTIONS FOR ALERTING THE PLAYER OF CERTAIN CHECKS

    public abstract void applyReward(ServerPlayerEntity player);
}
