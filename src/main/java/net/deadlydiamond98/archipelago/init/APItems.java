package net.deadlydiamond98.archipelago.init;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.common.items.EssenceOfArchipelagoItem;
import net.deadlydiamond98.archipelago.common.items.TotemOfMeteorologyItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class APItems {

    public static final Item ESSENCE_OF_ARCHIPELAGO = register("essence_of_archipelago", new EssenceOfArchipelagoItem(new FabricItemSettings()));
    public static final Item TOTEM_OF_METEOROLOGY = register("totem_of_meteorology", new TotemOfMeteorologyItem(new FabricItemSettings().maxCount(1)));

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, APMod.id(id), item);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ESSENCE_OF_ARCHIPELAGO);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(TOTEM_OF_METEOROLOGY);
        });
    }
}
