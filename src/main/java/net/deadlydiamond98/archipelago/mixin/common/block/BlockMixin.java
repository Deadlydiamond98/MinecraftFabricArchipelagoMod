package net.deadlydiamond98.archipelago.mixin.common.block;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.init.APTags;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public class BlockMixin {
    // Prevents exploding of blocks that can't be broken
    @WrapMethod(method = "getBlastResistance")
    private float archipelago$getBlastResistance(Operation<Float> original) {
        Block block = (Block) (Object) this;
        if (block.getDefaultState().isIn(APTags.ARCHIPELAGO_LOCK_BREAKING) && !APItemAccessUtil.allowCraftOrUse(null, block.asItem())) {
            return 3600000;
        }
        return original.call();
    }
}
