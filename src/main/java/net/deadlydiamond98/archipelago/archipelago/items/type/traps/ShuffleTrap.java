package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleTrap extends AbstractTrapItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {

        List<ItemStack> allItems = new ArrayList<>();

        for (int i = 0; i < player.getInventory().main.size(); i++) {
            ItemStack stack = player.getInventory().main.get(i);
            allItems.add(stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
            player.getInventory().main.set(i, ItemStack.EMPTY);
        }

        for (int i = 0; i < player.getInventory().armor.size(); i++) {
            ItemStack stack = player.getInventory().armor.get(i);
            if (!stack.isEmpty()) {
                allItems.add(stack.copy());
            }
            player.getInventory().armor.set(i, ItemStack.EMPTY);
        }

        ItemStack offhandStack = player.getInventory().offHand.get(0);
        if (!offhandStack.isEmpty()) {
            allItems.add(offhandStack.copy());
        }
        player.getInventory().offHand.set(0, ItemStack.EMPTY);

        Collections.shuffle(allItems, new Random(player.getWorld().getTime()));

        int itemIndex = 0;
        for (int i = 0; i < player.getInventory().main.size() && itemIndex < allItems.size(); i++) {
            ItemStack item = allItems.get(itemIndex);
            if (!item.isEmpty() || item == ItemStack.EMPTY) {
                player.getInventory().main.set(i, item);
                itemIndex++;
            } else {

                player.getInventory().main.set(i, ItemStack.EMPTY);
            }
        }

        while (itemIndex < allItems.size()) {
            ItemStack leftoverItem = allItems.get(itemIndex);
            if (!leftoverItem.isEmpty()) {
                player.dropItem(leftoverItem, false);
            }
            itemIndex++;
        }

        player.getInventory().markDirty();
    }
}