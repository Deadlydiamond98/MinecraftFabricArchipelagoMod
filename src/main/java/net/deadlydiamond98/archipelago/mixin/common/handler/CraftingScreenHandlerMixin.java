package net.deadlydiamond98.archipelago.mixin.common.handler;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.archipelago.recipe.APRecipeChecker;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.CraftingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {
    // Prevents Recipes from going through if the player doesn't have the proper recipe tier

    @WrapOperation(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isItemEnabled(Lnet/minecraft/resource/featuretoggle/FeatureSet;)Z"))
    private static boolean archipelago$updateResult(ItemStack instance, FeatureSet enabledFeatures, Operation<Boolean> original) {
        if (!APRecipeChecker.allowCrafting(instance)) {
            return false;
        }
        return original.call(instance, enabledFeatures);
    }
}
