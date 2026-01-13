package net.deadlydiamond98.archipelago.common.world.state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public class BooleanState extends AbstractState<Boolean> {
    public BooleanState(Boolean currentVal, String type, PersistentState state) {
        super(currentVal, type, state);
    }

    @Override
    public void write(NbtCompound nbt) {
        nbt.putBoolean(this.type, this.currentVal);
    }

    @Override
    public void read(NbtCompound nbt) {
        this.currentVal = nbt.getBoolean(this.type);
    }
}
