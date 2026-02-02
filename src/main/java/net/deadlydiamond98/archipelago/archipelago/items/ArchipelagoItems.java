package net.deadlydiamond98.archipelago.archipelago.items;

import net.deadlydiamond98.archipelago.archipelago.items.type.*;
import net.deadlydiamond98.archipelago.archipelago.items.type.filler.*;
import net.deadlydiamond98.archipelago.archipelago.items.type.progression.RubyAPItem;
import net.deadlydiamond98.archipelago.archipelago.items.type.traps.*;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.deadlydiamond98.archipelago.init.APTags;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.ItemTags;

import java.util.HashMap;
import java.util.Map;

public class ArchipelagoItems {
    public static final Map<String, AbstractAPItem> ITEMS = new HashMap<>();

    static {
        // Ruby Hunt Item //////////////////////////////////////////////////////////////////////////////////////////////
        ITEMS.put("Ruby", new RubyAPItem());

        // Limited Filler //////////////////////////////////////////////////////////////////////////////////////////////
        ITEMS.put("Bundle", new ItemstackAPItem(Items.BUNDLE, 1));

        // Filler //////////////////////////////////////////////////////////////////////////////////////////////////////
        // Experience
        ITEMS.put("5 Experience", new ExperienceAPItem(5, 0));
        ITEMS.put("10 Experience", new ExperienceAPItem(10, 0));
        ITEMS.put("1 Experience Level", new ExperienceAPItem(0, 1));
        ITEMS.put("2 Experience Levels", new ExperienceAPItem(0, 2));
        ITEMS.put("5 Experience Levels", new ExperienceAPItem(0, 5));

        // Arrows
        ITEMS.put("1 Arrow", new ItemstackAPItem(Items.ARROW, 1));
        ITEMS.put("8 Arrows", new ItemstackAPItem(Items.ARROW, 8));
        ITEMS.put("16 Arrows", new ItemstackAPItem(Items.ARROW, 16));
        ITEMS.put("32 Arrows", new ItemstackAPItem(Items.ARROW, 32));

        // Materials
        ITEMS.put("4 Emeralds", new ItemstackAPItem(Items.EMERALD, 4));
        ITEMS.put("8 Emeralds", new ItemstackAPItem(Items.EMERALD, 8));
        ITEMS.put("Netherite Scrap", new ItemstackAPItem(Items.NETHERITE_SCRAP, 2));
        ITEMS.put("Redstone Dust", new ItemstackAPItem(Items.REDSTONE, 8));
        ITEMS.put("Lapis Lazuli", new ItemstackAPItem(Items.LAPIS_LAZULI, 8));
        ITEMS.put("Ender Pearls", new ItemstackAPItem(Items.ENDER_PEARL, 3));
        ITEMS.put("Prismarine Shards", new ItemstackAPItem(Items.PRISMARINE_SHARD, 8));
        ITEMS.put("Prismarine Crystals", new ItemstackAPItem(Items.PRISMARINE_CRYSTALS, 2));

        // Ores
        ITEMS.put("Coal Ore Vein", new ItemstackAPItem(Blocks.COAL_ORE, 6));
        ITEMS.put("Iron Ore Vein", new ItemstackAPItem(Blocks.IRON_ORE, 4));
        ITEMS.put("Gold Ore Vein", new ItemstackAPItem(Blocks.GOLD_ORE, 4));
        ITEMS.put("Diamond Ore Vein", new ItemstackAPItem(Blocks.DIAMOND_ORE, 4));
        ITEMS.put("Emerald Ore Vein", new ItemstackAPItem(Blocks.EMERALD_ORE, 1));

        ITEMS.put("Large Coal Ore Vein", new ItemstackAPItem(Blocks.COAL_ORE, 12));
        ITEMS.put("Large Iron Ore Vein", new ItemstackAPItem(Blocks.IRON_ORE, 8));
        ITEMS.put("Large Gold Ore Vein", new ItemstackAPItem(Blocks.GOLD_ORE, 8));
        ITEMS.put("Large Diamond Ore Vein", new ItemstackAPItem(Blocks.DIAMOND_ORE, 8));
        ITEMS.put("Large Emerald Ore Vein", new ItemstackAPItem(Blocks.EMERALD_ORE, 2));

        // Foods
        ITEMS.put("Apples", new ItemstackAPItem(Items.APPLE, 16));
        ITEMS.put("Golden Carrots", new ItemstackAPItem(Items.GOLDEN_CARROT, 16));
        ITEMS.put("Baked Potatos", new ItemstackAPItem(Items.BAKED_POTATO, 16));
        ITEMS.put("Cookies", new ItemstackAPItem(Items.COOKIE, 16));

        ITEMS.put("Steak", new ItemstackAPItem(Items.COOKED_BEEF, 16));
        ITEMS.put("Porkchops", new ItemstackAPItem(Items.COOKED_PORKCHOP, 16));
        ITEMS.put("Chicken", new ItemstackAPItem(Items.COOKED_CHICKEN, 16));
        ITEMS.put("Mutton", new ItemstackAPItem(Items.COOKED_MUTTON, 16));

        ITEMS.put("Rotten Flesh", new ItemstackAPItem(Items.ROTTEN_FLESH, 8));
        ITEMS.put("Tropical Fish", new ItemstackAPItem(Items.TROPICAL_FISH, 16));
        ITEMS.put("Pufferfish", new ItemstackAPItem(Items.PUFFERFISH, 1));
        ITEMS.put("Poisonous Potato", new ItemstackAPItem(Items.POISONOUS_POTATO, 16));
        ITEMS.put("Suspicious Stew", new SuspiciousStewAPItem());

        // Blocks
        ITEMS.put("Wooden Planks", new ItemTagAPItem(ItemTags.PLANKS, 16));
        ITEMS.put("Stone", new ItemstackAPItem(Blocks.STONE, 64));
        ITEMS.put("Andesite", new ItemstackAPItem(Blocks.ANDESITE, 16));
        ITEMS.put("Diorite", new ItemstackAPItem(Blocks.DIORITE, 16));
        ITEMS.put("Granite", new ItemstackAPItem(Blocks.GRANITE, 16));

        // Enchanted Books
        ITEMS.put("Looting III", new EnchantmentAPItem(Enchantments.LOOTING, 3));
        ITEMS.put("Sharpness III", new EnchantmentAPItem(Enchantments.SHARPNESS, 3));
        ITEMS.put("Silk Touch", new EnchantmentAPItem(Enchantments.SILK_TOUCH, 1));
        ITEMS.put("Channeling", new EnchantmentAPItem(Enchantments.CHANNELING, 1));
        ITEMS.put("Piercing IV", new EnchantmentAPItem(Enchantments.PIERCING, 4));
        ITEMS.put("Unbreaking I", new EnchantmentAPItem(Enchantments.UNBREAKING, 1));
        ITEMS.put("Unbreaking II", new EnchantmentAPItem(Enchantments.UNBREAKING, 2));
        ITEMS.put("Unbreaking III", new EnchantmentAPItem(Enchantments.UNBREAKING, 3));

        // Potions
        ITEMS.put("Potion of Fire Resistance", new PotionAPItem(Potions.FIRE_RESISTANCE));
        ITEMS.put("Potion of Swiftness", new PotionAPItem(Potions.SWIFTNESS));
        ITEMS.put("Potion of Luck", new PotionAPItem(Potions.LUCK));
        ITEMS.put("Strong Potion of Harming", new PotionAPItem(Potions.STRONG_HARMING));
        ITEMS.put("Potion of Healing", new PotionAPItem(Potions.HEALING));
        ITEMS.put("Strong Potion of Healing", new PotionAPItem(Potions.STRONG_HEALING));

        // Misc
        ITEMS.put("Saddle", new ItemstackAPItem(Items.SADDLE, 1));
        ITEMS.put("Random Dye", new ItemTagAPItem(APTags.DYES, 3));

        // Traps ///////////////////////////////////////////////////////////////////////////////////////////////////////
        ITEMS.put("Reverse Controls Trap", new StatusEffectTrap(APEffects.CONFUSION, 1200));
        ITEMS.put("Inverted Mouse Trap", new StatusEffectTrap(APEffects.DISORIENTATION, 1200));
        ITEMS.put("Ice Trap", new StatusEffectTrap(APEffects.FROST_FOOTED, 1200));
        ITEMS.put("Random Status Trap", new RandomEffectTrap());
        ITEMS.put("Stun Trap", new StatusEffectTrap(APEffects.STUNNED, 50));
        ITEMS.put("TNT Trap", new TNTTrap());
        ITEMS.put("Teleport Trap", new TeleportTrap());
        ITEMS.put("Bee Trap", new BeeTrap());
        ITEMS.put("Literature Trap", new LiteratureTrap());
    }
}
