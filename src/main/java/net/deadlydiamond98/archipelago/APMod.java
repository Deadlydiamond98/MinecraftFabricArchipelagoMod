package net.deadlydiamond98.archipelago;


import net.deadlydiamond98.archipelago.archipelago.ArchipelagoServerConnector;
import net.deadlydiamond98.archipelago.archipelago.items.SavedArchipelagoItems;
import net.deadlydiamond98.archipelago.archipelago.items.dataloader.APItemDataLoader;
import net.deadlydiamond98.archipelago.events.common.*;
import net.deadlydiamond98.archipelago.init.APAdvancements;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.deadlydiamond98.archipelago.init.APSounds;
import net.deadlydiamond98.archipelago.networking.APNetworking;
import net.deadlydiamond98.koalalib.config.KoalaConfigCreator;
import net.deadlydiamond98.koalalib.updater.KoalaUpdateChecker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APMod implements ModInitializer {
	public static final String MOD_ID = "archipelago";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		KoalaConfigCreator.addModConfig(MOD_ID, APModConfigs.Main.class);
		KoalaUpdateChecker.addModUpdateChecker(MOD_ID);
		ArchipelagoServerConnector.readLastConnectedServer();

		// Register Persistent State Items
		SavedArchipelagoItems.register();

		// Registry
		APEffects.register();
		APAdvancements.register();
		APSounds.register();
		APNetworking.registerC2SReceivers();

		// Events
		APServerWorldEvents.register();
		APSeverCommandEvents.register();
		APServerChatEvents.register();
		APServerPlayConnectionEvents.register();

		// Data Pack Loader
		APItemDataLoader.register();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	public static boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}
}