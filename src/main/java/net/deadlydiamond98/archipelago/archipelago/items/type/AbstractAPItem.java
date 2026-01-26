package net.deadlydiamond98.archipelago.archipelago.items.type;

import io.github.archipelagomw.parts.NetworkItem;
import net.deadlydiamond98.archipelago.networking.ArchipelagoPacketManager;
import net.deadlydiamond98.koalalib.init.KoalaLibSounds;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAPItem {
    private static final Map<PlayerEntity, Integer> NO_MORE_EAR_BLEEDING = new HashMap<>();

    public abstract void applyReward(ServerPlayerEntity player);

    public final void apply(NetworkItem item, ServerPlayerEntity player, boolean traplink) {
        apply(item.itemName, player, traplink);
    }

    public final void apply(String itemName, ServerPlayerEntity player, boolean traplink) {
        // Sends Trap With traplink
        if (traplink) {
            ArchipelagoPacketManager.sendTraplink(itemName);
        }

        // Done like this so that getting multiple of these doesn't play a really loud sound due to multiple stacking
        if (NO_MORE_EAR_BLEEDING.getOrDefault(player, 0) < player.age) {
            player.playSound(getSoundEvent(), SoundCategory.PLAYERS, getSoundVolume(), 1);
            NO_MORE_EAR_BLEEDING.put(player, player.age);
        }
        Style style = Style.EMPTY.withColor(getTextColor());
        player.sendMessage(Text.literal(itemName).setStyle(style), true);
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
