package net.deadlydiamond98.archipelago.events.common;

import io.github.archipelagomw.Client;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.items.dataloader.APItemDataLoader;
import net.deadlydiamond98.archipelago.archipelago.locations.ArchipelagoLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.advancement.Advancement;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class APServerWorldEvents {
    public static void register() {
        ServerWorldEvents.LOAD.register(APServerWorldEvents::onLoad);
        ServerWorldEvents.UNLOAD.register(APServerWorldEvents::onUnload);
    }

    private static void onLoad(MinecraftServer server, ServerWorld serverWorld) {
        Archipelago.archipelago = new Archipelago();
        APServerUtil.server = server;
        APPersistentState.get().addMissingChecks();

        // Since ItemTags aren't able to be checked when loading the item data initially, it's loaded here
        APItemDataLoader.processItemTags();
    }

    private static void onUnload(MinecraftServer server, ServerWorld serverWorld) {
        Archipelago.run(Client::close);
        APServerUtil.server = null;
    }
}
