package net.deadlydiamond98.archipelago.common.world;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoClient;
import net.deadlydiamond98.archipelago.common.world.state.BooleanState;
import net.deadlydiamond98.archipelago.common.world.state.ProgressiveState;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.ArrayList;
import java.util.List;

public class APPersistentStates extends PersistentState {
    private static final List<Long> ITEM_INDEXES = new ArrayList<>();
    private static final List<Long> ADVANCEMENT_IDS = new ArrayList<>();

    public final ProgressiveState toolLevel = new ProgressiveState(0, "toolLevel", this);
    public final ProgressiveState furnaceLevel = new ProgressiveState(0, "furnaceLevel", this);

    public final BooleanState swim = new BooleanState(false, "swim", this);
    public final BooleanState sprint = new BooleanState(false, "sprint", this);

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putLongArray("AdvancementIDs", ADVANCEMENT_IDS);
        nbt.putLongArray("ItemIndexes", ITEM_INDEXES);

        this.toolLevel.write(nbt);
        this.furnaceLevel.write(nbt);

        this.swim.write(nbt);
        this.sprint.write(nbt);

        return nbt;
    }

    private static APPersistentStates fromNbt(NbtCompound nbt) {
        APPersistentStates states = new APPersistentStates();

        ADVANCEMENT_IDS.clear();
        for (long itemIndex : nbt.getLongArray("AdvancementIDs")) {
            ADVANCEMENT_IDS.add(itemIndex);
        }

        ITEM_INDEXES.clear();
        for (long itemIndex : nbt.getLongArray("ItemIndexes")) {
            ITEM_INDEXES.add(itemIndex);
        }

        states.toolLevel.read(nbt);
        states.furnaceLevel.read(nbt);

        states.swim.read(nbt);
        states.sprint.read(nbt);

        return states;
    }


    public List<Long> getAdvancementIds() {
        return ADVANCEMENT_IDS;
    }

    public List<Long> getItemIndexes() {
        return ITEM_INDEXES;
    }

    public void putItemIndex(long index) {
        ITEM_INDEXES.add(index);
        markDirty();
    }

    public void putAdvancementId(long id) {
        ADVANCEMENT_IDS.add(id);
        markDirty();
        syncChecks();
    }

    public void syncChecks() {
        for (Long advancementId : ADVANCEMENT_IDS) {
            ArchipelagoClient client = APMod.apClient();
            if (client != null) {
                client.checkLocation(advancementId);
            }
        }
    }

    public static APPersistentStates getPersistentStates() {
        MinecraftServer server = APMod.server;

        PersistentStateManager manager;
        if (server == null) {
            return new APPersistentStates();
        }

        manager = server.getOverworld().getPersistentStateManager();


        String id = "archipelago:persistant_states";
        return manager.getOrCreate(APPersistentStates::fromNbt, APPersistentStates::new, id);
    }
}
