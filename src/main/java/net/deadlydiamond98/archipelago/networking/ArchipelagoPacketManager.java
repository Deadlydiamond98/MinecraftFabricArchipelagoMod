package net.deadlydiamond98.archipelago.networking;

import io.github.archipelagomw.network.client.BouncePacket;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ArchipelagoPacketManager {
    public static void sendTraplink(String trapName) {
        if (Archipelago.getFromSlot(mcSlotData -> mcSlotData.traplink) != 0) {
            Archipelago.run(archipelago -> {
                BouncePacket packet = new BouncePacket();
                packet.tags = new String[]{"TrapLink"};
                packet.setData(new HashMap<>(Map.of(
                        "time", Instant.now().getEpochSecond(),
                        "source", archipelago.getMyName(),
                        "trap_name", trapName
                )));
                archipelago.sendBounce(packet);
            });
        }
    }
}
