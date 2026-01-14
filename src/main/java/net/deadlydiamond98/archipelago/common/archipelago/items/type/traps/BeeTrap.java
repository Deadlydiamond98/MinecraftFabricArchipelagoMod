package net.deadlydiamond98.archipelago.common.archipelago.items.type.traps;

import net.deadlydiamond98.archipelago.common.archipelago.items.type.AbstractAPItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class BeeTrap extends AbstractAPItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        for (int i = 0; i < 6; i++) {
            BeeEntity beeEntity = new BeeEntity(EntityType.BEE, player.getWorld());

            beeEntity.setPos(
                    player.getX() + player.getRandom().nextBetween(-3, 3),
                    player.getEyeY(),
                    player.getZ() + player.getRandom().nextBetween(-3, 3)
            );

            beeEntity.setTarget(player);
            player.getWorld().spawnEntity(beeEntity);
        }
    }
}
