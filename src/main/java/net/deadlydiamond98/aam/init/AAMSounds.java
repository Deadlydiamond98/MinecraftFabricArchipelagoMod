package net.deadlydiamond98.aam.init;

import net.deadlydiamond98.aam.AAM;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class AAMSounds {

    private static SoundEvent register(String name) {
        Identifier id = new Identifier(AAM.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {}
}
