package net.deadlydiamond98.archipelago.mixin.common.entity.player;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoServerConnector;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /*

    This Mixin is used for doing various things on the Player Entity
        - Kills the player if they're in water if Swim isn't unlocked

     */

    @Inject(method = "tick", at = @At("TAIL"))
    private void archipelago$tick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (!player.getWorld().isClient) {
            APPersistentState state = APPersistentState.get();

            if (!state.getBooleanCheckValue("swim")) {
                if (player.isTouchingWater()) {
                    player.damage(player.getDamageSources().drown(), player.getHealth());
                }
            }
        }
    }
}
