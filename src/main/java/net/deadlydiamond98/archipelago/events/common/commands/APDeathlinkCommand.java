package net.deadlydiamond98.archipelago.events.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class APDeathlinkCommand {
    public static void deathlinkCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("archipelago")
                .then(CommandManager.literal("deathlink")
                        .then(CommandManager.argument("enableDeathlink", BoolArgumentType.bool())
                                .executes(APDeathlinkCommand::toggleDeathlink)
                )
        ));

        dispatcher.register(CommandManager.literal("deathlink")
                .then(CommandManager.argument("enableDeathlink", BoolArgumentType.bool())
                        .executes(APDeathlinkCommand::toggleDeathlink)
        ));
    }

    private static int toggleDeathlink(CommandContext<ServerCommandSource> context) {
        return Archipelago.runCommand(archipelago -> archipelago.setDeathLinkEnabled(
                        BoolArgumentType.getBool(context, "enableDeathlink")),
                () -> APServerUtil.sendMessage(Text.translatable("archipelago.connection.no_connection"))
        );
    }
}
