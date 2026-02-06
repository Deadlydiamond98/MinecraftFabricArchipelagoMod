package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class LightningTrap extends AbstractTrapItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, player.getWorld());
        lightning.setPos(player.getX(), player.getY(), player.getZ());
        player.getWorld().spawnEntity(lightning);
    }
}