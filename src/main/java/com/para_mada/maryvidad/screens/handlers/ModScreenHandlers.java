package com.para_mada.maryvidad.screens.handlers;

import com.para_mada.maryvidad.Maryvidad;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ShopStandScreenHandler> SHOP_STAND_SCREEN_HANDLER = new ScreenHandlerType<>(ShopStandScreenHandler::new, FeatureSet.empty());

    public static void registerScreenHandlersForServer() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(Maryvidad.MOD_ID, "shop_stand_screen_handler"), SHOP_STAND_SCREEN_HANDLER);
    }
}
