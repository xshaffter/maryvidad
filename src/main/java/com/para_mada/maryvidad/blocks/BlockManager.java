package com.para_mada.maryvidad.blocks;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.blocks.custom.ShopBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockManager {
    public static final Block SHOP_STAND = new ShopBlock();


    private static void registerBlock(final String name, final Block block, final BlockItem blockItem) {
        Registry.register(Registries.BLOCK, new Identifier(Maryvidad.MOD_ID, name), block);
        registerBlockItem(name, blockItem);
    }

    private static void registerBlockItem(final String name, final BlockItem blockItem) {
        Registry.register(Registries.ITEM, new Identifier(Maryvidad.MOD_ID, name), blockItem);
    }

    private static void registerBlockAuto(final String name, final Block block) {
        registerBlock(name, block, new BlockItem(block, new FabricItemSettings().fireproof().maxCount(64)));
    }

    public static void registerModBlocks() {
        registerBlockAuto("shop_stand", SHOP_STAND);
    }
}
