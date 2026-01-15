package net.deadlydiamond98.archipelago.mixin.common.entity;

import net.deadlydiamond98.archipelago.init.APAdvancements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(Entity.class)
public class EntityMixin {

    /*

    This Mixin is used for doing various things on Entities
        - Checks if a player is riding a pig for checking the "When Pigs Fly" advancement

     */

    @Inject(method = "handleFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void archipelago$handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir, Iterator var4, Entity entity) {
        if (((Entity) (Object) (this)) instanceof PigEntity && entity instanceof PlayerEntity player && fallDistance >= 5) {
            APAdvancements.FALLING_WITH_PIG.trigger(player);
        }
    }
}
