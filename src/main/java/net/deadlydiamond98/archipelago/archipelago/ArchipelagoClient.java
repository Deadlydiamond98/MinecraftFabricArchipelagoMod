package net.deadlydiamond98.archipelago.archipelago;
import io.github.archipelagomw.Client;
import io.github.archipelagomw.flags.ItemsHandling;
import net.deadlydiamond98.archipelago.events.archipelago.APConnectEvents;
import net.deadlydiamond98.archipelago.events.archipelago.APPrintJsonEvents;
import net.deadlydiamond98.archipelago.events.archipelago.APReceiveItemEvents;

public class ArchipelagoClient extends Client {
    public ArchipelagoClient() {
        super();
        this.setGame("Minecraft Fabric");
        this.setItemsHandlingFlags(ItemsHandling.SEND_ITEMS + ItemsHandling.SEND_OWN_ITEMS + ItemsHandling.SEND_STARTING_INVENTORY);

        this.getEventManager().registerListener(new APReceiveItemEvents());
        this.getEventManager().registerListener(new APPrintJsonEvents());
        this.getEventManager().registerListener(new APConnectEvents());

        client = this;
    }

    public static boolean isAPConnected() {
        return client != null && client.isConnected();
    }

    @Override
    public void onError(Exception ex) {
        // TODO: Add Message here for Error
    }

    @Override
    public void onClose(String Reason, int attemptingReconnect) {
        // TODO: Add Message here for Closing Archipelago Client
    }
}
