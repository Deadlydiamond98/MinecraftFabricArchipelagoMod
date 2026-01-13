package net.deadlydiamond98.archipelago.common.world.state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public class ProgressiveState extends AbstractState<Integer> {
    public ProgressiveState(Integer currentVal, String type, PersistentState state) {
        super(currentVal, type, state);
    }

    @Override
    public void write(NbtCompound nbt) {
        nbt.putInt(this.type, this.currentVal);
    }

    @Override
    public void read(NbtCompound nbt) {
        this.currentVal = nbt.getInt(this.type);
    }

    public void increment() {
        this.set(this.get() + 1);
    }
}
