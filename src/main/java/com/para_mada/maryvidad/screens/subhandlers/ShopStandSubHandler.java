package com.para_mada.maryvidad.screens.subhandlers;

import com.para_mada.maryvidad.screens.handlers.ShopStandScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class ShopStandSubHandler {
    private final ShopStandScreenHandler screen;
    private final int MARGIN;
    private final int HOTBAR_GAP = 4;
    public Inventory tradesInventory;

    public ShopStandSubHandler(ShopStandScreenHandler screen, Inventory inventory, PlayerEntity player, int margin) {
        this.screen = screen;
        MARGIN = margin;
        tradesInventory = inventory;

        addPlayerInventory(player.getInventory());
        addPlayerHotbar(player.getInventory());
    }


    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                screen.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, MARGIN + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            screen.addSlot(new Slot(playerInventory, i, 8 + i * 18, MARGIN + 54 + HOTBAR_GAP));
        }
    }
}
