package net.deadlydiamond98.archipelago.events.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class APTextClientCommands {

    public static void textClientCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        // General
        registerAPCommand(dispatcher, "license");
        registerAPCommand(dispatcher, "options");
        registerAPCommand(dispatcher, "players");
        registerAPCommand(dispatcher, "status");
        registerAPCommand(dispatcher, "alias", "name");

        // Information
        registerAPCommand(dispatcher, "remaining");
        registerAPCommand(dispatcher, "missing");
        registerAPCommand(dispatcher, "checked");

        // Hints
        registerAPCommand(dispatcher, "hint");
        registerAPCommand(dispatcher, "hint", "item");
        registerAPCommand(dispatcher, "hint_location", "location");
        registerCommand(dispatcher, "hint");
        registerCommand(dispatcher, "hint", "item");

        // Collect / Release
        registerAPCommand(dispatcher, "collect");
        registerAPCommand(dispatcher, "release");
        registerCommand(dispatcher, "release");

        // Cheats
        registerAPCommand(dispatcher, "getitem", "item");
    }

    /**
     * Adds a Command to /archipelago with 1 parameter
     */
    private static void registerAPCommand(CommandDispatcher<ServerCommandSource> dispatcher, String command, String parameter) {
        dispatcher.register(CommandManager.literal("archipelago").then(CommandManager.literal(command)
                .then(CommandManager.argument(parameter, StringArgumentType.string())
                        .executes(context -> {
                            Archipelago client = APMod.apClient();
                            if (client != null && client.isConnected()) {
                                client.sendChat("!" + command + " " + StringArgumentType.getString(context, parameter));
                                return 1;
                            }
                            APServerUtil.sendMessage(Text.translatable("archipelago.connection.no_connection"));
                            return 0;
                        })
                )
        ));
    }

    /**
     * Adds a Command to /archipelago with no parameters
     */
    private static void registerAPCommand(CommandDispatcher<ServerCommandSource> dispatcher, String command) {
        dispatcher.register(CommandManager.literal("archipelago").then(CommandManager.literal(command)
                .executes(context -> {
                    Archipelago client = APMod.apClient();
                    if (client != null && client.isConnected()) {
                        client.sendChat("!" + command);
                        return 1;
                    }
                    APServerUtil.sendMessage(Text.translatable("archipelago.connection.no_connection"));
                    return 0;
                })
        ));
    }

    /**
     * creates a new command with a parameter
     */
    private static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher, String command, String parameter) {
        dispatcher.register(CommandManager.literal(command)
                .then(CommandManager.argument(parameter, StringArgumentType.string())
                        .executes(context -> {
                            Archipelago client = APMod.apClient();
                            if (client != null && client.isConnected()) {
                                client.sendChat("!" + command + " " + StringArgumentType.getString(context, parameter));
                                return 1;
                            }
                            APServerUtil.sendMessage(Text.translatable("archipelago.connection.no_connection"));
                            return 0;
                        })
                )
        );
    }

    /**
     * creates a new command with no parameters
     */
    private static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher, String command) {
        dispatcher.register(CommandManager.literal(command)
                .executes(context -> {
                    Archipelago client = APMod.apClient();
                    if (client != null && client.isConnected()) {
                        client.sendChat("!" + command);
                        return 1;
                    }
                    APServerUtil.sendMessage(Text.translatable("archipelago.connection.no_connection"));
                    return 0;
                })
        );
    }
}
