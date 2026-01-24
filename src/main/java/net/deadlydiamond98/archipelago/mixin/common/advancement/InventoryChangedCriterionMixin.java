package net.deadlydiamond98.archipelago.mixin.common.advancement;

import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.locations.APLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryChangedCriterion.class)
public class InventoryChangedCriterionMixin {
    @Inject(method = "trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"))
    private void archipelago$trigger(ServerPlayerEntity player, PlayerInventory inventory, ItemStack stack, CallbackInfo ci) {
        if (Archipelago.hasItemsanity()) {
            APPersistentState state = APPersistentState.get();
            Identifier id = Registries.ITEM.getId(stack.getItem());
            if (stack.getItem() != null && APLocations.ITEMSANITY_LOCATIONS.containsKey(id)) {
                long longID = APLocations.ITEMSANITY_LOCATIONS.get(id);
                if (!state.getItemsanityIds().contains(longID)) {
                    Archipelago.run(archipelago -> archipelago.checkLocation(longID));
                    state.putItemsanityID(longID);
                }
            }
        }
    }
}
