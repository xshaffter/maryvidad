package com.para_mada.maryvidad;

import com.para_mada.maryvidad.blocks.BlockManager;
import com.para_mada.maryvidad.blocks.entities.BlockEntityManager;
import com.para_mada.maryvidad.effects.EffectManager;
import com.para_mada.maryvidad.items.ItemManager;
import com.para_mada.maryvidad.networking.NetworkManager;
import com.para_mada.maryvidad.screens.handlers.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

public class Maryvidad implements ModInitializer {
    public static final String MOD_ID = "maryvidad";
    public static final int TPS = 20;

    @Override
    public void onInitialize() {
        ItemManager.registerModItems();
        BlockManager.registerModBlocks();
        ModScreenHandlers.registerScreenHandlersForServer();
        BlockEntityManager.registerBlockEntities();
        NetworkManager.registerC2SPackets();
        EffectManager.registerStatusEffects();
    }
}
