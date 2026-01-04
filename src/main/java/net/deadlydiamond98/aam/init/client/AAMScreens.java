package net.deadlydiamond98.aam.init.client;

import net.deadlydiamond98.aam.client.screen.FletchingTableScreen;
import net.deadlydiamond98.aam.init.AAMScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AAMScreens {
    public static void register() {
        HandledScreens.register(AAMScreenHandlers.FLETCHING_TABLE, FletchingTableScreen::new);
    }
}
