package com.para_mada.maryvidad.items;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.items.custom.BoxGauntlets;
import com.para_mada.maryvidad.items.custom.CrazySupra;
import com.para_mada.maryvidad.items.custom.EstusPotion;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ItemManager {

    public static final Item GAUNTLET_R = new BoxGauntlets(ToolMaterials.NETHERITE, 1, 1, new FabricItemSettings().maxCount(1));
    public static final Item GAUNTLET_L = new BoxGauntlets(ToolMaterials.NETHERITE, 1, 1, new FabricItemSettings().maxCount(1));
    public static final Item HELADERIA = new CrazySupra();
    public static final Item MARY_COIN = new Item(new FabricItemSettings().fireproof().rarity(Rarity.EPIC).maxCount(999));
    public static final Item ESTUS_POTION = new EstusPotion();

    private static void registerItem(String name, Item item) {
        Registry.register(Registries.ITEM, new Identifier(Maryvidad.MOD_ID, name), item);
    }

    public static void registerModItems() {
        registerItem("gauntlet_l", GAUNTLET_L);
        registerItem("gauntlet_r", GAUNTLET_R);
        registerItem("heladeria", HELADERIA);
        registerItem("mary_coin", MARY_COIN);
        registerItem("estus_potion", ESTUS_POTION);
    }
}
