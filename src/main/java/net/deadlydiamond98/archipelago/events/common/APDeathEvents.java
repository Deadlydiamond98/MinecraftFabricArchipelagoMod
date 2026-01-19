package net.deadlydiamond98.archipelago.events.common;

import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.init.APDamageTypes;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;

public class APDeathEvents {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof PlayerEntity && !damageSource.isOf(APDamageTypes.DEATHLINK)) {
                Archipelago.run(archipelago -> {
                    archipelago.sendDeathlink(
                            archipelago.getMyName(),
                            damageSource.getDeathMessage(entity).getString()
                    );
                });
            }
        });
    }
}
