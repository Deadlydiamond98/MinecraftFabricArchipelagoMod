package net.deadlydiamond98.archipelago.archipelago.items.type;

import io.github.archipelagomw.ItemManager;
import io.github.archipelagomw.parts.NetworkItem;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.items.ArchipelagoItems;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.networking.s2c.SendArchipelagoInfoS2CPacket;
import net.deadlydiamond98.archipelago.util.mixinterfaces.IPlayerReceivedItems;
import net.deadlydiamond98.koalalib.init.KoalaLibSounds;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractAPItem {

    private static final Map<PlayerEntity, Integer> NO_MORE_EAR_BLEEDING = new HashMap<>();

    public static void sync(MinecraftServer server) {
        server.getPlayerManager().getPlayerList().forEach(player -> {
            IPlayerReceivedItems items = (IPlayerReceivedItems) player;
            List<Long> ids = items.archipelago$getItemIDs();

            Archipelago.run(archipelago -> {
                APPersistentState state = APPersistentState.get();
                state.getReceivedItems().forEach((aLong, name) -> {
                    if (!ids.contains(aLong)) {
                        items.archipelago$putItemID(aLong);
                        AbstractAPItem item = ArchipelagoItems.ITEMS.get(name);
                        if (item != null) {
                            item.apply(name, player);
                        }
                    }
                });
            });
        });
    }

    /**
     * Called to trigger the item in the world.
     *
     * @param item   the Item
     * @param server the Server
     * @param index
     */
    public final void receiveItem(NetworkItem item, MinecraftServer server, long index) {
        triggerOneTimeEffect(item, server);
        server.getPlayerManager().getPlayerList().forEach(player -> {
            apply(item.itemName, player);
            IPlayerReceivedItems items = (IPlayerReceivedItems) player;
            items.archipelago$putItemID(index);
            SendArchipelagoInfoS2CPacket.send(player);
        });
    }

    /**
     * Triggers an event or effect that only happens when the item is initially received, such as sending a Traplink,
     * or Updating Persistant Data
     */
    protected void triggerOneTimeEffect(NetworkItem item, MinecraftServer server) {}

    /**
     * Applies the item to the player, with a sound effect and chat message
     * @param item the Item Name
     * @param player the Player
     */
    public final void apply(String item, ServerPlayerEntity player) {
        ServerWorld world = (ServerWorld) player.getWorld();
        int time = world.getServer().getTicks();
        // Done like this so that getting multiple of these doesn't play a really loud sound due to multiple stacking
        if (NO_MORE_EAR_BLEEDING.getOrDefault(player, 0) < time) {
            player.playSound(getSoundEvent(), SoundCategory.PLAYERS, getSoundVolume(), 1);
            NO_MORE_EAR_BLEEDING.put(player, time);
        }

        Style style = Style.EMPTY.withColor(getTextColor());
        player.sendMessage(Text.literal(item).setStyle(style), true);
        applyReward(player);
    }

    /** Applies the Reward to the Player
     * @param player the Player
     */
    protected void applyReward(ServerPlayerEntity player) {}

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
