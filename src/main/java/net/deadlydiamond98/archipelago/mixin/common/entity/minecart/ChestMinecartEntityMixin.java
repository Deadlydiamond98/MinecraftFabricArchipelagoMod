package net.deadlydiamond98.archipelago.mixin.common.entity.minecart;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChestMinecartEntity.class)
public class ChestMinecartEntityMixin extends AbstractMinecartEntityMixin {

    @WrapMethod(method = "interact")
    private ActionResult archipelago$interact(PlayerEntity player, Hand hand, Operation<ActionResult> original) {
        if (!APItemAccessUtil.hasCheck(player, "chests")) {
            return ActionResult.success(player.getWorld().isClient);
        }
        return original.call(player, hand);
    }

    @Override
    protected boolean archipelago$damage(DamageSource source, float amount, Operation<Boolean> original) {
        PlayerEntity player = source.getAttacker() instanceof PlayerEntity ? (PlayerEntity) source.getAttacker() : null;
        if (!APItemAccessUtil.hasCheck(player, "chests")) {
            return false;
        }
        return super.archipelago$damage(source, amount, original);
    }
}
