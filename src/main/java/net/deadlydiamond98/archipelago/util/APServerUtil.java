package net.deadlydiamond98.archipelago.util;

import net.deadlydiamond98.archipelago.common.archipelago.locations.ArchipelagoLocations;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Contains various helper methods for running things on the Current Minecraft Server
 */
public class APServerUtil {
    // Server Variable for current server
    public static @Nullable MinecraftServer server;

    /**
     * Runs a method on the current server if the server isn't null
     * @param fallback the value to return if the server isn't present
     * @param function the function
     * @return the return value
     * @param <T> the type of the value that is returned
     */
    public static <T> T runOnServer(T fallback, Function<MinecraftServer, T> function) {
        if (server != null) {
            return function.apply(server);
        }
        return fallback;
    }

    /**
     * Runs a method on the current server if the server isn't null
     * @param consumer the method to run
     */
    public static void runOnServer(Consumer<MinecraftServer> consumer) {
        if (server != null) {
            server.execute(() -> consumer.accept(server));
        }
    }

    /**
     * Sends a message to all players on the Server
     * @param text text to send
     */
    public static void sendMessage(MutableText text) {
        runOnServer(server -> server.getPlayerManager().broadcast(text, false));
    }
}
