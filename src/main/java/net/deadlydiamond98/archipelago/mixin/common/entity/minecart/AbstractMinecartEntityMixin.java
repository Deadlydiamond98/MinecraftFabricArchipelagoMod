package net.deadlydiamond98.archipelago.mixin.common.entity.minecart;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
    @WrapMethod(method = "damage")
    protected boolean archipelago$damage(DamageSource source, float amount, Operation<Boolean> original) {
        return original.call(source, amount);
    }
}
