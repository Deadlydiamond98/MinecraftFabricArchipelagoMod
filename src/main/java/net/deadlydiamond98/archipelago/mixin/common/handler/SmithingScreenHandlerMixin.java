package net.deadlydiamond98.archipelago.mixin.common.handler;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlerMixin {
    /*

    This Mixin is used for preventing Smithing Table recipes from working when locked
        - This also works in the inventory due to the same method being called there

     */

    @WrapOperation(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isItemEnabled(Lnet/minecraft/resource/featuretoggle/FeatureSet;)Z"))
    private boolean archipelago$updateResult(ItemStack instance, FeatureSet enabledFeatures, Operation<Boolean> original) {
        if (!APItemAccessUtil.allowCrafting(instance)) {
            return false;
        }
        return original.call(instance, enabledFeatures);
    }
}
