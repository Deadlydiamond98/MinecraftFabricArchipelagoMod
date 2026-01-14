package net.deadlydiamond98.archipelago.util;

import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class APItemUtil {
    public static final Map<String, HashMap<Item, Integer>> PROGRESSIVE_ITEMS = new HashMap<>();
    public static final Set<String> PROGRESSIVE_ITEM_IDS = new HashSet<>();

    public static boolean allowCrafting(ItemStack stack) {
        AtomicBoolean bl = new AtomicBoolean(true);
        PROGRESSIVE_ITEMS.forEach((key, items) -> {
            if (checkIfRecipeUnlocked(stack, APPersistentState.get().getIntCheckValue(key), items)) {
                bl.set(false);
            }
        });
        return bl.get();
    }

    private static boolean checkIfRecipeUnlocked(ItemStack stack, int lvl, Map<Item, Integer> map) {
        Integer tier = map.get(stack.getItem());
        return !(tier == null || lvl >= tier);
    }
}
