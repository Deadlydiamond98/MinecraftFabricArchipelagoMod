package net.deadlydiamond98.archipelago.archipelago.items.type.filler;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.minecraft.block.SuspiciousStewIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class SuspiciousStewAPItem extends AbstractAPItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        List<SuspiciousStewIngredient> ingredients = SuspiciousStewIngredient.getAll();
        SuspiciousStewIngredient ingredient = ingredients.get(player.getRandom().nextBetween(0, ingredients.size() - 1));
        ItemStack stew = new ItemStack(Items.SUSPICIOUS_STEW);
        SuspiciousStewItem.addEffectToStew(stew, ingredient.getEffectInStew(), ingredient.getEffectInStewDuration());
        giveItem(player, stew);
    }
}
