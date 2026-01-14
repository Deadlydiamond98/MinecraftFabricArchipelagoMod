package net.deadlydiamond98.archipelago.common.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Used for holding a Persistent State Value and Modifying It
 * @param <T>
 */
public class APState<T> {
    protected final PersistentState state;
    protected T currentVal;

    public APState(T currentVal, PersistentState state) {
        this.currentVal = currentVal;
        this.state = state;
    }

    public T get() {
        return this.currentVal;
    }

    public void set(T value) {
        this.currentVal = value;
        this.state.markDirty();
    }

    public <V> V get(Class<V> type) {
        if (type.isInstance(get())) {
            return type.cast(get());
        } else {
            throw new IllegalArgumentException("State type mismatch: expected " + type.getSimpleName());
        }
    }

    public <V> void set(V value, Class<V> type) {
        if (get(type) != null) {
            this.currentVal = (T) value;
        }
    }

    public void trigger() {
        if (this.currentVal instanceof Integer) {
            set(get(Integer.class) + 1, Integer.class);
        } else if (this.currentVal instanceof Boolean) {
            set(true, Boolean.class);
        }
    }

    public static <T> void write(NbtCompound nbt, String key, Map<String, APState<T>> map, TriConsumer<NbtCompound, String, T> write) {
        NbtCompound compound = new NbtCompound();
        map.forEach((id, state) -> write.accept(compound, id, state.get()));
        nbt.put(key, compound);
    }

    public static <T> Map<String, APState<T>> read(NbtCompound nbt, String key, APPersistentState states, BiFunction<NbtCompound, String, T> read) {
        NbtCompound items = nbt.getCompound(key);
        Map<String, APState<T>> map = new HashMap<>();
        for (String id : items.getKeys()) {
            map.put(id, new APState<>(read.apply(items, id), states));
        }
        return map;
    }
}
