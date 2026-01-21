package net.deadlydiamond98.archipelago.common.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EssenceOfArchipelagoItem extends Item {
    public EssenceOfArchipelagoItem(Settings settings) {
        super(settings.rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.archipelago.unimplemented_desc").setStyle(Style.EMPTY.withColor(Formatting.RED)));
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable(getTranslationKey() + ".tooltipa").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        tooltip.add(Text.translatable(getTranslationKey() + ".tooltipb").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
    }
}
