package net.deadlydiamond98.archipelago.mixin.common;

import net.deadlydiamond98.archipelago.archipelago.maps.ArchipelagoLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentStates;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.advancement.criterion.Criterion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Criterion.ConditionsContainer.class)
public class ConditionsContainerMixin {
    @Shadow @Final private Advancement advancement;

    @Inject(method = "grant", at = @At("TAIL"))
    private void archipelago$grant(PlayerAdvancementTracker tracker, CallbackInfo ci) {
        Long i = ArchipelagoLocations.LOCATIONS.get(this.advancement.getId());
        if (i != null) {
            APPersistentStates.getPersistentStates().putAdvancementId(i);
        }
    }
}
