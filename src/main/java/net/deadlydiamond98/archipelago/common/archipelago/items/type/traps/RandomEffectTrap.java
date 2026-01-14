package net.deadlydiamond98.archipelago.common.archipelago.items.type.traps;

import net.deadlydiamond98.archipelago.common.archipelago.items.type.AbstractAPItem;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;

public class RandomEffectTrap extends AbstractTrapItem {
    // Todo: Make this actually choose a random effect

    @Override
    public void applyReward(ServerPlayerEntity player) {
        StatusEffect effect = StatusEffects.POISON;
        player.addStatusEffect(new StatusEffectInstance(effect, 200));
    }
}
