package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.common.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class APServerWorldEvents {
    public static void register() {
        ServerWorldEvents.LOAD.register(APServerWorldEvents::onLoad);
        ServerWorldEvents.UNLOAD.register(APServerWorldEvents::onUnload);
    }

    private static void onLoad(MinecraftServer server, ServerWorld serverWorld) {
        Archipelago.client = new Archipelago();
        APServerUtil.server = server;
    }

    private static void onUnload(MinecraftServer server, ServerWorld serverWorld) {
        Archipelago.client.close();
        APServerUtil.server = null;
    }
}
