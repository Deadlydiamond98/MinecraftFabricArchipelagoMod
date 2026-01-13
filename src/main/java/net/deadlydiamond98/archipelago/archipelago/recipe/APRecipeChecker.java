package net.deadlydiamond98.archipelago.archipelago.recipe;

import net.deadlydiamond98.archipelago.common.world.APPersistentStates;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class APRecipeChecker {
    // Regular Crafting
    public static final Map<Item, Integer> PROGRESSIVE_TOOLS = new HashMap<>();
    public static final Map<Item, Integer> PROGRESSIVE_WEAPONS = new HashMap<>();

    public static boolean allowCrafting(ItemStack stack) {
        APPersistentStates states = APPersistentStates.getPersistentStates();
        boolean tools = checkList(stack, states.toolLevel.get(), PROGRESSIVE_TOOLS);
        boolean weapons = checkList(stack, states.weaponLevel.get(), PROGRESSIVE_WEAPONS);

        if (tools || weapons) {
            return false;
        }

        return true;
    }

    private static boolean checkList(ItemStack stack, int lvl, Map<Item, Integer> map) {
        Integer tier = map.get(stack.getItem());
        return !(tier == null || lvl >= tier);
    }
}
