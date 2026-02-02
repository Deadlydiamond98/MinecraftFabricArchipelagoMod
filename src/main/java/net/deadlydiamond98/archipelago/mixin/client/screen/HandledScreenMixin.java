package net.deadlydiamond98.archipelago.mixin.client.screen;

import net.deadlydiamond98.archipelago.client.ArchipelagoItemIconRenderer;
import net.deadlydiamond98.archipelago.util.tracker.ArchipelagoTrackingData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {
    @Inject(method = "drawItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/item/ItemStack;II)V"))
    private void archipelago$drawItem(DrawContext context, ItemStack stack, int x, int y, String amountText, CallbackInfo ci) {
        Item item = stack.getItem();

        if (ArchipelagoTrackingData.UNCHECKED_ITEMS.contains(item)) {
            ArchipelagoItemIconRenderer.renderIconHandledScreen(context.getMatrices(), context.getVertexConsumers(), x, y);
        }
    }

    @Inject(method = "drawSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/item/ItemStack;III)V"))
    private void archipelago$drawSlot(DrawContext context, Slot slot, CallbackInfo ci) {
        Item item = slot.getStack().getItem();

        if (ArchipelagoTrackingData.UNCHECKED_ITEMS.contains(item)) {
            ArchipelagoItemIconRenderer.renderIconHandledScreen(context.getMatrices(), context.getVertexConsumers(), slot.x, slot.y);
        }
    }
}
