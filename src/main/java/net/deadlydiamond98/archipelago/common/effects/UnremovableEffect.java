package net.deadlydiamond98.archipelago.common.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class UnremovableEffect extends StatusEffect {
    public UnremovableEffect(int color) {
        super(StatusEffectCategory.HARMFUL, color);
    }
}
