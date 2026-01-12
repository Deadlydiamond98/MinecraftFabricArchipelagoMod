package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoClient;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class APServerWorldEvents {
    public static void register() {
        ServerWorldEvents.LOAD.register(APServerWorldEvents::onLoad);
        ServerWorldEvents.UNLOAD.register(APServerWorldEvents::onUnload);
    }

    private static void onLoad(MinecraftServer server, ServerWorld serverWorld) {
        ArchipelagoClient.client = new ArchipelagoClient();
        APMod.server = server;
    }

    private static void onUnload(MinecraftServer server, ServerWorld serverWorld) {
        ArchipelagoClient.client.close();
        APMod.server = null;
    }
}
