package com.para_mada.maryvidad.networking.packets.s2c;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

public class CantPerformSkillS2CPacket {
    public static <T extends FabricPacket> void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                                                        PacketByteBuf buf, PacketSender responseSender) {
        assert client.player != null;
        client.player.sendMessage(Text.translatable("hud.skills.cant_perform"), true);
    }
}
