package net.deadlydiamond98.archipelago.common.world;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.ArchipelagoClient;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.ArrayList;
import java.util.List;

public class APPersistentStates extends PersistentState {
    private static final List<Long> ITEM_INDEXES = new ArrayList<>();
    private static final List<Long> ADVANCEMENT_IDS = new ArrayList<>();
    private boolean canSwim = false;
    private boolean canSprint = false;
    private int smeltLevel = 0;

    public static APPersistentStates getPersistentStates() {
        MinecraftServer server = APMod.server;

        PersistentStateManager manager;
        if (server != null) {
            manager = server.getOverworld().getPersistentStateManager();
        } else {
            return new APPersistentStates();
        }

        String id = "archipelago:persistant_states";
        return manager.getOrCreate(APPersistentStates::fromNbt, APPersistentStates::new, id);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putLongArray("AdvancementIDs", ADVANCEMENT_IDS);
        nbt.putLongArray("ItemIndexes", ITEM_INDEXES);
        nbt.putBoolean("SwimmingEnabled", this.canSwim);
        nbt.putBoolean("SprintingEnabled", this.canSprint);
        nbt.putInt("SmeltLvl", this.smeltLevel);

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

        states.canSwim = nbt.getBoolean("SwimmingEnabled");
        states.canSprint = nbt.getBoolean("SprintingEnabled");
        states.smeltLevel = nbt.getInt("SmeltLvl");

        return states;
    }

    public int getSmeltLevel() {
        return this.smeltLevel;
    }
    public boolean canSwim() {
        return this.canSwim;
    }
    public boolean canSprint() {
        return this.canSprint;
    }


    public void setSmeltLevel(int smeltLevel) {
        this.smeltLevel = smeltLevel;
        markDirty();
    }
    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
        markDirty();
    }
    public void setCanSprint(boolean canSprint) {
        this.canSprint = canSprint;
        markDirty();
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
}
