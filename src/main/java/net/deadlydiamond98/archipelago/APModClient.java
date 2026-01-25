package net.deadlydiamond98.archipelago;

import net.deadlydiamond98.archipelago.events.client.APClientTickEvent;
import net.deadlydiamond98.archipelago.init.APItems;
import net.deadlydiamond98.archipelago.init.client.APKeybindings;
import net.deadlydiamond98.archipelago.init.client.APShaders;
import net.deadlydiamond98.archipelago.networking.APNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.Identifier;

public class APModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		APKeybindings.register();
		APClientTickEvent.register();
		APShaders.register();
		APNetworking.Client.registerS2CReceivers();


		ModelPredicateProviderRegistry.register(APItems.TOTEM_OF_METEOROLOGY, new Identifier("weather"),  (stack, world, entity, seed) -> {
			if (world != null) {
				if (world.isThundering()) {
					return 1;
				} else if (world.isRaining()) {
					return 0.5f;
				}
			}
			return 0;
		});

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			if (tintIndex == 0) {
				return PotionUtil.getColor(stack);
			}
			return -1;
		}, APItems.SINGLE_USE_POTION);
	}
}