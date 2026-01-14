package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

public class SpecialEffectTrap extends AbstractTrapItem {
    private final StatusEffect effect;
    private final int length;

    public SpecialEffectTrap(StatusEffect effect, int length) {
        this.effect = effect;
        this.length = length;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(this.effect, this.length, 0, true, false));
    }
}
