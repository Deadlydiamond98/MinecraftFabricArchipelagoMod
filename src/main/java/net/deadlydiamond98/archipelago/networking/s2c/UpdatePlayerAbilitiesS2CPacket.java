package net.deadlydiamond98.archipelago.networking.s2c;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;


public class UpdatePlayerAbilitiesS2CPacket {
    public static final Identifier ID = APMod.id("update_player_abilities");

    public static boolean canSprint = false;
    public static boolean canJump = false;
    public static boolean canSwim = false;

    public static void send(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();

        APPersistentState state = APPersistentState.get();

        canSprint = state.getBooleanCheckValue("sprint");
        buf.writeBoolean(canSprint);

        canJump = state.getBooleanCheckValue("jump");
        buf.writeBoolean(canJump);

        canSwim = state.getBooleanCheckValue("swim");
        buf.writeBoolean(canSwim);

        ServerPlayNetworking.send(player, ID, buf);
    }

    public static class Handler {
        public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
            canSprint = buf.readBoolean();
            canJump = buf.readBoolean();
            canSwim = buf.readBoolean();
        }
    }
}
