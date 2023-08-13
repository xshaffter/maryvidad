package com.para_mada.maryvidad.networking;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.networking.packets.c2s.*;
import com.para_mada.maryvidad.networking.packets.s2c.CantPerformSkillS2CPacket;
import com.para_mada.maryvidad.networking.packets.s2c.SyncMoneyS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NetworkManager {
    public static final Identifier SYNC_MONEY_ID = new Identifier(Maryvidad.MOD_ID, "sync_money");
    public static final Identifier EARN_MONEY_ID = new Identifier(Maryvidad.MOD_ID, "earn_money");
    public static final Identifier SPEND_MONEY_ID = new Identifier(Maryvidad.MOD_ID, "spend_money");
    public static final Identifier REQUEST_MONEY_ID = new Identifier(Maryvidad.MOD_ID, "request_money");
    public static final Identifier FIRST_SKILL_ID = new Identifier(Maryvidad.MOD_ID, "first_skill");
    public static final Identifier SECOND_SKILL_ID = new Identifier(Maryvidad.MOD_ID, "second_skill");
    public static final Identifier THIRD_SKILL_ID = new Identifier(Maryvidad.MOD_ID, "third_skill");
    public static final Identifier RADAR_SKILL_ID = new Identifier(Maryvidad.MOD_ID, "radar_skill");
    public static final Identifier CANT_PERFORM_SKILL_ID = new Identifier(Maryvidad.MOD_ID, "cant_perform_skill");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(EARN_MONEY_ID, EarnMoneyC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SPEND_MONEY_ID, SpendMoneyC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(REQUEST_MONEY_ID, RequestMoneyC2SPacket::receive);


        //region SKILLS
        ServerPlayNetworking.registerGlobalReceiver(FIRST_SKILL_ID, CastFirstSkillC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(SECOND_SKILL_ID, CastSecondSkillC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(THIRD_SKILL_ID, CastThirdSkillC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(RADAR_SKILL_ID, CastRadarSkillC2SPacket::receive);

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(SYNC_MONEY_ID, SyncMoneyS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(CANT_PERFORM_SKILL_ID, CantPerformSkillS2CPacket::receive);
    }
}
