package net.deadlydiamond98.aam.init;

import net.deadlydiamond98.aam.AAM;
import net.deadlydiamond98.aam.common.handlers.FletchingTableScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class AAMScreenHandlers {

    public static final ScreenHandlerType<FletchingTableScreenHandler> FLETCHING_TABLE = register("fletching_table",
            new ScreenHandlerType<>(FletchingTableScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );

    public static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType<T> screenHandler) {
        return Registry.register(Registries.SCREEN_HANDLER, new Identifier(AAM.MOD_ID, name), screenHandler);
    }

    public static void register() {}
}
