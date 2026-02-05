package net.deadlydiamond98.archipelago.init;

import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class APTags {
    // ITEMS ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final TagKey<Item> FRUITS = item("fruits");
    public static final TagKey<Item> DYES = item("dyes");

    // Entities ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final TagKey<EntityType<?>> ADDITIONAL_SKULLS = entity("drops_more_wither_skulls");
    public static final TagKey<EntityType<?>> ADDITIONAL_RABBIT_FEET = entity("drops_more_rabbits_foot");

    // LOCKED BY CHECKS ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final TagKey<Item> ARCHIPELAGO_LOCK_SMITHING = item("locking/ap_lock_smithing");
    public static final TagKey<Block> ARCHIPELAGO_LOCK_INTERACTION = block("ap_lock_block_interaction");
    public static final TagKey<Block> ARCHIPELAGO_LOCK_BREAKING = block("ap_lock_block_breaking");

    // Registry ////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static TagKey<Item> item(String name) {
        return getTag(RegistryKeys.ITEM, name);
    }

    private static TagKey<Block> block(String name) {
        return getTag(RegistryKeys.BLOCK, name);
    }

    private static TagKey<EntityType<?>> entity(String name) {
        return getTag(RegistryKeys.ENTITY_TYPE, name);
    }

    private static <T> TagKey<T> getTag(RegistryKey<? extends Registry<T>> registry, String name) {
        return TagKey.of(registry, APMod.id(name));
    }
}
