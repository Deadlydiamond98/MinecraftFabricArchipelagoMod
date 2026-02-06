package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BoxTrap extends AbstractTrapItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        World world = player.getWorld();
        BlockPos playerPos = player.getBlockPos();
        
        boolean useStaticBox = player.getRandom().nextBoolean();
        
        if (useStaticBox) {
            for (int x = -1; x <= 1; x++) {
                for (int y = 0; y <= 2; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos pos = playerPos.add(x, y, z);
                        
                        if (y == 0 && x == 0 && z == 0) continue;
                        
                        boolean isWall = (x == -1 || x == 1 || z == -1 || z == 1);
                        boolean isRoof = (y == 2);
                        
                        if (isWall || isRoof) {
                            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
                        }
                    }
                }
            }
        } else {
            Block[] fallingBlocks = {Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND, Blocks.DIRT};
            Block chosenBlock = fallingBlocks[player.getRandom().nextInt(fallingBlocks.length)];
            
            int layers = 1 + player.getRandom().nextInt(5);
            
            for (int layer = 0; layer < layers; layer++) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        int height = 3 + layer;
                        BlockPos blockPos = new BlockPos(
                            playerPos.getX() + x,
                            playerPos.getY() + height,
                            playerPos.getZ() + z
                        );
                        
                        world.setBlockState(blockPos, chosenBlock.getDefaultState());
                        FallingBlockEntity.spawnFromBlock(
                            world,
                            blockPos,
                            chosenBlock.getDefaultState()
                        );
                        world.removeBlock(blockPos, false);
                    }
                }
            }
        }
    }
}