package net.deadlydiamond98.archipelago.mixin.common.worldgen.structure;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.deadlydiamond98.archipelago.archipelago.randomization.StructureRandomizer;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.StructureLocator;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StructureLocator.class)
public class StructureLocatorMixin {
    @Shadow @Final private Registry<Structure> structureRegistry;

    @WrapOperation(method = "isGenerationPossible", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/structure/Structure;getValidBiomes()Lnet/minecraft/registry/entry/RegistryEntryList;"))
    private RegistryEntryList<Biome> archipelago$isGenerationPossible(Structure instance, Operation<RegistryEntryList<Biome>> original) {
        return StructureRandomizer.getAlternativeStructure(instance, this.structureRegistry).getValidBiomes();
    }
}
