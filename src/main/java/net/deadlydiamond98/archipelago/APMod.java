package net.deadlydiamond98.archipelago;


import net.deadlydiamond98.archipelago.archipelago.ArchipelagoClient;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoReconnector;
import net.deadlydiamond98.archipelago.events.common.APServerChatEvents;
import net.deadlydiamond98.archipelago.events.common.APServerWorldEvents;
import net.deadlydiamond98.archipelago.events.common.APSeverCommandEvents;
import net.deadlydiamond98.koalalib.config.KoalaConfigCreator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APMod implements ModInitializer {
	public static final String MOD_ID = "archipelago";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Server Variable to get Current Server
	public static @Nullable MinecraftServer server;

	@Override
	public void onInitialize() {
		KoalaConfigCreator.addModConfig(MOD_ID, APModConfigs.Main.class);
		ArchipelagoReconnector.readLastConnectedServer();


		// Events
		APServerWorldEvents.register();
		APSeverCommandEvents.register();
		APServerChatEvents.register();
	}

	public static @Nullable ArchipelagoClient apClient() {
		return (ArchipelagoClient) ArchipelagoClient.client;
	}

	public static boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}
}