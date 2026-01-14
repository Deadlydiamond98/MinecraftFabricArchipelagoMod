package net.deadlydiamond98.archipelago.mixin.common.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.entity.mob.PiglinBrain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    @ModifyReturnValue(method = "isGoldenItem", at = @At("RETURN"))
    private static boolean archipelago$isGoldenItem(boolean original) {
        if (!APPersistentState.get().getBooleanCheckValue("bartering")) {
            return false;
        }
        return original;
    }
    @ModifyReturnValue(method = "acceptsForBarter", at = @At("RETURN"))
    private static boolean archipelago$acceptsForBarter(boolean original) {
        if (!APPersistentState.get().getBooleanCheckValue("bartering")) {
            return false;
        }
        return original;
    }
}
