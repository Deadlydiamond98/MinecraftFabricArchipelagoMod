package net.deadlydiamond98.archipelago.mixin.client.controls;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.deadlydiamond98.archipelago.init.APEffects;
import net.deadlydiamond98.archipelago.util.mixinterfaces.IKeybindSwap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(KeyBinding.class)
public abstract class KeybindingMixin implements IKeybindSwap {
    @Shadow public abstract String getTranslationKey();
    @Shadow @Final public static String GAMEPLAY_CATEGORY;
    @Shadow @Final public static String MOVEMENT_CATEGORY;
    @Shadow @Final private String category;
    @Shadow private int timesPressed;
    @Shadow private boolean pressed;

    @ModifyReturnValue(method = "wasPressed", at = @At("RETURN"))
    private boolean archipelago$wasPressed(boolean original) {
        if (archipelago$isStunned()) {
            return false;
        }

        if (archipelago$canSwapKey()) {
            KeyBinding key = archipelago$getOppositeKey(this.getTranslationKey());


            if (key instanceof IKeybindSwap swappedKey) {
                return swappedKey.archipelago$wasPressedCopy();
            }
        }

        return original;
    }

    @ModifyReturnValue(method = "isPressed", at = @At("RETURN"))
    private boolean archipelago$isPressed(boolean original) {
        if (archipelago$isStunned()) {
            return false;
        }

        if (archipelago$canSwapKey()) {
            KeyBinding key = archipelago$getOppositeKey(this.getTranslationKey());

            if (key instanceof IKeybindSwap swappedKey) {
                return swappedKey.archipelago$isPressedCopy();
            }
        }

        return original;
    }

    @Unique
    private boolean archipelago$isStunned() {
        boolean bl = this.category.equals(GAMEPLAY_CATEGORY) || this.category.equals(MOVEMENT_CATEGORY);
        PlayerEntity player = MinecraftClient.getInstance().player;
        return bl && player != null && player.hasStatusEffect(APEffects.STUNNED);
    }

    @Unique
    private boolean archipelago$canSwapKey() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        return player != null && player.hasStatusEffect(APEffects.CONFUSION);
    }

    @Unique
    private @Nullable KeyBinding archipelago$getOppositeKey(String translationKey) {
        return switch (translationKey) {
            case "key.back" -> MinecraftClient.getInstance().options.forwardKey;
            case "key.forward" -> MinecraftClient.getInstance().options.backKey;
            case "key.right" -> MinecraftClient.getInstance().options.leftKey;
            case "key.left" -> MinecraftClient.getInstance().options.rightKey;
            case "key.jump" -> MinecraftClient.getInstance().options.sneakKey;
            case "key.sneak" -> MinecraftClient.getInstance().options.jumpKey;
            case "key.attack" -> MinecraftClient.getInstance().options.useKey;
            case "key.use" -> MinecraftClient.getInstance().options.attackKey;
            default -> null;
        };

    }

    @Override
    public boolean archipelago$wasPressedCopy() {
        if (this.timesPressed == 0) {
            return false;
        } else {
            --this.timesPressed;
            return true;
        }
    }

    @Override
    public boolean archipelago$isPressedCopy() {
        return this.pressed;
    }
}
