package net.deadlydiamond98.archipelago.mixin.common.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.minecraft.block.Degradable;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Degradable.class)
public interface DegradableMixin {
    @WrapOperation(method = "tickDegradation", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    private float archipelago$tickDegradation(Random instance, Operation<Float> original) {
        float amount = 0;
        if (Archipelago.hasQOLSetting("Copper Oxidation")) {
            amount = 0.5f;
        }

        return original.call(instance) - amount;
    }
}
