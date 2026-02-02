package net.deadlydiamond98.archipelago.archipelago.items.type.filler;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class ItemstackAPItem extends AbstractAPItem {
    private final ItemConvertible item;
    private final int count;

    public ItemstackAPItem(ItemConvertible item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        ItemStack stack = new ItemStack(this.item, this.count);
        giveItem(player, stack);
    }
}
