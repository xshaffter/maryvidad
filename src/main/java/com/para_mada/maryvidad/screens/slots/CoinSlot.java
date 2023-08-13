package com.para_mada.maryvidad.screens.slots;

import com.para_mada.maryvidad.items.ItemManager;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class CoinSlot extends Slot {

    public CoinSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }


    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isOf(ItemManager.MARY_COIN);
    }
}
