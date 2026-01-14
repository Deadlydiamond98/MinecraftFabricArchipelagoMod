package net.deadlydiamond98.archipelago.archipelago.items.type;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

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
