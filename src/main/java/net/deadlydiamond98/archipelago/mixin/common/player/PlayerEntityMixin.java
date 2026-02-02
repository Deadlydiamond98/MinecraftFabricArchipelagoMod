package net.deadlydiamond98.archipelago.mixin.common.player;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.deadlydiamond98.archipelago.networking.s2c.UpdatePlayerAbilitiesS2CPacket;
import net.deadlydiamond98.archipelago.util.mixinterfaces.IPlayerReceivedItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IPlayerReceivedItems {
    @Unique private List<Long> archipelago$receivedItems = new ArrayList<>();

    // Prevents Jumping without Jump Item
    @WrapMethod(method = "jump")
    private void archipelago$jump(Operation<Void> original) {
        if (UpdatePlayerAbilitiesS2CPacket.canJump) {
            original.call();
        }
    }

    // READ & WRITE NBT ////////////////////////////////////////////////////////////////////////////////////////////////

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void zeldacraft$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putLongArray("ItemIndexes", this.archipelago$receivedItems);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void zeldacraft$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        this.archipelago$receivedItems.clear();
        for (long itemIndex : nbt.getLongArray("ItemIndexes")) {
            this.archipelago$receivedItems.add(itemIndex);
        }
    }

    @Override
    public List<Long> archipelago$getItemIDs() {
        return this.archipelago$receivedItems;
    }

    @Override
    public void archipelago$setItemIDs(List<Long> ids) {
        this.archipelago$receivedItems = ids;
    }

    @Override
    public void archipelago$putItemID(long id) {
        this.archipelago$receivedItems.add(id);
    }
}
