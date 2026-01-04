package net.deadlydiamond98.aam;

import net.deadlydiamond98.aam.init.client.AAMScreens;
import net.deadlydiamond98.aam.networking.AAMPackets;
import net.fabricmc.api.ClientModInitializer;

public class AAMClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		AAMScreens.register();
		AAMPackets.registerS2CPackets();
	}
}