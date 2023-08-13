package com.para_mada.maryvidad.screens.handlers;

import com.para_mada.maryvidad.screens.slots.CoinSlot;
import com.para_mada.maryvidad.screens.subhandlers.ShopStandSubHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ShopStandScreenHandler extends ScreenHandler {
    private final ShopStandSubHandler subHandler;

    public ShopStandScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, new SimpleInventory(24));
    }

    public ShopStandScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.SHOP_STAND_SCREEN_HANDLER, syncId);
        subHandler = new ShopStandSubHandler(this, inventory, playerInventory.player, 156);
        addChestSlots();
    }


    public void addChestSlots() {
        for (int column = 0; column < 2; column++) {
            for (int row = 0; row < 6; row++) {
                this.addSlot(new CoinSlot(subHandler.tradesInventory, (row * 2) + (column * 12), 8 + (98 * column), 17 + (row * 21)));
                this.addSlot(new Slot(subHandler.tradesInventory, (row * 2) + (column * 12 + 1), 54 + (98 * column), 17 + (row * 21)));
            }
        }
    }

    public Slot addSlot(Slot slot) {
        return super.addSlot(slot);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }


    @Override
    public boolean insertItem(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        return super.insertItem(stack, startIndex, endIndex, fromLast);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
