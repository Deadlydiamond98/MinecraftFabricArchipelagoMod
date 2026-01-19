package net.deadlydiamond98.archipelago.init.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class APKeybindings {
    public static final KeyBinding GAME_TRACKER_KEYBINDING = register("open_tracker", GLFW.GLFW_KEY_K);

    public static KeyBinding register(String name, int key) {
        return register(name, InputUtil.Type.KEYSYM, key);
    }

    public static KeyBinding register(String name, InputUtil.Type type, int key) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.archipelago." + name,
                type,
                key,
                "key.archipelago.category.keys"
        ));
    }

    public static void register() {}
}
