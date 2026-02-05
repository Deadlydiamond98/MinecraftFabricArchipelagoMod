package net.deadlydiamond98.archipelago.mixin.common.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.minecraft.block.BeehiveBlock.HONEY_LEVEL;

@Mixin(BeehiveTreeDecorator.class)
public class BeehiveTreeDecoratorMixin {
    @ModifyArgs(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/treedecorator/TreeDecorator$Generator;replace(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"))
    private void archipelago$generate(Args args) {
        BlockState beehive = args.get(1);
        args.set(1, beehive.with(HONEY_LEVEL, 5));
    }
}
