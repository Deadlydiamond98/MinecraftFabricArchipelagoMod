package net.deadlydiamond98.archipelago.mixin.common.player;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.init.APAdvancements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    // Triggers on a rail advancement
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

    // Prevents Jumping without Jump Item
    @WrapMethod(method = "jump")
    private void archipelago$jump(Operation<Void> original) {
        APPersistentState states = APPersistentState.get();
        if (states.getBooleanCheckValue("jump")) {
            original.call();
        }
    }
}
