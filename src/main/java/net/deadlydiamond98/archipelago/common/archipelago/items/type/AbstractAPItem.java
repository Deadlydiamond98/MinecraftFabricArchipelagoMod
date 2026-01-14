package net.deadlydiamond98.archipelago.common.archipelago.items.type;

import io.github.archipelagomw.parts.NetworkItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import static io.github.archipelagomw.flags.NetworkItem.*;

public abstract class AbstractAPItem {
    public enum ItemType {
        PROGRESSION(0xAF99EF, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP),
        TRAP(0xE9786B, SoundEvents.ENTITY_WITHER_AMBIENT);

        public final int textColor;
        public final SoundEvent sound;

        ItemType(int textColor, @Nullable SoundEvent sound) {
            this.textColor = textColor;
            this.sound = sound;
        }
    }

    public abstract void applyReward(ServerPlayerEntity player);

    public final void apply(NetworkItem item, ServerPlayerEntity player) {
        ItemType itemType = getItemType(item.flags);
        if (itemType != null) {
            player.playSound(itemType.sound, SoundCategory.PLAYERS, 1, 1);
            Style style = Style.EMPTY.withColor(itemType.textColor);
            player.sendMessage(Text.literal(item.itemName).setStyle(style), true);
        }
        applyReward(player);
    }

    private static ItemType getItemType(int flags) {
        if (flagCheck(flags, ADVANCEMENT)) {
            return ItemType.PROGRESSION;
        } else if (flagCheck(flags, TRAP)) {
            return ItemType.TRAP;
        }
        return null;
    }

    private static boolean flagCheck(int flags, int networkItem) {
        return (flags & networkItem) == networkItem;
    }

}
