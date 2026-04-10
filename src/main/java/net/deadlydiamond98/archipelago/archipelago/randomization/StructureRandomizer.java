package net.deadlydiamond98.archipelago.archipelago.randomization;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.Structure;

import java.util.*;

public class StructureRandomizer {

    public static BiMap<Identifier, Identifier> STRUCTURE = HashBiMap.create();
    public static BiMap<Identifier, Identifier> STRUCTURE_BACKUP = HashBiMap.create();

    static {
        add("minecraft:end_city");
        add("minecraft:ancient_city");
        add("minecraft:bastion_remnant");
        add("minecraft:buried_treasure");
        add("minecraft:desert_pyramid");
        add("minecraft:fortress");
        add("minecraft:igloo");
        add("minecraft:jungle_pyramid");
        add("minecraft:mansion");
        add("minecraft:mineshaft");
        add("minecraft:mineshaft_mesa");
        add("minecraft:monument");
        add("minecraft:nether_fossil");
        add("minecraft:ocean_ruin_cold");
        add("minecraft:ocean_ruin_warm");
        add("minecraft:pillager_outpost");
        add("minecraft:ruined_portal");
        add("minecraft:ruined_portal_desert");
        add("minecraft:ruined_portal_jungle");
        add("minecraft:ruined_portal_mountain");
        add("minecraft:ruined_portal_nether");
        add("minecraft:ruined_portal_ocean");
        add("minecraft:ruined_portal_swamp");
        add("minecraft:shipwreck");
        add("minecraft:shipwreck_beached");
        add("minecraft:stronghold");
        add("minecraft:swamp_hut");
        add("minecraft:trail_ruins");
        add("minecraft:village_desert");
        add("minecraft:village_plains");
        add("minecraft:village_savanna");
        add("minecraft:village_snowy");
        add("minecraft:village_taiga");
    }

    private static void add(String structure) {
        Identifier id = new Identifier(structure);
        STRUCTURE_BACKUP.put(id, id);
    }

    public static void randomizeStructures() {
        STRUCTURE.clear();
        List<Identifier> structures = new ArrayList<>();
        List<Identifier> replacements = new ArrayList<>();
        STRUCTURE_BACKUP.forEach((identifier, identifier2) -> {
            structures.add(identifier);
            replacements.add(identifier2);
        });
//        Collections.shuffle(structures);
        Collections.shuffle(replacements);

        while (!structures.isEmpty()) {
            STRUCTURE.put(structures.get(0), replacements.get(0));
            structures.remove(0);
            replacements.remove(0);
        }

        STRUCTURE.forEach((identifier, identifier2) -> {
            APMod.LOGGER.info("{} is replaced by.... {}", identifier.toString(), identifier2.toString());
        });
    }

    public static Structure getAlternativeStructure(Structure structure, Registry<Structure> registry) {
        Optional<RegistryKey<Structure>> key = registry.getKey(structure);

        if (key.isPresent()) {
            Identifier structureID = STRUCTURE.get(key.get().getValue());
            if (structureID != null) {
                return registry.get(structureID);
            }
        }

        return structure;
    }

    public static Structure getAlternativeStructureInverse(Structure structure, Registry<Structure> registry) {
        Optional<RegistryKey<Structure>> key = registry.getKey(structure);

        if (key.isPresent()) {
            Identifier structureID = STRUCTURE.inverse().get(key.get().getValue());
            if (structureID != null) {
                return registry.get(structureID);
            }
        }
        return structure;
    }
}
