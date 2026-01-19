package net.deadlydiamond98.archipelago.mixin.common.handler;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.deadlydiamond98.archipelago.init.APTags;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.CraftingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {

    /*

    This Mixin is used for preventing Crafting Table recipes from working when locked
        - This also works in the inventory due to the same method being called there

     */

    @WrapOperation(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isItemEnabled(Lnet/minecraft/resource/featuretoggle/FeatureSet;)Z"))
    private static boolean archipelago$updateResult(ItemStack instance, FeatureSet enabledFeatures, Operation<Boolean> original, @Local(argsOnly = true) PlayerEntity player) {
        if (!APItemAccessUtil.allowCraftOrUse(player, instance.getItem())) {
            return false;
        }
        return original.call(instance, enabledFeatures);
    }
}
