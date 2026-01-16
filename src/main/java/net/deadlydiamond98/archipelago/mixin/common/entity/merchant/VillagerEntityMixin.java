package net.deadlydiamond98.archipelago.mixin.common.entity.merchant;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {
    @Shadow protected abstract void sayNo();

    @WrapMethod(method = "interactMob")
    private ActionResult archipelago$interactMob(PlayerEntity player, Hand hand, Operation<ActionResult> original) {
        VillagerEntity trader = (VillagerEntity) (Object) this;
        if (!APItemAccessUtil.hasCheck(player, "trading")) {
            this.sayNo();
            return ActionResult.success(trader.getWorld().isClient);
        }
        return original.call(player, hand);
    }
}
