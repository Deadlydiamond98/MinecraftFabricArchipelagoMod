package net.deadlydiamond98.archipelago.mixin.common.entity;

import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {
    @Inject(method = "onSummoned", at = @At("TAIL"))
    private void archipelago$onSummoned(CallbackInfo ci) {
        WitherEntity wither = (WitherEntity) (Object) this;
        if (!APPersistentState.get().getBooleanCheckValue("wither_summoning")) {
            archipelago$dropStack(wither, new ItemStack(Blocks.SOUL_SAND, 4));
            archipelago$dropStack(wither, new ItemStack(Blocks.WITHER_SKELETON_SKULL, 3));
            PlayerEntity player = wither.getWorld().getClosestPlayer(wither, 10);
            if (player != null && !player.getWorld().isClient()) {
                player.sendMessage(Text.translatable("archipelago.check.wither").setStyle(Style.EMPTY.withColor(0xFF0000)), true);
            }
            wither.discard();
        }
    }

    @Unique
    private void archipelago$dropStack(WitherEntity wither, ItemStack stack) {
        wither.getWorld().spawnEntity(new ItemEntity(wither.getWorld(), wither.getX(), wither.getY(), wither.getZ(), stack));
    }
}
