package net.deadlydiamond98.archipelago.events.common;

import io.github.archipelagomw.bounce.DeathLinkHandler;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.init.APDamageTypes;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;

public class APDeathEvents {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof PlayerEntity && !damageSource.isOf(APDamageTypes.DEATHLINK)) {
                Archipelago.run(archipelago -> {
                    if (archipelago.getTags().contains(DeathLinkHandler.DEATHLINK_TAG)) {
                        archipelago.sendDeathlink(
                                archipelago.getMyName(),
                                damageSource.getDeathMessage(entity).getString()
                        );
                        APServerUtil.runOnServer(server -> server.getPlayerManager().getPlayerList().forEach(player -> {
                            player.damage(APDamageTypes.of(player.getWorld(), APDamageTypes.DEATHLINK), Float.MAX_VALUE);
                        }));
                    }
                });
            }
        });
    }
}
