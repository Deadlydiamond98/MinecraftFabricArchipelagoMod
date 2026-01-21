package net.deadlydiamond98.archipelago.mixin.common.advancement;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplays;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AdvancementDisplays.class)
public class AdvancementDisplaysMixin {

    // Makes it so that all Advancement Locations Added by Archipelago will display at all times, and hides others
    @WrapMethod(method = "getStatus")
    private static AdvancementDisplays.Status archipelago$getStatus(Advancement advancement, boolean force, Operation<AdvancementDisplays.Status> original) {
        return APAdvancementHelper.isValidAdvancement(advancement.getId()) ? AdvancementDisplays.Status.SHOW : AdvancementDisplays.Status.HIDE;
    }
}
