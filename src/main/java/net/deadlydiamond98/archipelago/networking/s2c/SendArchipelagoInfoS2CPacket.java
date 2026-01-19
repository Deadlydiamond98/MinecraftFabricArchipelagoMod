package net.deadlydiamond98.archipelago.networking.s2c;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoGoalHelper;
import net.deadlydiamond98.archipelago.archipelago.items.SavedArchipelagoItems;
import net.deadlydiamond98.archipelago.archipelago.locations.ArchipelagoLocations;
import net.deadlydiamond98.archipelago.client.screens.GameTrackerScreen;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.tracker.TrackerDataHolder;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SendArchipelagoInfoS2CPacket {
    public static final Identifier ID = APMod.id("tracker_receive_packet");

    public static void send(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        APPersistentState states = APPersistentState.get();

        // GOAL
        buf.writeVarInt(ArchipelagoGoalHelper.getGoalID());

        // ADVANCEMENTS
        buf.writeVarInt(states.getAdvancementIds().size());
        buf.writeVarInt(ArchipelagoLocations.LOCATIONS.size());

        // RUBIES
        buf.writeVarInt(APPersistentState.get().getCollectedRubies());
        buf.writeVarInt(ArchipelagoGoalHelper.getRubiesNeeded());

        // CHECKS
        buf.writeInt(states.toggleChecks.size() + states.progressiveLevelChecks.size());

        states.toggleChecks.forEach((s, booleanAPState) -> {
            buf.writeVarInt(booleanAPState.get() ? 1 : 0);
            buf.writeString(SavedArchipelagoItems.ID_TO_NAME_MAP.get(s));
            buf.writeString(s);
            buf.writeBoolean(false);
        });

        states.progressiveLevelChecks.forEach((s, integerAPState) -> {
            buf.writeVarInt(integerAPState.get());
            buf.writeString(SavedArchipelagoItems.ID_TO_NAME_MAP.get(s));
            buf.writeString(s);
            buf.writeBoolean(true);
        });

        ServerPlayNetworking.send(player, ID, buf);
    }

    public static class Handler {
        public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
            int goal = buf.readVarInt();
            int currentAdvancements = buf.readVarInt();
            int maxAdvancements = buf.readVarInt();
            int currentRubies = buf.readVarInt();
            int maxRubies = buf.readVarInt();

            List<TrackerDataHolder.TrackerEntry> entries = new ArrayList<>();
            int iterateNum = buf.readInt();
            for (int i = 0; i < iterateNum; i++) {
                entries.add(new TrackerDataHolder.TrackerEntry(buf.readVarInt(), buf.readString(), buf.readString(), buf.readBoolean()));
            }

            entries.sort((o1, o2) -> {
                String name1 = o1.name();
                String name2 = o2.name();
                return name1.compareToIgnoreCase(name2);
            });

            GameTrackerScreen.tracker = new TrackerDataHolder(
                    goal, currentAdvancements, maxAdvancements, currentRubies, maxRubies, entries
            );
        }
    }
}
