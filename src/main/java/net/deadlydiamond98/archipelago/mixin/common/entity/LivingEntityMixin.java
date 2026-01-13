package net.deadlydiamond98.archipelago.mixin.common.entity;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.common.effects.UnremovableEffect;
import net.deadlydiamond98.archipelago.common.world.APPersistentStates;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    // Prevents Certain Effects from Being Removed with things like Milk ///////////////////////////////////////////////

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);
    @Unique private List<StatusEffectInstance> archipelago$removedPersistentEffects = new ArrayList<>();

    @WrapWithCondition(method = "clearStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;onStatusEffectRemoved(Lnet/minecraft/entity/effect/StatusEffectInstance;)V"))
    private boolean archipelago$clearStatusEffects(LivingEntity instance, StatusEffectInstance effect) {
        if (effect.getEffectType() instanceof UnremovableEffect) {
            archipelago$removedPersistentEffects.add(effect);
        }
        return true;
    }

    @Inject(method = "clearStatusEffects", at = @At("TAIL"))
    private void archipelago$clearStatusEffects(CallbackInfoReturnable<Boolean> cir) {
        if (((LivingEntity) (Object) this) instanceof PlayerEntity player) {
            if (!player.isCreative()) {
                for (StatusEffectInstance removedPersistantEffect : archipelago$removedPersistentEffects) {
                    this.addStatusEffect(removedPersistantEffect);
                }
            }
        }
        archipelago$removedPersistentEffects.clear();
    }

    // Changes Slipperiness when under the Frost Footed Effect /////////////////////////////////////////////////////////

    @WrapOperation(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    private float archipelago$travel(Block instance, Operation<Float> original) {
        LivingEntity entity = ((LivingEntity) (Object) this);

        if (entity.hasStatusEffect(APEffects.FROST_FOOTED)) {
            return 0.99f;
        }
        return original.call(instance);
    }

    // Disables Sprinting if applicable ////////////////////////////////////////////////////////////////////////////////

    @WrapMethod(method = "setSprinting")
    private void archipelago$setSprinting(boolean sprinting, Operation<Void> original) {
        APPersistentStates states = APPersistentStates.getPersistentStates();
        if (states.sprint.get()) {
            original.call(sprinting);
        } else {
            original.call(false);
        }
    }
}
