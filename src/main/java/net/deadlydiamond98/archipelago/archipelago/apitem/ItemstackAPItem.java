package net.deadlydiamond98.archipelago.archipelago.apitem;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class ItemstackAPItem extends AbstractAPItem {
    private final Item item;
    private final int count;

    public ItemstackAPItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        ItemStack stack = new ItemStack(this.item, this.count);
        World world = player.getWorld();
        if (!player.giveItemStack(stack)) {
            world.spawnEntity(new ItemEntity(world, player.getX(), player.getY(), player.getZ(), stack));
        }
    }
}
