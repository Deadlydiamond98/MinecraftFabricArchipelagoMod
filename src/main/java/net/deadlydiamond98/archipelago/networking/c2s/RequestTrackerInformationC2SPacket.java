package net.deadlydiamond98.archipelago.networking.c2s;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.networking.s2c.SendArchipelagoInfoS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class RequestTrackerInformationC2SPacket {
    public static final Identifier ID = APMod.id("tracker_request_packet");

    public static void send() {
        ClientPlayNetworking.send(ID, PacketByteBufs.create());
    }

    public static class Handler {
        public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
            server.execute(() -> SendArchipelagoInfoS2CPacket.send(player));
        }
    }
}
