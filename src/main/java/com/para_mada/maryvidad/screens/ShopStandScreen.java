package com.para_mada.maryvidad.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.screens.handlers.ShopStandScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ShopStandScreen extends HandledScreen<ShopStandScreenHandler> {
    private int lastChangedSlot;
    private static final Identifier INVENTORY_TEXTURE =
            new Identifier(Maryvidad.MOD_ID, "textures/gui/shop_stand/trades.png");

    public ShopStandScreen(ShopStandScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        backgroundHeight = 238;
        y = (this.height - this.backgroundHeight) / 2;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        playerInventoryTitleY = backgroundHeight - 94;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(INVENTORY_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}