package net.deadlydiamond98.archipelago.init;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.koalalib.common.advancement.CustomAdvancement;
import net.minecraft.advancement.criterion.Criteria;

public class APAdvancements {

    public static final CustomAdvancement FALLING_WITH_PIG = register("falling_with_pig");
    public static final CustomAdvancement EAT_GOLDEN_APPLE = register("eat_golden_apple");

    public static CustomAdvancement register(String name) {
        return Criteria.register(new CustomAdvancement(APMod.id(name)));
    }

    public static void register() {}
}
