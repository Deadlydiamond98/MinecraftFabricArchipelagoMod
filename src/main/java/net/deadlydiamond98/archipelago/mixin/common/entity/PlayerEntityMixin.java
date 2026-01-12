package net.deadlydiamond98.archipelago.mixin.common.entity;

import net.deadlydiamond98.archipelago.common.world.APPersistentStates;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void archipelago$tick(CallbackInfo ci) {
        APPersistentStates states = APPersistentStates.getPersistentStates();
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (!states.canSwim()) {
            if (player.isTouchingWater()) {
                player.damage(player.getDamageSources().drown(), player.getHealth());
            }
        }
    }
}
