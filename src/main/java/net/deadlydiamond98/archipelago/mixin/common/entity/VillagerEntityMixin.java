package net.deadlydiamond98.archipelago.mixin.common.entity;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
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
        if (!APPersistentState.get().getBooleanCheckValue("trading")) {
            this.sayNo();
            if (!trader.getWorld().isClient()) {
                player.sendMessage(Text.translatable("archipelago.check.trading").setStyle(Style.EMPTY.withColor(0xFF0000)), true);
            }

            return ActionResult.success(trader.getWorld().isClient);
        }
        return original.call(player, hand);
    }
}
