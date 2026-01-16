package net.deadlydiamond98.archipelago.mixin.common.entity;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoGoalHelper;
import net.deadlydiamond98.archipelago.common.effects.UnremovableStatusEffect;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.init.APAdvancements;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    /*

    This Mixin is used for doing various things on the Living Entity
        - Prevents all effects that are un-removable from being removed, so milk can't be used to bypass them
        - Makes all blocks Slippery when under the Frost Footed Effect
        - Disables the ability to Sprint if the check isn't received!
        - Triggers the Overpowered Advancement
        - Triggers the Goal for killing the Wither or Ender Dragon

     */

    // Prevents Certain Effects from Being Removed with things like Milk ///////////////////////////////////////////////

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);
    @Unique private List<StatusEffectInstance> archipelago$removedPersistentEffects = new ArrayList<>();

    @WrapWithCondition(method = "clearStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;onStatusEffectRemoved(Lnet/minecraft/entity/effect/StatusEffectInstance;)V"))
    private boolean archipelago$clearStatusEffects(LivingEntity instance, StatusEffectInstance effect) {
        if (effect.getEffectType() instanceof UnremovableStatusEffect) {
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
        APPersistentState states = APPersistentState.get();
        if (states.getBooleanCheckValue("sprint")) {
            original.call(sprinting);
        } else {
            original.call(false);
        }
    }

    // Overpowered Advancement Trigger /////////////////////////////////////////////////////////////////////////////////

    @Inject(method = "applyFoodEffects", at = @At("HEAD"))
    private void archipelago$applyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity, CallbackInfo ci) {
        if (stack.isOf(Items.ENCHANTED_GOLDEN_APPLE) && targetEntity instanceof PlayerEntity player) {
            APAdvancements.EAT_GOLDEN_APPLE.trigger(player);
        }
    }

    // Kill Boss Goal //////////////////////////////////////////////////////////////////////////////////////////////////

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void archipelago$onDeath(DamageSource damageSource, CallbackInfo ci) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if (entity instanceof EnderDragonEntity) {
            ArchipelagoGoalHelper.updateGoal(0);
        } else if (entity instanceof WitherEntity) {
            ArchipelagoGoalHelper.updateGoal(1);
        }
    }
}
