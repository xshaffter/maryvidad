package com.para_mada.maryvidad.blocks.entities;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.blocks.BlockManager;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityManager {
    public static BlockEntityType<ShopStandBlockEntity> SHOP_STAND_ENTITY;

    public static void registerBlockEntities() {
        SHOP_STAND_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Maryvidad.MOD_ID, "shop_stand_manager"),
                FabricBlockEntityTypeBuilder.create(ShopStandBlockEntity::new, BlockManager.SHOP_STAND).build(null));
    }
}