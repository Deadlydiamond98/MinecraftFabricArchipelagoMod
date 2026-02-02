package net.deadlydiamond98.archipelago.networking.s2c;

import com.google.common.collect.BiMap;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.locations.APLocations;
import net.deadlydiamond98.archipelago.client.ArchipelagoItemIconRenderer;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.deadlydiamond98.archipelago.util.tracker.ArchipelagoTrackingData;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Set;

public class SendUncheckedItemsS2CPacket {
    public static final Identifier ID = APMod.id("send_unchecked_items");

    public static void send() {
        APServerUtil.runOnServer(server -> server.getPlayerManager().getPlayerList()
                .forEach(SendUncheckedItemsS2CPacket::send)
        );
    }

    public static void send(ServerPlayerEntity player) {
        Archipelago.run(archipelago -> {
            PacketByteBuf buf = PacketByteBufs.create();
            Set<Long> locations = archipelago.getLocationManager().getMissingLocations();
            long[] locationsArray = new long[locations.size()];
            int index = 0;

            for (Long location : locations) {
                locationsArray[index++] = location;
            }

            buf.writeLongArray(locationsArray);

            ServerPlayNetworking.send(player, ID, buf);
        });
    }

    public static class Handler {
        public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
            BiMap<Long, Identifier> items = APLocations.ITEMSANITY_LOCATIONS.inverse();
            ArchipelagoTrackingData.UNCHECKED_LOCATIONS.clear();
            ArchipelagoTrackingData.UNCHECKED_ITEMS.clear();

            long[] locationsArray = buf.readLongArray();
            for (long id : locationsArray) {
                Identifier itemID = items.get(id);
                Item item = Registries.ITEM.get(itemID);
                if (!item.getDefaultStack().isEmpty()) {
                    ArchipelagoTrackingData.UNCHECKED_ITEMS.add(item);
                }
            }

            for (long id : locationsArray) {
                ArchipelagoTrackingData.UNCHECKED_LOCATIONS.add(id);
            }
        }
    }
}
