package net.deadlydiamond98.archipelago.mixin.common.block.station;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractFurnaceBlock.class)
public class AbstractFurnaceBlockMixin {
    @WrapMethod(method = "onUse")
    private ActionResult archipelago$onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Operation<ActionResult> original) {
        if (!APItemAccessUtil.hasProgressiveCheck(player, "smelting", state.getBlock())) {
            return ActionResult.success(false);
        }
        return original.call(state, world, pos, player, hand, hit);
    }
}
