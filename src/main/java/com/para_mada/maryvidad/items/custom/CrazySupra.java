package com.para_mada.maryvidad.items.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class CrazySupra extends Item {
    public CrazySupra() {
        super(new FabricItemSettings()
                .maxCount(1)
                .rarity(Rarity.EPIC)
                .fireproof()
        );
    }


}
