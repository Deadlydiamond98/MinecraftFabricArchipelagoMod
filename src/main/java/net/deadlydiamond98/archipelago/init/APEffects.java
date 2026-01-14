package net.deadlydiamond98.archipelago.init;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.common.effects.UnremovableStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class APEffects {

    // Effects used by certain traps

    // Reversed Controls Trap
    public static final StatusEffect CONFUSION = register("confusion", new UnremovableStatusEffect(0xE99E5D));
    // Inverted Mouse Trap
    public static final StatusEffect DISORIENTATION = register("disorientation", new UnremovableStatusEffect(0xE99E5D));
    // Ice Trap
    public static final StatusEffect FROST_FOOTED = register("frost_footed", new UnremovableStatusEffect(0x00FFAA));
    // Stun Trap
    public static final StatusEffect STUNNED = register("stunned", new UnremovableStatusEffect(0xF7AF70));

    public static StatusEffect register(String id, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, APMod.id(id), effect);
    }

    public static void register() {}
}
