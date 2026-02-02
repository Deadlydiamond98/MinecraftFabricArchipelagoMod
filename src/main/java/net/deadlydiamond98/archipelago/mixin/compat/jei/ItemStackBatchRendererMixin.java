package net.deadlydiamond98.archipelago.mixin.compat.jei;

import mezz.jei.library.render.batch.ItemStackBatchRenderer;
import net.deadlydiamond98.archipelago.client.ArchipelagoItemIconRenderer;
import net.deadlydiamond98.archipelago.util.tracker.ArchipelagoTrackingData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStackBatchRenderer.class)
public class ItemStackBatchRendererMixin {
    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V"))
    private void archipelago$renderItem(DrawContext guiGraphics, ItemRenderer itemRenderer, BakedModel bakedmodel, ItemStack itemStack, int x, int y, CallbackInfo ci) {
        if (ArchipelagoTrackingData.UNCHECKED_ITEMS.contains(itemStack.getItem())) {
            ArchipelagoItemIconRenderer.renderIconScreen(guiGraphics.getMatrices(), guiGraphics.getVertexConsumers());
        }
    }
}
