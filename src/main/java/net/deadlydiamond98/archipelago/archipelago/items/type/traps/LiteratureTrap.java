package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.deadlydiamond98.archipelago.networking.s2c.LiteratureTrapS2CPacket;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LiteratureTrap extends AbstractTrapItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        LiteratureTrapS2CPacket.send(player);
    }
}
