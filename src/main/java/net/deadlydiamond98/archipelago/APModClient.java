package net.deadlydiamond98.archipelago;

import net.deadlydiamond98.archipelago.networking.ArchipelagoPackets;
import net.fabricmc.api.ClientModInitializer;

public class APModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ArchipelagoPackets.registerS2CPackets();
	}
}