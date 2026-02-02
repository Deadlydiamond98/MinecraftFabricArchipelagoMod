package net.deadlydiamond98.archipelago.util.mixinterfaces;

import java.util.List;

public interface IPlayerReceivedItems {
    List<Long> archipelago$getItemIDs();
    void archipelago$setItemIDs(List<Long> ids);
    void archipelago$putItemID(long id);
}
