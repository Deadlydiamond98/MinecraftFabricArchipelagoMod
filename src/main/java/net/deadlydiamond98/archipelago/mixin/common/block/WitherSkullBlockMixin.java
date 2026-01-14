package net.deadlydiamond98.archipelago.mixin.common.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.block.WitherSkullBlock;
import net.minecraft.block.pattern.BlockPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WitherSkullBlock.class)
public class WitherSkullBlockMixin {
    @WrapOperation(method = "onPlaced(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/SkullBlockEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/WitherSkullBlock;getWitherBossPattern()Lnet/minecraft/block/pattern/BlockPattern;"))
    private static BlockPattern archipelago$onPlaced(Operation<BlockPattern> original) {
        if (APPersistentState.get().getBooleanCheckValue("wither_summoning")) {
            return original.call();
        }
        return null;
    }
}
