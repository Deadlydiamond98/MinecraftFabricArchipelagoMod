package net.deadlydiamond98.archipelago.events.archipelago;

import io.github.archipelagomw.Client;
import io.github.archipelagomw.events.ArchipelagoEventListener;
import io.github.archipelagomw.events.ConnectionResultEvent;
import io.github.archipelagomw.network.ConnectionResult;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.archipelago.Archipelago;
import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.deadlydiamond98.archipelago.archipelago.locations.APLocations;
import net.deadlydiamond98.archipelago.common.world.APPersistentState;
import net.deadlydiamond98.archipelago.networking.s2c.SendArchipelagoInfoS2CPacket;
import net.deadlydiamond98.archipelago.networking.s2c.SendUncheckedItemsS2CPacket;
import net.deadlydiamond98.archipelago.util.APAdvancementHelper;
import net.deadlydiamond98.archipelago.util.APServerUtil;
import net.deadlydiamond98.koalalib.init.KoalaLibSounds;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;

import java.util.ArrayList;
import java.util.List;

public class APConnectEvents {

    @ArchipelagoEventListener
    public void onConnectionEvent(ConnectionResultEvent event) {
        if (event.getResult() == ConnectionResult.Success) {
            APPersistentState state = APPersistentState.get();
            Archipelago.MCSlotData slot = Archipelago.initSlotData(event);

            String version = slot.world_version;
            Style style = Style.EMPTY.withColor(Formatting.YELLOW);
            if (version != null) {
                if (!version.contains(APMod.VALID_WORLD_VERSION)) {
                    APServerUtil.sendMessage(Text.translatable("archipelago.version_message.mismatched", version, APMod.VALID_WORLD_VERSION).setStyle(style));
                    APServerUtil.runOnServer(server -> server.getPlayerManager().getPlayerList().forEach(player ->
                            player.playSound(KoalaLibSounds.MAGIC_FAIL, SoundCategory.PLAYERS, 1, 1))
                    );
                    Archipelago.run(Client::close);
                    return;
                }
            } else {
                APServerUtil.sendMessage(Text.translatable("archipelago.version_message.invalid").setStyle(style));
                Archipelago.run(Client::close);
                return;
            }

            List<String> missing = new ArrayList<>();

            slot.enabled_mods.forEach(modID -> {
                if (!APMod.isModLoaded(modID)) {
                    missing.add(modID);
                }
            });

            APServerUtil.sendMessage(Text.translatable("archipelago.mods_enabled", slot.enabled_mods.toString()).setStyle(style));

            if (!missing.isEmpty()) {
                APServerUtil.runOnServer(server -> server.getPlayerManager().getPlayerList().forEach(player ->
                        player.playSound(KoalaLibSounds.MAGIC_FAIL, SoundCategory.PLAYERS, 1, 1))
                );
                APServerUtil.sendMessage(Text.translatable("archipelago.mod_missing", missing.toString()).setStyle(style.withColor(Formatting.RED)));
                APServerUtil.runOnServer(server -> server.getPlayerManager().broadcast(
                        Text.translatable("archipelago.mod_missing_alert")
                                .setStyle(style.withColor(Formatting.RED)),
                        true
                ));
            }

            // Unlocks Optional Abilities that aren't randomized
            slot.possible_randomized_abilities.forEach(ability -> {
                if (!slot.randomized_abilities.contains(ability)) {
                    state.triggerCheck(ability.toLowerCase());
                }
            });

            APServerUtil.runOnServer(server -> {
                // Enable Keep Inventory
                if (slot.keep_inventory == 1) {
                    server.getGameRules().get(GameRules.KEEP_INVENTORY).set(true, server);
                }

                // Sync Some Data
                server.getPlayerManager().getPlayerList().forEach(player -> {
                    server.getPlayerManager().getAdvancementTracker(player).reload(server.getAdvancementLoader());
                    SendArchipelagoInfoS2CPacket.send(player);
                    SendUncheckedItemsS2CPacket.send(player);
                });
            });

            Archipelago.run(archipelago -> {
                // Enable Deathlink
                if (slot.deathlink != 0) {
                    archipelago.setDeathLinkEnabled(true);
                }
                // Enable Traplink
                if (slot.traplink != 0) {
                    archipelago.addTag("TrapLink");
                }
                // Unlock Advancements that are already received
                archipelago.getLocationManager().getCheckedLocations().forEach(aLong -> {
                    if (APLocations.ADVANCEMENT_LOCATIONS.containsValue(aLong)) {
                        state.putAdvancementId(aLong);
                    }
                });
            });
            APAdvancementHelper.resyncAdvancements();
        }
    }
}
