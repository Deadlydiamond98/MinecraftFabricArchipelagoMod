package net.deadlydiamond98.archipelago.mixin.common.worldgen.structure;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.deadlydiamond98.archipelago.archipelago.randomization.StructureRandomizer;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ChunkGenerator.class)
public abstract class ChunkGeneratorMixin {

    @WrapOperation(method = "locateStructure(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/registry/entry/RegistryEntryList;Lnet/minecraft/util/math/BlockPos;IZ)Lcom/mojang/datafixers/util/Pair;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/StructurePlacementCalculator;getPlacements(Lnet/minecraft/registry/entry/RegistryEntry;)Ljava/util/List;"))
    private List<StructurePlacement> archieplago$locateStructure(StructurePlacementCalculator instance, RegistryEntry<Structure> structureEntry, Operation<List<StructurePlacement>> original, @Local ServerWorld world, @Local StructurePlacementCalculator structurePlacementCalculator) {
        Registry<Structure> registry = world.getRegistryManager().get(RegistryKeys.STRUCTURE);
        Structure altStructure = StructureRandomizer.getAlternativeStructure(structureEntry.value(), registry);
        RegistryEntry<Structure> altKey = registry.getEntry(altStructure);
        return structurePlacementCalculator.getPlacements(altKey);
    }

    @WrapOperation(method = "trySetStructureStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/entry/RegistryEntry;value()Ljava/lang/Object;"))
    private <T extends Structure> Object archipelago$trySetStructureStart(RegistryEntry<Structure> instance, Operation<T> original, @Local(argsOnly = true) StructureAccessor structureAccessor) {
        return StructureRandomizer.getAlternativeStructure(original.call(instance), structureAccessor.world.getRegistryManager().get(RegistryKeys.STRUCTURE));
    }

    @WrapOperation(method = "trySetStructureStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/structure/Structure;getValidBiomes()Lnet/minecraft/registry/entry/RegistryEntryList;"))
    private RegistryEntryList<Biome> archipelago$trySetStructureStart(Structure instance, Operation<RegistryEntryList<Biome>> original, @Local(argsOnly = true) StructureAccessor structureAccessor) {
        return StructureRandomizer.getAlternativeStructure(instance, structureAccessor.world.getRegistryManager().get(RegistryKeys.STRUCTURE)).getValidBiomes();
    }

}
