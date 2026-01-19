package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.DeathLinkEvent;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.init.APDamageTypes;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.minecraft.text.Text;

public class APDeathlinkEvents {

    @ArchipelagoEventListener
    public static void onDeathlinkEvent(DeathLinkEvent event) {
        int deathlink = Archipelago.getFromSlot(mcSlotData -> mcSlotData.deathlink);
        if (deathlink != 0) {
            Archipelago.run(archipelago -> {
                Archipelago.lastDeathlinkPlayer = event.source;
                APServerUtil.runOnServer(server -> server.getPlayerManager().getPlayerList().forEach(player -> {
                    player.damage(APDamageTypes.of(player.getWorld(), APDamageTypes.DEATHLINK), Float.MAX_VALUE);
                }));
                APServerUtil.sendMessage(Text.translatable("archipelago.deathlinked.player", event.source));
                APServerUtil.sendMessage(Text.translatable("archipelago.deathlinked.cause", event.cause));
            });
        }
    }
}
