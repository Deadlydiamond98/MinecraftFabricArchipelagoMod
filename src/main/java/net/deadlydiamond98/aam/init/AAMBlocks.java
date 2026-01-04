package net.deadlydiamond98.aam.init;

import net.deadlydiamond98.aam.AAM;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AAMBlocks {

    public static Block register(String blockName, Block block) {
        AAMItems.register(blockName, new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, new Identifier(AAM.MOD_ID, blockName), block);
    }

    public static void register() {}
}
