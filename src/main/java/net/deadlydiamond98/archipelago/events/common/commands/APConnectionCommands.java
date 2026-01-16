package net.deadlydiamond98.archipelago.events.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.archipelagomw.Client;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoServerConnector;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class APConnectionCommands {

    public static void connectionCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> start = CommandManager.literal("archipelago");

        // Connect Command /////////////////////////////////////////////////////////////////////////////////////////////
        dispatcher.register(start.then(CommandManager.literal("connect")
                .then(CommandManager.argument("server", StringArgumentType.string())
                        .then(CommandManager.argument("player", StringArgumentType.string())
                                .executes(context -> ArchipelagoServerConnector.connectToServer(
                                        StringArgumentType.getString(context, "server"),
                                        StringArgumentType.getString(context, "player"),
                                        ""
                                ))
                        )
                ))
        );

        dispatcher.register(CommandManager.literal("connect")
                .then(CommandManager.argument("server", StringArgumentType.string())
                        .then(CommandManager.argument("player", StringArgumentType.string())
                                .executes(context -> ArchipelagoServerConnector.connectToServer(
                                        StringArgumentType.getString(context, "server"),
                                        StringArgumentType.getString(context, "player"),
                                        ""
                                ))
                        )
                )
        );

        // Connect Command (with password) /////////////////////////////////////////////////////////////////////////////
        dispatcher.register(start.then(CommandManager.literal("connect")
                .then(CommandManager.argument("server", StringArgumentType.string())
                        .then(CommandManager.argument("player", StringArgumentType.string())
                                .then(CommandManager.argument("password", StringArgumentType.string())
                                        .executes(context -> ArchipelagoServerConnector.connectToServer(
                                                StringArgumentType.getString(context, "server"),
                                                StringArgumentType.getString(context, "player"),
                                                StringArgumentType.getString(context, "password")
                                        ))
                                )
                        )
                ))
        );

        dispatcher.register(CommandManager.literal("connect")
                .then(CommandManager.argument("server", StringArgumentType.string())
                        .then(CommandManager.argument("player", StringArgumentType.string())
                                .then(CommandManager.argument("password", StringArgumentType.string())
                                        .executes(context -> ArchipelagoServerConnector.connectToServer(
                                                StringArgumentType.getString(context, "server"),
                                                StringArgumentType.getString(context, "player"),
                                                StringArgumentType.getString(context, "password")
                                        ))
                                )
                        )
                )
        );

        // Disconnect Command //////////////////////////////////////////////////////////////////////////////////////////
        dispatcher.register(start.then(CommandManager.literal("disconnect")
                .executes(context -> Archipelago.runCommand(Client::close, () -> {}))
        ));

        dispatcher.register(CommandManager.literal("disconnect")
                .executes(context -> Archipelago.runCommand(Client::close, () -> {}))
        );
    }
}
