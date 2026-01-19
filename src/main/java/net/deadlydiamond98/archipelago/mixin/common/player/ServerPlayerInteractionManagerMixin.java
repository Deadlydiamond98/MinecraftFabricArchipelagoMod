package net.deadlydiamond98.archipelago.mixin.common.player;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.init.APTags;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {

    @Shadow protected ServerWorld world;

    @Shadow @Final protected ServerPlayerEntity player;

    // BLOCK INTERACTIONS
    @WrapOperation(method = "interactBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;onUse(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;"))
    private ActionResult archipelago$interactBlock(BlockState instance, World world, PlayerEntity player, Hand hand, BlockHitResult blockHitResult, Operation<ActionResult> original) {
        if (instance.isIn(APTags.ARCHIPELAGO_LOCK_INTERACTION) && !APItemAccessUtil.allowCraftOrUse(player, instance.getBlock().asItem())) {
            return ActionResult.SUCCESS;
        }
        return original.call(instance, world, player, hand, blockHitResult);
    }

    // BLOCK BREAKING
    @WrapOperation(method = "finishMining", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;tryBreakBlock(Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean archipelago$finishMining(ServerPlayerInteractionManager instance, BlockPos pos, Operation<Boolean> original) {
        BlockState state = this.world.getBlockState(pos);
        if (state.isIn(APTags.ARCHIPELAGO_LOCK_BREAKING) && !APItemAccessUtil.allowCraftOrUse(this.player, state.getBlock().asItem())) {
            return false;
        }
        return original.call(instance, pos);
    }
}
