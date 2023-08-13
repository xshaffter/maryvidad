package com.para_mada.maryvidad.networking.packets.c2s;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.effects.EffectManager;
import com.para_mada.maryvidad.networking.NetworkManager;
import com.para_mada.maryvidad.util.LivingEntityBridge;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;

public class CastRadarSkillC2SPacket {
    public static <T extends FabricPacket> void receive(MinecraftServer server, ServerPlayerEntity player,
                                                        ServerPlayNetworkHandler handler,
                                                        PacketByteBuf buf, PacketSender responseSender) {
        if (!LivingEntityBridge.hasFlag(player, "radar_unlocked")) {
        }
        if (player.hasStatusEffect(EffectManager.RADAR_COOLDOWN)) {
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeString("radar");
            ServerPlayNetworking.send(player, NetworkManager.CANT_PERFORM_SKILL_ID, buffer);
        } else {
            int x = player.getBlockX();
            int y = player.getBlockY();
            int z = player.getBlockZ();
            var entities = player.getWorld().getEntitiesByClass(LivingEntity.class, new Box(x - 15, y - 15, z - 15, x + 15, y + 15, z + 15), livingEntity -> !livingEntity.isPlayer());
            for (var entity : entities) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 5 * Maryvidad.TPS));
            }
            //TODO: add markers on minimap

            player.addStatusEffect(new StatusEffectInstance(EffectManager.RADAR_COOLDOWN, Maryvidad.TPS * 15, 0, false, false, true));
        }
    }
}
