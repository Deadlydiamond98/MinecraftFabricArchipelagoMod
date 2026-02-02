package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import io.github.archipelagomw.parts.NetworkItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.networking.ArchipelagoPacketManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public abstract class AbstractTrapItem extends AbstractAPItem {

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Override
    protected SoundEvent getSoundEvent() {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected int getTextColor() {
        return 0xFF0000;
    }

    @Override
    protected void triggerOneTimeEffect(NetworkItem item, MinecraftServer server) {
        ArchipelagoPacketManager.sendTraplink(item.itemName);
    }
}
