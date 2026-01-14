package net.deadlydiamond98.archipelago.archipelago;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ArchipelagoReconnector {
    // TODO: Will likely end up removing this in favor of doing an Archipelago World Type or something
    //       not 100% sure how I want to handle it yet

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static String lastConnectedServer = "";

    public static void updateLastConnectedServer(String newServer) {
        lastConnectedServer = newServer;
        writeLastConnectedServer();
    }

    public static String getLastConnectedServer() {
        return lastConnectedServer;
    }

    public static void readLastConnectedServer() {
        try {
            FileReader reader = new FileReader(getPath().toFile());
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            lastConnectedServer = json.get("lastConnectedServer").getAsString();
            reader.close();
        } catch (Exception ignored) {
            try {
                if (!Files.exists(getPath())) {
                    Files.createFile(getPath());
                }
                writeLastConnectedServer();
            } catch (Exception ignored2) {}
        }
    }

    public static void writeLastConnectedServer() {
        try {
            FileWriter writer = new FileWriter(getPath().toFile());
            GSON.toJson(Map.of("lastConnectedServer", lastConnectedServer), writer);
            writer.close();
        } catch (Exception ignored) {}
    }

    private static Path getPath() {
        return FabricLoader.getInstance().getConfigDir().resolve("minecraft_ap_reconnection.json");
    }
}
