package net.deadlydiamond98.archipelago.archipelago.items.type.filler;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.init.APItems;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;

public class PotionAPItem extends AbstractAPItem {
    private final Potion potion;

    public PotionAPItem(Potion potion) {
        this.potion = potion;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        ItemStack stack = new ItemStack(APItems.SINGLE_USE_POTION);
        PotionUtil.setPotion(stack, this.potion);
        giveItem(player, stack);
    }
}
