package net.deadlydiamond98.archipelago.events.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoClient;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoReconnector;
import net.deadlydiamond98.archipelago.util.APMessageUtil;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.net.URISyntaxException;

public class APConnectionCommands {

    public static void connectionCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> start = CommandManager.literal("archipelago");

        // Connect Command /////////////////////////////////////////////////////////////////////////////////////////////
        dispatcher.register(start.then(CommandManager.literal("connect")
                .then(CommandManager.argument("server", StringArgumentType.string())
                        .then(CommandManager.argument("player", StringArgumentType.string())
                                .executes(context -> connectToArchipelago(
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
                                .executes(context -> connectToArchipelago(
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
                                        .executes(context -> connectToArchipelago(
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
                                        .executes(context -> connectToArchipelago(
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
                .executes(context -> {
                    ArchipelagoClient client = APMod.apClient();
                    if (client != null) {
                        client.disconnect();
                        return 1;
                    }
                    return 0;
                })
        ));

        dispatcher.register(CommandManager.literal("disconnect")
                .executes(context -> {
                    ArchipelagoClient client = APMod.apClient();
                    if (client != null) {
                        client.disconnect();
                        return 1;
                    }
                    return 0;
                })
        );

        // Reconnect Command ///////////////////////////////////////////////////////////////////////////////////////////
        dispatcher.register(start.then(CommandManager.literal("reconnect")
                .then(CommandManager.argument("player", StringArgumentType.string())
                        .executes(context -> connectToArchipelago(
                                ArchipelagoReconnector.getLastConnectedServer(),
                                StringArgumentType.getString(context, "player"),
                                ""
                        ))
                )
        ));

        dispatcher.register(CommandManager.literal("reconnect")
                .then(CommandManager.argument("player", StringArgumentType.string())
                        .executes(context -> connectToArchipelago(
                                ArchipelagoReconnector.getLastConnectedServer(),
                                StringArgumentType.getString(context, "player"),
                                ""
                        ))
                )
        );

        // Reconnect Command (with password) ///////////////////////////////////////////////////////////////////////////
        dispatcher.register(start.then(CommandManager.literal("reconnect")
                .then(CommandManager.argument("player", StringArgumentType.string())
                        .then(CommandManager.argument("password", StringArgumentType.string())
                                .executes(context -> connectToArchipelago(
                                        ArchipelagoReconnector.getLastConnectedServer(),
                                        StringArgumentType.getString(context, "player"),
                                        StringArgumentType.getString(context, "password")
                                ))
                        )
                )
        ));

        dispatcher.register(CommandManager.literal("reconnect")
                .then(CommandManager.argument("player", StringArgumentType.string())
                        .then(CommandManager.argument("password", StringArgumentType.string())
                                .executes(context -> connectToArchipelago(
                                        ArchipelagoReconnector.getLastConnectedServer(),
                                        StringArgumentType.getString(context, "player"),
                                        StringArgumentType.getString(context, "password")
                                ))
                        )
                )
        );
    }


    public static int connectToArchipelago(String apServer, String player, String password) {
        ArchipelagoClient client = APMod.apClient();

        if (client != null) {
            client.setName(player);
            client.setPassword(password);
            try {
                if (!apServer.isEmpty()) {
                    client.connect(apServer);
                    ArchipelagoReconnector.updateLastConnectedServer(apServer);
                } else {
                    APMessageUtil.sendMessage(Text.translatable("archipelago.connection.failed"));
                    return 0;
                }
            } catch (URISyntaxException e) {
                return 0;
            }
            return 1;
        }
        return 0;
    }
}
