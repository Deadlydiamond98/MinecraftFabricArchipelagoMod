package net.deadlydiamond98.archipelago.archipelago.randomization;

import net.minecraft.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class RandomizedMobSpawns {
    public static final List<EntityType<?>> MOB_LIST = new ArrayList<>();
    public static final List<EntityType<?>> MOB_LIST_COPY;

    static {
        MOB_LIST.add(EntityType.ALLAY);
        MOB_LIST.add(EntityType.BEE);
        MOB_LIST.add(EntityType.BLAZE);
        MOB_LIST.add(EntityType.ENDERMITE);
        MOB_LIST.add(EntityType.ZOMBIE);

        MOB_LIST_COPY = MOB_LIST;
    }
}
