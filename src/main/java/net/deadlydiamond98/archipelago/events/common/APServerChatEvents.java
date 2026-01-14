package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.APModConfigs;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;

public class APServerChatEvents {
    public static void register() {
        // Sends Chat messages that Minecraft Players Send in Chat
        ServerMessageEvents.CHAT_MESSAGE.register((message, sender, params) -> {
            if (sender != null && APModConfigs.Main.chatMessagesAppearInClient) {
                Archipelago.run(archipelago -> {
                    String msg = message.getContent().getString();

                    if (!msg.startsWith("!")) {
                        msg = "<" + sender.getName().getString() + "> " + msg;
                    }

                    archipelago.sendChat(msg);
                });
            }
        });
    }
}
