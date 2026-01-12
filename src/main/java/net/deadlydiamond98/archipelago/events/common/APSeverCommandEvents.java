package net.deadlydiamond98.archipelago.events.common;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoClient;
import net.deadlydiamond98.archipelago.events.common.commands.APConnectionCommands;
import net.deadlydiamond98.archipelago.events.common.commands.APTextClientCommands;
import net.deadlydiamond98.archipelago.util.APMessageUtil;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class APSeverCommandEvents {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            APConnectionCommands.connectionCommands(dispatcher);
            APTextClientCommands.textClientCommands(dispatcher);

            // Say Command (used for commands that aren't covered by what I added or for sending general messages)
            dispatcher.register(CommandManager.literal("archipelago").then(CommandManager.literal("say")
                    .then(CommandManager.argument("message", StringArgumentType.string())
                            .executes(context -> {
                                ArchipelagoClient client = APMod.apClient();
                                if (client != null && client.isConnected()) {
                                    client.sendChat(StringArgumentType.getString(context, "message"));
                                    return 1;
                                }
                                APMessageUtil.sendMessage(Text.translatable("archipelago.connection.no_connection"));
                                return 0;
                            })
                    )
            ));
        });
    }
}
