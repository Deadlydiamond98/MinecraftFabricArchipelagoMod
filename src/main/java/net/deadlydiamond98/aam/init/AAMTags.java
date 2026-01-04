package net.deadlydiamond98.aam.init;

import net.deadlydiamond98.aam.AAM;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class AAMTags {

    private static <T> TagKey<T> create(String name, RegistryKey<Registry<T>> key) {
        return TagKey.of(key, new Identifier(AAM.MOD_ID, name));
    }
}
