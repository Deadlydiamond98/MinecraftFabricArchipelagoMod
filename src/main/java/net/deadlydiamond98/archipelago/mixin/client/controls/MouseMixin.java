package net.deadlydiamond98.archipelago.mixin.client.controls;

import net.deadlydiamond98.archipelago.init.APEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {

    /*

    This Mixin is used for changing effect of mouse movement when under certain effects
        - Reverses Mouse Direction when under Disorientation Effect
        - Stops Mouse Movement when under Stun Effect

     */

    @Shadow @Final private MinecraftClient client;
    @Shadow private double cursorDeltaX;
    @Shadow private double cursorDeltaY;

    @Inject(method = "updateMouse", at = @At("HEAD"))
    private void archipelago$updateMouse(CallbackInfo ci) {
        archipelago$modifyCursorDelta(APEffects.STUNNED, 0);
        archipelago$modifyCursorDelta(APEffects.DISORIENTATION, -1);
    }

    @Unique
    private void archipelago$modifyCursorDelta(StatusEffect effect, double amount) {
        if (client.player != null && client.player.hasStatusEffect(effect)) {
            this.cursorDeltaX *= amount;
            this.cursorDeltaY *= amount;
        }
    }
}
