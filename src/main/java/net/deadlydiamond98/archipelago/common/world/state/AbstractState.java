package net.deadlydiamond98.archipelago.common.world.state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public abstract class AbstractState<T> {
    protected final PersistentState state;
    protected final String type;
    protected T currentVal;

    public AbstractState(T currentVal, String type, PersistentState state) {
        this.currentVal = currentVal;
        this.type = type;
        this.state = state;
    }

    public abstract void write(NbtCompound nbt);

    public abstract void read(NbtCompound nbt);

    public T get() {
        return this.currentVal;
    }

    public void set(T value) {
        this.currentVal = value;
        this.state.markDirty();
    }
}
