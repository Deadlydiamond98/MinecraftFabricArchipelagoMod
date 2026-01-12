package net.deadlydiamond98.archipelago.mixin.common.block;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.common.world.APPersistentStates;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FurnaceBlock.class)
public class FurnaceBlockMixin {
    @WrapMethod(method = "openScreen")
    private void archipelago$openScreen(World world, BlockPos pos, PlayerEntity player, Operation<Void> original) {
        if (APPersistentStates.getPersistentStates().getSmeltLevel() > 0) {
            original.call(world, pos, player);
        }
    }
}
