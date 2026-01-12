package net.deadlydiamond98.archipelago.archipelago.apitem.traps;

import net.deadlydiamond98.archipelago.archipelago.apitem.AbstractAPItem;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TNTTrap extends AbstractAPItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        World world = player.getWorld();
        Vec3d pos = player.getPos();

        TntEntity tntEntity = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), null);
        world.spawnEntity(tntEntity);
        world.playSound(
                null,
                tntEntity.getX(),
                tntEntity.getY(),
                tntEntity.getZ(),
                SoundEvents.ENTITY_TNT_PRIMED,
                SoundCategory.BLOCKS,
                1, 1
        );
    }
}
