package net.deadlydiamond98.archipelago.archipelago.items;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.ExperienceAPItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.ItemstackAPItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.SuspiciousStewAPItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.traps.*;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public class ArchipelagoItems {
    public static final Map<String, AbstractAPItem> ITEMS = new HashMap<>();

    static {
        // Filler
        ITEMS.put("5 Experience", new ExperienceAPItem(5, 0));
        ITEMS.put("10 Experience", new ExperienceAPItem(10, 0));
        ITEMS.put("1 Experience Level", new ExperienceAPItem(0, 1));
        ITEMS.put("2 Experience Levels", new ExperienceAPItem(0, 2));
        ITEMS.put("5 Experience Levels", new ExperienceAPItem(0, 5));

        ITEMS.put("4 Emeralds", new ItemstackAPItem(Items.EMERALD, 4));
        ITEMS.put("8 Emeralds", new ItemstackAPItem(Items.EMERALD, 8));

        ITEMS.put("1 Arrow", new ItemstackAPItem(Items.ARROW, 1));
        ITEMS.put("8 Arrows", new ItemstackAPItem(Items.ARROW, 8));
        ITEMS.put("16 Arrows", new ItemstackAPItem(Items.ARROW, 16));
        ITEMS.put("32 Arrows", new ItemstackAPItem(Items.ARROW, 32));

        ITEMS.put("1 Netherite Scrap", new ItemstackAPItem(Items.NETHERITE_SCRAP, 1));
        ITEMS.put("2 Netherite Scrap", new ItemstackAPItem(Items.NETHERITE_SCRAP, 2));

        ITEMS.put("Redstone Dust", new ItemstackAPItem(Items.REDSTONE, 8));
        ITEMS.put("Rotten Flesh", new ItemstackAPItem(Items.ROTTEN_FLESH, 8));

        ITEMS.put("Suspicious Stew", new SuspiciousStewAPItem());


        // Traps
        ITEMS.put("Reverse Controls Trap", new SpecialEffectTrap(APEffects.CONFUSION, 1200));
        ITEMS.put("Inverted Mouse Trap", new SpecialEffectTrap(APEffects.DISORIENTATION, 1200));
        ITEMS.put("Ice Trap", new SpecialEffectTrap(APEffects.FROST_FOOTED, 1200));
        ITEMS.put("Random Status Trap", new RandomEffectTrap());
        ITEMS.put("Stun Trap", new SpecialEffectTrap(APEffects.STUNNED, 50));
        ITEMS.put("TNT Trap", new TNTTrap());
        ITEMS.put("Teleport Trap", new TeleportTrap());
        ITEMS.put("Bee Trap", new BeeTrap());
    }
}
