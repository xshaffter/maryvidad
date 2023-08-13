package com.para_mada.maryvidad.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerEntityBridge {

    public static int getMoney(PlayerEntity player) {
        return LivingEntityBridge.getPersistentData(player).getInt("coins");
    }

    public static void spendMoney(ServerPlayerEntity player, int price) {
        var persistent = LivingEntityBridge.getPersistentData(player);
        var balance = persistent.getInt("coins");
        setMoney(player, balance - price);
    }

    public static void setMoney(ServerPlayerEntity player, int amount) {
        var persistent = LivingEntityBridge.getPersistentData(player);
        persistent.putInt("coins", amount);
        MoneyManager.syncMoney(player);
    }

    public static void addMoney(Entity entity, int count) {
        if (entity instanceof ServerPlayerEntity player) {
            var money = getMoney(player);
            setMoney(player, money + count);
        }
    }
}
