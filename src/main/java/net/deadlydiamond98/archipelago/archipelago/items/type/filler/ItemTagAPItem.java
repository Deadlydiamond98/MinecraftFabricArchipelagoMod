package net.deadlydiamond98.archipelago.archipelago.items.type.filler;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemTagAPItem extends AbstractAPItem {
    private final TagKey<Item> itemTag;
    private final int count;

    public ItemTagAPItem(TagKey<Item> itemTag, int count) {
        this.itemTag = itemTag;
        this.count = count;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        List<Item> items = new ArrayList<>();
        Registries.ITEM.getEntryList(this.itemTag).ifPresent(registryEntries -> {
            registryEntries.forEach(itemRegistryEntry -> {
                items.add(itemRegistryEntry.value());
            });
        });
        Item item = items.get(player.getRandom().nextBetween(0, items.size() - 1));
        if (item != null) {
            player.giveItemStack(new ItemStack(item, this.count));
        }
    }
}
