package net.deadlydiamond98.archipelago.archipelago.randomization;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

import java.util.Map;
import java.util.Optional;

public class StructureRandomizer {

    public static final BiMap<Identifier, Identifier> STRUCTURE = HashBiMap.create();

    private static void add(Identifier structure) {
        STRUCTURE.put(structure, structure);
    }

    public static final Map<Identifier, Identifier> TEMP = Map.of(
            new Identifier("minecraft:jungle_pyramid"), new Identifier("minecraft:end_city"),
            new Identifier("minecraft:end_city"), new Identifier("minecraft:jungle_pyramid"),
            new Identifier("minecraft:stronghold"), new Identifier("minecraft:fortress"),
            new Identifier("minecraft:fortress"), new Identifier("minecraft:stronghold")
    );

    public static Structure getAlternativeStructure(Structure structure, Registry<Structure> registry) {
        Optional<RegistryKey<Structure>> key = registry.getKey(structure);

        if (key.isPresent()) {
            Identifier structureID = TEMP.get(key.get().getValue());
            if (structureID != null) {
                return registry.get(structureID);
            }
        }

        return structure;
    }
}
