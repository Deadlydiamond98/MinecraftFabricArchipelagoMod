package net.deadlydiamond98.archipelago.mixin.common;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
    @Shadow @Final private RegistryEntry<DamageType> type;

    @Shadow public abstract DamageType getType();

    @WrapMethod(method = "getDeathMessage")
    private Text archipelago$getDeathMessage(LivingEntity killed, Operation<Text> original) {
        String id = this.getType().msgId();
        if (id.equals("deathlink")) {
            return Text.translatable("death.attack.deathlink", killed.getDisplayName(), Archipelago.lastDeathlinkPlayer);
        }
        return original.call(killed);
    }
}
