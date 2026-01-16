package net.deadlydiamond98.archipelago.archipelago;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

import java.io.FileReader;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ArchipelagoServerConnector {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static String lastConnectedServer = "archipelago.gg:";
    public static String lastConnectedPlayer = "";

    public static String archipelagoServer = "";
    public static String archipelagoPlayer = "";
    public static String archipelagoPassword = "";


    public static void connectToServer() {
        APPersistentState state = APPersistentState.get();
        String server = state.currentServer;
        if (server == null) {
            if (!archipelagoServer.isEmpty()) {
                connectToServer(archipelagoServer, archipelagoPlayer, archipelagoPassword);
                archipelagoServer = "";
                archipelagoPlayer = "";
                archipelagoPassword = "";
            }
        } else {
            connectToServer(server, state.currentPlayer, state.currentPassword);
        }
    }

    public static int connectToServer(String server, String player, String password) {
        APPersistentState state = APPersistentState.get();
        AtomicInteger i = new AtomicInteger();
        Archipelago.run(archipelago -> {
            archipelago.setName(player);
            archipelago.setPassword(password);
            try {
                archipelago.connect(server);
                updateLastConnectedServer(server, player);
                state.currentServer = archipelagoServer;
                state.currentPlayer = archipelagoPlayer;
                state.currentPassword = archipelagoPassword;
                i.set(1);
            } catch (URISyntaxException e) {
                APServerUtil.sendMessage(Text.translatable("archipelago.connection.failed"));
            }
        });
        return i.get();
    }

    public static void updateLastConnectedServer(String newServer, String player) {
        lastConnectedServer = newServer;
        lastConnectedPlayer = player;
        writeLastConnectedServer();
    }

    // Read and Write last Connected Server to file

    public static void readLastConnectedServer() {
        try {
            FileReader reader = new FileReader(getPath().toFile());
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            lastConnectedServer = json.get("lastConnectedServer").getAsString();
            lastConnectedPlayer = json.get("lastPlayer").getAsString();
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
            GSON.toJson(Map.of("lastConnectedServer", lastConnectedServer, "lastPlayer", lastConnectedPlayer), writer);
            writer.close();
        } catch (Exception ignored) {}
    }

    private static Path getPath() {
        return FabricLoader.getInstance().getConfigDir().resolve("minecraft_ap_reconnection.json");
    }
}
