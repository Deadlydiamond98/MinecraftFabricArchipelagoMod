package net.deadlydiamond98.archipelago.common.world;

import net.deadlydiamond98.archipelago.archipelago.ArchipelagoGoalHelper;
import net.deadlydiamond98.archipelago.util.tracker.IAbilityCheck;
import net.deadlydiamond98.archipelago.archipelago.items.SavedArchipelagoItems;
import net.deadlydiamond98.archipelago.networking.s2c.SendUncheckedItemsS2CPacket;
import net.deadlydiamond98.archipelago.networking.s2c.UpdatePlayerAbilitiesS2CPacket;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.*;

/**
 * Saves Data to the Minecraft World
 *  - Received Items
 *  - Checked Locations
 *  - Status of various abilities
 *  - The Current Archipelago Server
 */
public class APPersistentState extends PersistentState implements IAbilityCheck {

    // This Could probably still be cleaned up even more, but it's fine

    // Checks that are handled by world data
    public final Map<String, APState<?>> allChecks = new HashMap<>();
    public final Map<String, APState<Integer>> progressiveLevelChecks = new HashMap<>();
    public final Map<String, APState<Boolean>> toggleChecks = new HashMap<>();

    // Saves the Indexes of items to prevent them from being re-given in a world that they were already obtained in
    private final Map<Long, String> receivedItems = new HashMap<>();
    // Saves unlocked advancements, so they're granted to any additional players in the world
    private final List<Long> advancementIds = new ArrayList<>();
    // Saves found Itemsanity Checks
    private final List<Long> itemsanityIds = new ArrayList<>();

//    private final Set<Item> collectedItems = new HashSet<>();
    private boolean hasKilledEnderDragon;
    private boolean hasKilledWither;
    private int currentRubyCount;

    private String currentServer = "";
    private String currentPlayer = "";
    private String currentPassword = "";

    // Location ID METHODS /////////////////////////////////////////////////////////////////////////////////////////////

    public List<Long> getAdvancementIds() {
        return advancementIds;
    }

    public void putAdvancementId(long id) {
        advancementIds.add(id);
        markDirty();
        APAdvancementHelper.resyncAdvancements();
    }

    public List<Long> getItemsanityIds() {
        return itemsanityIds;
    }

    public void putItemsanityID(long id) {
        itemsanityIds.add(id);
        markDirty();
        SendUncheckedItemsS2CPacket.send();
    }

    // ITEM INDEX METHODS //////////////////////////////////////////////////////////////////////////////////////////////

    public Map<Long, String> getReceivedItems() {
        return receivedItems;
    }

    public void putItemIndex(long index, String name) {
        receivedItems.put(index, name);
        markDirty();
    }

    // ITEM CHECK METHODS //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Triggers a check to be given
     * @param id the id of the check
     */
    public void triggerCheck(String id) {
        APState<?> state = this.allChecks.get(id);
        if (state != null) {
            state.trigger();
        }
    }

    public void setIntCheckValue(String id, int i) {
        this.setCheckValue(id, i, Integer.class);
    }

    public void setBooleanCheckValue(String id, boolean bl) {
        this.setCheckValue(id, bl, Boolean.class);
    }

    @Override
    public int getIntCheckValue(String id) {
        return getCheckValue(id, 0, Integer.class);
    }

    @Override
    public boolean getBooleanCheckValue(String id) {
        return getCheckValue(id, false, Boolean.class);
    }

    /**
     * Get the value of a check
     * @param id the check id
     * @param fallback the value to return if the check isn't found
     * @param type the class for the check
     * @return Returns the value of a check
     * @param <T> the check type
     */
    public <T> T getCheckValue(String id, T fallback, Class<T> type) {
        APState<?> state = this.allChecks.get(id);
        if (state != null) {
            T value = state.get(type);
            if (value != null) {
                return value;
            }
        }
        return fallback;
    }

    /**
     * Set the value of a check
     * @param id the check id
     * @param value the value to set the check to
     * @param type the class for the check
     * @param <T> the check type
     */
    public <T> void setCheckValue(String id, T value, Class<T> type) {
        APState<?> state = this.allChecks.get(id);
        if (state != null) {
            state.set(value, type);
        }
    }

//    public void collectItem(Item item) {
//        this.collectedItems.add(item);
//        markDirty();
//    }
//
//    public Set<Item> getCollectedItems() {
//        return this.collectedItems;
//    }

    @Override
    public void markDirty() {
        super.markDirty();
        APServerUtil.runOnServer(server -> server.getPlayerManager().getPlayerList().forEach(UpdatePlayerAbilitiesS2CPacket::send));
    }

    // NBT Reading and Writing /////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putLongArray("AdvancementIds", this.advancementIds);
        nbt.putLongArray("ItemsanityIds", this.itemsanityIds);

        NbtList receivedItemsList = new NbtList();
        for (Map.Entry<Long, String> entry : this.receivedItems.entrySet()) {

            NbtCompound itemNbt = new NbtCompound();
            itemNbt.putString("itemName", entry.getValue());
            itemNbt.putLong("itemValue", entry.getKey());

            receivedItemsList.add(itemNbt);
        }
        nbt.put("receivedItems", receivedItemsList);


