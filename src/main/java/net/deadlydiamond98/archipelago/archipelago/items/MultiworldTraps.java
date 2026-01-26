package net.deadlydiamond98.archipelago.archipelago.items;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.traps.*;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.minecraft.entity.effect.StatusEffects;

import java.util.HashMap;
import java.util.Map;

public class MultiworldTraps {
    public static final Map<String, AbstractAPItem> TRAPS = new HashMap<>();

    static {
        TRAPS.put("Banana Peel Trap", new StatusEffectTrap(APEffects.FROST_FOOTED, 1200));
        TRAPS.put("Banana Trap", new StatusEffectTrap(APEffects.FROST_FOOTED, 1200));
        TRAPS.put("Bee Trap", new BeeTrap());
        TRAPS.put("Bomb", new TNTTrap());
        TRAPS.put("Chaos Control Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Confuse Trap", new StatusEffectTrap(APEffects.CONFUSION, 1200));
        TRAPS.put("Confusion Trap", new StatusEffectTrap(APEffects.CONFUSION, 1200));
        TRAPS.put("Controller Drift Trap", new StatusEffectTrap(APEffects.FROST_FOOTED, 1200));
        TRAPS.put("Cutscene Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Depletion Trap", new StatusEffectTrap(StatusEffects.HUNGER, 200));
        TRAPS.put("Disable A Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Disable B Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Disable C Up Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Disable Tag Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Disable Z Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Energy Drain Trap", new StatusEffectTrap(StatusEffects.HUNGER, 200));
        TRAPS.put("Fear Trap", new StatusEffectTrap(StatusEffects.DARKNESS, 200));
        TRAPS.put("Freeze Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Frozen Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Ice Floor Trap", new StatusEffectTrap(APEffects.FROST_FOOTED, 1200));
        TRAPS.put("Ice Trap", new StatusEffectTrap(APEffects.FROST_FOOTED, 1200));
        TRAPS.put("Inverted Mouse Trap", new StatusEffectTrap(APEffects.DISORIENTATION, 1200));
        TRAPS.put("Literature Trap", new LiteratureTrap());
        TRAPS.put("Mirror Trap", new StatusEffectTrap(APEffects.CONFUSION, 1200));
        TRAPS.put("Paralyze Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Paralysis Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Poison Mushroom", new StatusEffectTrap(StatusEffects.POISON, 200));
        TRAPS.put("Poison Trap", new StatusEffectTrap(StatusEffects.POISON, 200));
        TRAPS.put("Random Status Trap", new RandomEffectTrap());
        TRAPS.put("Reversal Trap", new StatusEffectTrap(APEffects.CONFUSION, 1200));
        TRAPS.put("Reverse Trap", new StatusEffectTrap(APEffects.CONFUSION, 1200));
        TRAPS.put("Sleep Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Slip Trap", new StatusEffectTrap(APEffects.FROST_FOOTED, 1200));
        TRAPS.put("Slow Trap", new StatusEffectTrap(StatusEffects.SLOWNESS, 600));
        TRAPS.put("Slowness Trap", new StatusEffectTrap(StatusEffects.SLOWNESS, 600));
        TRAPS.put("Stun Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        TRAPS.put("Swap Trap", new StatusEffectTrap(APEffects.CONFUSION, 1200));
        TRAPS.put("Teleport Trap", new TeleportTrap());
        TRAPS.put("Text Trap", new LiteratureTrap());
        TRAPS.put("TNT Barrel Trap", new TNTTrap());
        TRAPS.put("TNT Trap", new TNTTrap());
    }
}
