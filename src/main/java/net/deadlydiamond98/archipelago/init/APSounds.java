package net.deadlydiamond98.archipelago.init;

import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class APSounds {

    public static final SoundEvent RUBY_RECEIVED = register("archipelago.ruby.receive");

    public static SoundEvent register(String name) {
        Identifier id = APMod.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {}
}
