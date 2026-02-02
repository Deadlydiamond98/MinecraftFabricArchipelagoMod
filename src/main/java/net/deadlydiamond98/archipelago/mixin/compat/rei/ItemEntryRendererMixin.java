package net.deadlydiamond98.archipelago.mixin.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.plugin.client.entry.ItemEntryDefinition;
import net.deadlydiamond98.archipelago.client.ArchipelagoItemIconRenderer;
import net.deadlydiamond98.archipelago.util.tracker.ArchipelagoTrackingData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntryDefinition.ItemEntryRenderer.class)
public class ItemEntryRendererMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V"))
    private void archipelago$render(EntryStack<ItemStack> entry, DrawContext graphics, Rectangle bounds, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (ArchipelagoTrackingData.UNCHECKED_ITEMS.contains(entry.getValue().getItem())) {
            ArchipelagoItemIconRenderer.renderIconScreen(graphics.getMatrices(), graphics.getVertexConsumers());
        }
    }

    @Inject(method = "renderBase(Lme/shedaniel/rei/api/common/entry/EntryStack;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lme/shedaniel/math/Rectangle;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V"))
    private void archipelago$renderBase(EntryStack<ItemStack> entry, BakedModel model, DrawContext graphics, VertexConsumerProvider.Immediate immediate, Rectangle bounds, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (ArchipelagoTrackingData.UNCHECKED_ITEMS.contains(entry.getValue().getItem())) {
            ArchipelagoItemIconRenderer.renderIconScreen(graphics.getMatrices(), graphics.getVertexConsumers());
        }
    }
}
