package net.deadlydiamond98.archipelago.archipelago.items.type;

import io.github.archipelagomw.parts.NetworkItem;
import net.deadlydiamond98.koalalib.init.KoalaLibSounds;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public abstract class AbstractAPItem {
    public abstract void applyReward(ServerPlayerEntity player);

    public final void apply(NetworkItem item, ServerPlayerEntity player) {
        player.playSound(getSoundEvent(), SoundCategory.PLAYERS, getSoundVolume(), 1);
        Style style = Style.EMPTY.withColor(getTextColor());
        player.sendMessage(Text.literal(item.itemName).setStyle(style), true);
        applyReward(player);
    }

    protected void giveItem(ServerPlayerEntity player, ItemStack stack) {
        World world = player.getWorld();
        if (!player.giveItemStack(stack)) {
            world.spawnEntity(new ItemEntity(world, player.getX(), player.getY(), player.getZ(), stack));
        }
    }

    protected float getSoundVolume() {
        return 1;
    }

    protected SoundEvent getSoundEvent() {
        return KoalaLibSounds.CONSOLE_CRAFT;
    }

    protected int getTextColor() {
        return 0x00ffaa;
    }
}
