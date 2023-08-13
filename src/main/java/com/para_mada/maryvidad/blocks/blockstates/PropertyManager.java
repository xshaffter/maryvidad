package com.para_mada.maryvidad.blocks.blockstates;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public class PropertyManager {
    public static final EnumProperty<InfiniteInventory> INFINITE_INVENTORY = EnumProperty.of("infinite_inventory", InfiniteInventory.class);
    public static final BooleanProperty PERFORM_ACTION = BooleanProperty.of("perform_action");
}
