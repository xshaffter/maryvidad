package com.para_mada.maryvidad.networking.packets.c2s;

import com.para_mada.maryvidad.networking.NetworkManager;
import com.para_mada.maryvidad.util.PlayerEntityBridge;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class RequestMoneyC2SPacket {
    public static <T extends FabricPacket> void receive(MinecraftServer server, ServerPlayerEntity player,
                                                        ServerPlayNetworkHandler handler,
                                                        PacketByteBuf buf, PacketSender responseSender) {
        var newBuf = PacketByteBufs.create();
        newBuf.writeInt(PlayerEntityBridge.getMoney(player));
        ServerPlayNetworking.send(player, NetworkManager.SYNC_MONEY_ID, newBuf);
    }
}
