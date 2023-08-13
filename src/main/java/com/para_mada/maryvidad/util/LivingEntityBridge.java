package com.para_mada.maryvidad.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;

public class LivingEntityBridge {
    public static NbtCompound getPersistentData(Entity entity) {
        var living = (IEntityDataSaver) entity;
        return living.maryvidad$getPersistentData();
    }
    public static boolean hasFlag(final Entity entity, final String flag) {
        return getPersistentData(entity).getBoolean(flag);
    }

    public static Inventory getInventory(Entity entity) {
        if (entity instanceof PlayerEntity living) {
            return living.getInventory();
        }
        return null;
    }

    public static boolean isCreative(Entity entity) {
        if (entity instanceof PlayerEntity player) {
            return player.isCreative();
        }
        return false;
    }
}