package net.deadlydiamond98.aam.init;

import net.deadlydiamond98.aam.AAM;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AAMItems {

    public static Item register(String itemName, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AAM.MOD_ID, itemName), item);
    }

    public static void register() {}
}
