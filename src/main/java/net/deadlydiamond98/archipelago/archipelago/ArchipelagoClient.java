package net.deadlydiamond98.archipelago.archipelago;
import io.github.archipelagomw.Client;
import io.github.archipelagomw.flags.ItemsHandling;
import net.deadlydiamond98.archipelago.events.archipelago.APPrintJsonEvents;

public class ArchipelagoClient extends Client {
    public ArchipelagoClient() {
        super();
        this.setGame("Minecraft Fabric");
        this.setItemsHandlingFlags(ItemsHandling.SEND_ITEMS + ItemsHandling.SEND_OWN_ITEMS + ItemsHandling.SEND_STARTING_INVENTORY);

        this.getEventManager().registerListener(new APPrintJsonEvents());

        client = this;
    }

    public static boolean isAPConnected() {
        return client != null && client.isConnected();
    }

    @Override
    public void onError(Exception ex) {

    }

    @Override
    public void onClose(String Reason, int attemptingReconnect) {

    }
}
