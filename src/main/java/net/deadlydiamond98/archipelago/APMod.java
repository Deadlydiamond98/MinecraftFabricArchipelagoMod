package net.deadlydiamond98.archipelago;


import net.deadlydiamond98.archipelago.archipelago.ArchipelagoClient;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoReconnector;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoSlotData;
import net.deadlydiamond98.archipelago.events.common.APServerChatEvents;
import net.deadlydiamond98.archipelago.events.common.APServerWorldEvents;
import net.deadlydiamond98.archipelago.events.common.APSeverCommandEvents;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.deadlydiamond98.koalalib.config.KoalaConfigCreator;
import net.deadlydiamond98.koalalib.updater.KoalaUpdateChecker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APMod implements ModInitializer {
	public static final String MOD_ID = "archipelago";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Server Variable to get Current Server
	public static @Nullable MinecraftServer server;
	// Archipelago Slot Data
	public static @Nullable ArchipelagoSlotData slotData;

	@Override
	public void onInitialize() {
		KoalaConfigCreator.addModConfig(MOD_ID, APModConfigs.Main.class);
		KoalaUpdateChecker.addModUpdateChecker(MOD_ID);
		ArchipelagoReconnector.readLastConnectedServer();

		// Registry
		APEffects.register();

		// Events
		APServerWorldEvents.register();
		APSeverCommandEvents.register();
		APServerChatEvents.register();

		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new APReloadListener());
	}

	public static @Nullable ArchipelagoClient apClient() {
		return (ArchipelagoClient) ArchipelagoClient.client;
	}

	public static boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}
}