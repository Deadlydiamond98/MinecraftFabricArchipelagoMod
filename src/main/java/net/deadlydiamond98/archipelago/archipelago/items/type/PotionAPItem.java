package net.deadlydiamond98.archipelago.archipelago.items.type;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        ItemStack stack = new ItemStack(Items.POTION);
        PotionUtil.setPotion(stack, this.potion);
        giveItem(player, stack);
    }
}
