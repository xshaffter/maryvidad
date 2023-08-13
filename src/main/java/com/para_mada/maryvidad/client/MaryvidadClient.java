package com.para_mada.maryvidad.client;

import com.para_mada.maryvidad.events.KeyboardHandler;
import com.para_mada.maryvidad.networking.NetworkManager;
import com.para_mada.maryvidad.screens.handlers.ModScreenHandlers;
import com.para_mada.maryvidad.screens.ShopStandScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MaryvidadClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.SHOP_STAND_SCREEN_HANDLER, ShopStandScreen::new);
        NetworkManager.registerS2CPackets();
        KeyboardHandler.register();
    }
}
