package net.deadlydiamond98.archipelago.mixin.common.block;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.block.BlastFurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlastFurnaceBlock.class)
public class BlastFurnaceBlockMixin {
    @WrapMethod(method = "openScreen")
    private void archipelago$openScreen(World world, BlockPos pos, PlayerEntity player, Operation<Void> original) {
        if (APPersistentState.get().getIntCheckValue("smelting") > 1) {
            original.call(world, pos, player);
        }
    }
}
