package net.deadlydiamond98.archipelago.util.tracker;

import net.deadlydiamond98.archipelago.networking.c2s.RequestTrackerInformationC2SPacket;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArchipelagoTrackingData {
    public static final Set<Long> UNCHECKED_LOCATIONS = new HashSet<>();
    public static final List<Item> UNCHECKED_ITEMS = new ArrayList<>();
    public static ItemTrackerDataHolder tracker;

    public static ItemTrackerDataHolder tracker() {
        if (tracker != null) {
            return tracker;
        }
        RequestTrackerInformationC2SPacket.send();
        return null;
    }

    public static void clear() {
        UNCHECKED_LOCATIONS.clear();
        UNCHECKED_ITEMS.clear();
        tracker = null;
    }
}