        APState.write(nbt, "progressiveLevelChecks", this.progressiveLevelChecks, NbtCompound::putInt);
        APState.write(nbt, "toggleChecks", this.toggleChecks, NbtCompound::putBoolean);


//        NbtList collectedItemsList = new NbtList();
//        for (Item collectedItem : this.collectedItems) {
//            NbtCompound item = collectedItem.getDefaultStack().writeNbt(new NbtCompound());
//            collectedItemsList.add(item);
//        }
//        nbt.put("collectedItems", collectedItemsList);

        nbt.putBoolean("HasKilledEnderDragon", this.hasKilledEnderDragon);
        nbt.putBoolean("HasKilledWither", this.hasKilledWither);
        nbt.putInt("RubiesCollected", this.currentRubyCount);

        nbt.putString("ArchipelagoServer", this.currentServer);
        nbt.putString("ArchipelagoPlayer", this.currentPlayer);
        nbt.putString("ArchipelagoPassword", this.currentPassword);

        return nbt;
    }

    private static APPersistentState fromNbt(NbtCompound nbt) {
        APPersistentState states = new APPersistentState();

        for (long itemIndex : nbt.getLongArray("AdvancementIds")) {
            states.advancementIds.add(itemIndex);
        }

        for (long itemIndex : nbt.getLongArray("ItemsanityIds")) {
            states.itemsanityIds.add(itemIndex);
        }

        NbtList receivedItemsList = nbt.getList("receivedItems", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < receivedItemsList.size(); i++) {
            NbtCompound itemNbt = receivedItemsList.getCompound(i);
            String itemName = itemNbt.getString("itemName");
            long itemValue = itemNbt.getLong("itemValue");

            states.receivedItems.put(itemValue, itemName);
        }

        states.progressiveLevelChecks.putAll(APState.read(nbt, "progressiveLevelChecks", states, NbtCompound::getInt));
        states.toggleChecks.putAll(APState.read(nbt, "toggleChecks", states, NbtCompound::getBoolean));

        states.allChecks.putAll(states.progressiveLevelChecks);
        states.allChecks.putAll(states.toggleChecks);

//        NbtList collectedItemsList = nbt.getList("collectedItems", NbtElement.COMPOUND_TYPE);
//        for (int i = 0; i < collectedItemsList.size(); i++) {
//            NbtCompound itemNbt = collectedItemsList.getCompound(i);
//            states.collectedItems.add(ItemStack.fromNbt(itemNbt).getItem());
//        }

        states.hasKilledEnderDragon = nbt.getBoolean("HasKilledEnderDragon");
        states.hasKilledWither = nbt.getBoolean("HasKilledWither");
        states.currentRubyCount = nbt.getInt("RubiesCollected");

        states.currentServer = nbt.getString("ArchipelagoServer");
        states.currentPlayer = nbt.getString("ArchipelagoPlayer");
        states.currentPassword = nbt.getString("ArchipelagoPassword");

        return states;
    }

    /**
     * Adds Checks that might be missing in Persistent State
     */
    public void addMissingChecks() {
        for (String item : SavedArchipelagoItems.PERSISTENT_STATE_PROGRESSIVES) {
            if (!this.progressiveLevelChecks.containsKey(item)) {
                this.progressiveLevelChecks.put(item, new APState<>(0, this));
            }
        }

        for (String item : SavedArchipelagoItems.PERSISTENT_STATE_BOOLEANS) {
            if (!this.toggleChecks.containsKey(item)) {
                this.toggleChecks.put(item, new APState<>(false, this));
            }
        }

        this.allChecks.putAll(this.progressiveLevelChecks);
        this.allChecks.putAll(this.toggleChecks);
        this.markDirty();
    }

    /**
     * Changes the Archipelago Server Info assigned to the world
     * @param server the server
     * @param player the player slot
     * @param password the password
     */
    public void updateWorldServerInformation(String server, String player, String password) {
        this.currentServer = server;
        this.currentPlayer = player;
        this.currentPassword = password;
        this.markDirty();
    }

    public String getCurrentServer() {
        return this.currentServer;
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    public String getCurrentPassword() {
        return this.currentPassword;
    }

    /**
     * Gets Persistent States from Server (or creates them if needed)
     * @return the persistent State
     */
    public static APPersistentState get() {
        return APServerUtil.runOnServer(new APPersistentState(), server -> {
            PersistentStateManager manager = server.getOverworld().getPersistentStateManager();
            return manager.getOrCreate(
                    APPersistentState::fromNbt,
                    APPersistentState::new,
                    "archipelago:persistant_states"
            );
        });
    }

    // Goal Management Stuff ///////////////////////////////////////////////////////////////////////////////////////////


    public boolean hasKilledEnderDragon() {
        return this.hasKilledEnderDragon;
    }

    public boolean hasKilledWither() {
        return this.hasKilledWither;
    }

    public int getCollectedRubies() {
        return this.currentRubyCount;
    }

    public void setHasKilledEnderDragon(boolean hasKilledEnderDragon) {
        this.hasKilledEnderDragon = hasKilledEnderDragon;
        this.markDirty();
    }

    public void setHasKilledWither(boolean hasKilledWither) {
        this.hasKilledWither = hasKilledWither;
        this.markDirty();
    }

    public void setCurrentRubyCount(int currentRubyCount) {
        this.currentRubyCount = currentRubyCount;
        ArchipelagoGoalHelper.tryTriggerGoal();
        this.markDirty();
    }
}
