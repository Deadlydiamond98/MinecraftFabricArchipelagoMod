package net.deadlydiamond98.archipelago.networking;

import net.deadlydiamond98.archipelago.networking.c2s.RequestTrackerInformationC2SPacket;
import net.deadlydiamond98.archipelago.networking.s2c.SendArchipelagoInfoS2CPacket;
import net.deadlydiamond98.archipelago.networking.s2c.UpdatePlayerAbilitiesS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class APNetworking {

    public static void registerC2SReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(RequestTrackerInformationC2SPacket.ID, RequestTrackerInformationC2SPacket.Handler::receive);
    }

    public static class Client {
        public static void registerS2CReceivers() {
            ClientPlayNetworking.registerGlobalReceiver(SendArchipelagoInfoS2CPacket.ID, SendArchipelagoInfoS2CPacket.Handler::receive);
            ClientPlayNetworking.registerGlobalReceiver(UpdatePlayerAbilitiesS2CPacket.ID, UpdatePlayerAbilitiesS2CPacket.Handler::receive);
        }
    }
}
