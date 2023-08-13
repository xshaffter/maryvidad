package com.para_mada.maryvidad.blocks.entities;

import com.para_mada.maryvidad.screens.handlers.ShopStandScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;


public class ShopStandBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(24, ItemStack.EMPTY);

    public ShopStandBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityManager.SHOP_STAND_ENTITY, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("shop.inventory.name");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ShopStandScreenHandler(syncId, playerInventory, this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
    }

    public int firstNonEmpty() {
        for (int slot = 1; slot < inventory.size(); slot += 2) {
            if (inventory.get(slot) != ItemStack.EMPTY) {
                return slot;
            }
        }
        return -1;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    public int getPrice(int slot) {
        return getStack(slot - 1).getCount();
    }
}
