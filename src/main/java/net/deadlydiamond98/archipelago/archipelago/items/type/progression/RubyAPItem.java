package net.deadlydiamond98.archipelago.archipelago.items.type.progression;

import io.github.archipelagomw.parts.NetworkItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.init.APSounds;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundEvent;

public class RubyAPItem extends AbstractAPItem {

    @Override
    protected SoundEvent getSoundEvent() {
        return APSounds.RUBY_RECEIVED;
    }

    @Override
    protected void triggerOneTimeEffect(NetworkItem item, MinecraftServer server) {
        APPersistentState state = APPersistentState.get();
        state.setCurrentRubyCount(state.getCollectedRubies() + 1);
    }
}
