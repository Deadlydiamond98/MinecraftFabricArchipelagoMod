package net.deadlydiamond98.archipelago.mixin.common.entity;

import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DrownedEntity.class)
public class DrownedEntityMixin {
    @Inject(method = "initialize", at = @At("TAIL"))
    private void archipelago$initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        if (Archipelago.hasQOLSetting("Drowned Items")) {
            DrownedEntity drowned = (DrownedEntity) (Object) this;
            drowned.setEquipmentDropChance(EquipmentSlot.MAINHAND, 2);
        }
    }
}
