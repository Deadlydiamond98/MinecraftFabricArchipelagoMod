package net.deadlydiamond98.archipelago.mixin.common.entity.player;

import net.deadlydiamond98.archipelago.init.APAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void archipelago$tick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player instanceof ServerPlayerEntity serverPlayer) {
            int riddenDist = serverPlayer.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.MINECART_ONE_CM));

            if (riddenDist >= 500) {
                APAdvancements.ON_A_RAIL.trigger(serverPlayer);
            }
        }
    }
}
