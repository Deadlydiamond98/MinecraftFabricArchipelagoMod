package net.deadlydiamond98.archipelago.common.archipelago.items.type.traps;

import net.deadlydiamond98.archipelago.common.archipelago.items.type.AbstractAPItem;
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
}
