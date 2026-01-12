package net.deadlydiamond98.archipelago.util;

import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.MutableText;

public class APMessageUtil {

    /**
     * Sends a message to all players on the Server
     * @param text text to send
     */
    public static void sendMessage(MutableText text) {
        MinecraftServer server = APMod.server;

        if (server != null) {
            server.execute(() -> server.getPlayerManager().broadcast(text, false));
        }
    }
}
