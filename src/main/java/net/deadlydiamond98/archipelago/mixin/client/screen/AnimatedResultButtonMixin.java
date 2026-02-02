package net.deadlydiamond98.archipelago.mixin.client.screen;

import com.llamalad7.mixinextras.sugar.Local;
import net.deadlydiamond98.archipelago.client.ArchipelagoItemIconRenderer;
import net.deadlydiamond98.archipelago.util.tracker.ArchipelagoTrackingData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.AnimatedResultButton;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimatedResultButton.class)
public abstract class AnimatedResultButtonMixin extends ClickableWidget  {
    public AnimatedResultButtonMixin(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Inject(method = "renderButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;II)V"))
    private void archipelago$renderButton(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, @Local ItemStack itemStack) {
        Item item = itemStack.getItem();

        if (ArchipelagoTrackingData.UNCHECKED_ITEMS.contains(item)) {
            ArchipelagoItemIconRenderer.renderIconHandledScreen(context.getMatrices(), context.getVertexConsumers(), this.getX() + 3, this.getY() + 3);
        }
    }
}
