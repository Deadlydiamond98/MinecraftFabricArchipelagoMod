package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

public class StatusEffectTrap extends AbstractTrapItem {
    private final StatusEffect effect;
    private final int length;

    public StatusEffectTrap(StatusEffect effect, int length) {
        this.effect = effect;
        this.length = length;
    }

    protected StatusEffect getEffect(ServerPlayerEntity player) {
        return this.effect;
    }

    protected int getLength(StatusEffect effect) {
        return this.length;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        StatusEffect effect = getEffect(player);

        int statusLength = getLength(effect);
        if (player.hasStatusEffect(effect)) {
            statusLength += player.getStatusEffect(effect).getDuration();
        }
        player.addStatusEffect(new StatusEffectInstance(effect, statusLength, 0, true, false));
    }
}
