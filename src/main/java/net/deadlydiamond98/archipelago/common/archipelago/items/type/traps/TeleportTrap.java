package net.deadlydiamond98.archipelago.common.archipelago.items.type.traps;

import net.deadlydiamond98.archipelago.common.archipelago.items.type.AbstractAPItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class TeleportTrap extends AbstractAPItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        World world = player.getWorld();
        double d = player.getX();
        double e = player.getY();
        double f = player.getZ();

        int maxRange = 32;

        for(int i = 0; i < 16; ++i) {
            double g = player.getX() + (player.getRandom().nextDouble() - 0.5) * maxRange;
            double h = MathHelper.clamp(
                    player.getY() + (player.getRandom().nextInt(maxRange) - (maxRange / 2.0)),
                    world.getBottomY(),
                    (world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1)
            );
            double j = player.getZ() + (player.getRandom().nextDouble() - 0.5) * maxRange;
            if (player.hasVehicle()) {
                player.stopRiding();
            }

            Vec3d vec3d = player.getPos();
            if (player.teleport(g, h, j, true)) {
                world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(player));
                SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1, 1);
                player.playSound(soundEvent, 1, 1);
                break;
            }
        }
    }
}
