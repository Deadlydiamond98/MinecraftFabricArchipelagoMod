package net.deadlydiamond98.archipelago.archipelago.items.type.filler;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;

public class EnchantmentAPItem extends AbstractAPItem {
    private final Enchantment enchantment;
    private final int lvl;

    public EnchantmentAPItem(Enchantment enchantment, int lvl) {
        this.enchantment = enchantment;
        this.lvl = lvl;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantedBookItem.addEnchantment(stack, new EnchantmentLevelEntry(this.enchantment, this.lvl));
        giveItem(player, stack);
    }
}
