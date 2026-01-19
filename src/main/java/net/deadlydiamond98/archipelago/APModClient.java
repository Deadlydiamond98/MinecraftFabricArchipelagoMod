package net.deadlydiamond98.archipelago;

import net.deadlydiamond98.archipelago.events.client.APClientTickEvent;
import net.deadlydiamond98.archipelago.init.client.APKeybindings;
import net.deadlydiamond98.archipelago.init.client.APShaders;
import net.deadlydiamond98.archipelago.networking.APNetworking;
import net.fabricmc.api.ClientModInitializer;

public class APModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		APKeybindings.register();
		APClientTickEvent.register();
		APShaders.register();
		APNetworking.Client.registerS2CReceivers();
	}
}