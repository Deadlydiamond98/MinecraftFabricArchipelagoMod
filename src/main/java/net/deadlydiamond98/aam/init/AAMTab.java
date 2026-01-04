package net.deadlydiamond98.aam.init;

import net.deadlydiamond98.aam.AAM;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AAMTab {

//    public static final ItemGroup MAGIC_ITEMGROUP = register("magic_tab", FabricItemGroup.builder()
//            .icon(MagicItems.STAR_FRAGMENT::getDefaultStack).displayName(Text.translatable("itemGroup.magicbarlib.items"))
//            .entries((displayContext, entries) -> MagicItems.ITEMS.forEach(entries::add)).build());

    public static ItemGroup register(String name, ItemGroup itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, new Identifier(AAM.MOD_ID, name), itemGroup);
    }

    public static void register() {}
}
