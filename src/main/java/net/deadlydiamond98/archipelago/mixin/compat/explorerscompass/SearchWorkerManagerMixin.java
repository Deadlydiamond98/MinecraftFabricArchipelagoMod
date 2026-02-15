package net.deadlydiamond98.archipelago.mixin.compat.explorerscompass;

import com.chaosthedude.explorerscompass.workers.SearchWorkerManager;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.deadlydiamond98.archipelago.archipelago.randomization.StructureRandomizer;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(SearchWorkerManager.class)
public class SearchWorkerManagerMixin {
    @WrapOperation(method = "createWorkers", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/StructurePlacementCalculator;getPlacements(Lnet/minecraft/registry/entry/RegistryEntry;)Ljava/util/List;"))
    private List<StructurePlacement> archipelago$createWorkers(StructurePlacementCalculator instance, RegistryEntry<Structure> structureEntry, Operation<List<StructurePlacement>> original, @Local ServerWorld world) {
        Registry<Structure> registry = world.getRegistryManager().get(RegistryKeys.STRUCTURE);
        Structure altStructure = StructureRandomizer.getAlternativeStructure(structureEntry.value(), registry);
        RegistryEntry<Structure> altKey = registry.getEntry(altStructure);
        return world.getChunkManager().getStructurePlacementCalculator().getPlacements(altKey);
    }
}
