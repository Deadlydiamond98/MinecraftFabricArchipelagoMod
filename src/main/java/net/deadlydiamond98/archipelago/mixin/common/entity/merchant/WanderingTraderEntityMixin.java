package net.deadlydiamond98.archipelago.mixin.common.entity.merchant;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTraderEntity.class)
public class WanderingTraderEntityMixin extends WanderingTraderHeadRollTickFixingMixin {

    @Override
    protected void archipelago$tick(CallbackInfo ci) {
        super.archipelago$tick(ci);
        WanderingTraderEntity trader = (WanderingTraderEntity) (Object) this;
        if (trader.getHeadRollingTimeLeft() > 0) {
            trader.setHeadRollingTimeLeft(trader.getHeadRollingTimeLeft() - 1);
        }
    }

    @WrapMethod(method = "interactMob")
    private ActionResult archipelago$interactMob(PlayerEntity player, Hand hand, Operation<ActionResult> original) {
        WanderingTraderEntity trader = (WanderingTraderEntity) (Object) this;
        if (!APItemAccessUtil.hasCheck(player, "trading")) {
            if (!trader.getWorld().isClient()) {
                trader.setHeadRollingTimeLeft(40);
                trader.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1, 1);
            }
            return ActionResult.success(trader.getWorld().isClient);
        }
        return original.call(player, hand);
    }
}
