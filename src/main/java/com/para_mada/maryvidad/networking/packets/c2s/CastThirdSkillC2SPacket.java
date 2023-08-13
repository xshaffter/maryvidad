package com.para_mada.maryvidad.networking.packets.c2s;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.effects.EffectManager;
import com.para_mada.maryvidad.networking.NetworkManager;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class CastThirdSkillC2SPacket {
    public static <T extends FabricPacket> void receive(MinecraftServer server, ServerPlayerEntity player,
                                                        ServerPlayNetworkHandler handler,
                                                        PacketByteBuf buf, PacketSender responseSender) {
        if (player.hasStatusEffect(EffectManager.THIRD_SKILL_COOLDOWN)) {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeString("paramada");
            ServerPlayNetworking.send(player, NetworkManager.CANT_PERFORM_SKILL_ID, buffer);
        } else {
            player.addStatusEffect(new StatusEffectInstance(EffectManager.THIRD_SKILL_COOLDOWN, Maryvidad.TPS * 15, 0, false, false, true));
        }
    }
}
