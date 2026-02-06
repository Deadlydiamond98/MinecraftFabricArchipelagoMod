package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ItemDropTrap extends AbstractTrapItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        for (int i = 0; i < player.getInventory().main.size(); i++) {
            ItemStack stack = player.getInventory().main.get(i);
            if (!stack.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(
                    player.getWorld(),
                    player.getX() + player.getRandom().nextGaussian() * 2.0,
                    player.getY() + 1.0,
                    player.getZ() + player.getRandom().nextGaussian() * 2.0,
                    stack.copy()
                );
                
                Vec3d velocity = new Vec3d(
                    player.getRandom().nextGaussian() * 0.5,
                    0.3 + player.getRandom().nextDouble() * 0.3,
                    player.getRandom().nextGaussian() * 0.5
                );
                itemEntity.setVelocity(velocity);
                
                player.getWorld().spawnEntity(itemEntity);
                
                player.getInventory().main.set(i, ItemStack.EMPTY);
            }
        }
        

        for (int i = 0; i < player.getInventory().armor.size(); i++) {
            ItemStack stack = player.getInventory().armor.get(i);
            if (!stack.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(
                    player.getWorld(),
                    player.getX() + player.getRandom().nextGaussian() * 2.0,
                    player.getY() + 1.0,
                    player.getZ() + player.getRandom().nextGaussian() * 2.0,
                    stack.copy()
                );
                
                Vec3d velocity = new Vec3d(
                    player.getRandom().nextGaussian() * 0.5,
                    0.3 + player.getRandom().nextDouble() * 0.3,
                    player.getRandom().nextGaussian() * 0.5
                );
                itemEntity.setVelocity(velocity);
                
                player.getWorld().spawnEntity(itemEntity);
                player.getInventory().armor.set(i, ItemStack.EMPTY);
            }
        }
        

        ItemStack offhandStack = player.getInventory().offHand.get(0);
        if (!offhandStack.isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(
                player.getWorld(),
                player.getX() + player.getRandom().nextGaussian() * 2.0,
                player.getY() + 1.0,
                player.getZ() + player.getRandom().nextGaussian() * 2.0,
                offhandStack.copy()
            );
            
            Vec3d velocity = new Vec3d(
                player.getRandom().nextGaussian() * 0.5,
                0.3 + player.getRandom().nextDouble() * 0.3,
                player.getRandom().nextGaussian() * 0.5
            );
            itemEntity.setVelocity(velocity);
            
            player.getWorld().spawnEntity(itemEntity);
            player.getInventory().offHand.set(0, ItemStack.EMPTY);
        }
    }
}