package net.deadlydiamond98.archipelago.archipelago.items.type.progression;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.init.APSounds;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;

public class RubyAPItem extends AbstractAPItem {

    @Override
    protected SoundEvent getSoundEvent() {
        return APSounds.RUBY_RECEIVED;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        APPersistentState state = APPersistentState.get();
        state.setCurrentRubyCount(state.getCollectedRubies() + 1);
    }
}
