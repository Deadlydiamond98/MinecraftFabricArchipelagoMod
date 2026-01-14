package net.deadlydiamond98.archipelago.common.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class UnremovableStatusEffect extends StatusEffect {
    public UnremovableStatusEffect(int color) {
        super(StatusEffectCategory.HARMFUL, color);
    }
}
