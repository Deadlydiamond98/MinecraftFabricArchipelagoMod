package net.deadlydiamond98.archipelago.init;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.common.effects.UnremovableEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class APEffects {
    public static final StatusEffect CONFUSION = register("confusion", new UnremovableEffect(0xE99E5D));
    public static final StatusEffect DISORIENTATION = register("disorientation", new UnremovableEffect(0xE99E5D));
    public static final StatusEffect FROST_FOOTED = register("frost_footed", new UnremovableEffect(0x00FFAA));
    public static final StatusEffect STUNNED = register("stunned", new UnremovableEffect(0xF7AF70));

    public static StatusEffect register(String id, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(APMod.MOD_ID, id), effect);
    }

    public static void register() {}
}
