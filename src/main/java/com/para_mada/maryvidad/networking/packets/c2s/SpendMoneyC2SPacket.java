package com.para_mada.maryvidad.networking.packets.c2s;

import com.para_mada.maryvidad.util.PlayerEntityBridge;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SpendMoneyC2SPacket {
    public static <T extends FabricPacket> void receive(MinecraftServer server, ServerPlayerEntity player,
                                                        ServerPlayNetworkHandler handler,
                                                        PacketByteBuf buf, PacketSender responseSender) {
        PlayerEntityBridge.spendMoney(player, buf.readInt());
    }
}
