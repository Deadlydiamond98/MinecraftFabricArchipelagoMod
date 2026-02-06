package net.deadlydiamond98.archipelago.events.common;

import com.mojang.brigadier.arguments.StringArgumentType;

import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.items.ArchipelagoItems;
import net.deadlydiamond98.archipelago.archipelago.items.MultiworldTraps;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.events.common.commands.APConnectionCommands;
import net.deadlydiamond98.archipelago.events.common.commands.APDeathlinkCommand;
import net.deadlydiamond98.archipelago.events.common.commands.APTextClientCommands;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class APSeverCommandEvents {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            APConnectionCommands.connectionCommands(dispatcher);
            APTextClientCommands.textClientCommands(dispatcher);
            APDeathlinkCommand.deathlinkCommand(dispatcher);

            // Say Command (used for commands that aren't covered by what I added or for sending general messages)
            dispatcher.register(CommandManager.literal("archipelago").then(CommandManager.literal("say")
                    .then(CommandManager.argument("message", StringArgumentType.string())
                            .executes(context -> Archipelago.runCommand(archipelago -> {
                                archipelago.sendChat(StringArgumentType.getString(context, "message"));
                            }, () -> {
                                APServerUtil.sendMessage(Text.translatable("archipelago.connection.no_connection"));
                            }))
                    )
            ));

            // Command for granting or revoking checks, only enabled in the Dev Environment
            if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                dispatcher.register(CommandManager.literal("apDebug")
                        .then(CommandManager.argument("check", StringArgumentType.string())
                                .executes(context -> {
                                    APPersistentState.get().triggerCheck(StringArgumentType.getString(context, "check"));
                                    return 1;
                                })
                        )
                );

                // Command for testing items and traps, only enabled in the Dev Environment
            if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                dispatcher.register(CommandManager.literal("apTest")
                        .then(CommandManager.literal("trap")
                                .then(CommandManager.argument("trapName", StringArgumentType.string())
                                        .executes(context -> {
                                            String trapName = StringArgumentType.getString(context, "trapName");
                                            ServerPlayerEntity player = context.getSource().getPlayer();
                                            
                                            if (player == null) {
                                                context.getSource().sendFeedback(() -> Text.literal("§cCan only be used by a player!"), false);
                                                return 0;
                                            }
                                            
                                            AbstractAPItem trap = MultiworldTraps.TRAPS.get(trapName);
                                            if (trap != null) {
                                                trap.apply(trapName, player);
                                                context.getSource().sendFeedback(() -> Text.literal("§aApplied trap: " + trapName), true);
                                                return 1;
                                            } else {
                                                context.getSource().sendFeedback(() -> Text.literal("§cTrap '" + trapName + "' not found!"), false);
                                                return 0;
                                            }
                                        })
                                )
                                .executes(context -> {
                                    context.getSource().sendFeedback(() -> Text.literal("§eAvailable traps:"), false);
                                    MultiworldTraps.TRAPS.keySet().forEach(trapName -> {
                                        context.getSource().sendFeedback(() -> Text.literal("§7- " + trapName), false);
                                    });
                                    return 1;
                                })
                        )
                        .then(CommandManager.literal("item")
                                .then(CommandManager.argument("itemName", StringArgumentType.string())
                                        .executes(context -> {
                                            String itemName = StringArgumentType.getString(context, "itemName");
                                            ServerPlayerEntity player = context.getSource().getPlayer();
                                            
                                            if (player == null) {
                                                context.getSource().sendFeedback(() -> Text.literal("§cCan only be used by a player!"), false);
                                                return 0;
                                            }
                                            
                                            AbstractAPItem item = ArchipelagoItems.ITEMS.get(itemName);
                                            if (item != null) {
                                                item.apply(itemName, player);
                                                context.getSource().sendFeedback(() -> Text.literal("§aGave item: " + itemName), true);
                                                return 1;
                                            } else {
                                                context.getSource().sendFeedback(() -> Text.literal("§cItem '" + itemName + "' not found!"), false);
                                                return 0;
                                            }
                                        })
                                )
                                .executes(context -> {
                                    context.getSource().sendFeedback(() -> Text.literal("§eAvailable items:"), false);
                                    ArchipelagoItems.ITEMS.keySet().forEach(itemName -> {
                                        context.getSource().sendFeedback(() -> Text.literal("§7- " + itemName), false);
                                    });
                                    return 1;
                                })
                        )
                        .executes(context -> {
                            context.getSource().sendFeedback(() -> Text.literal("§eUsage:"), false);
                            context.getSource().sendFeedback(() -> Text.literal("§7/apTest trap [trapName] - Test a trap"), false);
                            context.getSource().sendFeedback(() -> Text.literal("§7/apTest item [itemName] - Test an item"), false);
                            context.getSource().sendFeedback(() -> Text.literal("§7/apTest trap - List all traps"), false);
                            context.getSource().sendFeedback(() -> Text.literal("§7/apTest item - List all items"), false);
                            return 1;
                        })
                );
            }}
        });
    }
}
