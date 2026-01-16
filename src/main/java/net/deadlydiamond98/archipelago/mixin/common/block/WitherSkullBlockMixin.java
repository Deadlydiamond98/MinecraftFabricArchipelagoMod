package net.deadlydiamond98.archipelago.mixin.common.block;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.minecraft.advancement.criterion.SummonedEntityCriterion;
import net.minecraft.block.WitherSkullBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WitherSkullBlock.class)
public class WitherSkullBlockMixin {
    @WrapWithCondition(method = "onPlaced(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/SkullBlockEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/SummonedEntityCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/entity/Entity;)V"))
    private static boolean archipelago$onPlaced(SummonedEntityCriterion instance, ServerPlayerEntity player, Entity entity) {
        return APItemAccessUtil.hasCheck(player, "wither_summoning");
    }
}
