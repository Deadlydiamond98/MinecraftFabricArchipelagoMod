package net.deadlydiamond98.archipelago.archipelago.items.type;

import net.minecraft.enchantment.Enchantment;
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
        stack.addEnchantment(this.enchantment, this.lvl);
        giveItem(player, stack);
    }
}
