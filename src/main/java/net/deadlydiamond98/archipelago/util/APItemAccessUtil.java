package net.deadlydiamond98.archipelago.util;

import net.deadlydiamond98.archipelago.archipelago.items.SavedArchipelagoItems;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.koalalib.init.KoalaLibSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class APItemAccessUtil {
    public static final Map<String, Set<Item>> BOOLEAN_ITEMS = new HashMap<>();
    public static final Map<String, HashMap<Item, Integer>> PROGRESSIVE_ITEMS = new HashMap<>();

    public static final Set<String> PROGRESSIVE_ITEM_IDS = new HashSet<>();
    public static final Set<String> BOOLEAN_ITEM_IDS = new HashSet<>();

    /**
     * Checks if an item is unlocked
     * @param player the player
     * @param item the item to check
     */
    public static boolean allowCraftOrUse(PlayerEntity player, ItemConvertible item) {
        AtomicBoolean bl = new AtomicBoolean(true);
        AtomicBoolean triggeredCheckRequired = new AtomicBoolean(false);
        PROGRESSIVE_ITEMS.forEach((key, items) -> {
            if (checkIfProgressiveRecipeUnlocked(item.asItem(), APPersistentState.get().getIntCheckValue(key), items)) {
                if (!triggeredCheckRequired.get()) {
                    triggeredCheckRequired.set(true);
                    sendRequiresText(player, key, getTierForProgressive(key, item.asItem()));
                }
                bl.set(false);
            }
        });
        BOOLEAN_ITEMS.forEach((key, items) -> {
            if (items.contains(item.asItem()) && !APPersistentState.get().getBooleanCheckValue(key)) {
                if (!triggeredCheckRequired.get()) {
                    triggeredCheckRequired.set(true);
                    sendRequiresText(player, key, 1);
                }
                bl.set(false);
            }
        });

        return bl.get();
    }

    public static int getTierForProgressive(String check, Item item) {
        HashMap<Item, Integer> map = PROGRESSIVE_ITEMS.get(check);
        if (map != null) {
            return map.getOrDefault(item, 0);
        }
        return 0;
    }

    /**
     * Checks if the player has a check, and sends a message if they don't
     * @param player the player
     * @param check the name of the check
     * @param amount the amount of the check the player needs
     */
    public static boolean hasProgressiveCheck(PlayerEntity player, String check, int amount) {
        if (APPersistentState.get().getIntCheckValue(check) >= amount) {
            return true;
        }
        sendRequiresText(player, check, amount);
        return false;
    }

    /**
     * Checks if the player has a check, and sends a message if they don't
     * @param player the player
     * @param check the name of the check
     * @param item the item that's associated with the check
     */
    public static boolean hasProgressiveCheck(PlayerEntity player, String check, ItemConvertible item) {
        return hasProgressiveCheck(player, check, getTierForProgressive(check, item.asItem()));
    }

    /**
     * Checks if the player has a check, and sends a message if they don't
     * @param player the player
     * @param check the name of the check
     */
    public static boolean hasCheck(PlayerEntity player, String check) {
        if (APPersistentState.get().getBooleanCheckValue(check)) {
            return true;
        }
        sendRequiresText(player, check, 1);
        return false;
    }

    /**
     * Sends Message to the player to inform them that they're missing the required Check
     * @param player the player
     * @param check the name of the check
     */
    public static void sendRequiresText(PlayerEntity player, String check, int amount) {
        if (player != null) {
            if (!player.getWorld().isClient()) {
                String checkName = SavedArchipelagoItems.ID_TO_NAME_MAP.get(check);
                String extraInfo = amount > 1 ? " (x" + amount + ")" : "";

                player.sendMessage(
                        Text.translatable("archipelago.check.requires_check", checkName + extraInfo)
                                .setStyle(Style.EMPTY.withColor(0xFF0000)),
                        true
                );
                player.playSound(KoalaLibSounds.CONSOLE_CRAFT_FAIL, SoundCategory.PLAYERS, 1, 1);
            }
        }
    }

    private static boolean checkIfProgressiveRecipeUnlocked(ItemConvertible item, int lvl, Map<Item, Integer> map) {
        Integer tier = map.get(item.asItem());
        return !(tier == null || lvl >= tier);
    }
}
