package net.deadlydiamond98.archipelago.mixin.common.advancement;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.advancement.AdvancementDisplay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AdvancementDisplay.class)
public class AdvancementDisplayMixin {
    // Stops hidden advancements from hiding
    @WrapMethod(method = "isHidden")
    private boolean archipelago$isHidden(Operation<Boolean> original) {
        return false;
    }
}
