package net.deadlydiamond98.archipelago.mixin.compat.jade;

import net.deadlydiamond98.archipelago.client.ArchipelagoItemIconRenderer;
import net.deadlydiamond98.archipelago.util.tracker.ArchipelagoTrackingData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.overlay.DisplayHelper;

@Mixin(DisplayHelper.class)
public class DisplayHelperMixin {
    @Inject(method = "drawItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;II)V"))
    private void archipelago$drawItem(DrawContext guiGraphics, float x, float y, ItemStack stack, float scale, String text, CallbackInfo ci) {
        if (ArchipelagoTrackingData.UNCHECKED_ITEMS.contains(stack.getItem())) {
            ArchipelagoItemIconRenderer.renderIconHandledScreen(guiGraphics.getMatrices(), guiGraphics.getVertexConsumers(), 0, 0);
        }
    }
}
