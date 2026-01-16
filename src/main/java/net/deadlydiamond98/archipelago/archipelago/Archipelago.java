package net.deadlydiamond98.archipelago.archipelago;
import io.github.archipelagomw.Client;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.flags.ItemsHandling;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.events.archipelago.APConnectEvents;
import net.deadlydiamond98.archipelago.events.archipelago.APPrintJsonEvents;
import net.deadlydiamond98.archipelago.events.archipelago.APReceiveItemEvents;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.net.URISyntaxException;
import java.util.function.Consumer;

public class Archipelago extends Client {
    public static String worldInputServer = null;
    public static String worldInputPlayer = null;
    public static String worldInputPassword = null;

    public static Archipelago archipelago;
    public static @Nullable MCSlotData slotData;

    public Archipelago() {
        super();
        this.setGame("Minecraft Fabric");
        this.setItemsHandlingFlags(ItemsHandling.SEND_ITEMS + ItemsHandling.SEND_OWN_ITEMS + ItemsHandling.SEND_STARTING_INVENTORY);

        this.getEventManager().registerListener(new APPrintJsonEvents());
        this.getEventManager().registerListener(new APReceiveItemEvents());
        this.getEventManager().registerListener(new APConnectEvents());
    }

    @Override
    public void onError(Exception ex) {
        APServerUtil.sendMessage(Text.translatable("archipelago.connection.error", ex.toString()).setStyle(Style.EMPTY.withColor(Formatting.RED)));
    }

    @Override
    public void onClose(String reason, int attemptingReconnect) {
        APServerUtil.sendMessage(Text.translatable("archipelago.connection.error", reason).setStyle(Style.EMPTY.withColor(Formatting.RED)));
        if (attemptingReconnect > 0) {
            APServerUtil.sendMessage(Text.translatable("archipelago.connection.reconnecting", attemptingReconnect));
        }
    }

    // Helper Methods //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Runs a Command on the Archipelago Client if it's present, otherwise runs a separate method
     * @param success the method to run if the Client is present
     * @param fail the method to run if the Client isn't present
     * @return returns 1 if the client is present, otherwise returns 0. Is used for Minecraft Text commands
     */
    public static int runCommand(Consumer<Archipelago> success, Runnable fail) {
        if (!run(success)) {
            fail.run();
            return 0;
        }
        return 1;
    }

    /**
     * Runs an action on the Archipelago Client if it's present
     * @param consumer the method to run
     * @return returns true if the Client is present
     */
    public static boolean run(Consumer<Archipelago> consumer) {
        if (archipelago != null) {
            consumer.accept(archipelago);
            return true;
        }
        return false;
    }

    public static void connectToAPServer(String server, String player, String password) {
        run(archipelago1 -> {
            archipelago1.setName(player);
            archipelago1.setPassword(password);
            try {
                archipelago1.connect(server);
            } catch (URISyntaxException e) {
                APServerUtil.sendMessage(Text.translatable("archipelago.connection.failed"));
            }
        });
    }

    // Slot Data Methods ///////////////////////////////////////////////////////////////////////////////////////////////

    public static MCSlotData initSlotData(ConnectionResultEvent event) {
        slotData = event.getSlotData(MCSlotData.class);
        return slotData;
    }

    public static @Nullable MCSlotData getSlotData() {
        return slotData;
    }

    public static class MCSlotData {
        public int goal_condition;
        public int randomize_swim;
        public int randomize_sprint;
    }
}
