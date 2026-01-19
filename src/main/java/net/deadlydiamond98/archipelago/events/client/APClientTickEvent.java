package net.deadlydiamond98.archipelago.events.client;

import net.deadlydiamond98.archipelago.client.screens.GameTrackerScreen;
import net.deadlydiamond98.archipelago.init.client.APKeybindings;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;

public class APClientTickEvent {
    private static boolean pressedTrackerOpenKeybinding = false;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (APKeybindings.GAME_TRACKER_KEYBINDING.isPressed()) {
                if (!pressedTrackerOpenKeybinding) {
                    client.setScreen(new GameTrackerScreen(Text.literal("Game Tracker")));
                    pressedTrackerOpenKeybinding = true;
                }
            } else {
                pressedTrackerOpenKeybinding = false;
            }
        });
    }
}
