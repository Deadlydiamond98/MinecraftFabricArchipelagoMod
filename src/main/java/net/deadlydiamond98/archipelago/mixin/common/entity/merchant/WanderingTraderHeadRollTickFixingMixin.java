package net.deadlydiamond98.archipelago.mixin.common.entity.merchant;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class WanderingTraderHeadRollTickFixingMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    protected void archipelago$tick(CallbackInfo ci) {}
}
