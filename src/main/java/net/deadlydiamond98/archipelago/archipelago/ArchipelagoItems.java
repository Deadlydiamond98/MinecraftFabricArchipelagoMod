package net.deadlydiamond98.archipelago.archipelago;

import net.deadlydiamond98.archipelago.archipelago.apitem.*;
import net.deadlydiamond98.archipelago.archipelago.apitem.traps.*;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.minecraft.item.Items;

import java.util.Map;

public class ArchipelagoItems {
    public static final Map<String, AbstractAPItem> ITEMS = Map.of(
            "Apple", new ItemstackAPItem(Items.APPLE, 10),
            "Sword", new ItemstackAPItem(Items.IRON_SWORD, 1),

            // TRAPS
            "Reverse Controls Trap", new SpecialEffectTrap(APEffects.CONFUSION, 1200),
            "Inverted Mouse Trap", new SpecialEffectTrap(APEffects.DISORIENTATION, 1200),
            "Ice Trap", new SpecialEffectTrap(APEffects.FROST_FOOTED, 1200),
            "Random Status Trap", new RandomEffectTrap(),
            "Stun Trap", new SpecialEffectTrap(APEffects.STUNNED, 50),
            "TNT Trap", new TNTTrap(),
            "Teleport Trap", new TeleportTrap(),
            "Bee Trap", new BeeTrap()
    );
}
