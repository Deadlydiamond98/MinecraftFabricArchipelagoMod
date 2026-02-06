package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class NighttimeTrap extends AbstractTrapItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        ServerWorld world = (ServerWorld) player.getWorld();
                world.setTimeOfDay(new int[]{13000, 14000, 16000, 18000, 20000}[world.getRandom().nextInt(5)]);
    }
}