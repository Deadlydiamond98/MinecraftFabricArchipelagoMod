package net.deadlydiamond98.aam;


import net.deadlydiamond98.aam.init.*;
import net.deadlydiamond98.aam.networking.AAMPackets;
import net.deadlydiamond98.koalalib.updater.KoalaUpdateChecker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AAM implements ModInitializer {
	public static final String MOD_ID = "aam";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
//		KoalaConfigCreator.addModConfig(MOD_ID, AAMConfigs.Main.class);
		KoalaUpdateChecker.addModUpdateChecker(MOD_ID);

		AAMItems.register();
		AAMBlocks.register();
		AAMTab.register();
		AAMScreenHandlers.register();

		AAMSounds.register();
		AAMPackets.registerC2SPackets();
	}

	public static boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}
}